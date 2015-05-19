package com.huemedia.cms.model.dao.jpa;

import org.springframework.stereotype.Repository;

import com.huemedia.cms.model.dao.CountryDAO;
import com.huemedia.cms.model.entity.Country;
import com.huemedia.cms.model.entity.QActivityType;
import com.huemedia.cms.model.entity.QCountry;
import com.mysema.query.BooleanBuilder;
import com.mysema.query.jpa.impl.JPAQuery;
import com.mysema.query.types.EntityPath;
@Repository
public class JpaCountryDAO extends JpaMasterDAO<Country> implements CountryDAO {

	@Override
	EntityPath<?> path() {
		// TODO Auto-generated method stub
		return QCountry.country1;
	}

	@Override
	JPAQuery prepareQuerySearch(Object form) {
		QCountry qCountry = (QCountry) path();
		JPAQuery query = new JPAQuery(em);
		query.from(qCountry);
		final BooleanBuilder builder = new BooleanBuilder();
		query.where(builder).orderBy(qCountry.continent.asc());
		return query;
	}

	

}
