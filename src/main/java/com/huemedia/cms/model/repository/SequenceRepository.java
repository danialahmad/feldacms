package com.huemedia.cms.model.repository;

import org.springframework.data.repository.CrudRepository;

import com.huemedia.cms.model.entity.Sequence;

public interface SequenceRepository extends CrudRepository<Sequence, Integer> {
   
	Sequence findByPrefixAndYearAndMonth(String prefix,Integer year, Integer month);
}
