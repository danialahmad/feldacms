package com.huemedia.cms.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.huemedia.cms.model.entity.Chat;
import com.huemedia.cms.model.entity.Ticket;
import com.huemedia.cms.model.repository.ChatRepository;
import com.huemedia.cms.model.repository.TicketRepository;
import com.huemedia.cms.security.service.UserDetail;
import com.huemedia.cms.web.form.ChatForm;

@Service
public class ChatServiceImpl implements ChatService {
	
	@Autowired
	ChatRepository chatRepository;
	@Autowired
	EmailService emailService;
	@Autowired
	UserService userService;
	@Autowired
	TicketRepository ticketRepository;
	@Override
	public List<Chat> findAllMessagesByTicketID(String ticketId) {
		// TODO Auto-generated method stub
		return chatRepository.findByTicketIdOrderByCreateDateDesc(ticketId);
	}
	@Override
	public void save(ChatForm chatForm) {
		Chat chat = new Chat();
		chat.setTicketId(chatForm.getTicketId());
		chat.setMessage(chatForm.getMessage());
		Ticket t=ticketRepository.findOne(chatForm.getTicketId());
		UserDetail u= (UserDetail) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		
		emailService.sendChatMessage(t, u.getProfile(), chatForm.getMessage());
		
		chatRepository.save(chat);
	}
	

}
