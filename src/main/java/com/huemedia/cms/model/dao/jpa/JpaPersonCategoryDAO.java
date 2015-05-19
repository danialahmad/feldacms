package com.huemedia.cms.model.dao.jpa;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;

import com.huemedia.cms.model.dao.PersonCategoryDAO;
import com.huemedia.cms.model.entity.PersonCategory;
import com.huemedia.cms.model.entity.QPersonCategory;
import com.huemedia.cms.web.form.MasterForm;
import com.mysema.query.BooleanBuilder;
import com.mysema.query.jpa.impl.JPAQuery;
import com.mysema.query.types.EntityPath;
import com.mysema.query.types.expr.BooleanExpression;

@Repository
public class JpaPersonCategoryDAO extends JpaMasterDAO<PersonCategory> implements
PersonCategoryDAO{

	@Override
	EntityPath<?> path() {
		// TODO Auto-generated method stub
		return QPersonCategory.personCategory;
	}

	@Override
	JPAQuery prepareQuerySearch(Object form) {
		QPersonCategory qPersonCategory = (QPersonCategory) path();
		JPAQuery query = new JPAQuery(em);
		MasterForm masterForm=(MasterForm) form;
		query.from(qPersonCategory);
		final BooleanBuilder builder = new BooleanBuilder();
		 if(StringUtils.isNotEmpty(masterForm.getName())){
				final BooleanExpression containsName =qPersonCategory.name.like("%"+masterForm.getName()+"%");
				builder.and(containsName);
			}
		
		query.where(builder).orderBy(qPersonCategory.updateDate.desc());
		return query;
	}

}
