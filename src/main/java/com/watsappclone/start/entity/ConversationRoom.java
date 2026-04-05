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
@Table(name = "conversation_room")
public class ConversationRoom {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@ManyToOne
	@JoinColumn(name = "conversationid")
	private Conversation conversationid;

	@ManyToOne
	@JoinColumn(name = "userid")
	private User userid;

	private LocalDateTime joinedate;

	public ConversationRoom(Conversation conversationid, User userid, LocalDateTime joinedate) {
		super();
		this.conversationid = conversationid;
		this.userid = userid;
		this.joinedate = joinedate;
	}

	public Conversation getConversationid() {
		return conversationid;
	}

	public void setConversationid(Conversation conversationid) {
		this.conversationid = conversationid;
	}

	public User getUserid() {
		return userid;
	}

	public void setUserid(User userid) {
		this.userid = userid;
	}

	public LocalDateTime getJoinedate() {
		return joinedate;
	}

	public void setJoinedate(LocalDateTime joinedate) {
		this.joinedate = joinedate;
	}

}
