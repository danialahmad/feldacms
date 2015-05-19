package com.huemedia.cms.service;

import com.huemedia.cms.model.entity.User;

public interface UserRegistrationService {
	
	String encryptPassword(String password);
	String generateActivationID();
	void sendActivationID(User user);
	void activateUser(String username, String id);
	boolean isUserExist(String username,String icNo);
	
}
