package com.huemedia.cms.model.repository;

import org.springframework.data.repository.CrudRepository;

import com.huemedia.cms.model.entity.PersonCategory;

public interface PersonCategoryRepository extends
		CrudRepository<PersonCategory, Integer> {

}
