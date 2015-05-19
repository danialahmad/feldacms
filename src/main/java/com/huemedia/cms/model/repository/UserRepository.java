package com.huemedia.cms.model.repository;

import java.util.Date;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.huemedia.cms.model.entity.Profile;
import com.huemedia.cms.model.entity.User;

public interface UserRepository extends CrudRepository<User, String> {
    
	User findByUsernameAndStatus(String username, String status);

	@Modifying
	@Query("update User u set u.status = ?1 where u.username = ?2 and u.activationId=?3")
	int updateStatus(String status,String username,String activationId);
	
	@Modifying
	@Query("update User u set u.lastLogin = ?1, u.lastIp=?2 where u.username = ?3")
	int updateLastLoginAndLastIp(Date lastLogin,String ip,String username);
	
	User findByProfile(Profile profile);
	
	@Query("select u from User u join fetch u.profile p where u.username=?1")
	User findProfileByUsername(String username);
	
	@Query("select u from User u join u.profile p where u.username=?1 or p.icNo=?2")
	User findByUsernameOrIcNo(String username,String icNo);
}
