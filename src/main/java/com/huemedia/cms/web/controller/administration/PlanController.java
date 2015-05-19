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

import com.huemedia.cms.service.CodeService;
import com.huemedia.cms.service.PlanService;
import com.huemedia.cms.web.controller.administration.datatables.PlanResult;
import com.huemedia.cms.web.controller.datatables.DataTablesRequest;
import com.huemedia.cms.web.controller.datatables.DataTablesResponse;
import com.huemedia.cms.web.form.MasterForm;

@Controller
public class PlanController {
	@Autowired
	PlanService planService;
	@Autowired
	CodeService codeService;
	
	@RequestMapping(value = "/administration/md/plan", method = RequestMethod.GET)
	public ModelAndView user(Locale locale) {
		final ModelAndView mav = new ModelAndView("administration/md/plan");
		mav.addObject("regionList", codeService.getRegionList());
	    mav.addObject("masterForm", new MasterForm());
		return mav;
	}
	
	@RequestMapping(value = "/administration/md/plan/add", method = RequestMethod.GET)
	public ModelAndView add(Locale locale) {
		final ModelAndView mav = new ModelAndView("administration/md/form");
		mav.addObject("title", "Plan");
		mav.addObject("id", false);
		mav.addObject("name", true);
		mav.addObject("rank", false);
		mav.addObject("caseGroup", false);
		mav.addObject("address", true);
		mav.addObject("region", true);
		mav.addObject("action", "/administration/md/plan/save");
		mav.addObject("regionList", codeService.getRegionList());
		mav.addObject("stateList", codeService.getStateList(458));
	//	mav.addObject("countryList", codeService.getCountryList());
		mav.addObject("masterForm", new MasterForm());
		return mav;
	}
	@RequestMapping(value = "/administration/md/plan/edit/{id}", method = RequestMethod.GET)
	public ModelAndView edit(Locale locale,@PathVariable("id") Integer id) {
		final ModelAndView mav = new ModelAndView("administration/md/form");
		mav.addObject("title", "Plan");
		mav.addObject("id", false);
		mav.addObject("name", true);
		mav.addObject("rank", false);
		mav.addObject("caseGroup", false);
		mav.addObject("address", true);
		mav.addObject("region", true);
		mav.addObject("action", "/administration/md/plan/save");
		MasterForm form= new MasterForm();
		form=planService.findPlan(id);
		mav.addObject("regionList", codeService.getRegionList());
		mav.addObject("stateList", codeService.getStateList(458));
	//	mav.addObject("countryList", codeService.getCountryList());
		mav.addObject("masterForm", form);
		return mav;
	}
	
	@RequestMapping(value ="/administration/md/plan/save",method = RequestMethod.POST)
	public @ResponseBody String save(final Locale locale, @ModelAttribute("masterForm") MasterForm form,BindingResult result) throws ParseException{
		planService.save(form);
		return "success";
	}
	
	@RequestMapping(value ="/administration/md/plan/delete/{id}",method = RequestMethod.GET)
	public @ResponseBody String delete(@PathVariable("id") Integer id) throws ParseException{
		planService.delete(id);
		return "success";
	}
	
	@RequestMapping("/administration/md/plan/list")
	public @ResponseBody DataTablesResponse<PlanResult> getUserList(HttpServletRequest request, final DataTablesRequest dtRequest,@ModelAttribute("masterForm") final MasterForm form){
		DataTablesResponse<PlanResult> dtResponse = new DataTablesResponse<PlanResult>();
		final String echo = dtRequest.getsEcho();
		dtResponse.setsEcho(echo);
		final Long countAll = planService.countAll();
		final Long countSearch = planService.countSearch(form);
		try {
			dtResponse.setAaData(planService.getPlans(form, dtRequest.getiDisplayStart(), dtRequest.getiDisplayLength()));
			dtResponse.setiTotalDisplayRecords(countSearch.intValue());
			dtResponse.setiTotalRecords(countAll.intValue());
		} catch (final RuntimeException e){
			e.printStackTrace();
		}
		
		return dtResponse;
	}
}
