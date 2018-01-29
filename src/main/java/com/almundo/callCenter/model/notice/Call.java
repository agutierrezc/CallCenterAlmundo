package com.almundo.callCenter.model.notice;

public class Call extends Notice{
	
	private String recordId;

	public Call(String UUID, String clientId) {
		super(UUID, clientId);
	}

	public String getRecordId() {
		return recordId;
	}

	public void setRecordId(String recordId) {
		this.recordId = recordId;
	}

	@Override
	public String getAdditionalInformation() {
		return recordId;
	}
}
