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

import com.huemedia.cms.service.StatusService;
import com.huemedia.cms.web.controller.administration.datatables.StatusResult;
import com.huemedia.cms.web.controller.datatables.DataTablesRequest;
import com.huemedia.cms.web.controller.datatables.DataTablesResponse;
import com.huemedia.cms.web.form.MasterForm;

@Controller
public class StatusController {
	@Autowired
	StatusService statusService;
	
	@RequestMapping(value = "/administration/md/status", method = RequestMethod.GET)
	public ModelAndView user(Locale locale) {
		final ModelAndView mav = new ModelAndView("administration/md/status");

	    mav.addObject("masterForm", new MasterForm());
		return mav;
	}
	
	
	@RequestMapping(value = "/administration/md/status/add", method = RequestMethod.GET)
	public ModelAndView add(Locale locale) {
		final ModelAndView mav = new ModelAndView("administration/md/form");
		mav.addObject("title", "Status");
		mav.addObject("id", true);
		mav.addObject("name", true);
		mav.addObject("rank", true);
		mav.addObject("caseGroup", false);
		mav.addObject("address", false);
		mav.addObject("region", false);
		mav.addObject("action", "/administration/md/status/save");
		
	//	mav.addObject("countryList", codeService.getCountryList());
		mav.addObject("masterForm", new MasterForm());
		return mav;
	}
	@RequestMapping(value = "/administration/md/status/edit/{id}", method = RequestMethod.GET)
	public ModelAndView edit(Locale locale,@PathVariable("id") String id) {
		final ModelAndView mav = new ModelAndView("administration/md/form");
		mav.addObject("title", "Status");
		mav.addObject("id", true);
		mav.addObject("name", true);
		mav.addObject("rank", true);
		mav.addObject("caseGroup", false);
		mav.addObject("address", false);
		mav.addObject("region", false);
		mav.addObject("action", "/administration/md/status/save");
		MasterForm form= new MasterForm();
		form=statusService.findStatus(id);
		
	//	mav.addObject("countryList", codeService.getCountryList());
		mav.addObject("masterForm", form);
		return mav;
	}
	
	@RequestMapping(value ="/administration/md/status/save",method = RequestMethod.POST)
	public @ResponseBody String save(final Locale locale, @ModelAttribute("masterForm") MasterForm form,BindingResult result) throws ParseException{
		statusService.save(form);
		return "success";
	}
	
	@RequestMapping(value ="/administration/md/status/delete/{id}",method = RequestMethod.GET)
	public @ResponseBody String delete(@PathVariable("id") String id) throws ParseException{
		statusService.delete(id);
		return "success";
	}
	
	@RequestMapping("/administration/md/status/list")
	public @ResponseBody DataTablesResponse<StatusResult> getUserList(HttpServletRequest request, final DataTablesRequest dtRequest,@ModelAttribute("masterForm") final MasterForm form){
		DataTablesResponse<StatusResult> dtResponse = new DataTablesResponse<StatusResult>();
		final String echo = dtRequest.getsEcho();
		dtResponse.setsEcho(echo);
		final Long countAll = statusService.countAll();
		final Long countSearch = statusService.countSearch(form);
		try {
			dtResponse.setAaData(statusService.getStatuses(form, dtRequest.getiDisplayStart(), dtRequest.getiDisplayLength()));
			dtResponse.setiTotalDisplayRecords(countSearch.intValue());
			dtResponse.setiTotalRecords(countAll.intValue());
		} catch (final RuntimeException e){
			e.printStackTrace();
		}
		
		return dtResponse;
	}
}
