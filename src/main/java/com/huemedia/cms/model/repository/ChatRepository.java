package com.huemedia.cms.model.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.huemedia.cms.model.entity.Chat;

public interface ChatRepository extends
CrudRepository<Chat, Integer>{
   List<Chat> findByTicketIdOrderByCreateDateDesc(String ticketId);
}
