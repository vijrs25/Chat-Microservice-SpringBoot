package com.watsappclone.start.service;

import java.util.List;

import com.watsappclone.start.dto.MassageResponse;
import com.watsappclone.start.dto.MessageRequest;

public interface MassageService {
	List<MassageResponse> getMessagesByConversationId(Long conversationId);

	MassageResponse sendMessage(MessageRequest request, Long currentUserId);


}
