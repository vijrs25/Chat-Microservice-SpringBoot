package com.watsappclone.start.controller.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.watsappclone.start.dto.MassageResponse;
import com.watsappclone.start.dto.MessageRequest;
import com.watsappclone.start.service.MassageService;

import jakarta.servlet.http.HttpSession;

@RestController
@RequestMapping("api/messages")
public class MessageController {

	@Autowired
	MassageService massageService;
	
	@GetMapping("/conversation/{conversationId}")
   public List<MassageResponse> getMassage(@PathVariable("conversationId") Long conversationid, HttpSession session){
		 Long currentUserId = (Long) session.getAttribute("userid");
		 System.out.println(conversationid);
	        if (currentUserId == null) {
	            throw new RuntimeException("User not logged in");
	        }
	        
	        return massageService.getMessagesByConversationId(conversationid);
	}
	
	
	@PostMapping("/send")
	public MassageResponse saveMassage(@RequestBody MessageRequest request, HttpSession session){
		Long currentUserId = (Long) session.getAttribute("userid");
		 System.out.println("Cuurent UserId"+currentUserId);
		 System.out.println("request"+ request.getMessagetext()+" "+request.getConversationid());
	        if (currentUserId == null) {
	            throw new RuntimeException("User not logged in");
	        }
	        
	        return massageService.sendMessage(request, currentUserId);
	}
}