package com.huemedia.cms.service;

import java.util.List;

import com.huemedia.cms.web.controller.administration.datatables.PersonCategoryResult;
import com.huemedia.cms.web.form.MasterForm;

public interface PersonCategoryService {
	Long countAll();
	Long countSearch(MasterForm form);
	List<PersonCategoryResult> getPersonCategories(MasterForm form, Integer iDisplayStart, Integer iDisplayLength);
	boolean save(MasterForm form);
	MasterForm findPersonCategory(Integer id);
	void delete(Integer id);
}
