package com.watsappclone.start.controller.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.security.core.context.SecurityContextHolder;
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

    @Autowired
    MassageService massageService;

    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    @GetMapping("/conversation/{conversationId}")
    public List<MassageResponse> getMassage(@PathVariable("conversationId") Long conversationid, HttpSession session) {
        User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Long currentUserId = currentUser.getId();

        if (currentUserId == null) {
            throw new RuntimeException("User not logged in");
        }

        return massageService.getMessagesByConversationId(conversationid);
    }

    @PostMapping("/send")
    public MassageResponse saveMassage(@RequestBody MessageRequest request, HttpSession session) {
        User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Long currentUserId = currentUser.getId();

        if (currentUserId == null) {
            throw new RuntimeException("User not logged in");
        }

        MassageResponse savedMessage = massageService.sendMessage(request, currentUserId);

        messagingTemplate.convertAndSend(
                "/topic/conversation/" + request.getConversationid(),
                savedMessage
        );

        return savedMessage;
    }
}
