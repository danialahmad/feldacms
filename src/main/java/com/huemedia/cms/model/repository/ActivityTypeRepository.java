package com.huemedia.cms.model.repository;

import org.springframework.data.repository.CrudRepository;

import com.huemedia.cms.model.entity.ActivityType;

public interface ActivityTypeRepository extends
		CrudRepository<ActivityType, Integer> {

}
