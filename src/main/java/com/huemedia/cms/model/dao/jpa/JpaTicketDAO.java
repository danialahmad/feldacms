package com.huemedia.cms.model.dao.jpa;

import java.text.SimpleDateFormat;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;

import com.huemedia.cms.model.dao.TicketDAO;
import com.huemedia.cms.model.entity.QGroup;
import com.huemedia.cms.model.entity.QPriority;
import com.huemedia.cms.model.entity.QProfile;
import com.huemedia.cms.model.entity.QRating;
import com.huemedia.cms.model.entity.QStatus;
import com.huemedia.cms.model.entity.QTicket;
import com.huemedia.cms.model.entity.QTicketAssignment;
import com.huemedia.cms.model.entity.QTicketCategory;
import com.huemedia.cms.model.entity.QTicketGroup;
import com.huemedia.cms.model.entity.Ticket;
import com.huemedia.cms.web.form.SearchTicketForm;
import com.mysema.query.BooleanBuilder;
import com.mysema.query.QueryModifiers;
import com.mysema.query.jpa.impl.JPAQuery;
import com.mysema.query.types.expr.BooleanExpression;

@Repository
public class JpaTicketDAO implements TicketDAO{

	@PersistenceContext
	private EntityManager em;
	private Long countVal;
	private Long countSearchVal;
	
	
	
	private JPAQuery prepareQueryForSearchByStatus(SearchTicketForm form,String[] status,QTicket qTicket,boolean unassigned){
	    QStatus qStatus= QStatus.status;
	    QProfile qProfile =QProfile.profile;
	    QPriority qPriority = QPriority.priority;
	    QTicketCategory qTicketCategory= QTicketCategory.ticketCategory;
	    QTicketGroup qTicketGroup=QTicketGroup.ticketGroup;
		JPAQuery query = new JPAQuery(em);
	    query.from(qTicket).join(qTicket.status,qStatus).fetch().join(qTicket.profile,qProfile).fetch().leftJoin(qTicket.ticketGroup,qTicketGroup).fetch()
	    .leftJoin(qTicket.ticketCategory, qTicketCategory).fetch()
	    .leftJoin(qTicket.priority,qPriority).fetch();
	    
        final BooleanBuilder builder = new BooleanBuilder();
       
        if(status!=null){
        final BooleanExpression containsName = qStatus.id.in(status);
        builder.and(containsName);
        }
        if(unassigned){
        	 final BooleanExpression containsName =qTicket.assigneeId.isNull();
             builder.and(containsName);
        }else{
        	final BooleanExpression containsName =qTicket.assigneeId.isNotNull();
            builder.and(containsName);
        }
        if(StringUtils.isNotEmpty(form.getTicketTitle())){
			final BooleanExpression containsName =qTicket.ticketTitle.like("%"+form.getTicketTitle()+"%");
			builder.and(containsName);
		}
        if(form.getTicketCategoryId()!=null && form.getTicketCategoryId()!=-1){
			final BooleanExpression containsName =qTicketCategory.id.eq(form.getTicketCategoryId());
			builder.and(containsName);
		}
        if(form.getTicketGroupId()!=null){
			final BooleanExpression containsName =qTicketGroup.id.eq(form.getTicketGroupId());
			builder.and(containsName);
		}
        if(StringUtils.isNotEmpty(form.getStatus())){
        	final BooleanExpression containsName = qStatus.id.eq(form.getStatus());
        	builder.and(containsName);
        }
        if(StringUtils.isNotEmpty(form.getPriorityId())){
        	final BooleanExpression containsName = qPriority.id.eq(form.getPriorityId());
        	builder.and(containsName);
        }
        if(form.getDateFrom()!=null && form.getDateTo()!=null){
			SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
			System.out.println("DATE FROM :"+dateFormat.format(form.getDateFrom()));
			System.out.println("DATE TO :"+dateFormat.format(form.getDateTo()));
			final BooleanExpression containsName =qTicket.createDate.between(form.getDateFrom(), form.getDateTo());
			builder.and(containsName);
		}
		
		query.where(builder).orderBy(qTicket.updateDate.desc());
		return query;
	}
	private JPAQuery prepareQueryForSearchByStatus(SearchTicketForm form,String[] status,Integer supervisorId,QTicket qTicket){
	    QStatus qStatus= QStatus.status;
	    QProfile qProfile =QProfile.profile;
	    QPriority qPriority = QPriority.priority;
	    QTicketCategory qTicketCategory= QTicketCategory.ticketCategory;
	    QTicketGroup qTicketGroup=QTicketGroup.ticketGroup;
	    QRating qRating = QRating.rating;
		JPAQuery query = new JPAQuery(em);
	    query.from(qTicket).join(qTicket.status,qStatus).fetch().join(qTicket.profile,qProfile).fetch().join(qTicket.ticketGroup,qTicketGroup).fetch()
	    .leftJoin(qTicket.ticketCategory, qTicketCategory).fetch()
	    .leftJoin(qTicket.priority,qPriority).fetch()
	    .leftJoin(qTicket.rating,qRating).fetch();
	    
        final BooleanBuilder builder = new BooleanBuilder();
       
        if(status!=null){
        final BooleanExpression containsName = qStatus.id.in(status);
		builder.and(containsName).and(qTicket.supervisorId.eq(supervisorId));
        }
		if(StringUtils.isNotEmpty(form.getTicketTitle())){
			final BooleanExpression containsName =qTicket.ticketTitle.like("%"+form.getTicketTitle()+"%");
			builder.and(containsName);
		}
        if(form.getTicketCategoryId()!=null && form.getTicketCategoryId()!=-1){
			final BooleanExpression containsName =qTicketCategory.id.eq(form.getTicketCategoryId());
			builder.and(containsName);
		}
        if(form.getTicketGroupId()!=null){
			final BooleanExpression containsName =qTicketGroup.id.eq(form.getTicketGroupId());
			builder.and(containsName);
		}
        if(StringUtils.isNotEmpty(form.getStatus())){
        	final BooleanExpression containsName = qStatus.id.eq(form.getStatus());
        	builder.and(containsName);
        }
        if(StringUtils.isNotEmpty(form.getPriorityId())){
        	final BooleanExpression containsName = qPriority.id.eq(form.getPriorityId());
        	builder.and(containsName);
        }
        if(form.getDateFrom()!=null && form.getDateTo()!=null){
			SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
			System.out.println("DATE FROM :"+dateFormat.format(form.getDateFrom()));
			System.out.println("DATE TO :"+dateFormat.format(form.getDateTo()));
			final BooleanExpression containsName =qTicket.createDate.between(form.getDateFrom(), form.getDateTo());
			builder.and(containsName);
		}
		
		
		
		query.where(builder).orderBy(qTicket.updateDate.desc());
		return query;
	}
	
