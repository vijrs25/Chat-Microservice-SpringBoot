package com.watsappclone.start.dto;

public class ConversationIdResponse {
	
	private Long id;
	private String type;
	
	public ConversationIdResponse(Long id2, String type2) {
		this.id = id2;
		this.type =type2;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}

}
