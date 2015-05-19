package com.huemedia.cms.model.dao;

import java.util.List;

import com.huemedia.cms.model.entity.User;
import com.huemedia.cms.web.form.UserForm;

public interface UserDAO {
	Long countAll();
	Long countSearch(UserForm form);
	List<User> search(UserForm form,Integer iDisplayStart, Integer iDisplayLength);
	
	User findByUsername(String username);
}
