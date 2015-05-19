package com.huemedia.cms.web.controller.administration;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.beans.propertyeditors.CustomNumberEditor;
import org.springframework.stereotype.Controller;
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

import com.huemedia.cms.model.entity.User;
import com.huemedia.cms.service.CodeService;
import com.huemedia.cms.service.TicketService;
import com.huemedia.cms.service.UserService;
import com.huemedia.cms.web.controller.administration.datatables.UserResult;
import com.huemedia.cms.web.controller.complaint.datatables.TicketResult;
import com.huemedia.cms.web.controller.datatables.DataTablesRequest;
import com.huemedia.cms.web.controller.datatables.DataTablesResponse;
import com.huemedia.cms.web.form.Response;
import com.huemedia.cms.web.form.SearchTicketForm;
import com.huemedia.cms.web.form.UserForm;

@Controller
public class UserManagementController {
	@Autowired
	CodeService codeService;
	@Autowired
	UserService userService;
	@Autowired
	TicketService ticketService;
	
	
	@RequestMapping(value = "/user/profile/{username}", method = RequestMethod.GET)
	public ModelAndView profile(Locale locale,@PathVariable("username") String username) {
		final ModelAndView mav = new ModelAndView("administration/um/profile");
		UserForm userForm =new UserForm();
		userForm=userService.findUserByUsername(username);
		mav.addObject("roleList", codeService.getRoleList());
		mav.addObject("userForm", userForm);
		return mav;
	}
	
	
	
	
	@RequestMapping("/user/profile/list/complaints/{username}")
	public @ResponseBody DataTablesResponse<TicketResult> getMycomplaints(HttpServletRequest request,@PathVariable("username") String username, final DataTablesRequest dtRequest,@ModelAttribute("searchTicketForm") final SearchTicketForm searchTicketForm){
		DataTablesResponse<TicketResult> dtResponse = new DataTablesResponse<TicketResult>();
		final String echo = dtRequest.getsEcho();
		dtResponse.setsEcho(echo);
		User user=userService.getByUsername(username);
		final Long countAll = ticketService.countAllByProfileId(user.getProfile().getId());
		final Long countSearch = ticketService.countSearchByProfileId(searchTicketForm, user.getProfile().getId());
		try {
			dtResponse.setAaData(ticketService.getTicketsByProfileId(searchTicketForm,user.getProfile().getId(), dtRequest.getiDisplayStart(), dtRequest.getiDisplayLength()));
			dtResponse.setiTotalDisplayRecords(countSearch.intValue());
			dtResponse.setiTotalRecords(countAll.intValue());
		} catch (final RuntimeException e){
			e.printStackTrace();
		}
		
		return dtResponse;
	}
	
	
	
	
	@RequestMapping(value = "/administration/um/user", method = RequestMethod.GET)
	public ModelAndView user(Locale locale) {
		final ModelAndView mav = new ModelAndView("administration/um/user");
		mav.addObject("roleList", codeService.getRoleList());
		mav.addObject("userForm", new UserForm());
		return mav;
	}
	@RequestMapping(value = "/administration/um/user/add", method = RequestMethod.GET)
	public ModelAndView addUser(Locale locale) {
		final ModelAndView mav = new ModelAndView("administration/um/form");
		mav.addObject("countryList", codeService.getCountryList());
		mav.addObject("roleList", codeService.getRoleList());
		mav.addObject("groupList", codeService.getGroupList());
		mav.addObject("personCategoryList", codeService.getPersonCategoryList());
		mav.addObject("regionList", codeService.getRegionList());
		mav.addObject("userForm", new UserForm());
		mav.addObject("url", "/administration/um/user/save");
		return mav;
	}
	@RequestMapping(value ="/administration/um/user/save",method = RequestMethod.POST)
	public @ResponseBody String doRegister(final Locale locale, @ModelAttribute("userForm") UserForm userForm,BindingResult result) throws ParseException{
		Response r=userService.registerNewUser(userForm, true);
		String msg=r.getMessage();
		if(r.isSuccess()){
			msg="Success";
		}
		return msg;
	}
	
	@RequestMapping(value = "/administration/um/user/edit/{username}/", method = RequestMethod.GET)
	public ModelAndView editUser(Locale locale,@PathVariable("username") String username) {
		final ModelAndView mav = new ModelAndView("administration/um/form");
		UserForm userForm =new UserForm();
		userForm=userService.findUserByUsername(username);
		mav.addObject("countryList", codeService.getCountryList());
		mav.addObject("personCategoryList", codeService.getPersonCategoryList());
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
		mav.addObject("url", "/administration/um/user/update");
		return mav;
	}
	
	@RequestMapping(value ="/administration/um/user/update",method = RequestMethod.POST)
	public @ResponseBody String doUpdate(final Locale locale, @ModelAttribute("userForm") UserForm userForm,BindingResult result) throws ParseException{
		Response r=userService.updateUser(userForm);
		String msg=r.getMessage();
		if(r.isSuccess()){
			msg="Success";
		}
		return msg;
	}
	@RequestMapping(value ="/administration/um/user/reset/{username}",method = RequestMethod.GET)
	public @ResponseBody String resetPassword(final Locale locale,@PathVariable("username") String username) throws ParseException{
		String msg="error";
		if(userService.resetPassword(username)){
			msg="success";
		}
		return msg;
	}
	
	
	
	@RequestMapping("/administration/um/list")
	public @ResponseBody DataTablesResponse<UserResult> getUserList(HttpServletRequest request, final DataTablesRequest dtRequest,@ModelAttribute("userForm") final UserForm form){
		DataTablesResponse<UserResult> dtResponse = new DataTablesResponse<UserResult>();
		final String echo = dtRequest.getsEcho();
		dtResponse.setsEcho(echo);
		final Long countAll = userService.countAll();
		final Long countSearch = userService.countSearch(form);
		try {
			dtResponse.setAaData(userService.getUsers(form, dtRequest.getiDisplayStart(), dtRequest.getiDisplayLength()));
			dtResponse.setiTotalDisplayRecords(countSearch.intValue());
			dtResponse.setiTotalRecords(countAll.intValue());
		} catch (final RuntimeException e){
			e.printStackTrace();
		}
		
		return dtResponse;
	}
	@RequestMapping(value = "/administration/um/role", method = RequestMethod.GET)
	public ModelAndView role(Locale locale) {
		final ModelAndView mav = new ModelAndView("administration/um/role");
		
		return mav;
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
