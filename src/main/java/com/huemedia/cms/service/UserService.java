package com.huemedia.cms.service;

import java.util.List;

import com.huemedia.cms.model.entity.Profile;
import com.huemedia.cms.model.entity.StaffGroup;
import com.huemedia.cms.model.entity.User;
import com.huemedia.cms.model.entity.UserRole;
import com.huemedia.cms.web.controller.administration.datatables.UserResult;
import com.huemedia.cms.web.form.Response;
import com.huemedia.cms.web.form.UserForm;

public interface UserService {

	User getByUsername(String username);
	List<UserRole> getUserRoles(String username);
	List<StaffGroup> getStaffGroups(Profile p);
	
	UserForm findUserByUsername(String username);
	Response registerNewUser(UserForm userForm,boolean admin);
	Response updateUser(UserForm userForm);
	
	Response changePassword(UserForm userForm);
	boolean resetPassword(String username);
	
	
	Long countAll();
	Long countSearch(UserForm form);
	List<UserResult> getUsers(UserForm form, Integer iDisplayStart, Integer iDisplayLength);
	
}
