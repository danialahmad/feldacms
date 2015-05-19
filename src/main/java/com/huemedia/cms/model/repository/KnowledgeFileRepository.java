package com.huemedia.cms.model.repository;

import org.springframework.data.repository.CrudRepository;

import com.huemedia.cms.model.entity.KnowledgeFile;

public interface KnowledgeFileRepository extends
		CrudRepository<KnowledgeFile, Integer> {

}
