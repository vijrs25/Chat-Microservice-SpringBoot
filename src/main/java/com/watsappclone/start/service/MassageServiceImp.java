package com.watsappclone.start.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.watsappclone.start.dto.MassageResponse;
import com.watsappclone.start.dto.MessageRequest;
import com.watsappclone.start.entity.Conversation;
import com.watsappclone.start.entity.Message;
import com.watsappclone.start.entity.User;
import com.watsappclone.start.repository.ConversationRepository;
import com.watsappclone.start.repository.MassageRepository;
import com.watsappclone.start.repository.UserRepository;

@Service
public class MassageServiceImp implements MassageService {
	
	@Autowired
	MassageRepository massageRepo;
	
	  @Autowired
	    private MassageRepository messageRepository;

	    @Autowired
	    private ConversationRepository conversationRepository;

	    @Autowired
	    private UserRepository userRepository;

	    
	@Override
	public List<MassageResponse> getMessagesByConversationId(Long conversationId) {
		
		return  massageRepo.findByConversation_IdOrderBySentatAsc(conversationId)
				    .stream()
				    .map(m -> new MassageResponse(
				    		m.getId(), 
				    		m.getSenderid().getId(), 
				    		m.getSenderid().getName(), 
				    		m.getText(), 
				    		m.getSentat()))
				    .toList();
	}

	@Override
		 public MassageResponse sendMessage(MessageRequest request, Long userId) {
				
		System.out.println("Conversation id"+request.getConversationid());
		System.out.println("User id"+userId);
		        Conversation conversation = conversationRepository
		                .findById(request.getConversationid())
		                .orElseThrow(() -> new RuntimeException("Conversation not found"));

		        User sender = userRepository
		                .findById(userId)
		                .orElseThrow(() -> new RuntimeException("User not found"));

		        Message message = new Message();
		        message.setConversationid(conversation);
		        message.setSenderid(sender);
		        message.setText(request.getMessagetext());
		        message.setSentat(LocalDateTime.now());

		        Message saved = messageRepository.save(message);

		        return new MassageResponse(
		                saved.getId(),
		                sender.getId(),
		                saved.getText()
		        );
	}

}
