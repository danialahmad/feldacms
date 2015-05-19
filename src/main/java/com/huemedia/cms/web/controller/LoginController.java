package com.huemedia.cms.web.controller;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.beans.propertyeditors.CustomNumberEditor;
import org.springframework.context.MessageSource;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.support.ByteArrayMultipartFileEditor;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.huemedia.cms.model.entity.Plan;
import com.huemedia.cms.model.entity.State;
import com.huemedia.cms.security.service.UserDetail;
import com.huemedia.cms.service.CodeService;
import com.huemedia.cms.service.UserRegistrationService;
import com.huemedia.cms.service.UserService;
import com.huemedia.cms.web.form.Response;
import com.huemedia.cms.web.form.UserForm;

@Controller
public class LoginController {
	
	@Autowired
	UserService userService;
	@Autowired
	UserRegistrationService userRegistrationService;
	@Autowired
	CodeService codeService;

	@Autowired
    MessageSource messageSource;
	
	/** Login form */
	@RequestMapping("login")
	public ModelAndView loginPage(Locale locale,HttpServletRequest req){
		final SecurityContext securityContext = SecurityContextHolder.getContext();
		final ModelAndView mav = new ModelAndView();
		if(securityContext!=null && securityContext.getAuthentication()!=null){
			if(securityContext.getAuthentication().getPrincipal() instanceof UserDetail){
				return new ModelAndView(new RedirectView("/", true));
			}
		}
		if(req.getParameter("errorMessage")!=null){
		String s=messageSource.getMessage(req.getParameter("errorMessage"), req.getParameterValues("errorMessage"), locale);
		mav.addObject("errorMessage",s);
		}
		return mav;
	}
	
	/** Login form with error. */
    @RequestMapping("/login-error")
    public String loginError(final Model model) {
        model.addAttribute("loginError", true);
        return "login";
    }
    
	@RequestMapping(value = "/registration", method = RequestMethod.GET)
	public ModelAndView register(Locale locale) {
		final ModelAndView mav = new ModelAndView("registration");
		mav.addObject("countryList", codeService.getCountryList());
		mav.addObject("regionList", codeService.getRegionList());
		mav.addObject("personCategoryList", codeService.getPersonCategoryList());
		mav.addObject("relationList", codeService.getRelationList());
		mav.addObject("userForm", new UserForm());
		return mav;
	}
	@RequestMapping(value = "/page/registration", method = RequestMethod.GET)
	public ModelAndView registerP(Locale locale) {
		final ModelAndView mav = new ModelAndView("registrationP");
		mav.addObject("countryList", codeService.getCountryList());
		mav.addObject("regionList", codeService.getRegionList());
		mav.addObject("personCategoryList", codeService.getPersonCategoryList());
		mav.addObject("relationList", codeService.getRelationList());
		mav.addObject("userForm", new UserForm());
		return mav;
	}
	@RequestMapping(value ="/registration/save",method = RequestMethod.POST)
	public @ResponseBody String doRegister(final Locale locale, @ModelAttribute("userForm") UserForm userForm,BindingResult result) throws ParseException{
		Response r=userService.registerNewUser(userForm, false);
		String msg=r.getMessage();
		if(r.isSuccess()){
			msg="Success";
		}
		return msg;
	}
	@RequestMapping(value = "/registration/activate/{username}/{activationId}", method = RequestMethod.GET)
	public ModelAndView activate(Locale locale,@PathVariable("username")String username,@PathVariable("activationId")String activationId) {
		final ModelAndView mav = new ModelAndView("redirect:/success?alert=alert.success.activate&param=login");
		userRegistrationService.activateUser(username, activationId);
		return mav;
	}
	
	@RequestMapping("/list/state/{countryId}")
	public @ResponseBody String[][] getStateList(@PathVariable("countryId") Integer countryId){
		List<String[]> list = new ArrayList<String[]>();
		
		List<State> listState = codeService.getStateList(countryId);
		for(State obj:listState){
			String id = obj.getId().toString();
			String label = obj.getName();
			String[] row = {id, label};
			list.add(row);
		}
		return list.toArray(new String[list.size()][2]);
	}
	@RequestMapping("/list/plan/{regionId}")
	public @ResponseBody String[][] getPlanList(@PathVariable("regionId") Integer regionId){
		List<String[]> list = new ArrayList<String[]>();
		
		List<Plan> listPlan = codeService.getPlanRepositoryList(regionId);
		for(Plan obj:listPlan){
			String id = obj.getId().toString();
			String label = obj.getName();
			String[] row = {id, label};
			list.add(row);
		}
		return list.toArray(new String[list.size()][2]);
	}
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		final SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
		dateFormat.setLenient(false);
		binder.registerCustomEditor(Date.class, new CustomDateEditor(
				dateFormat, true));
		binder.registerCustomEditor(byte[].class,
				new ByteArrayMultipartFileEditor());	
        DecimalFormat formatter = new DecimalFormat("#,###.00");
        binder.registerCustomEditor(Double.class,
        		new CustomNumberEditor(Double.class, formatter, true));
	}
}
