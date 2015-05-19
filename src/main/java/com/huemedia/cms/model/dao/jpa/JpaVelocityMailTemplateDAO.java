package com.huemedia.cms.model.dao.jpa;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;

import com.huemedia.cms.model.dao.VelocityMailTemplateDAO;
import com.huemedia.cms.model.entity.QRefVelocityMailTemplate;
import com.huemedia.cms.model.entity.RefVelocityMailTemplate;
import com.huemedia.cms.web.form.MasterForm;
import com.mysema.query.BooleanBuilder;
import com.mysema.query.jpa.impl.JPAQuery;
import com.mysema.query.types.EntityPath;
import com.mysema.query.types.expr.BooleanExpression;

@Repository
public class JpaVelocityMailTemplateDAO extends JpaMasterDAO<RefVelocityMailTemplate> implements
VelocityMailTemplateDAO{

	@Override
	EntityPath<?> path() {
		// TODO Auto-generated method stub
		return QRefVelocityMailTemplate.refVelocityMailTemplate;
	}

	@Override
	JPAQuery prepareQuerySearch(Object form) {
		QRefVelocityMailTemplate qRefVelocityMailTemplate = (QRefVelocityMailTemplate) path();
		JPAQuery query = new JPAQuery(em);
		MasterForm masterForm=(MasterForm) form;
		query.from(qRefVelocityMailTemplate);
		final BooleanBuilder builder = new BooleanBuilder();
		 if(StringUtils.isNotEmpty(masterForm.getName())){
				final BooleanExpression containsName =qRefVelocityMailTemplate.name.like("%"+masterForm.getName()+"%");
				builder.and(containsName);
			}
		
		query.where(builder).orderBy(qRefVelocityMailTemplate.timestamp.desc());
		return query;
	}

}
