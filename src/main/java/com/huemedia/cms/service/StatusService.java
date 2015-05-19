package com.huemedia.cms.service;

import java.util.List;

import com.huemedia.cms.web.controller.administration.datatables.StatusResult;
import com.huemedia.cms.web.form.MasterForm;

public interface StatusService {
	Long countAll();
	Long countSearch(MasterForm form);
	List<StatusResult> getStatuses(MasterForm form, Integer iDisplayStart, Integer iDisplayLength);
	boolean save(MasterForm form);
	MasterForm findStatus(String id);
	void delete(String id);
}
