package com.huemedia.cms.security.service;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.SimpleUrlLogoutSuccessHandler;

//import com.utusan.jobhouse.bean.NavMenuAop;

public class CustomLogoutHandler extends SimpleUrlLogoutSuccessHandler {
	
	//@Autowired
	//private NavMenuAop navMenuAop;
	
	@Override
	public void onLogoutSuccess(final HttpServletRequest request, final HttpServletResponse response, final Authentication authentication)
            throws IOException, ServletException {
        //navMenuAop.setInitialize(false);
		super.handle(request, response, authentication);
    }

}
