package com.huemedia.cms.model.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.huemedia.cms.model.entity.User;
import com.huemedia.cms.model.entity.UserRole;

public interface UserRoleRepository extends CrudRepository<UserRole, String> {
    
	
	List<UserRole> findByUser(User user);
	List<UserRole> findByRoleId(String roleId);
}
