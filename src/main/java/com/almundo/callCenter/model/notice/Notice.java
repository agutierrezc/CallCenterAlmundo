package com.almundo.callCenter.model.notice;

/**
 * Clase abstracta que reprecenta una notificacion, ejemplo: una Llamada o un Chat
 * 
 * @author felipe.gutierrez
 *
 */
public abstract class Notice {
	
	protected String UUID;

	protected String clientId;
	
	protected Long duration;
	
	protected String stratTime;
	
	protected String endTime;
	
	protected String attendedBy;
	
	public Notice (String UUID, String clientId) {
		this.UUID = UUID;
		this.clientId = clientId;
	}
	
	public String getUUID() {
		return UUID;
	}

	public String getClientId() {
		return clientId;
	}

	public String getStratTime() {
		return stratTime;
	}

	public void setStratTime(String stratTime) {
		this.stratTime = stratTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	
	public Long getDuration() {
		return duration;
	}
	
	public void setDuration(Long duration) {
		this.duration = duration;
	}

	public String getAttendedBy() {
		return attendedBy;
	}

	public void setAttendedBy(String attendedBy) {
		this.attendedBy = attendedBy;
	}
	
	public abstract String getAdditionalInformation();

	@Override
	public String toString() {
		return "Notice [UUID=" + UUID + ", clientId=" + clientId + ", duration=" + duration + ", stratTime=" + stratTime
				+ ", endTime=" + endTime + ", attendedBy=" + attendedBy + "]";
	}
}
