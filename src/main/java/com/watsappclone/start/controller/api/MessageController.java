package com.watsappclone.start.controller.api;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.watsappclone.start.dto.MassageResponse;
import com.watsappclone.start.dto.MessageRequest;
import com.watsappclone.start.entity.User;
import com.watsappclone.start.service.MassageService;

import jakarta.servlet.http.HttpSession;

@RestController
@RequestMapping("api/messages")
public class MessageController {
	private static final Logger logger = LoggerFactory.getLogger(MessageController.class);

	@Autowired
	MassageService massageService;

	@Autowired
	private SimpMessagingTemplate messagingTemplate;
	
	@GetMapping("/conversation/{conversationId}")
   public List<MassageResponse> getMassage(@PathVariable("conversationId") Long conversationid, HttpSession session){
		logger.info("Fetching messages for conversationId={}", conversationid);
		User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Long currentUserId = currentUser.getId();
	        if (currentUserId == null) {
	        	logger.warn("Unauthorized attempt to fetch messages for conversationId={}", conversationid);
	            throw new RuntimeException("User not logged in");
	        }
	        logger.debug("User {} requested messages for conversationId={}", currentUserId, conversationid);
	        return massageService.getMessagesByConversationId(conversationid);
	}
	
	
	@PostMapping("/send")
	public MassageResponse saveMassage(@RequestBody MessageRequest request, HttpSession session){
		User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Long currentUserId = currentUser.getId();
	        if (currentUserId == null) {
	        	logger.warn("Unauthorized attempt to send message to conversationId={}", request.getConversationid());
	            throw new RuntimeException("User not logged in");
	        }
	        logger.info("User {} is sending a message to conversationId={}", currentUserId, request.getConversationid());
	        MassageResponse savedMessage = massageService.sendMessage(request, currentUserId);
	        messagingTemplate.convertAndSend("/topic/conversation/" + request.getConversationid(), savedMessage);
	        return savedMessage;
	}
}
