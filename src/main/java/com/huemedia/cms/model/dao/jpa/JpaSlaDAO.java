package com.huemedia.cms.model.dao.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.huemedia.cms.model.dao.SlaDAO;
import com.huemedia.cms.model.entity.QSla;
import com.huemedia.cms.model.entity.QTicket;
import com.huemedia.cms.model.entity.Sla;
import com.huemedia.cms.web.form.SlaForm;
import com.mysema.query.BooleanBuilder;
import com.mysema.query.QueryModifiers;
import com.mysema.query.jpa.impl.JPAQuery;

@Repository
public class JpaSlaDAO implements SlaDAO{

	@PersistenceContext
	private EntityManager em;
	
	@Override
	public Long countAll() {
		JPAQuery query = new JPAQuery(em);
		QSla qSla = QSla.sla;
		query.from(qSla);
		return query.count();
	}

	@Override
	public Long countSearch(SlaForm slaForm) {
		final QSla qSla = QSla.sla;
		final JPAQuery query = prepareQuery(slaForm,qSla);
		return query.count();
	}

	@Override
	public List<Sla> search(SlaForm slaForm,Integer iDisplayStart,
			Integer iDisplayLength) {
		final QSla qSla = QSla.sla;
		final JPAQuery query = prepareQuery(slaForm,qSla);
		query.restrict(new QueryModifiers(iDisplayLength.longValue(), iDisplayStart.longValue()));
	    return query.list(qSla);
	}
	
	private JPAQuery prepareQuery(SlaForm slaForm, QSla qSla){
		JPAQuery query = new JPAQuery(em);
		query.from(qSla);
		 final BooleanBuilder builder = new BooleanBuilder();
		 query.where(builder).orderBy(qSla.createDate.desc());
		return query;
	}

}
