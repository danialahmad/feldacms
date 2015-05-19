package com.huemedia.cms.web.controller.administration;

import java.text.ParseException;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.huemedia.cms.service.ActivityTypeService;
import com.huemedia.cms.web.controller.administration.datatables.ActivityTypeResult;
import com.huemedia.cms.web.controller.datatables.DataTablesRequest;
import com.huemedia.cms.web.controller.datatables.DataTablesResponse;
import com.huemedia.cms.web.form.MasterForm;

@Controller
public class ActivityTypeController {
	@Autowired
	ActivityTypeService activityTypeService;
	
	@RequestMapping(value = "/administration/md/activityType", method = RequestMethod.GET)
	public ModelAndView user(Locale locale) {
		final ModelAndView mav = new ModelAndView("administration/md/activityType");

	    mav.addObject("masterForm", new MasterForm());
		return mav;
	}
	
	
	@RequestMapping(value = "/administration/md/activityType/add", method = RequestMethod.GET)
	public ModelAndView add(Locale locale) {
		final ModelAndView mav = new ModelAndView("administration/md/form");
		mav.addObject("title", "Activity Type");
		mav.addObject("id", false);
		mav.addObject("name", true);
		mav.addObject("rank", false);
		mav.addObject("caseGroup", false);
		mav.addObject("address", false);
		mav.addObject("region", false);
		mav.addObject("action", "/administration/md/activityType/save");
		
	//	mav.addObject("countryList", codeService.getCountryList());
		mav.addObject("masterForm", new MasterForm());
		return mav;
	}
	@RequestMapping(value = "/administration/md/activityType/edit/{id}", method = RequestMethod.GET)
	public ModelAndView edit(Locale locale,@PathVariable("id") Integer id) {
		final ModelAndView mav = new ModelAndView("administration/md/form");
		mav.addObject("title", "Activity Type");
		mav.addObject("id", false);
		mav.addObject("name", true);
		mav.addObject("rank", false);
		mav.addObject("caseGroup", false);
		mav.addObject("address", false);
		mav.addObject("region", false);
		mav.addObject("action", "/administration/md/activityType/save");
		MasterForm form= new MasterForm();
		form=activityTypeService.findActivityType(id);
		
	//	mav.addObject("countryList", codeService.getCountryList());
		mav.addObject("masterForm", form);
		return mav;
	}
	
	@RequestMapping(value ="/administration/md/activityType/save",method = RequestMethod.POST)
	public @ResponseBody String save(final Locale locale, @ModelAttribute("masterForm") MasterForm form,BindingResult result) throws ParseException{
		activityTypeService.save(form);
		return "success";
	}
	
	@RequestMapping(value ="/administration/md/activityType/delete/{id}",method = RequestMethod.GET)
	public @ResponseBody String delete(@PathVariable("id") Integer id) throws ParseException{
		activityTypeService.delete(id);
		return "success";
	}
	
	
	@RequestMapping("/administration/md/activityType/list")
	public @ResponseBody DataTablesResponse<ActivityTypeResult> getUserList(HttpServletRequest request, final DataTablesRequest dtRequest,@ModelAttribute("masterForm") final MasterForm form){
		DataTablesResponse<ActivityTypeResult> dtResponse = new DataTablesResponse<ActivityTypeResult>();
		final String echo = dtRequest.getsEcho();
		dtResponse.setsEcho(echo);
		final Long countAll = activityTypeService.countAll();
		final Long countSearch = activityTypeService.countSearch(form);
		try {
			dtResponse.setAaData(activityTypeService.getActivityTypes(form, dtRequest.getiDisplayStart(), dtRequest.getiDisplayLength()));
			dtResponse.setiTotalDisplayRecords(countSearch.intValue());
			dtResponse.setiTotalRecords(countAll.intValue());
		} catch (final RuntimeException e){
			e.printStackTrace();
		}
		
		return dtResponse;
	}
}
