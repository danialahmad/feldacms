package com.huemedia.cms.model.dao.jpa;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;

import com.huemedia.cms.model.dao.KnowledgeDAO;
import com.huemedia.cms.model.entity.Knowledge;
import com.huemedia.cms.model.entity.QKnowledge;
import com.huemedia.cms.model.entity.QKnowledgeCategory;
import com.huemedia.cms.web.form.KnowledgeForm;
import com.mysema.query.BooleanBuilder;
import com.mysema.query.QueryModifiers;
import com.mysema.query.jpa.impl.JPAQuery;
import com.mysema.query.types.EntityPath;
import com.mysema.query.types.expr.BooleanExpression;

@Repository
public class JpaKnowledgeDAO extends JpaMasterDAO<Knowledge> implements
KnowledgeDAO{

	@Override
	EntityPath<?> path() {
		// TODO Auto-generated method stub
		return QKnowledge.knowledge;
	}

	@Override
	JPAQuery prepareQuerySearch(Object form) {
		QKnowledge qKnowledge = (QKnowledge) path();
		QKnowledgeCategory qKnowledgeCategory=QKnowledgeCategory.knowledgeCategory;
		JPAQuery query = new JPAQuery(em);
		query.from(qKnowledge).join(qKnowledge.knowledgeCategory,qKnowledgeCategory).fetch();
		KnowledgeForm knowledgeForm=(KnowledgeForm) form;
		final BooleanBuilder builder = new BooleanBuilder();
		 if(StringUtils.isNotEmpty(knowledgeForm.getTitle())){
				final BooleanExpression containsName =qKnowledge.title.like("%"+knowledgeForm.getTitle()+"%");
				builder.and(containsName);
		 }
		 if(knowledgeForm.getKnowledgeCategory()!=null){
			 if(knowledgeForm.getKnowledgeCategory().getId()!=null){
				 final BooleanExpression containsName =qKnowledgeCategory.id.eq(knowledgeForm.getKnowledgeCategory().getId());
				 builder.and(containsName);
			 }
		 }
		 if(StringUtils.isNotEmpty(knowledgeForm.getStatus())){
				final BooleanExpression containsName =qKnowledge.status.eq(knowledgeForm.getStatus());
				builder.and(containsName);
		 }
		query.where(builder).orderBy(qKnowledge.createDate.desc());
		return query;
	}
	
	
	private JPAQuery prepareQuerySearchKeyword(String search){
		QKnowledge qKnowledge = (QKnowledge) path();
		QKnowledgeCategory qKnowledgeCategory=QKnowledgeCategory.knowledgeCategory;
		JPAQuery query = new JPAQuery(em);
		
		
		
		if(StringUtils.isNotEmpty(search)){
			query.from(qKnowledge).join(qKnowledge.knowledgeCategory,qKnowledgeCategory).fetch().where(qKnowledge.title.like("%"+search+"%")
					.or(qKnowledgeCategory.name.like("%"+search+"%").or(qKnowledge.description.like("%"+search+"%").or(qKnowledge.solution.like("%"+search+"%")))));
		}
		return query;
	}
	@Override
	public List<Knowledge> searchByKeyword(String search,Integer iDisplayStart,
			Integer iDisplayLength){
		QKnowledge qKnowledge =(QKnowledge) path();
		JPAQuery query = prepareQuerySearchKeyword(search);
		query.restrict(new QueryModifiers(iDisplayLength.longValue(), iDisplayStart.longValue()));
		return query.list(qKnowledge);
	}

	@Override
	public Long countSearchByKeyword(String search) {
		JPAQuery query = prepareQuerySearchKeyword(search);
		return query.count();
	}

	

}
