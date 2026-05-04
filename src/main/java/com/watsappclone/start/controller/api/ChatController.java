package com.watsappclone.start.controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.watsappclone.start.dto.ConversationIdResponse;
import com.watsappclone.start.entity.User;
import com.watsappclone.start.service.ConversationService;

@RestController
@RequestMapping("/api/chats")
public class ChatController {

    @Autowired
    private ConversationService conversationService;

    @GetMapping("/open")
    public ConversationIdResponse openChat(@RequestParam("otherUserId") Long otherUserId) {

        User currentUser = (User) SecurityContextHolder.getContext()
                .getAuthentication()
                .getPrincipal();

        Long currentUserId = currentUser.getId();

        if (otherUserId == null) {
            throw new RuntimeException("No other user id in Conversation Response");
        }

        return conversationService.findOrCreateDirectCOnversation(currentUserId, otherUserId);
    }
}