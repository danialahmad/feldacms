package com.huemedia.cms.model.dao.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;

import com.huemedia.cms.model.dao.UserDAO;
import com.huemedia.cms.model.entity.QProfile;
import com.huemedia.cms.model.entity.QUser;
import com.huemedia.cms.model.entity.QUserRole;
import com.huemedia.cms.model.entity.User;
import com.huemedia.cms.web.form.UserForm;
import com.mysema.query.BooleanBuilder;
import com.mysema.query.QueryModifiers;
import com.mysema.query.jpa.impl.JPAQuery;
import com.mysema.query.types.expr.BooleanExpression;

@Repository
public class JpaUserDAO implements UserDAO{

	@PersistenceContext
	private EntityManager em;
	
	@Override
	public Long countAll() {
		final QUser qUser = QUser.user;
		JPAQuery query = new JPAQuery(em);
		query.from(qUser);
		return query.count();
	}

	@Override
	public Long countSearch(UserForm form) {
		final QUser qUser = QUser.user;
		final JPAQuery query = prepareQuerySearch(form, qUser);
		return query.count();
	}

	@Override
	public List<User> search(UserForm form, Integer iDisplayStart,
			Integer iDisplayLength) {
		final QUser qUser = QUser.user;
		final JPAQuery query = prepareQuerySearch(form, qUser);
		query.restrict(new QueryModifiers(iDisplayLength.longValue(), iDisplayStart.longValue()));
		return query.list(qUser);
	}
	
	private JPAQuery prepareQuerySearch(UserForm form,QUser qUser){
		QProfile qProfile=QProfile.profile;
		QUserRole qUserRole= QUserRole.userRole;
		JPAQuery query = new JPAQuery(em);
	    query.distinct().from(qUser).leftJoin(qUser.profile,qProfile).fetch().leftJoin(qUser.userRoles,qUserRole).fetch();
	    
        final BooleanBuilder builder = new BooleanBuilder();
        if(StringUtils.isNotEmpty(form.getUsername())){
			final BooleanExpression containsName =qUser.username.like("%"+form.getUsername()+"%");
			builder.and(containsName);
		}
        if(StringUtils.isNotEmpty(form.getName())){
			final BooleanExpression containsName =qProfile.name.like("%"+form.getName()+"%");
			builder.and(containsName);
		}
        if(StringUtils.isNotEmpty(form.getEmail())){
			final BooleanExpression containsName =qProfile.email.like("%"+form.getEmail()+"%");
			builder.and(containsName);
		}
        if(StringUtils.isNotEmpty(form.getCompany())){
			final BooleanExpression containsName =qProfile.company.like("%"+form.getCompany()+"%");
			builder.and(containsName);
		}
        if(form.getRole()!=null){
        	if(StringUtils.isNotEmpty(form.getRole().getId())){
        	final BooleanExpression containsName =qUserRole.roleId.eq(form.getRole().getId());
        	builder.and(containsName);
        	}
        }
		query.where(builder).orderBy(qUser.createDate.desc());
		return query;
	}

	@Override
	public User findByUsername(String username) {
		QUser qUser = QUser.user;
		QProfile qProfile=QProfile.profile;
		JPAQuery query = new JPAQuery(em);
	    query.from(qUser).leftJoin(qUser.profile,qProfile).fetch().where(qUser.username.eq(username));
		return query.singleResult(qUser);
	}

}
