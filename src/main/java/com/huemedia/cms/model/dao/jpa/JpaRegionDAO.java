package com.huemedia.cms.model.dao.jpa;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;

import com.huemedia.cms.model.dao.RegionDAO;
import com.huemedia.cms.model.entity.QRegion;
import com.huemedia.cms.model.entity.Region;
import com.huemedia.cms.web.form.MasterForm;
import com.mysema.query.BooleanBuilder;
import com.mysema.query.jpa.impl.JPAQuery;
import com.mysema.query.types.EntityPath;
import com.mysema.query.types.expr.BooleanExpression;

@Repository
public class JpaRegionDAO extends JpaMasterDAO<Region> implements
RegionDAO{

	@Override
	EntityPath<?> path() {
		// TODO Auto-generated method stub
		return QRegion.region;
	}

	@Override
	JPAQuery prepareQuerySearch(Object form) {
		QRegion qRegion = (QRegion) path();
		JPAQuery query = new JPAQuery(em);
		MasterForm masterForm=(MasterForm) form;
		query.from(qRegion);
		final BooleanBuilder builder = new BooleanBuilder();
		 if(StringUtils.isNotEmpty(masterForm.getName())){
				final BooleanExpression containsName =qRegion.name.like("%"+masterForm.getName()+"%");
				builder.and(containsName);
			}
		
		query.where(builder).orderBy(qRegion.createDate.desc());
		return query;
	}

}
