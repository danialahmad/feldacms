package com.huemedia.cms.model.dao.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.huemedia.cms.model.dao.TicketActivityDAO;
import com.huemedia.cms.model.entity.QActivityType;
import com.huemedia.cms.model.entity.QTicket;
import com.huemedia.cms.model.entity.QTicketActivity;
import com.huemedia.cms.model.entity.QTicketAssignment;
import com.huemedia.cms.model.entity.TicketActivity;
import com.huemedia.cms.web.form.ActivityForm;
import com.mysema.query.BooleanBuilder;
import com.mysema.query.QueryModifiers;
import com.mysema.query.jpa.impl.JPAQuery;
import com.mysema.query.types.expr.BooleanExpression;

@Repository
public class JpaTicketActivityDAO implements TicketActivityDAO {
	@PersistenceContext
	private EntityManager em;

	@Override
	public Long countAll(Integer assignmentId) {
		JPAQuery query = new JPAQuery(em);
		QTicket qTicket = QTicket.ticket;
		QTicketAssignment qTicketAssignment=QTicketAssignment.ticketAssignment;
		QTicketActivity qTicketActivity = QTicketActivity.ticketActivity;
		query.from(qTicketActivity).join(qTicketActivity.ticketAssignment,qTicketAssignment).fetch().where(qTicketAssignment.id.eq(assignmentId));
		return query.count();
	}

	@Override
	public Long countSearch(ActivityForm activityForm,Integer assignmentId) {
		final QTicketActivity qTicketActivity = QTicketActivity.ticketActivity;
		final JPAQuery query = prepareQuery(activityForm,assignmentId,qTicketActivity);
		return query.count();
	}

	@Override
	public List<TicketActivity> search(ActivityForm activityForm,Integer ticketId, Integer iDisplayStart,
			Integer iDisplayLength) {
		final QTicketActivity qTicketActivity = QTicketActivity.ticketActivity;
		final JPAQuery query = prepareQuery(activityForm,ticketId,qTicketActivity);
		query.restrict(new QueryModifiers(iDisplayLength.longValue(), iDisplayStart.longValue()));
	    return query.list(qTicketActivity);
	}
	
	private JPAQuery prepareQuery(ActivityForm activityForm,Integer ticketId, QTicketActivity qTicketActivity){
		JPAQuery query = new JPAQuery(em);
		QTicket qTicket = QTicket.ticket;
		QTicketAssignment qTicketAssignment=QTicketAssignment.ticketAssignment;
		QActivityType qActivityType = QActivityType.activityType;
		query.from(qTicketActivity).join(qTicketActivity.ticket,qTicket).fetch().join(qTicketActivity.ticketAssignment,qTicketAssignment).fetch()
		.leftJoin(qTicketActivity.activityType,qActivityType).fetch();
		final BooleanBuilder builder = new BooleanBuilder();
        final BooleanExpression containsName = qTicketAssignment.id.eq(ticketId);
		builder.and(containsName);
		 
		 query.where(builder).orderBy(qTicketActivity.createDate.desc());
		return query;
	}
	
	private JPAQuery prepareQuery(ActivityForm activityForm,String ticketId, QTicketActivity qTicketActivity){
		JPAQuery query = new JPAQuery(em);
		QTicket qTicket = QTicket.ticket;
		QTicketAssignment qTicketAssignment=QTicketAssignment.ticketAssignment;
		QActivityType qActivityType = QActivityType.activityType;
		query.from(qTicketActivity).leftJoin(qTicketActivity.ticket,qTicket).fetch()
		.leftJoin(qTicketActivity.activityType,qActivityType).fetch();
		final BooleanBuilder builder = new BooleanBuilder();
        final BooleanExpression containsName = qTicket.id.eq(ticketId);
		builder.and(containsName);
		 
		 query.where(builder).orderBy(qTicketActivity.createDate.desc());
		return query;
	}

	@Override
	public Long countAll(String ticketId) {
		JPAQuery query = new JPAQuery(em);
		QTicket qTicket = QTicket.ticket;
		QTicketActivity qTicketActivity = QTicketActivity.ticketActivity;
		query.from(qTicketActivity).join(qTicketActivity.ticket,qTicket).fetch().where(qTicket.id.eq(ticketId));
		return query.count();
	}

	@Override
	public Long countSearch(ActivityForm activityForm, String ticketId) {
		final QTicketActivity qTicketActivity = QTicketActivity.ticketActivity;
		final JPAQuery query = prepareQuery(activityForm,ticketId,qTicketActivity);
		return query.count();
	}

	@Override
	public List<TicketActivity> search(ActivityForm activityForm,
			String ticketId, Integer iDisplayStart, Integer iDisplayLength) {
		final QTicketActivity qTicketActivity = QTicketActivity.ticketActivity;
		final JPAQuery query = prepareQuery(activityForm,ticketId,qTicketActivity);
		query.restrict(new QueryModifiers(iDisplayLength.longValue(), iDisplayStart.longValue()));
	    return query.list(qTicketActivity);
	}
}