	private JPAQuery prepareQuerySearchByGroup(SearchTicketForm form,String[] group,Integer region,String[] status,QTicket qTicket){
		QStatus qStatus= QStatus.status;
	    QProfile qProfile =QProfile.profile;
	    QPriority qPriority = QPriority.priority;
	    QTicketCategory qTicketCategory= QTicketCategory.ticketCategory;
	    QTicketGroup qTicketGroup=QTicketGroup.ticketGroup;
	    QRating qRating = QRating.rating;
		JPAQuery query = new JPAQuery(em);
	    query.from(qTicket).join(qTicket.status,qStatus).fetch().join(qTicket.profile,qProfile).fetch().leftJoin(qTicket.ticketGroup,qTicketGroup).fetch()
	    .leftJoin(qTicket.ticketCategory, qTicketCategory).fetch()
	    .leftJoin(qTicket.priority,qPriority).fetch()
	    .leftJoin(qTicket.rating,qRating).fetch();
	    System.out.println("SIZE :"+group.length);
        final BooleanBuilder builder = new BooleanBuilder();
        if(group!=null){
        final BooleanExpression containsName = qTicket.groupId.in(group);
		builder.and(containsName);
        }
        if(region!=null){
        	 final BooleanExpression containsName = qTicket.regionId.in(region);
     		builder.and(containsName);
        }
        if(status!=null){
       	 final BooleanExpression containsName = qStatus.id.in(status);
     	 builder.and(containsName);
        }
		if(StringUtils.isNotEmpty(form.getTicketTitle())){
			final BooleanExpression containsName =qTicket.ticketTitle.like("%"+form.getTicketTitle()+"%");
			builder.and(containsName);
		}
        if(form.getTicketCategoryId()!=null && form.getTicketCategoryId()!=-1){
			final BooleanExpression containsName =qTicketCategory.id.eq(form.getTicketCategoryId());
			builder.and(containsName);
		}
        if(form.getTicketGroupId()!=null){
			final BooleanExpression containsName =qTicketGroup.id.eq(form.getTicketGroupId());
			builder.and(containsName);
		}
        if(StringUtils.isNotEmpty(form.getStatus())){
        	final BooleanExpression containsName = qStatus.id.eq(form.getStatus());
        	builder.and(containsName);
        }
        if(StringUtils.isNotEmpty(form.getPriorityId())){
        	final BooleanExpression containsName = qPriority.id.eq(form.getPriorityId());
        	builder.and(containsName);
        }
        if(form.getDateFrom()!=null && form.getDateTo()!=null){
			SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
			System.out.println("DATE FROM :"+dateFormat.format(form.getDateFrom()));
			System.out.println("DATE TO :"+dateFormat.format(form.getDateTo()));
			final BooleanExpression containsName =qTicket.createDate.between(form.getDateFrom(), form.getDateTo());
			builder.and(containsName);
		}
		
		query.where(builder).orderBy(qTicket.updateDate.desc());
		return query;
	}
	
	
	
	
	private JPAQuery prepareQueryUnassigned(SearchTicketForm form,String[] group,Integer region,String[] status,QTicket qTicket){
		QStatus qStatus= QStatus.status;
	    QProfile qProfile =QProfile.profile;
	    QPriority qPriority = QPriority.priority;
	    QTicketCategory qTicketCategory= QTicketCategory.ticketCategory;
	    QTicketGroup qTicketGroup=QTicketGroup.ticketGroup;
	    QRating qRating = QRating.rating;
		JPAQuery query = new JPAQuery(em);
	    query.from(qTicket).join(qTicket.status,qStatus).fetch().join(qTicket.profile,qProfile).fetch().leftJoin(qTicket.ticketGroup,qTicketGroup).fetch()
	    .leftJoin(qTicket.ticketCategory, qTicketCategory).fetch()
	    .leftJoin(qTicket.priority,qPriority).fetch()
	    .leftJoin(qTicket.rating,qRating).fetch();
	    
        final BooleanBuilder builder = new BooleanBuilder();
        if(group!=null){
        final BooleanExpression containsName = qTicket.groupId.in(group);
		builder.and(containsName);
        }
        if(region!=null){
        	 final BooleanExpression containsName = qTicket.regionId.in(region);
     		builder.and(containsName);
        }
        if(status!=null){
       	 final BooleanExpression containsName = qStatus.id.in(status);
     	 builder.and(containsName);
        }
		if(StringUtils.isNotEmpty(form.getTicketTitle())){
			final BooleanExpression containsName =qTicket.ticketTitle.like("%"+form.getTicketTitle()+"%");
			builder.and(containsName);
		}
        if(form.getTicketCategoryId()!=null && form.getTicketCategoryId()!=-1){
			final BooleanExpression containsName =qTicketCategory.id.eq(form.getTicketCategoryId());
			builder.and(containsName);
		}
        if(form.getTicketGroupId()!=null){
			final BooleanExpression containsName =qTicketGroup.id.eq(form.getTicketGroupId());
			builder.and(containsName);
		}
        if(StringUtils.isNotEmpty(form.getStatus())){
        	final BooleanExpression containsName = qStatus.id.eq(form.getStatus());
        	builder.and(containsName);
        }
        if(StringUtils.isNotEmpty(form.getPriorityId())){
        	final BooleanExpression containsName = qPriority.id.eq(form.getPriorityId());
        	builder.and(containsName);
        }
        if(form.getDateFrom()!=null && form.getDateTo()!=null){
			SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
			System.out.println("DATE FROM :"+dateFormat.format(form.getDateFrom()));
			System.out.println("DATE TO :"+dateFormat.format(form.getDateTo()));
			final BooleanExpression containsName =qTicket.createDate.between(form.getDateFrom(), form.getDateTo());
			builder.and(containsName);
		}
		
		query.where(builder.and(qTicket.assigneeId.isNull())).orderBy(qTicket.updateDate.desc());
		return query;
	}
	
	
	
