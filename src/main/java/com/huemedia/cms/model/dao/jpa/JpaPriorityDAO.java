package com.huemedia.cms.model.dao.jpa;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;

import com.huemedia.cms.model.dao.PriorityDAO;
import com.huemedia.cms.model.entity.Priority;
import com.huemedia.cms.model.entity.QPriority;
import com.huemedia.cms.web.form.MasterForm;
import com.mysema.query.BooleanBuilder;
import com.mysema.query.jpa.impl.JPAQuery;
import com.mysema.query.types.EntityPath;
import com.mysema.query.types.expr.BooleanExpression;
@Repository
public class JpaPriorityDAO extends JpaMasterDAO<Priority> implements
		PriorityDAO {

	@Override
	EntityPath<?> path() {
		// TODO Auto-generated method stub
		return QPriority.priority;
	}

	@Override
	JPAQuery prepareQuerySearch(Object form) {
		QPriority qPriority = (QPriority) path();
		JPAQuery query = new JPAQuery(em);
		MasterForm masterForm=(MasterForm) form;
		query.from(qPriority);
		final BooleanBuilder builder = new BooleanBuilder();
		 if(StringUtils.isNotEmpty(masterForm.getName())){
				final BooleanExpression containsName =qPriority.name.like("%"+masterForm.getName()+"%");
				builder.and(containsName);
			}
		query.where(builder).orderBy(qPriority.rank.asc());
		return query;
	}

}
