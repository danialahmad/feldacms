package com.huemedia.cms.model.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.huemedia.cms.model.entity.Ticket;
import com.huemedia.cms.model.entity.TicketAssignment;

public interface TicketAssignmentRepository extends
		CrudRepository<TicketAssignment, Integer> {
  List<TicketAssignment> findByTicket(Ticket ticket);
}
