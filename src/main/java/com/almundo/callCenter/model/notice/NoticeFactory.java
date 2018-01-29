package com.almundo.callCenter.model.notice;

import java.lang.reflect.InvocationTargetException;
import java.util.UUID;

import org.springframework.stereotype.Component;

@Component
public class NoticeFactory {

	public static <T extends Notice> Notice createNotice(String clientId, Class<T> noticeType ) throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
		
		String noticeUUID = UUID.randomUUID().toString();
		
		Notice notice = noticeType.getConstructor(String.class, String.class).newInstance(noticeUUID, clientId);
		return notice;
	}
}
