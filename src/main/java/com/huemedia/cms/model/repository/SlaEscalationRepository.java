package com.huemedia.cms.model.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.huemedia.cms.model.entity.Sla;
import com.huemedia.cms.model.entity.SlaEscalation;

public interface SlaEscalationRepository extends
		CrudRepository<SlaEscalation, Integer> {

	List<SlaEscalation> findBySla(Sla sla);
}
