package com.huemedia.cms.model.dao.jpa;

import org.springframework.stereotype.Repository;

import com.huemedia.cms.model.dao.StateDAO;
import com.huemedia.cms.model.entity.QActivityType;
import com.huemedia.cms.model.entity.QCountry;
import com.huemedia.cms.model.entity.QState;
import com.huemedia.cms.model.entity.State;
import com.mysema.query.BooleanBuilder;
import com.mysema.query.jpa.impl.JPAQuery;
import com.mysema.query.types.EntityPath;
@Repository
public class JpaStateDAO extends JpaMasterDAO<State> implements StateDAO {

	@Override
	EntityPath<?> path() {
		// TODO Auto-generated method stub
		return QState.state;
	}

	@Override
	JPAQuery prepareQuerySearch(Object form) {
		QState qState = (QState) path();
		QCountry qCountry=QCountry.country1;
		JPAQuery query = new JPAQuery(em);
		query.from(qState).join(qState.country,qCountry).fetch();
		final BooleanBuilder builder = new BooleanBuilder();
		query.where(builder).orderBy(qCountry.country.asc()).orderBy(qState.name.asc());
		return query;
	}

	
}
