package com.almundo.callCenter.dispatcher;

import java.util.NoSuchElementException;

import com.almundo.callCenter.model.notice.Notice;

public interface IDispatcher {
	
	public void dispatchNotice(Notice notice) throws NoSuchElementException;

}
