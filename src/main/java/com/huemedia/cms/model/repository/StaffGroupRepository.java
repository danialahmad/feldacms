package com.huemedia.cms.model.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.huemedia.cms.model.entity.Group;
import com.huemedia.cms.model.entity.Profile;
import com.huemedia.cms.model.entity.StaffGroup;

public interface StaffGroupRepository extends
		CrudRepository<StaffGroup, Integer> {

	List<StaffGroup> findByProfile(Profile profile);
	List<StaffGroup> findByProfileAndGroup(Profile profile,Group group);
	List<StaffGroup> findByGroup(Group group);
}