	private JPAQuery prepareQuerySearchByAssigneeID(SearchTicketForm form,Integer assigneeId,QTicket qTicket){
		QStatus qStatus= QStatus.status;
	    QProfile qProfile =QProfile.profile;
	    QPriority qPriority = QPriority.priority;
	    QTicketCategory qTicketCategory= QTicketCategory.ticketCategory;
	    QTicketGroup qTicketGroup=QTicketGroup.ticketGroup;
	    QRating qRating = QRating.rating;
		JPAQuery query = new JPAQuery(em);
	    query.from(qTicket).join(qTicket.status,qStatus).fetch().join(qTicket.profile,qProfile).fetch().join(qTicket.ticketGroup,qTicketGroup).fetch()
	    .leftJoin(qTicket.ticketCategory, qTicketCategory).fetch()
	    .leftJoin(qTicket.priority,qPriority).fetch()
	    .leftJoin(qTicket.rating,qRating).fetch();
	    
        final BooleanBuilder builder = new BooleanBuilder();
        if(assigneeId!=null){
        final BooleanExpression containsName = qTicket.assigneeId.eq(assigneeId);
		builder.and(containsName);
        }
		
		if(StringUtils.isNotEmpty(form.getTicketTitle())){
			final BooleanExpression containsName =qTicket.ticketTitle.like("%"+form.getTicketTitle()+"%");
			builder.and(containsName);
		}
        if(form.getTicketCategoryId()!=null && form.getTicketCategoryId()!=-1){
			final BooleanExpression containsName =qTicketCategory.id.eq(form.getTicketCategoryId());
			builder.and(containsName);
		}
        if(form.getTicketGroupId()!=null){
			final BooleanExpression containsName =qTicketGroup.id.eq(form.getTicketGroupId());
			builder.and(containsName);
		}
        if(StringUtils.isNotEmpty(form.getStatus())){
        	final BooleanExpression containsName = qStatus.id.eq(form.getStatus());
        	builder.and(containsName);
        }
        if(StringUtils.isNotEmpty(form.getPriorityId())){
        	final BooleanExpression containsName = qPriority.id.eq(form.getPriorityId());
        	builder.and(containsName);
        }
        if(form.getDateFrom()!=null && form.getDateTo()!=null){
			SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
			System.out.println("DATE FROM :"+dateFormat.format(form.getDateFrom()));
			System.out.println("DATE TO :"+dateFormat.format(form.getDateTo()));
			final BooleanExpression containsName =qTicket.createDate.between(form.getDateFrom(), form.getDateTo());
			builder.and(containsName);
		}
		
		query.where(builder).orderBy(qTicket.updateDate.desc());
		return query;
	}
	
