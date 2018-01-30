package com.almundo.callCenter.service;

import java.util.List;
import java.util.NoSuchElementException;

import com.almundo.callCenter.model.notice.Notice;

public interface NoticeService {
	
	public <T extends Notice> Notice doNotice(String clientId, Class<T> noticeType );
	
	public List<Notice> getAllAttendNotice();
	
	public Notice getAttendNotice(String callId) throws NoSuchElementException;

	public List<Notice> getAllRejectedNotice();
}
