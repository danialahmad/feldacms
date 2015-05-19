package com.huemedia.cms.web.controller.settings;

import java.text.ParseException;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.huemedia.cms.service.CodeService;
import com.huemedia.cms.service.UserService;
import com.huemedia.cms.web.form.Response;
import com.huemedia.cms.web.form.UserForm;

@Controller
public class AccountController {
	@Autowired
	CodeService codeService;
	@Autowired
	UserService userService;
	
	@RequestMapping(value = "/settings/account", method = RequestMethod.GET)
	public ModelAndView editUser(Locale locale) {
		final ModelAndView mav = new ModelAndView("settings/account");
		String username= SecurityContextHolder.getContext().getAuthentication().getName();
		UserForm userForm =new UserForm();
		userForm=userService.findUserByUsername(username);
		mav.addObject("roleList", codeService.getRoleList());
		mav.addObject("userForm", userForm);
		mav.addObject("url", "/settings/account/update");
		return mav;
	}
	
	@RequestMapping(value ="/settings/account/update",method = RequestMethod.POST)
	public @ResponseBody Response doUpdate(final Locale locale, @ModelAttribute("userForm") UserForm userForm,BindingResult result) throws ParseException{
		Response r=userService.changePassword(userForm);
		String msg=r.getMessage();
		if(r.isSuccess()){
			msg="Success";
			r.setMessage("Success");
		}
		return r;
	}
}
