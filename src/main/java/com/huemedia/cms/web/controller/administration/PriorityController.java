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

import com.huemedia.cms.service.PriorityService;
import com.huemedia.cms.web.controller.administration.datatables.PriorityResult;
import com.huemedia.cms.web.controller.datatables.DataTablesRequest;
import com.huemedia.cms.web.controller.datatables.DataTablesResponse;
import com.huemedia.cms.web.form.MasterForm;

@Controller
public class PriorityController {
	@Autowired
	PriorityService priorityService;
	
	@RequestMapping(value = "/administration/md/priority", method = RequestMethod.GET)
	public ModelAndView user(Locale locale) {
		final ModelAndView mav = new ModelAndView("administration/md/priority");

	    mav.addObject("masterForm", new MasterForm());
		return mav;
	}
	
	@RequestMapping(value = "/administration/md/priority/add", method = RequestMethod.GET)
	public ModelAndView add(Locale locale) {
		final ModelAndView mav = new ModelAndView("administration/md/form");
		mav.addObject("title", "Priority");
		mav.addObject("id", true);
		mav.addObject("name", true);
		mav.addObject("rank", true);
		mav.addObject("caseGroup", false);
		mav.addObject("address", false);
		mav.addObject("region", false);
		mav.addObject("action", "/administration/md/priority/save");
		
	//	mav.addObject("countryList", codeService.getCountryList());
		mav.addObject("masterForm", new MasterForm());
		return mav;
	}
	@RequestMapping(value = "/administration/md/priority/edit/{id}", method = RequestMethod.GET)
	public ModelAndView edit(Locale locale,@PathVariable("id") String id) {
		final ModelAndView mav = new ModelAndView("administration/md/form");
		mav.addObject("title", "Priority");
		mav.addObject("id", true);
		mav.addObject("name", true);
		mav.addObject("rank", true);
		mav.addObject("caseGroup", false);
		mav.addObject("address", false);
		mav.addObject("region", false);
		mav.addObject("action", "/administration/md/priority/save");
		
		MasterForm form= new MasterForm();
		form=priorityService.findPriority(id);
		
	//	mav.addObject("countryList", codeService.getCountryList());
		mav.addObject("masterForm", form);
		return mav;
	}
	
	@RequestMapping(value ="/administration/md/priority/save",method = RequestMethod.POST)
	public @ResponseBody String save(final Locale locale, @ModelAttribute("masterForm") MasterForm form,BindingResult result) throws ParseException{
		priorityService.save(form);
		return "success";
	}
	
	@RequestMapping(value ="/administration/md/priority/delete/{id}",method = RequestMethod.GET)
	public @ResponseBody String delete(@PathVariable("id") String id) throws ParseException{
		priorityService.delete(id);
		return "success";
	}
	
	@RequestMapping("/administration/md/priority/list")
	public @ResponseBody DataTablesResponse<PriorityResult> getUserList(HttpServletRequest request, final DataTablesRequest dtRequest,@ModelAttribute("masterForm") final MasterForm form){
		DataTablesResponse<PriorityResult> dtResponse = new DataTablesResponse<PriorityResult>();
		final String echo = dtRequest.getsEcho();
		dtResponse.setsEcho(echo);
		final Long countAll = priorityService.countAll();
		final Long countSearch = priorityService.countSearch(form);
		try {
			dtResponse.setAaData(priorityService.getTicketGroups(form, dtRequest.getiDisplayStart(), dtRequest.getiDisplayLength()));
			dtResponse.setiTotalDisplayRecords(countSearch.intValue());
			dtResponse.setiTotalRecords(countAll.intValue());
		} catch (final RuntimeException e){
			e.printStackTrace();
		}
		
		return dtResponse;
	}
}
