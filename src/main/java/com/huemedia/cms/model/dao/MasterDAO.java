package com.huemedia.cms.model.dao;

import java.util.List;

public interface MasterDAO<T> {
	Long countAll();
	Long countSearch(Object form);
	List<T> search(Object form,Integer iDisplayStart, Integer iDisplayLength);
}
