package com.huemedia.cms.model.repository;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.huemedia.cms.model.entity.Status;

public interface StatusRepository extends PagingAndSortingRepository<Status, String> {
  
	
	List<Status> findByIdInOrderByRankAsc(List<String> ids);
}
