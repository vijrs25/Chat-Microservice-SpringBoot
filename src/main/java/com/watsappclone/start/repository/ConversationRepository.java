package com.watsappclone.start.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.watsappclone.start.entity.Conversation;

public interface ConversationRepository extends JpaRepository<Conversation , Long> {

}
