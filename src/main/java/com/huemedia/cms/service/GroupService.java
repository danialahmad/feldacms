package com.huemedia.cms.service;

import java.util.List;

import com.huemedia.cms.web.controller.administration.datatables.GroupResult;
import com.huemedia.cms.web.form.MasterForm;

public interface GroupService {
	Long countAll();
	Long countSearch(MasterForm form);
	List<GroupResult> getGroups(MasterForm form, Integer iDisplayStart, Integer iDisplayLength);
	boolean save(MasterForm form);
	MasterForm findGroup(String id);
	void delete(String id);
	
}
