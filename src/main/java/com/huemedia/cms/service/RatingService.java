package com.huemedia.cms.service;

import java.util.List;

import com.huemedia.cms.web.controller.administration.datatables.RatingResult;
import com.huemedia.cms.web.form.MasterForm;

public interface RatingService {
	Long countAll();
	Long countSearch(MasterForm form);
	List<RatingResult> getRatings(MasterForm form, Integer iDisplayStart, Integer iDisplayLength);
	boolean save(MasterForm form);
	MasterForm findRating(Integer id);
	void delete(Integer id);
}
