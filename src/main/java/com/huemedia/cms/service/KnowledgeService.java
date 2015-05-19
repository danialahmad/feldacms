package com.huemedia.cms.service;

import java.util.List;

import com.huemedia.cms.web.controller.knowledge.datatables.KnowledgeCatResult;
import com.huemedia.cms.web.controller.knowledge.datatables.KnowledgeResult;
import com.huemedia.cms.web.form.KnowledgeForm;

public interface KnowledgeService {

	Long countAll();
	Long countSearch(KnowledgeForm form);
	List<KnowledgeResult> getKnowledges(KnowledgeForm form, Integer iDisplayStart, Integer iDisplayLength);
	
	
	List<KnowledgeResult> getByKeyword(String search, Integer iDisplayStart, Integer iDisplayLength);
	Long countSearchByKeyword(String search);
	
	KnowledgeForm findByID(Integer id);
	boolean save(KnowledgeForm form);
	
	boolean approve(Integer id);
	
	List<KnowledgeCatResult> getKnowledgeCategories(Integer iDisplayStart, Integer iDisplayLength);
	Long countKnowledgeCategories();
	
	void delete(Integer id);
}
