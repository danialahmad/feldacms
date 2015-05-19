package com.huemedia.cms.service;

import java.util.List;

import com.huemedia.cms.web.controller.administration.datatables.OriginatorResult;
import com.huemedia.cms.web.form.MasterForm;

public interface OriginatorService {
	Long countAll();
	Long countSearch(MasterForm form);
	List<OriginatorResult> getOriginators(MasterForm form, Integer iDisplayStart, Integer iDisplayLength);
	boolean save(MasterForm form);
	MasterForm findOriginator(Integer id);
	void delete(Integer id);
}