	private JPAQuery prepareQuerySearchBySupervisorID(SearchTicketForm form,Integer supervisorId,QTicket qTicket){
		QStatus qStatus= QStatus.status;
	    QProfile qProfile =QProfile.profile;
	    QPriority qPriority = QPriority.priority;
	    QTicketCategory qTicketCategory= QTicketCategory.ticketCategory;
	    QTicketGroup qTicketGroup=QTicketGroup.ticketGroup;
		JPAQuery query = new JPAQuery(em);
	    query.from(qTicket).join(qTicket.status,qStatus).fetch().join(qTicket.profile,qProfile).fetch().join(qTicket.ticketGroup,qTicketGroup).fetch()
	    .leftJoin(qTicket.ticketCategory, qTicketCategory).fetch()
	    .join(qTicket.priority,qPriority).fetch();
	    
        final BooleanBuilder builder = new BooleanBuilder();
        if(supervisorId!=null){
        final BooleanExpression containsName = qTicket.supervisorId.eq(supervisorId);
		builder.and(containsName);
        }
		
		 if(StringUtils.isNotEmpty(form.getTicketTitle())){
				final BooleanExpression containsName =qTicket.ticketTitle.like("%"+form.getTicketTitle()+"%");
				builder.and(containsName);
			}
	        if(form.getTicketCategoryId()!=null && form.getTicketCategoryId()!=-1){
				final BooleanExpression containsName =qTicketCategory.id.eq(form.getTicketCategoryId());
				builder.and(containsName);
			}
	        if(form.getTicketGroupId()!=null){
				final BooleanExpression containsName =qTicketGroup.id.eq(form.getTicketGroupId());
				builder.and(containsName);
			}
	        if(StringUtils.isNotEmpty(form.getStatus())){
	        	final BooleanExpression containsName = qStatus.id.eq(form.getStatus());
	        	builder.and(containsName);
	        }
	        if(StringUtils.isNotEmpty(form.getPriorityId())){
	        	final BooleanExpression containsName = qPriority.id.eq(form.getPriorityId());
	        	builder.and(containsName);
	        }
	        if(form.getDateFrom()!=null && form.getDateTo()!=null){
				SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
				System.out.println("DATE FROM :"+dateFormat.format(form.getDateFrom()));
				System.out.println("DATE TO :"+dateFormat.format(form.getDateTo()));
				final BooleanExpression containsName =qTicket.createDate.between(form.getDateFrom(), form.getDateTo());
				builder.and(containsName);
			}
		
		
		query.where(builder).orderBy(qTicket.updateDate.desc());
		return query;
	}
	
	private JPAQuery prepareQuerySearchByProfileID(SearchTicketForm form,Integer profileId,QTicket qTicket){
		QStatus qStatus= QStatus.status;
	    QProfile qProfile =QProfile.profile;
	    QPriority qPriority = QPriority.priority;
		JPAQuery query = new JPAQuery(em);
	    query.from(qTicket).join(qTicket.status,qStatus).fetch().join(qTicket.profile,qProfile).fetch()
	    .leftJoin(qTicket.priority,qPriority).fetch();
	    
        final BooleanBuilder builder = new BooleanBuilder();
        if(profileId!=null){
        final BooleanExpression containsName = qTicket.profile.id.eq(profileId);
		builder.and(containsName);
        }
		if(form.getActive()!=null){
			if(form.getActive().booleanValue()){
				final BooleanExpression containsName =qStatus.id.in("NEW","ASSIGNED","IN PROGRESS","WAITING","COMPLETED");
				builder.and(containsName);
			}else{
				final BooleanExpression containsName =qStatus.id.eq("CLOSED");
				builder.and(containsName);
			}
		}
		if(StringUtils.isNotEmpty(form.getId())){
			final BooleanExpression containsName =qTicket.id.eq(form.getId());
			builder.and(containsName);
		}
		
		if(form.getDateFrom()!=null && form.getDateTo()!=null){
			SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
			System.out.println("DATE FROM :"+dateFormat.format(form.getDateFrom()));
			System.out.println("DATE TO :"+dateFormat.format(form.getDateTo()));
			final BooleanExpression containsName =qTicket.createDate.between(form.getDateFrom(), form.getDateTo());
			builder.and(containsName);
		}
		 if(StringUtils.isNotEmpty(form.getTicketTitle())){
				final BooleanExpression containsName =qTicket.ticketTitle.like("%"+form.getTicketTitle()+"%");
				builder.and(containsName);
			}
	      
	        if(StringUtils.isNotEmpty(form.getStatus())){
	        	final BooleanExpression containsName = qStatus.id.eq(form.getStatus());
	        	builder.and(containsName);
	        }
	        if(StringUtils.isNotEmpty(form.getPriorityId())){
	        	final BooleanExpression containsName = qPriority.id.eq(form.getPriorityId());
	        	builder.and(containsName);
	        }
		
		
		query.where(builder).orderBy(qTicket.updateDate.desc());
		return query;
	}
	
