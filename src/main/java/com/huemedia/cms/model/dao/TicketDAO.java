package com.huemedia.cms.model.dao;

import java.util.List;

import com.huemedia.cms.model.entity.QTicket;
import com.huemedia.cms.model.entity.Ticket;
import com.huemedia.cms.web.form.SearchTicketForm;
import com.mysema.query.BooleanBuilder;
import com.mysema.query.QueryModifiers;
import com.mysema.query.jpa.impl.JPAQuery;
import com.mysema.query.types.expr.BooleanExpression;

public interface TicketDAO {

	Long countPriorityByGroup(String gId,String priorityId);
	
	Long countAllByStatus(String[] status,boolean unassigned);
	Long countSearchByStatus(SearchTicketForm form,String[] status,boolean unassigned);
	List<Ticket> searchByStatus(SearchTicketForm form,String[] status,Integer iDisplayStart, Integer iDisplayLength,boolean unassigned);
	
	Long countAllByStatus(String[] status,Integer supervisorId);
	Long countSearchByStatus(SearchTicketForm form,String[] status,Integer supervisorId);
	List<Ticket> searchByStatus(SearchTicketForm form,String[] status,Integer supervisorId,Integer iDisplayStart, Integer iDisplayLength);
	
	List<Ticket> searchByGroup(SearchTicketForm form,String[] group,Integer region,String[] status, Integer iDisplayStart,
			Integer iDisplayLength);
	Long countAllByGroup(String[] group,Integer region,String[] status);
	Long countSearchByGroup(SearchTicketForm form,String[] group,Integer region,String[] status);
	
	
	List<Ticket> searchUnassigned(SearchTicketForm form,String[] group,Integer region,String[] status, Integer iDisplayStart,
			Integer iDisplayLength);
	Long countAllUnassigned(String[] group,Integer region,String[] status);
	Long countSearchUnassigned(SearchTicketForm form,String[] group,Integer region,String[] status);
	
	
	
	List<Ticket> searchByAssigneeID(SearchTicketForm form,Integer assigneeId, Integer iDisplayStart,
			Integer iDisplayLength);
	Long countAllByAssigneeID(Integer assigneeId);
	Long countSearchByAssigneeID(SearchTicketForm form,Integer assigneeId);
	
	
	List<Ticket> searchBySupervisorID(SearchTicketForm form,Integer supervisorId, Integer iDisplayStart,
			Integer iDisplayLength);
	Long countAllBySupervisorID(Integer supervisorId);
	Long countSearchBySupervisorID(SearchTicketForm form,Integer supervisorId);
	
	List<Ticket> searchByProfileID(SearchTicketForm form,Integer profileId, Integer iDisplayStart,
			Integer iDisplayLength);
	Long countAllByByProfileID(Integer profileId);
	Long countSearchByProfileID(SearchTicketForm form,Integer profileId);
	
	
	List<Ticket> searchTicketByTime(SearchTicketForm form,Integer iDisplayStart, Integer iDisplayLength);
	Long countSearchTicketByTime(SearchTicketForm form);
	Long countSearchAllTicketByTime(SearchTicketForm form);
	
	
	
	
	List<Ticket> findAll(Integer iDisplayLength,Integer iDisplayStart);
	
	List<Ticket> search(SearchTicketForm form,Integer iDisplayStart, Integer iDisplayLength);
	Long countAll();
	Long countSearch(SearchTicketForm form);
	
}
