package com.huemedia.cms.model.repository;

import org.springframework.data.repository.CrudRepository;

import com.huemedia.cms.model.entity.Contact;

public interface ContactRepository extends
CrudRepository<Contact, Integer>{

}
