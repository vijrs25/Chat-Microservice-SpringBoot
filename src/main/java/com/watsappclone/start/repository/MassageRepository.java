package com.watsappclone.start.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.watsappclone.start.entity.Conversation;
import com.watsappclone.start.entity.Message;

public interface MassageRepository extends JpaRepository<Message, Long>{
	List<Message> findByConversation_IdOrderBySentatAsc(Long conversation);

}
