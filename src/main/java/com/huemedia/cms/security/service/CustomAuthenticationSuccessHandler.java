package com.huemedia.cms.security.service;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.transaction.annotation.Transactional;

import com.huemedia.cms.model.repository.UserRepository;

//import com.utusan.jobhouse.bean.NavMenuAop;
//import com.utusan.jobhouse.model.repository.JobseekerRepository;

public class CustomAuthenticationSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

	@Autowired
	UserRepository userRepository;

	public CustomAuthenticationSuccessHandler(){
	}
	
    @Override
    @Transactional
    public void onAuthenticationSuccess(final HttpServletRequest request, final HttpServletResponse response,
            final Authentication authentication) throws ServletException, IOException {
    	final UserDetail userDetail = (UserDetail) authentication.getPrincipal();
    	userRepository.updateLastLoginAndLastIp(userDetail.getLastLogin(), request.getRemoteAddr(), userDetail.getId());
    	super.onAuthenticationSuccess(request, response, authentication);
    }

}
