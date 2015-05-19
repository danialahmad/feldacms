package com.huemedia.cms.model.dao.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.huemedia.cms.model.dao.MasterDAO;
import com.huemedia.cms.model.entity.QUser;
import com.huemedia.cms.web.form.UserForm;
import com.mysema.query.QueryModifiers;
import com.mysema.query.jpa.impl.JPAQuery;
import com.mysema.query.types.EntityPath;

public abstract class JpaMasterDAO<T> implements MasterDAO<T> {

	
	
	@PersistenceContext
	protected EntityManager em;
	
	abstract EntityPath<?> path();
	abstract JPAQuery prepareQuerySearch(Object form);
   
	@Override
	public Long countAll() {
		JPAQuery query = new JPAQuery(em);
		query.from(path());
		return query.count();
	}

	@Override
	public Long countSearch(Object form) {
		final JPAQuery query = prepareQuerySearch(form);
		return query.count();
	}

	@Override
	public List<T> search(Object form, Integer iDisplayStart,
			Integer iDisplayLength) {
		final JPAQuery query = prepareQuerySearch(form);
		query.restrict(new QueryModifiers(iDisplayLength.longValue(), iDisplayStart.longValue()));
		return (List<T>) query.list(path());
	}

}
