package com.huemedia.cms.model.dao.jpa;

import org.springframework.stereotype.Repository;

import com.huemedia.cms.model.dao.KnowledgeCategoryDAO;
import com.huemedia.cms.model.entity.KnowledgeCategory;
import com.huemedia.cms.model.entity.QKnowledge;
import com.huemedia.cms.model.entity.QKnowledgeCategory;
import com.mysema.query.jpa.impl.JPAQuery;
import com.mysema.query.types.EntityPath;

@Repository
public class JpaKnowledgeCategoryDAO extends JpaMasterDAO<KnowledgeCategory> implements
KnowledgeCategoryDAO{

	@Override
	EntityPath<?> path() {
		// TODO Auto-generated method stub
		return QKnowledgeCategory.knowledgeCategory;
	}

	@Override
	JPAQuery prepareQuerySearch(Object form) {
		QKnowledgeCategory qKnowledgeCategory=(QKnowledgeCategory) path();
		QKnowledge qKnowledge = QKnowledge.knowledge;
		JPAQuery query = new JPAQuery(em);
		query.from(qKnowledgeCategory).join(qKnowledgeCategory.knowledges,qKnowledge).fetch();
		return query;
	}

}
