package com.huemedia.cms.model.repository;

import org.springframework.data.repository.CrudRepository;

import com.huemedia.cms.model.entity.Escalation;

public interface EscalationRepository extends
		CrudRepository<Escalation, Integer> {

}
