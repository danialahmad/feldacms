package com.huemedia.cms.model.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.huemedia.cms.model.entity.Plan;
import com.huemedia.cms.model.entity.Region;

public interface PlanRepository extends CrudRepository<Plan, Integer> {
    List<Plan> findByRegion(Region region);
}
