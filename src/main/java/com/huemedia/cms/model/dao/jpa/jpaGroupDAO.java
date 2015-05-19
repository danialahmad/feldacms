package com.huemedia.cms.model.dao.jpa;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;

import com.huemedia.cms.model.dao.GroupDAO;
import com.huemedia.cms.model.entity.Group;
import com.huemedia.cms.model.entity.QGroup;
import com.huemedia.cms.web.form.MasterForm;
import com.mysema.query.BooleanBuilder;
import com.mysema.query.jpa.impl.JPAQuery;
import com.mysema.query.types.EntityPath;
import com.mysema.query.types.expr.BooleanExpression;

@Repository
public class jpaGroupDAO extends JpaMasterDAO<Group> implements GroupDAO{

	@Override
	EntityPath<?> path() {
		// TODO Auto-generated method stub
		return QGroup.group;
	}

	@Override
	JPAQuery prepareQuerySearch(Object form) {
		QGroup qGroup = (QGroup) path();
		JPAQuery query = new JPAQuery(em);
		query.from(qGroup);
		MasterForm masterForm=(MasterForm) form;
		final BooleanBuilder builder = new BooleanBuilder();
		 if(StringUtils.isNotEmpty(masterForm.getName())){
				final BooleanExpression containsName =qGroup.name.like("%"+masterForm.getName()+"%");
				builder.and(containsName);
			}
		query.where(builder).orderBy(qGroup.createDate.desc());
		return query;
	}



}
