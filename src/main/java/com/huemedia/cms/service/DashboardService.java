package com.huemedia.cms.service;

import java.util.List;
import java.util.Map;

import com.huemedia.cms.web.controller.complaint.datatables.TicketResult;
import com.huemedia.cms.web.form.SearchTicketForm;

public interface DashboardService {
    
	Map caseCountInfoByProfile();
	Map caseCountInfoBySupervisor();
	Map caseCountInfo();
	Map caseYearStatistics();
	Map caseInfoByStatus();
	Map caseInfoByPriorityByProfile();
	Map caseInfoByPriorityBySupervisor();
	Map caseInfoByPriority();
	
	Long countAllTicketByTime(SearchTicketForm form);
	Long countSearchTicketByTime(SearchTicketForm form);
	List<TicketResult> getTicketByTime(SearchTicketForm form,Integer iDisplayStart, Integer iDisplayLength);
	
	Long  countPriorityByGroup(String gId,String priorityId);
}
