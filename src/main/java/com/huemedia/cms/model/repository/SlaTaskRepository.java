package com.huemedia.cms.model.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.huemedia.cms.model.entity.SlaTask;
import com.huemedia.cms.model.entity.Ticket;

public interface SlaTaskRepository extends CrudRepository<SlaTask, Integer> {
   List<SlaTask> findByTicket(Ticket ticket);
}
