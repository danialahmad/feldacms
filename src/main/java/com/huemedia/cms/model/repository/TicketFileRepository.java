package com.huemedia.cms.model.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.huemedia.cms.model.entity.Ticket;
import com.huemedia.cms.model.entity.TicketFile;

public interface TicketFileRepository extends
		CrudRepository<TicketFile, Integer> {

	List<TicketFile> findByTicket(Ticket ticket);
	
	@Query("select f.id,f.name from TicketFile f where f.ticket=?1")
	List<TicketFile> findIdAndNameByTicket(Ticket ticket);
}
