package com.huemedia.cms.model.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.huemedia.cms.model.entity.Profile;

public interface ProfileRepository extends CrudRepository<Profile, Integer> {


	@Query("select p from Profile p join p.staffGroups sg join sg.group g where g.id=?1")
	List<Profile> findByGroup(String groupId);
	
	@Query("select p from Profile p join p.staffGroups sg join sg.group g join p.region r where g.id=?1 and r.id=?2")
	List<Profile> findByGroupAndRegion(String groupId,Integer regionId);
	
	Profile findByIcNo(String icNo);
}
