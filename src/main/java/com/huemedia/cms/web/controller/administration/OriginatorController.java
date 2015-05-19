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

import com.huemedia.cms.service.OriginatorService;
import com.huemedia.cms.web.controller.administration.datatables.OriginatorResult;
import com.huemedia.cms.web.controller.datatables.DataTablesRequest;
import com.huemedia.cms.web.controller.datatables.DataTablesResponse;
import com.huemedia.cms.web.form.MasterForm;

@Controller
public class OriginatorController {
	@Autowired
	OriginatorService originatorService;
	
	@RequestMapping(value = "/administration/md/originator", method = RequestMethod.GET)
	public ModelAndView user(Locale locale) {
		final ModelAndView mav = new ModelAndView("administration/md/originator");

	    mav.addObject("masterForm", new MasterForm());
		return mav;
	}
	
	@RequestMapping(value = "/administration/md/originator/add", method = RequestMethod.GET)
	public ModelAndView add(Locale locale) {
		final ModelAndView mav = new ModelAndView("administration/md/form");
		mav.addObject("title", "Originator");
		mav.addObject("id", false);
		mav.addObject("name", true);
		mav.addObject("rank", false);
		mav.addObject("caseGroup", false);
		mav.addObject("address", false);
		mav.addObject("region", false);
		mav.addObject("action", "/administration/md/originator/save");
		
	//	mav.addObject("countryList", codeService.getCountryList());
		mav.addObject("masterForm", new MasterForm());
		return mav;
	}
	@RequestMapping(value = "/administration/md/originator/edit/{id}", method = RequestMethod.GET)
	public ModelAndView edit(Locale locale,@PathVariable("id") Integer id) {
		final ModelAndView mav = new ModelAndView("administration/md/form");
		mav.addObject("title", "Originator");
		mav.addObject("id", false);
		mav.addObject("name", true);
		mav.addObject("rank", false);
		mav.addObject("caseGroup", false);
		mav.addObject("address", false);
		mav.addObject("region", false);
		mav.addObject("action", "/administration/md/originator/save");
		MasterForm form= new MasterForm();
		form=originatorService.findOriginator(id);
		
	//	mav.addObject("countryList", codeService.getCountryList());
		mav.addObject("masterForm", form);
		return mav;
	}
	
	@RequestMapping(value ="/administration/md/originator/save",method = RequestMethod.POST)
	public @ResponseBody String save(final Locale locale, @ModelAttribute("masterForm") MasterForm form,BindingResult result) throws ParseException{
		originatorService.save(form);
		return "success";
	}
	
	@RequestMapping(value ="/administration/md/originator/delete/{id}",method = RequestMethod.GET)
	public @ResponseBody String delete(@PathVariable("id") Integer id) throws ParseException{
		originatorService.delete(id);
		return "success";
	}
	
	@RequestMapping("/administration/md/originator/list")
	public @ResponseBody DataTablesResponse<OriginatorResult> getUserList(HttpServletRequest request, final DataTablesRequest dtRequest,@ModelAttribute("masterForm") final MasterForm form){
		DataTablesResponse<OriginatorResult> dtResponse = new DataTablesResponse<OriginatorResult>();
		final String echo = dtRequest.getsEcho();
		dtResponse.setsEcho(echo);
		final Long countAll = originatorService.countAll();
		final Long countSearch = originatorService.countSearch(form);
		try {
			dtResponse.setAaData(originatorService.getOriginators(form, dtRequest.getiDisplayStart(), dtRequest.getiDisplayLength()));
			dtResponse.setiTotalDisplayRecords(countSearch.intValue());
			dtResponse.setiTotalRecords(countAll.intValue());
		} catch (final RuntimeException e){
			e.printStackTrace();
		}
		
		return dtResponse;
	}
}
