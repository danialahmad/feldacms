package com.huemedia.cms.model.dao.jpa;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;

import com.huemedia.cms.model.dao.OriginatorDAO;
import com.huemedia.cms.model.entity.Originator;
import com.huemedia.cms.model.entity.QOriginator;
import com.huemedia.cms.model.entity.QStatus;
import com.huemedia.cms.web.form.MasterForm;
import com.mysema.query.BooleanBuilder;
import com.mysema.query.jpa.impl.JPAQuery;
import com.mysema.query.types.EntityPath;
import com.mysema.query.types.expr.BooleanExpression;
@Repository
public class JpaOriginatorDAO extends JpaMasterDAO<Originator> implements
		OriginatorDAO {

	@Override
	EntityPath<?> path() {
		// TODO Auto-generated method stub
		return QOriginator.originator;
	}

	@Override
	JPAQuery prepareQuerySearch(Object form) {
		QOriginator qOriginator = (QOriginator) path();
		JPAQuery query = new JPAQuery(em);
		query.from(qOriginator);
		MasterForm masterForm=(MasterForm) form;
		final BooleanBuilder builder = new BooleanBuilder();
		 if(StringUtils.isNotEmpty(masterForm.getName())){
				final BooleanExpression containsName =qOriginator.name.like("%"+masterForm.getName()+"%");
				builder.and(containsName);
			}
		query.where(builder).orderBy(qOriginator.createDate.desc());
		return query;
	}

	
}
