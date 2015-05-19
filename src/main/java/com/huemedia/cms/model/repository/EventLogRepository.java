package com.huemedia.cms.model.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.huemedia.cms.model.entity.EventLog;

public interface EventLogRepository extends CrudRepository<EventLog, Integer> {
   
	List<EventLog> findByTicketId(String ticketId);
	
	List<EventLog> findByAssignmentId(Integer assignmentId);
}
