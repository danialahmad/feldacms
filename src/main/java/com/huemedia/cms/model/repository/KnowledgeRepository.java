package com.huemedia.cms.model.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.huemedia.cms.model.entity.Knowledge;

public interface KnowledgeRepository extends CrudRepository<Knowledge, Integer> {
	
	@Query("select distinct k from Knowledge k join k.knowledgeCategory c where k.staffOnly=?1 and k.approved=?2")
	List<Knowledge> findByStaffOnlyAndApproved(Boolean staffOnly,Boolean approved);

}
