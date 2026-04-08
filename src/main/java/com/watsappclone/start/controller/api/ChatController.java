package com.watsappclone.start.controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.watsappclone.start.dto.ConversationIdResponse;
import com.watsappclone.start.service.ConversationService;

import jakarta.servlet.http.HttpSession;

@RestController
@RequestMapping("/api/chats")
public class ChatController {

    @Autowired
    private ConversationService conversationService;

    @GetMapping("/open")
    public ConversationIdResponse openChat(
            HttpSession session,
            @RequestParam("otherUserId") Long otherUserId) {

        Long currentUser = (Long) session.getAttribute("userid");

        if (currentUser == null) {
            throw new RuntimeException("User not logged in");
        }

        if (otherUserId == null) {
            throw new RuntimeException("No other user id in Conversation Response");
        }

        return conversationService.findOrCreateDirectCOnversation(currentUser, otherUserId);
    }
}