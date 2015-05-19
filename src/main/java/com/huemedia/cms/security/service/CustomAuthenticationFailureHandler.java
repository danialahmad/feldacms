package com.huemedia.cms.security.service;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.ExceptionMappingAuthenticationFailureHandler;
import org.springframework.security.web.util.UrlUtils;
import org.springframework.util.Assert;

public class CustomAuthenticationFailureHandler extends ExceptionMappingAuthenticationFailureHandler {
    private final Map<String, String> failureUrlMap = new HashMap<String, String>();

    @Override
    public void onAuthenticationFailure(final HttpServletRequest request, final HttpServletResponse response,
            final AuthenticationException exception) throws IOException, ServletException {
    	String className = exception.getClass().getName();
    	if(StringUtils.equals(className, "org.springframework.security.authentication.AuthenticationServiceException")){
    		if(StringUtils.equals(exception.getMessage(), "not.authorized")){
    			className = "org.springframework.security.authentication.AuthenticationCredentialsNotFoundException";
    		}
    	}
        final String url = failureUrlMap.get(className);
        if (url != null) {
            getRedirectStrategy().sendRedirect(request, response, url);
        } else {
            super.onAuthenticationFailure(request, response, exception);
        }
    }

    /**
     * Sets the map of exception types (by name) to URLs.
     *
     * @param failureUrlMap the map keyed by the fully-qualified name of the exception class, with the corresponding
     * failure URL as the value.
     *
     * @throws IllegalArgumentException if the entries are not Strings or the URL is not valid.
     */
    @Override
	public void setExceptionMappings(final Map<?,?> failureUrlMap) {
        this.failureUrlMap.clear();
        for (final Map.Entry<?,?> entry : failureUrlMap.entrySet()) {
            final Object exception = entry.getKey();
            final Object url = entry.getValue();
            Assert.isInstanceOf(String.class, exception, "Exception key must be a String (the exception classname).");
            Assert.isInstanceOf(String.class, url, "URL must be a String");
            Assert.isTrue(UrlUtils.isValidRedirectUrl((String)url), "Not a valid redirect URL: " + url);
            this.failureUrlMap.put((String)exception, (String)url);
        }
    }
}