	@Override
	public Long countAllByStatus(String[] status,boolean unassigned) {
		QTicket qTicket = QTicket.ticket;
	    QStatus qStatus= QStatus.status;
		JPAQuery query = new JPAQuery(em);
		
		if(unassigned){
			 query.from(qTicket).join(qTicket.status,qStatus).fetch().where(qStatus.id.in(status).and(qTicket.assigneeId.isNull()));
		}else{
			query.from(qTicket).join(qTicket.status,qStatus).fetch().where(qStatus.id.in(status).and(qTicket.assigneeId.isNotNull()));
		}
		
	    
		return query.count();
	}

	@Override
	public Long countSearchByStatus(SearchTicketForm form,String[] status,boolean unassigned) {
		final QTicket qTicket = QTicket.ticket;
		final JPAQuery query = prepareQueryForSearchByStatus(form,status, qTicket,unassigned);
		return query.count();
	}

	@Override
	public List<Ticket> searchByStatus(SearchTicketForm form,String[] status, Integer iDisplayStart,
			Integer iDisplayLength,boolean unassigned) {
		final QTicket qTicket = QTicket.ticket;
		final JPAQuery query = prepareQueryForSearchByStatus(form,status, qTicket,unassigned);
		query.restrict(new QueryModifiers(iDisplayLength.longValue(), iDisplayStart.longValue()));
	    return query.list(qTicket);
	}
	
	@Override
	public List<Ticket> searchByGroup(SearchTicketForm form,String[] group,Integer region,String[] status, Integer iDisplayStart,
			Integer iDisplayLength) {
		final QTicket qTicket = QTicket.ticket;
		final JPAQuery query = prepareQuerySearchByGroup(form,group,region,status,qTicket);
		query.restrict(new QueryModifiers(iDisplayLength.longValue(), iDisplayStart.longValue()));
	    return query.list(qTicket);
	}

	@Override
	public Long countAllByGroup(String[] group,Integer region,String[] status) {
		QTicket qTicket = QTicket.ticket;
		QStatus qStatus= QStatus.status;
		JPAQuery query = new JPAQuery(em);
		if(status!=null){
	        if(region!=null){
	        	query.from(qTicket).join(qTicket.status,qStatus).fetch().where(qTicket.groupId.in(group).and(qStatus.id.in(status)).and(qTicket.regionId.eq(region)));
	        }else{
	        	query.from(qTicket).join(qTicket.status,qStatus).fetch().where(qTicket.groupId.in(group).and(qStatus.id.in(status)));
	        }
			
		}else{
			if(region!=null){
				query.from(qTicket).where(qTicket.groupId.in(group).and(qTicket.regionId.eq(region)));
			}else{
				query.from(qTicket).where(qTicket.groupId.in(group));
			}
			
		}
	    return query.count();
	}

	@Override
	public Long countSearchByGroup(SearchTicketForm form,String[] group,Integer region, String[] status) {
		final QTicket qTicket = QTicket.ticket;
		final JPAQuery query = prepareQuerySearchByGroup(form,group,region,status,qTicket);
		return query.count();
	}

	@Override
	public List<Ticket> searchByAssigneeID(SearchTicketForm form,
			Integer assigneeId, Integer iDisplayStart, Integer iDisplayLength) {
		final QTicket qTicket = QTicket.ticket;
		final JPAQuery query = prepareQuerySearchByAssigneeID(form,assigneeId,qTicket);
		query.restrict(new QueryModifiers(iDisplayLength.longValue(), iDisplayStart.longValue()));
	    return query.list(qTicket);
	}

	@Override
	public Long countAllByAssigneeID(Integer assigneeId) {
		QTicket qTicket = QTicket.ticket;
		JPAQuery query = new JPAQuery(em);
	    query.from(qTicket).where(qTicket.assigneeId.eq(assigneeId));
		return query.count();
	}

