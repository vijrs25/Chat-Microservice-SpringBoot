package com.watsappclone.start.dto;

import java.time.LocalDateTime;

public class MassageResponse {
	private Long id;
	private Long SenderId;
	private String SenderName;
	private String massageText;
	private LocalDateTime sentAt;

	public MassageResponse(Long id, Long senderId, String senderName, String massageText, LocalDateTime sentAt) {
		super();
		this.id = id;
		SenderId = senderId;
		SenderName = senderName;
		this.massageText = massageText;
		this.sentAt = sentAt;
	}

	public MassageResponse(Long id, Long senderId, String massageText) {
		super();
		this.id = id;
		SenderId = senderId;
		this.massageText = massageText;
	}


	public MassageResponse() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getSenderId() {
		return SenderId;
	}

	public void setSenderId(Long senderId) {
		SenderId = senderId;
	}

	public String getSenderName() {
		return SenderName;
	}

	public void setSenderName(String senderName) {
		SenderName = senderName;
	}

	public String getMassageText() {
		return massageText;
	}

	public void setMassageText(String massageText) {
		this.massageText = massageText;
	}

	public LocalDateTime getSentAt() {
		return sentAt;
	}

	public void setSentAt(LocalDateTime sentAt) {
		this.sentAt = sentAt;
	}
	
	

}
