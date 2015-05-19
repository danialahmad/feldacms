package com.huemedia.cms.service;

import java.util.List;

import com.huemedia.cms.model.entity.Ticket;
import com.huemedia.cms.model.entity.TicketAssignment;
import com.huemedia.cms.web.controller.complaint.datatables.ActivityResult;
import com.huemedia.cms.web.controller.complaint.datatables.TicketResult;
import com.huemedia.cms.web.form.ActivityForm;
import com.huemedia.cms.web.form.SearchTicketForm;

public interface TicketService {
	Long countAll(String[] status,boolean unassigned);
	Long countSearch(SearchTicketForm form,String[] status,boolean unassigned);
	List<TicketResult> getTickets(SearchTicketForm form,String[] status, Integer iDisplayStart, Integer iDisplayLength,boolean unassigned);
	
	Long countAll(String[] status,Integer supervisorId);
	Long countSearch(SearchTicketForm form,String[] status,Integer supervisorId);
	List<TicketResult> getTickets(SearchTicketForm form,String[] status,Integer supervisorId,Integer iDisplayStart, Integer iDisplayLength);
	
	Long countAllByGroup(String[] group,Integer region,String[] status);
	Long countSearchByGroup(SearchTicketForm form,String[] group,Integer region,String[] status);
	List<TicketResult> getTicketsByGroup(SearchTicketForm form,String[] group,Integer region,String[] status, Integer iDisplayStart, Integer iDisplayLength);
	
	Long countAllUnassigned(String[] group,Integer region,String[] status);
	Long countSearchUnassigned(SearchTicketForm form,String[] group,Integer region,String[] status);
	List<TicketResult> getTicketsUnassigned(SearchTicketForm form,String[] group,Integer region,String[] status, Integer iDisplayStart, Integer iDisplayLength);
	
	Long countAllByAssignee(Integer assigneeId);
	Long countSearchByAssignee(SearchTicketForm form,Integer assigneeId);
	List<TicketResult> getTicketsByAssignee(SearchTicketForm form,Integer assigneeId, Integer iDisplayStart, Integer iDisplayLength);
	
	Long countAllByAssignee(String[] status,Integer assigneeId);
	Long countSearchByAssignee(SearchTicketForm form,String[] status,Integer assigneeId);
	List<TicketResult> getTicketsByAssignee(SearchTicketForm form,String[] status,Integer assigneeId, Integer iDisplayStart, Integer iDisplayLength);
	
	
	Long countAllBySupervisor(Integer supervisorId);
	Long countSearchBySupervisor(SearchTicketForm form,Integer supervisorId);
	List<TicketAssignment> getTicketsBySupervisor(Integer supervisorId,Integer iDisplayStart, Integer iDisplayLength);
	
	
	
	Long countAllByProfileId(Integer profileId);
	Long countSearchByProfileId(SearchTicketForm form,Integer profileId);
	List<TicketResult> getTicketsByProfileId(SearchTicketForm form,Integer profileId, Integer iDisplayStart, Integer iDisplayLength);

	Long countAllActivities(Integer assignmentId);
	Long countSearchActivities(ActivityForm form,Integer assignmentId);
	List<ActivityResult> getTicketActivities(ActivityForm form,Integer assignmentId, Integer iDisplayStart, Integer iDisplayLength);
	
	Long countAllActivities(String ticketId);
	Long countSearchActivities(ActivityForm form,String ticketId);
	List<ActivityResult> getTicketActivities(ActivityForm form,String ticketId, Integer iDisplayStart, Integer iDisplayLength);
	
	List<Ticket> getAllTickets(Integer iDisplayStart, Integer iDisplayLength);
	List<TicketAssignment> getTicketsByAssignee(Integer assigneeId,Integer iDisplayStart, Integer iDisplayLength);
	List<Ticket> getTicketsByGroup(String[] group,Integer region,String[] status, Integer iDisplayStart, Integer iDisplayLength);
	
	Long countAll();
	Long countSearch(SearchTicketForm form);
	List<TicketResult> searchAll(SearchTicketForm form,Integer iDisplayStart, Integer iDisplayLength);
	
	Long countAllAssignments(String ticketId);
	Long countSearchAssignments(SearchTicketForm form,String ticketId);
	List<TicketResult> getTicketAssignments(SearchTicketForm form,String ticketId, Integer iDisplayStart, Integer iDisplayLength);
}
