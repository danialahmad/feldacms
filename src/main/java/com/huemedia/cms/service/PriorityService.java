package com.huemedia.cms.service;

import java.util.List;

import com.huemedia.cms.web.controller.administration.datatables.PriorityResult;
import com.huemedia.cms.web.form.MasterForm;

public interface PriorityService {
	Long countAll();
	Long countSearch(MasterForm form);
	List<PriorityResult> getTicketGroups(MasterForm form, Integer iDisplayStart, Integer iDisplayLength);
	boolean save(MasterForm form);
	MasterForm findPriority(String id);
	void delete(String id);
}
