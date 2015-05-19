package com.huemedia.cms.model.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.huemedia.cms.model.entity.Country;
import com.huemedia.cms.model.entity.State;

public interface StateRepository extends CrudRepository<State, Integer> {
   List<State> findByCountry(Country country);
}
