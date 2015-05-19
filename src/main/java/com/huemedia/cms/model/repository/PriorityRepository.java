package com.huemedia.cms.model.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.huemedia.cms.model.entity.Priority;

public interface PriorityRepository extends PagingAndSortingRepository<Priority, String> {
  
}
