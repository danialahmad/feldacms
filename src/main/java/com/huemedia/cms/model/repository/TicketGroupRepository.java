package com.huemedia.cms.model.repository;

import org.springframework.data.repository.CrudRepository;

import com.huemedia.cms.model.entity.TicketGroup;

public interface TicketGroupRepository extends
		CrudRepository<TicketGroup, Integer> {

}
