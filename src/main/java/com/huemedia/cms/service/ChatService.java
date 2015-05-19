package com.huemedia.cms.service;

import java.util.List;

import com.huemedia.cms.model.entity.Chat;
import com.huemedia.cms.web.form.ChatForm;


public interface ChatService {
	List<Chat> findAllMessagesByTicketID(String ticketId);
	
	void save(ChatForm chatForm);
}
