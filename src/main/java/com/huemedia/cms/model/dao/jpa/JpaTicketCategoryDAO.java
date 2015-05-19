package com.huemedia.cms.model.dao.jpa;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;

import com.huemedia.cms.model.dao.TicketCategoryDAO;
import com.huemedia.cms.model.entity.QTicketCategory;
import com.huemedia.cms.model.entity.QTicketGroup;
import com.huemedia.cms.model.entity.TicketCategory;
import com.huemedia.cms.web.form.MasterForm;
import com.mysema.query.BooleanBuilder;
import com.mysema.query.jpa.impl.JPAQuery;
import com.mysema.query.types.EntityPath;
import com.mysema.query.types.expr.BooleanExpression;

@Repository
public class JpaTicketCategoryDAO extends JpaMasterDAO<TicketCategory>
		implements TicketCategoryDAO {

	@Override
	EntityPath<?> path() {
		// TODO Auto-generated method stub
		return QTicketCategory.ticketCategory;
	}

	@Override
	JPAQuery prepareQuerySearch(Object form) {
		QTicketCategory qTicketCategory = (QTicketCategory) path();
		QTicketGroup qTicketGroup=QTicketGroup.ticketGroup;
		MasterForm masterForm=(MasterForm) form;
		JPAQuery query = new JPAQuery(em);
		query.from(qTicketCategory).join(qTicketCategory.ticketGroup,qTicketGroup).fetch();
		final BooleanBuilder builder = new BooleanBuilder();
		if(StringUtils.isNotEmpty(masterForm.getName())){
			final BooleanExpression containsName =qTicketCategory.name.like("%"+masterForm.getName()+"%");
			builder.and(containsName);
		}
		if(masterForm.getTicketGroup()!=null){
			if(masterForm.getTicketGroup().getId()!=null){
				final BooleanExpression containsName =qTicketGroup.id.eq(masterForm.getTicketGroup().getId());
				builder.and(containsName);
			}
		}
		query.where(builder).orderBy(qTicketGroup.name.asc()).orderBy(qTicketCategory.createDate.desc());
		return query;
	}

}
