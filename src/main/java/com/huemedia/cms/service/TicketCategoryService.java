package com.huemedia.cms.service;

import java.util.List;

import com.huemedia.cms.web.controller.administration.datatables.TicketCategoryResult;
import com.huemedia.cms.web.form.MasterForm;

public interface TicketCategoryService {
	Long countAll();
	Long countSearch(MasterForm form);
	List<TicketCategoryResult> getTicketCategories(MasterForm form, Integer iDisplayStart, Integer iDisplayLength);
    boolean save(MasterForm form);
    MasterForm findTicketCategory(Integer id);
    void delete(Integer id);
}
