package com.watsappclone.start.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.watsappclone.start.entity.Conversation;
import com.watsappclone.start.entity.ConversationRoom;

public interface ConversationRoomRepository extends JpaRepository<ConversationRoom, Long> {
	   List<ConversationRoom> findByConversationid(Conversation conversation);
	  Conversation save(Conversation newConversation);
	}

