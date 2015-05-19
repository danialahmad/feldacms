package com.huemedia.cms.model.dao.jpa;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;

import com.huemedia.cms.model.dao.PlanDAO;
import com.huemedia.cms.model.entity.Plan;
import com.huemedia.cms.model.entity.QPlan;
import com.huemedia.cms.model.entity.QRegion;
import com.huemedia.cms.model.entity.QState;
import com.huemedia.cms.web.form.MasterForm;
import com.mysema.query.BooleanBuilder;
import com.mysema.query.jpa.impl.JPAQuery;
import com.mysema.query.types.EntityPath;
import com.mysema.query.types.expr.BooleanExpression;
@Repository
public class JpaPlanDAO extends JpaMasterDAO<Plan> implements
PlanDAO{

	@Override
	EntityPath<?> path() {
		// TODO Auto-generated method stub
		return QPlan.plan;
	}

	@Override
	JPAQuery prepareQuerySearch(Object form) {
		QPlan qPlan = (QPlan) path();
		QState qState= QState.state;
		QRegion qRegion= QRegion.region;
		JPAQuery query = new JPAQuery(em);
		MasterForm masterForm=(MasterForm) form;
		query.from(qPlan).join(qPlan.state,qState).fetch().join(qPlan.region,qRegion).fetch();
		final BooleanBuilder builder = new BooleanBuilder();
		 if(StringUtils.isNotEmpty(masterForm.getName())){
				final BooleanExpression containsName =qPlan.name.like("%"+masterForm.getName()+"%");
				builder.and(containsName);
			}
		 if(masterForm.getState() !=null){
				if(masterForm.getState().getId()!=null){
					final BooleanExpression containsName =qState.id.eq(masterForm.getState().getId());
					builder.and(containsName);
				}
			}
		 if(masterForm.getRegion() !=null){
				if(masterForm.getRegion().getId()!=null){
					final BooleanExpression containsName =qRegion.id.eq(masterForm.getRegion().getId());
					builder.and(containsName);
				}
			}
		query.where(builder).orderBy(qPlan.createDate.desc());
		return query;
	}

}
