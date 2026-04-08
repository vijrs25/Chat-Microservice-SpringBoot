package com.watsappclone.start.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.watsappclone.start.dto.ConversationIdResponse;
import com.watsappclone.start.entity.Conversation;
import com.watsappclone.start.entity.ConversationRoom;
import com.watsappclone.start.entity.User;
import com.watsappclone.start.repository.ConversationRepository;
import com.watsappclone.start.repository.ConversationRoomRepository;
import com.watsappclone.start.repository.UserRepository;

@Service
public class ConversarionServiceImp implements ConversationService {

	@Autowired
	ConversationRepository conversationRepo;

	@Autowired
	ConversationRoomRepository conversationRoomRepo;

	@Autowired
	UserRepository userRepo;

	@Override
	public ConversationIdResponse findOrCreateDirectCOnversation(Long userid, Long otheruserId) {
		User currentUser = userRepo.findById(userid).orElseThrow();
		User otherUser = userRepo.findById(otheruserId).orElseThrow();

		List<Conversation> conversationList = conversationRepo.findAll();

		for (Conversation conversation : conversationList) {
			List<ConversationRoom> converRoomParticipants = conversationRoomRepo
					.findByConversationid(conversation);

			boolean hasCurrentUser = converRoomParticipants.stream()
					.anyMatch(p -> p.getUserid().getId().equals(userid));
			boolean hasOtherUser = converRoomParticipants.stream()
					.anyMatch(p -> p.getUserid().getId().equals(otheruserId));
					
			if (hasCurrentUser && hasOtherUser && converRoomParticipants.size() == 2) {
				return new ConversationIdResponse(conversation.getId(), conversation.getType());
			}
		}

		Conversation newConversation = new Conversation();
		newConversation.setType("DIRECT");
		newConversation.setCreatedAt(LocalDateTime.now());
		Conversation savedConversation = conversationRoomRepo.save(newConversation);

		ConversationRoom r1 = new ConversationRoom();
		r1.setConversationid(savedConversation);
		r1.setUserid(currentUser);
		r1.setJoinedate(LocalDateTime.now());

		ConversationRoom r2 = new ConversationRoom();
		r2.setConversationid(savedConversation);
		r2.setUserid(otherUser);
		r2.setJoinedate(LocalDateTime.now());
		
		conversationRoomRepo.save(r1);
		conversationRoomRepo.save(r2);
		return new ConversationIdResponse(savedConversation.getId(), newConversation.getType()) ;
	}

}