	@Override
	public Long countSearchByAssigneeID(SearchTicketForm form,
			Integer assigneeId) {
		final QTicket qTicket = QTicket.ticket;
		final JPAQuery query = prepareQuerySearchByAssigneeID(form,assigneeId, qTicket);
		return query.count();
	}

	@Override
	public List<Ticket> searchByProfileID(SearchTicketForm form,
			Integer profileId, Integer iDisplayStart, Integer iDisplayLength) {
		final QTicket qTicket = QTicket.ticket;
		final JPAQuery query = prepareQuerySearchByProfileID(form,profileId,qTicket);
		query.restrict(new QueryModifiers(iDisplayLength.longValue(), iDisplayStart.longValue()));
	    return query.list(qTicket);
	}

	@Override
	public Long countAllByByProfileID(Integer profileId) {
		QTicket qTicket = QTicket.ticket;
		JPAQuery query = new JPAQuery(em);
	    query.from(qTicket).where(qTicket.profile.id.eq(profileId));
		return query.count();
	}

	@Override
	public Long countSearchByProfileID(SearchTicketForm form, Integer profileId) {
		final QTicket qTicket = QTicket.ticket;
		final JPAQuery query = prepareQuerySearchByProfileID(form,profileId, qTicket);
		return query.count();
	}
	
	
	
	private JPAQuery prepareQuerySearchByTime(SearchTicketForm form,QTicket qTicket){
		QStatus qStatus= QStatus.status;
	    QProfile qProfile =QProfile.profile;
	    QPriority qPriority = QPriority.priority;
	    final JPAQuery query = new JPAQuery(em);
	    query.from(qTicket).join(qTicket.status,qStatus).fetch().join(qTicket.profile,qProfile).fetch()
	    .join(qTicket.priority,qPriority).fetch();
	    
        BooleanBuilder builder = new BooleanBuilder();
		if(form.getDay()!=null){
			final BooleanExpression containsName =qTicket.createDate.dayOfYear().eq(form.getDay());
			builder.and(containsName);
		}
		if(form.getWeek()!=null){
			final BooleanExpression containsName =qTicket.createDate.week().eq(form.getWeek());
			builder.and(containsName);
		}
		if(form.getMonth()!=null){
			final BooleanExpression containsName =qTicket.createDate.month().eq(form.getMonth());
			builder.and(containsName);
		}
		if(form.getYear()!=null){
			final BooleanExpression containsName =qTicket.createDate.year().eq(form.getYear());
			builder.and(containsName);
		}
		if(form.getSupervisorId()!=null){
			final BooleanExpression containsName =qTicket.supervisorId.eq(form.getSupervisorId());
			builder.and(containsName);
		}
		if(form.getAssigneeId()!=null){
			final BooleanExpression containsName =qTicket.assigneeId.eq(form.getAssigneeId());
			builder.and(containsName);
		}
		
		query.where(builder).orderBy(qTicket.updateDate.desc());
		return query;
	}
	
	@Override
	public List<Ticket> searchTicketByTime(SearchTicketForm form, Integer iDisplayStart, Integer iDisplayLength) {
		final QTicket qTicket = QTicket.ticket;
		final JPAQuery query = prepareQuerySearchByTime(form,qTicket);
		query.restrict(new QueryModifiers(iDisplayLength.longValue(), iDisplayStart.longValue()));
	    return query.list(qTicket);
	}
	
	@Override
	public Long countSearchTicketByTime(SearchTicketForm form) {
		final QTicket qTicket = QTicket.ticket;
		final JPAQuery query = prepareQuerySearchByTime(form, qTicket);
		return query.count();
	}
	
	@Override
	public Long countSearchAllTicketByTime(SearchTicketForm form) {
		final QTicket qTicket = QTicket.ticket;
		final JPAQuery query = new JPAQuery(em);
		 query.from(qTicket);
	        BooleanBuilder builder = new BooleanBuilder();
			if(form.getDay()!=null){
				final BooleanExpression containsName =qTicket.createDate.dayOfYear().eq(form.getDay());
				builder.and(containsName);
			}
			if(form.getWeek()!=null){
				final BooleanExpression containsName =qTicket.createDate.week().eq(form.getWeek());
				builder.and(containsName);
			}
			if(form.getMonth()!=null){
				final BooleanExpression containsName =qTicket.createDate.month().eq(form.getMonth());
				builder.and(containsName);
			}
			if(form.getYear()!=null){
				final BooleanExpression containsName =qTicket.createDate.year().eq(form.getYear());
				builder.and(containsName);
			}
			query.where(builder);
		return query.count();
	}
	

