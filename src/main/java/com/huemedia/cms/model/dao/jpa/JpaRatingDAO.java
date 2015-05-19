package com.huemedia.cms.model.dao.jpa;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;

import com.huemedia.cms.model.dao.RatingDAO;
import com.huemedia.cms.model.entity.QRating;
import com.huemedia.cms.model.entity.Rating;
import com.huemedia.cms.web.form.MasterForm;
import com.mysema.query.BooleanBuilder;
import com.mysema.query.jpa.impl.JPAQuery;
import com.mysema.query.types.EntityPath;
import com.mysema.query.types.expr.BooleanExpression;
@Repository
public class JpaRatingDAO extends JpaMasterDAO<Rating> implements RatingDAO {

	@Override
	EntityPath<?> path() {
		// TODO Auto-generated method stub
		return QRating.rating;
	}

	@Override
	JPAQuery prepareQuerySearch(Object form) {
		QRating qRating = (QRating) path();
		JPAQuery query = new JPAQuery(em);
		query.from(qRating);
		MasterForm masterForm=(MasterForm) form;
		final BooleanBuilder builder = new BooleanBuilder();
		 if(StringUtils.isNotEmpty(masterForm.getName())){
				final BooleanExpression containsName =qRating.name.like("%"+masterForm.getName()+"%");
				builder.and(containsName);
			}
		query.where(builder).orderBy(qRating.rank.asc());
		return query;
	}

	

}
