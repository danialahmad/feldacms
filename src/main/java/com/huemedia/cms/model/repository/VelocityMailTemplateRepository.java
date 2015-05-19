package com.huemedia.cms.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.huemedia.cms.model.entity.RefVelocityMailTemplate;

public interface VelocityMailTemplateRepository extends JpaRepository<RefVelocityMailTemplate, String>  {

}