	@Override
	public List<Ticket> findAll(Integer iDisplayLength,Integer iDisplayStart) {
		final QTicket qTicket = QTicket.ticket;
		QStatus qStatus= QStatus.status;
	    QProfile qProfile =QProfile.profile;
	    QPriority qPriority = QPriority.priority;
		JPAQuery query = new JPAQuery(em);
	    query.from(qTicket).join(qTicket.status,qStatus).fetch().join(qTicket.profile,qProfile).fetch()
	    .join(qTicket.priority,qPriority).fetch().orderBy(qTicket.createDate.desc());
	    
	    query.restrict(new QueryModifiers(iDisplayLength.longValue(), iDisplayStart.longValue()));
	    return query.list(qTicket);
	}

	@Override
	public Long countAllByStatus(String[] status, Integer supervisorId) {
		QTicket qTicket = QTicket.ticket;
	    QStatus qStatus= QStatus.status;
		JPAQuery query = new JPAQuery(em);
	    query.from(qTicket).join(qTicket.status,qStatus).fetch().where(qStatus.id.in(status).and(qTicket.supervisorId.eq(supervisorId)));
		return query.count();
	}

	@Override
	public Long countSearchByStatus(SearchTicketForm form, String[] status,
			Integer supervisorId) {
		final QTicket qTicket = QTicket.ticket;
		final JPAQuery query = prepareQueryForSearchByStatus(form,status,supervisorId,qTicket);
		return query.count();
	}

	@Override
	public List<Ticket> searchByStatus(SearchTicketForm form, String[] status,
			Integer supervisorId, Integer iDisplayStart, Integer iDisplayLength) {
		final QTicket qTicket = QTicket.ticket;
		final JPAQuery query = prepareQueryForSearchByStatus(form,status,supervisorId,qTicket);
		query.restrict(new QueryModifiers(iDisplayLength.longValue(), iDisplayStart.longValue()));
	    return query.list(qTicket);
	}
	@Override
	public List<Ticket> searchBySupervisorID(SearchTicketForm form,
			Integer supervisorId, Integer iDisplayStart, Integer iDisplayLength) {
		final QTicket qTicket = QTicket.ticket;
		final JPAQuery query = prepareQuerySearchBySupervisorID(form,supervisorId,qTicket);
		query.restrict(new QueryModifiers(iDisplayLength.longValue(), iDisplayStart.longValue()));
	    return query.list(qTicket);
	}
	@Override
	public Long countAllBySupervisorID(Integer supervisorId) {
		QTicket qTicket = QTicket.ticket;
		JPAQuery query = new JPAQuery(em);
	    query.from(qTicket).where(qTicket.supervisorId.eq(supervisorId));
		return query.count();
	}
	@Override
	public Long countSearchBySupervisorID(SearchTicketForm form,
			Integer supervisorId) {
		final QTicket qTicket = QTicket.ticket;
		final JPAQuery query = prepareQuerySearchBySupervisorID(form,supervisorId, qTicket);
		return query.count();
	}
	
	
	
	private JPAQuery prepareQuery(SearchTicketForm form,QTicket qTicket){
		QStatus qStatus= QStatus.status;
	    QProfile qProfile =QProfile.profile;
	    QPriority qPriority = QPriority.priority;
	    QTicketCategory qTicketCategory= QTicketCategory.ticketCategory;
	    QTicketGroup qTicketGroup=QTicketGroup.ticketGroup;
	    QRating qRating=QRating.rating;
	    
		JPAQuery query = new JPAQuery(em);
	    query.from(qTicket).join(qTicket.status,qStatus).fetch().join(qTicket.profile,qProfile).fetch().leftJoin(qTicket.ticketGroup,qTicketGroup).fetch()
	    .leftJoin(qTicket.ticketCategory,qTicketCategory).fetch()
	    .leftJoin(qTicket.priority,qPriority).fetch()
	    .leftJoin(qTicket.rating,qRating).fetch()
	    .leftJoin(qTicket.status,qStatus).fetch();
	    
	    final BooleanBuilder builder = new BooleanBuilder();
        if(StringUtils.isNotEmpty(form.getId())){
			final BooleanExpression containsName =qTicket.id.eq(form.getId());
			builder.and(containsName);
		}
        if(StringUtils.isNotEmpty(form.getTicketTitle())){
			final BooleanExpression containsName =qTicket.ticketTitle.like("%"+form.getTicketTitle()+"%");
			builder.and(containsName);
		}
        if(form.getTicketCategoryId()!=null && form.getTicketCategoryId()!=-1){
			final BooleanExpression containsName =qTicketCategory.id.eq(form.getTicketCategoryId());
			builder.and(containsName);
		}
        if(form.getTicketGroupId()!=null){
			final BooleanExpression containsName =qTicketGroup.id.eq(form.getTicketGroupId());
			builder.and(containsName);
		}
        if(StringUtils.isNotEmpty(form.getStatus())){
        	final BooleanExpression containsName = qStatus.id.eq(form.getStatus());
        	builder.and(containsName);
        }
        if(form.getStatusList()!=null){
        	final BooleanExpression containsName = qStatus.id.in(form.getStatusList());
        	builder.and(containsName);
        }
        if(StringUtils.isNotEmpty(form.getPriorityId())){
        	final BooleanExpression containsName = qPriority.id.eq(form.getPriorityId());
        	builder.and(containsName);
        }
      if(StringUtils.isNotEmpty(form.getIcNo())){
    	  final BooleanExpression containsName = qProfile.icNo.eq(form.getIcNo());
      	builder.and(containsName);
      }
      if(StringUtils.isNotEmpty(form.getPhoneNo())){
    	  final BooleanExpression containsName = qProfile.phoneNo.eq(form.getPhoneNo()).or(qProfile.mobileNo.eq(form.getPhoneNo()));
      	builder.and(containsName);
      }
        
        if(form.getDateFrom()!=null && form.getDateTo()!=null){
			SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
			System.out.println("DATE FROM :"+dateFormat.format(form.getDateFrom()));
			System.out.println("DATE TO :"+dateFormat.format(form.getDateTo()));
			final BooleanExpression containsName =qTicket.createDate.between(form.getDateFrom(), form.getDateTo());
			builder.and(containsName);
		}
		
		query.where(builder).orderBy(qStatus.rank.asc()).orderBy(qTicket.updateDate.desc());
		return query;
	}
	
