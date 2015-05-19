package com.huemedia.cms.model.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.huemedia.cms.model.entity.TicketCategory;
import com.huemedia.cms.model.entity.TicketGroup;

public interface TicketCategoryRepository extends
		CrudRepository<TicketCategory, Integer> {

	List<TicketCategory> findByTicketGroup(TicketGroup ticketGroup);
}
