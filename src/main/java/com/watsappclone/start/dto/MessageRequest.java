package com.watsappclone.start.dto;

public class MessageRequest {
   private Long conversationid;
   private String messagetext;
    
public MessageRequest(Long conversationid, String messagetext) {
	super();
	this.conversationid = conversationid;
	this.messagetext = messagetext;
}
public Long getConversationid() {
	return conversationid;
}
public void setConversationid(Long conversationid) {
	this.conversationid = conversationid;
}
public String getMessagetext() {
	return messagetext;
}
public void setMessagetext(String messagetext) {
	this.messagetext = messagetext;
}
   
   
} 
