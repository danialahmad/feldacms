package com.huemedia.cms.model.dao.jpa;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;

import com.huemedia.cms.model.dao.TicketGroupDAO;
import com.huemedia.cms.model.entity.QTicketGroup;
import com.huemedia.cms.model.entity.TicketGroup;
import com.huemedia.cms.web.form.MasterForm;
import com.mysema.query.BooleanBuilder;
import com.mysema.query.jpa.impl.JPAQuery;
import com.mysema.query.types.EntityPath;
import com.mysema.query.types.expr.BooleanExpression;

@Repository
public class JpaTicketGroupDAO extends JpaMasterDAO<TicketGroup> implements TicketGroupDAO{

	@Override
	EntityPath<?> path() {
		// TODO Auto-generated method stub
		return QTicketGroup.ticketGroup;
	}

	@Override
	JPAQuery prepareQuerySearch(Object form) {
		QTicketGroup qTicketGroup=(QTicketGroup) path();
		MasterForm masterForm=(MasterForm) form;
		JPAQuery query = new JPAQuery(em);
	    query.from(qTicketGroup);
        final BooleanBuilder builder = new BooleanBuilder();
        if(StringUtils.isNotEmpty(masterForm.getName())){
			final BooleanExpression containsName =qTicketGroup.name.like("%"+masterForm.getName()+"%");
			builder.and(containsName);
		}
		query.where(builder).orderBy(qTicketGroup.createDate.desc());
		return query;
	}

}
