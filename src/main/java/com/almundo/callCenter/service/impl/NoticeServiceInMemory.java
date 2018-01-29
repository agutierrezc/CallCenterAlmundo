package com.almundo.callCenter.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.almundo.callCenter.dispatcher.IDispatcher;
import com.almundo.callCenter.model.notice.Notice;
import com.almundo.callCenter.model.notice.NoticeFactory;
import com.almundo.callCenter.service.NoticeService;

@Service
public class NoticeServiceInMemory implements NoticeService {
	
	private static final Logger logger = LoggerFactory.getLogger(NoticeServiceInMemory.class);
	
	@Autowired
	private IDispatcher iDispatcher;
		
	private List<Notice> attendNotices;

	private List<Notice> rejectedNotices;

	public NoticeServiceInMemory() {
		attendNotices = new ArrayList<>();
		rejectedNotices = new ArrayList<>();
	}

	@Override
	public <T extends Notice> Notice doNotice(String clientId, Class<T> noticeClass) {
		
		Notice notice = null;
		
		try {
			notice = NoticeFactory.createNotice(clientId, noticeClass);
		} catch (Exception e) {
			logger.error("Error creando una comunicacion", e);
			throw new RuntimeException("Error creando una comunicacion", e);
		}
		
		try {
			iDispatcher.dispatchNotice(notice);
			attendNotices.add(notice);
		} catch (NoSuchElementException e) {
			rejectedNotices.add(notice);
			throw new NoSuchElementException(e.getMessage());
		} catch (Exception e) {
			logger.error("Error atendiendo la comunicacion", e);
			throw new RuntimeException("Error atendiendo la comunicacion", e);
		}
		
		return notice;
	}

	@Override
	public List<Notice> getAllAttendNotice() {
		return attendNotices;
	}

	@Override
	public List<Notice> getAllRejectedNotice() {
		return rejectedNotices;
	}


}
