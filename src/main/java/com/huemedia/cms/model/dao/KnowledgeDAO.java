package com.huemedia.cms.model.dao;

import java.util.List;

import com.huemedia.cms.model.entity.Knowledge;

public interface KnowledgeDAO extends MasterDAO<Knowledge>{
	List<Knowledge> searchByKeyword(String search,Integer iDisplayStart,
			Integer iDisplayLength);
	Long countSearchByKeyword(String search);
}
