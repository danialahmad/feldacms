package com.huemedia.cms.model.dao.jpa;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;

import com.huemedia.cms.model.dao.StatusDAO;
import com.huemedia.cms.model.entity.QPriority;
import com.huemedia.cms.model.entity.QStatus;
import com.huemedia.cms.model.entity.Status;
import com.huemedia.cms.web.form.MasterForm;
import com.mysema.query.BooleanBuilder;
import com.mysema.query.jpa.impl.JPAQuery;
import com.mysema.query.types.EntityPath;
import com.mysema.query.types.expr.BooleanExpression;
@Repository
public class JpaStatusDAO extends JpaMasterDAO<Status> implements StatusDAO {

	@Override
	EntityPath<?> path() {
		// TODO Auto-generated method stub
		return QStatus.status;
	}

	@Override
	JPAQuery prepareQuerySearch(Object form) {
		QStatus qStatus = (QStatus) path();
		JPAQuery query = new JPAQuery(em);
		query.from(qStatus);
		MasterForm masterForm=(MasterForm) form;
		final BooleanBuilder builder = new BooleanBuilder();
		 if(StringUtils.isNotEmpty(masterForm.getName())){
				final BooleanExpression containsName =qStatus.name.like("%"+masterForm.getName()+"%");
				builder.and(containsName);
			}
		query.where(builder).orderBy(qStatus.rank.asc());
		return query;
	}

	

}
