package com.huemedia.cms.model.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.huemedia.cms.model.entity.Country;

public interface CountryRepository extends
CrudRepository<Country, Integer>{
	
	@Query("select c from Country c order by c.country asc")
	List<Country> findAllWithOrderBy();

}