	@Override
	public List<Ticket> search(SearchTicketForm form, Integer iDisplayStart,
			Integer iDisplayLength) {
		final QTicket qTicket = QTicket.ticket;
		final JPAQuery query = prepareQuery(form,qTicket);
		query.restrict(new QueryModifiers(iDisplayLength.longValue(), iDisplayStart.longValue()));
	    return query.list(qTicket);
	}
	@Override
	public Long countAll() {
		QTicket qTicket = QTicket.ticket;
		JPAQuery query = new JPAQuery(em);
	    query.from(qTicket);
		return query.count();
	}
	@Override
	public Long countSearch(SearchTicketForm form) {
		final QTicket qTicket = QTicket.ticket;
		final JPAQuery query = prepareQuery(form, qTicket);
		return query.count();
	}
	@Override
	public List<Ticket> searchUnassigned(SearchTicketForm form, String[] group,
			Integer region, String[] status, Integer iDisplayStart,
			Integer iDisplayLength) {
		final QTicket qTicket = QTicket.ticket;
		final JPAQuery query = prepareQueryUnassigned(form, group, region, status, qTicket);
		query.restrict(new QueryModifiers(iDisplayLength.longValue(), iDisplayStart.longValue()));
	    return query.list(qTicket);
	}
	@Override
	public Long countAllUnassigned(String[] group, Integer region,
			String[] status) {
		QTicket qTicket = QTicket.ticket;
		QStatus qStatus= QStatus.status;
		JPAQuery query = new JPAQuery(em);
		if(status!=null){
	        if(region!=null){
	        	query.from(qTicket).join(qTicket.status,qStatus).fetch().where(qTicket.groupId.in(group).and(qStatus.id.in(status)).and(qTicket.regionId.eq(region).and(qTicket.assigneeId.isNull())));
	        }else{
	        	query.from(qTicket).join(qTicket.status,qStatus).fetch().where(qTicket.groupId.in(group).and(qStatus.id.in(status).and(qTicket.assigneeId.isNull())));
	        }
			
		}else{
			if(region!=null){
				query.from(qTicket).where(qTicket.groupId.in(group).and(qTicket.regionId.eq(region).and(qTicket.assigneeId.isNull())));
			}else{
				query.from(qTicket).where(qTicket.groupId.in(group).and(qTicket.assigneeId.isNull())  );
			}
			
		}
	    return query.count();
	}
	@Override
	public Long countSearchUnassigned(SearchTicketForm form, String[] group,
			Integer region, String[] status) {
		final QTicket qTicket = QTicket.ticket;
		final JPAQuery query =prepareQueryUnassigned(form, group, region, status, qTicket);
		return query.count();
	}
	@Override
	public Long countPriorityByGroup(String gId,String priorityId) {
		JPAQuery query = new JPAQuery(em);
		 QTicketAssignment qTicketAssignment = QTicketAssignment.ticketAssignment;
		  QTicket qTicket = QTicket.ticket;
		 QGroup qGroup =QGroup.group;
		 QPriority qPriority = QPriority.priority;
		 query.from(qTicketAssignment)
		 .leftJoin(qTicketAssignment.ticket,qTicket).fetch()
		 .leftJoin(qTicket.priority,qPriority).fetch()
		 .leftJoin(qTicketAssignment.group, qGroup).fetch()
		 .where(qTicketAssignment.group.id.eq(gId).and(qPriority.id.eq(priorityId)));
		return query.count();
	}
	

	



}
