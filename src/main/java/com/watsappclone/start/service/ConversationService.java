package com.watsappclone.start.service;

import com.watsappclone.start.dto.ConversationIdResponse;


public interface ConversationService {
	ConversationIdResponse findOrCreateDirectCOnversation(Long userid, Long otheruserId);
}
