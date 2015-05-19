package com.huemedia.cms.security.service;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

@Service
public class SecurityServiceImpl implements SecurityService {

//	@Autowired private UserRepository userRepository;
	
	public SecurityServiceImpl(){
		
	}
	
	@Override
	public String getUsername(){
		String username = "";
		if(SecurityContextHolder.getContext().getAuthentication()!=null){
			username = SecurityContextHolder.getContext().getAuthentication().getName();
		} else {
			username = "anonymousUser";
		}	
		
		return username;
	}

	
	/**
	@Override
	public User getUser(){
		String username = "";
		if(SecurityContextHolder.getContext().getAuthentication()!=null){
			username = SecurityContextHolder.getContext().getAuthentication().getName();
			return userRepository.findOne(username);
		} else {
			username = "anonymousUser";
		}	
		return null;
	}
	**/
}
