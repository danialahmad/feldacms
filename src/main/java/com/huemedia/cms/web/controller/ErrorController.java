package com.huemedia.cms.web.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ErrorController {

    /** Error page. */
    @RequestMapping("/error")
    public String error(final HttpServletRequest request, final Model model) {
        model.addAttribute("errorCode", request.getAttribute("javax.servlet.error.status_code"));
        Throwable throwable = (Throwable) request.getAttribute("javax.servlet.error.exception");
        final StringBuilder errorMessage = new StringBuilder();
        errorMessage.append("<ul>");
        final List<String> errList= new ArrayList<String>();
        while (throwable != null) {
            errorMessage.append("<li>").append(escapeTags(throwable.getMessage())).append("</li>");
            errList.add(throwable.getMessage());
            throwable = throwable.getCause();
           
        }
        errorMessage.append("</ul>");
      //  model.addAttribute("errorMessage", errorMessage.toString());
        model.addAttribute("errorMessage", getMessage(request.getAttribute("javax.servlet.error.status_code").toString()));
        model.addAttribute("errList", errList);
        return "error";
    }

    /** Substitute 'less than' and 'greater than' symbols by its HTML entities. */
    private String escapeTags(final String text) {
        if (text == null) {
            return null;
        }
        return text.replaceAll("<", "&lt;").replaceAll(">", "&gt;");
    }

    private String getMessage(String code){
    	String msg=null;
    	if(code.equals("400")){
    		msg="Bad Request";
    	}else if(code.equals("401")){
    		msg="Unauthorized Page";
    	}else if(code.equals("404")){
    		msg="Page Not Found";
    	}else if(code.equals("500")){
    		msg="Internal Server Error";
    	}else if(code.equals("503")){
    		msg="Service Unavailable";
    	}
    	return msg;
    }
}
