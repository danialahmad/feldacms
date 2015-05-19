package com.huemedia.cms.model.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.huemedia.cms.model.entity.KnowledgeCategory;

public interface KnowledgeCategoryRepository extends
PagingAndSortingRepository<KnowledgeCategory, Integer> {

	@Query(value="select c from KnowledgeCategory c join fetch c.knowledges k where k.staffOnly=?1 and k.approved=?2",
			countQuery="select count(c.id) from KnowledgeCategory c join c.knowledges k where k.staffOnly=?1 and k.approved=?2")
	Page<KnowledgeCategory> findAllCategories(Boolean staffOnly,Boolean approved,Pageable pageable);
}
