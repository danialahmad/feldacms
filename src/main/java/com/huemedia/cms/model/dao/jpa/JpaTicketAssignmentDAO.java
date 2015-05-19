package com.huemedia.cms.model.dao.jpa;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;

import com.huemedia.cms.model.dao.TicketAssignmentDAO;
import com.huemedia.cms.model.entity.QGroup;
import com.huemedia.cms.model.entity.QPriority;
import com.huemedia.cms.model.entity.QRating;
import com.huemedia.cms.model.entity.QStatus;
import com.huemedia.cms.model.entity.QTicket;
import com.huemedia.cms.model.entity.QTicketAssignment;
import com.huemedia.cms.model.entity.QTicketGroup;
import com.huemedia.cms.model.entity.TicketAssignment;
import com.huemedia.cms.web.form.SearchTicketForm;
import com.mysema.query.BooleanBuilder;
import com.mysema.query.jpa.impl.JPAQuery;
import com.mysema.query.types.EntityPath;
import com.mysema.query.types.expr.BooleanExpression;
@Repository
public class JpaTicketAssignmentDAO extends JpaMasterDAO<TicketAssignment> implements TicketAssignmentDAO{

	@Override
	EntityPath<?> path() {
		// TODO Auto-generated method stub
		return QTicketAssignment.ticketAssignment;
	}

	@Override
	JPAQuery prepareQuerySearch(Object form) {
		QTicketAssignment qTicketAssignment = (QTicketAssignment) path();
		QTicket qTicket = QTicket.ticket;
		QStatus qStatus = QStatus.status;
		QGroup qGroup=QGroup.group;
		QPriority qPriority = QPriority.priority;
		QTicketGroup qTicketGroup = QTicketGroup.ticketGroup;
		QRating qRating = QRating.rating;
		SearchTicketForm cForm=(SearchTicketForm) form;
		JPAQuery query = new JPAQuery(em);
		query.from(qTicketAssignment).leftJoin(qTicketAssignment.ticket,qTicket).fetch().leftJoin(qTicketAssignment.status,qStatus).fetch().
		leftJoin(qTicketAssignment.group,qGroup).fetch().leftJoin(qTicket.priority,qPriority).fetch().fetch().leftJoin(qTicketAssignment.ticketGroup,qTicketGroup).fetch().
		leftJoin(qTicket.rating,qRating).fetch();
		final BooleanBuilder builder = new BooleanBuilder();
		if(StringUtils.isNotEmpty(cForm.getId())){
			final BooleanExpression containsName =qTicket.id.eq(cForm.getId());
			builder.and(containsName);
		}
		if(StringUtils.isNotEmpty(cForm.getStatus())){
			final BooleanExpression containsName =qStatus.id.eq(cForm.getStatus());
			builder.and(containsName);
		}
		if(cForm.getStatusList()!=null){
			final BooleanExpression containsName =qStatus.id.in(cForm.getStatusList());
			builder.and(containsName);
		}
		if(cForm.getGroup()!=null){
			final BooleanExpression containsName =qGroup.id.in(cForm.getGroup());
			builder.and(containsName);
		}
		if(cForm.getSupervisorId()!=null){
			final BooleanExpression containsName =qTicketAssignment.supervisorId.eq(cForm.getSupervisorId());
			builder.and(containsName);
		}
		if(cForm.getAssigneeId()!=null){
			final BooleanExpression containsName =qTicketAssignment.assigneeId.eq(cForm.getAssigneeId());
			builder.and(containsName);
		}
		if(cForm.getTicketGroupId()!=null){
			final BooleanExpression containsName =qTicketGroup.id.eq(cForm.getTicketGroupId());
			builder.and(containsName);
		}
		if(cForm.getRatingId()!=null){
			final BooleanExpression containsName =qRating.id.eq(cForm.getRatingId());
			builder.and(containsName);
		}
		query.where(builder).orderBy(qTicketAssignment.updateDate.desc());
		return query;
	}
	
}


