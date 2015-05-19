package com.huemedia.cms.service;

import java.util.List;

import com.huemedia.cms.web.controller.administration.datatables.PlanResult;
import com.huemedia.cms.web.form.MasterForm;

public interface PlanService {
	Long countAll();
	Long countSearch(MasterForm form);
	List<PlanResult> getPlans(MasterForm form, Integer iDisplayStart, Integer iDisplayLength);
	boolean save(MasterForm form);
	MasterForm findPlan(Integer id);
	void delete(Integer id);
}
