package com.huemedia.cms.model.dao.jpa;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;

import com.huemedia.cms.model.dao.ActivityTypeDAO;
import com.huemedia.cms.model.entity.ActivityType;
import com.huemedia.cms.model.entity.QActivityType;
import com.huemedia.cms.web.form.MasterForm;
import com.mysema.query.BooleanBuilder;
import com.mysema.query.jpa.impl.JPAQuery;
import com.mysema.query.types.EntityPath;
import com.mysema.query.types.expr.BooleanExpression;
@Repository
public class JpaActivityTypeDAO extends JpaMasterDAO<ActivityType> implements
		ActivityTypeDAO {

	@Override
	EntityPath<?> path() {
		// TODO Auto-generated method stub
		return QActivityType.activityType;
	}

	@Override
	JPAQuery prepareQuerySearch(Object form) {
		QActivityType qActivityType = (QActivityType) path();
		JPAQuery query = new JPAQuery(em);
		query.from(qActivityType);
		MasterForm masterForm=(MasterForm) form;
		final BooleanBuilder builder = new BooleanBuilder();
		 if(StringUtils.isNotEmpty(masterForm.getName())){
				final BooleanExpression containsName =qActivityType.name.like("%"+masterForm.getName()+"%");
				builder.and(containsName);
			}
		query.where(builder).orderBy(qActivityType.createDate.asc());
		return query;
	}

	
}
