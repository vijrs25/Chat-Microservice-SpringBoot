package com.watsappclone.start.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "message")
public class Message {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@ManyToOne
	@JoinColumn(name = "conversationid")
	private Conversation conversationid;

	@ManyToOne
	@JoinColumn(name = "senderid")
	private User senderid;

	@JoinColumn(name = "messagetext")
	private String text;
	private String type;
	private LocalDateTime sentat;

	public Message(Conversation conversationid, User senderid, String text, String type, LocalDateTime sentat) {
		super();
		this.conversationid = conversationid;
		this.senderid = senderid;
		this.text = text;
		this.type = type;
		this.sentat = sentat;
	}

	public Conversation getConversationid() {
		return conversationid;
	}

	public void setConversationid(Conversation conversationid) {
		this.conversationid = conversationid;
	}

	public User getSenderid() {
		return senderid;
	}

	public void setSenderid(User senderid) {
		this.senderid = senderid;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public LocalDateTime getSentat() {
		return sentat;
	}

	public void setSentat(LocalDateTime sentat) {
		this.sentat = sentat;
	}

}
