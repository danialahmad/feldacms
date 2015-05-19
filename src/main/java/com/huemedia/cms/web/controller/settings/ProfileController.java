package com.huemedia.cms.web.controller.settings;

import java.text.ParseException;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.huemedia.cms.service.CodeService;
import com.huemedia.cms.service.ProfileService;
import com.huemedia.cms.web.form.Response;
import com.huemedia.cms.web.form.UserForm;

@Controller
public class ProfileController {
	@Autowired
	CodeService codeService;
	@Autowired
	ProfileService profileService;
	
	@RequestMapping(value = "/settings/profile", method = RequestMethod.GET)
	public ModelAndView editUser(Locale locale) {
		final ModelAndView mav = new ModelAndView("settings/profile");
		String username= SecurityContextHolder.getContext().getAuthentication().getName();
		UserForm userForm =new UserForm();
		userForm=profileService.findProfileByUsername(username);
		mav.addObject("countryList", codeService.getCountryList());
		if(userForm.getCountry()!=null){
			if(userForm.getCountry().getId()!=null){
				mav.addObject("stateList", codeService.getStateList(userForm.getCountry().getId()));
			}
		}
		if(userForm.getRegion()!=null){
			if(userForm.getRegion().getId()!=null){
				mav.addObject("planList", codeService.getPlanRepositoryList(userForm.getRegion().getId()));
			}
		}
		
		
		mav.addObject("roleList", codeService.getRoleList());
		mav.addObject("groupList", codeService.getGroupList());
		mav.addObject("regionList", codeService.getRegionList());
		mav.addObject("userForm", userForm);
		mav.addObject("url", "/settings/profile/update");
		return mav;
	}
	@RequestMapping(value ="/settings/profile/update",method = RequestMethod.POST)
	public @ResponseBody String doUpdate(final Locale locale, @ModelAttribute("userForm") UserForm userForm,BindingResult result) throws ParseException{
		Response r=profileService.updateProfile(userForm);
		String msg=r.getMessage();
		if(r.isSuccess()){
			msg="Success";
		}
		return msg;
	}
}
