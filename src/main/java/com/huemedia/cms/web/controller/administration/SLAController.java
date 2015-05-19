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
import com.huemedia.cms.service.SLAService;
import com.huemedia.cms.web.controller.administration.datatables.SlaResult;
import com.huemedia.cms.web.controller.datatables.DataTablesRequest;
import com.huemedia.cms.web.controller.datatables.DataTablesResponse;
import com.huemedia.cms.web.form.EscalationForm;
import com.huemedia.cms.web.form.SlaForm;

@Controller
public class SLAController {
	@Autowired
	CodeService codeService;
	@Autowired
	SLAService slaService;
	
	@RequestMapping(value = "/administration/sla", method = RequestMethod.GET)
	public ModelAndView home(Locale locale) {
		final ModelAndView mav = new ModelAndView("administration/sla/sla");
		SlaForm slaForm = new SlaForm();
		mav.addObject("slaForm", slaForm);
		mav.addObject("ticketGroupList", codeService.getTicketGroupList());
		mav.addObject("escalationList",codeService.getEscalationList());
		mav.addObject("statusList", codeService.getStatusList());
		mav.addObject("priorityList", codeService.getPriorityList());
		return mav;
	}
	@RequestMapping(value = "/administration/escalation/edit/{slaId}", method = RequestMethod.GET)
	public ModelAndView escalation(Locale locale,@PathVariable("slaId")Integer slaId) {
		final ModelAndView mav = new ModelAndView("administration/sla/escalation");
		EscalationForm escForm = new EscalationForm();
		escForm =slaService.findSlaById(slaId);
		mav.addObject("escForm", escForm);
		mav.addObject("escalationList",codeService.getEscalationList());
		mav.addObject("statusList", codeService.getStatusList());
		mav.addObject("priorityList", codeService.getPriorityList());
		return mav;
	}
	@RequestMapping(value ="/administration/escalation/save",method = RequestMethod.POST)
	public @ResponseBody String saveEsc(final Locale locale, @ModelAttribute("escForm") EscalationForm escForm,BindingResult result) throws ParseException{
		
		slaService.saveEscalation(escForm);
		return "success";
	}
	
	@RequestMapping("/administration/sla/list/sla")
	public @ResponseBody DataTablesResponse<SlaResult> getSlaList(HttpServletRequest request, final DataTablesRequest dtRequest,@ModelAttribute("slaForm") final SlaForm slaForm){
		DataTablesResponse<SlaResult> dtResponse = new DataTablesResponse<SlaResult>();
		final String echo = dtRequest.getsEcho();
		dtResponse.setsEcho(echo);
		final Long countAll = slaService.countAll();
		final Long countSearch = slaService.countSearch(slaForm);
		try {
			dtResponse.setAaData(slaService.getSlas(slaForm, dtRequest.getiDisplayStart(), dtRequest.getiDisplayLength()));
			dtResponse.setiTotalDisplayRecords(countSearch.intValue());
			dtResponse.setiTotalRecords(countAll.intValue());
		} catch (final RuntimeException e){
			e.printStackTrace();
		}
		
		return dtResponse;
	}
	
	
	
	@RequestMapping(value = "/administration/sla/setting/new", method = RequestMethod.GET)
	public ModelAndView setting(Locale locale) {
		final ModelAndView mav = new ModelAndView("administration/sla/setting");
		SlaForm slaForm = new SlaForm();
		mav.addObject("slaForm", slaForm);
		mav.addObject("ticketGroupList", codeService.getTicketGroupList());
		mav.addObject("escalationList",codeService.getEscalationList());
		mav.addObject("statusList", codeService.getStatusList());
		mav.addObject("priorityList", codeService.getPriorityList());
		return mav;
	}
	@RequestMapping(value = "/administration/sla/setting/edit/{id}", method = RequestMethod.GET)
	public ModelAndView edit(Locale locale,@PathVariable("id")Integer id) {
		final ModelAndView mav = new ModelAndView("administration/sla/setting");
		SlaForm slaForm = new SlaForm();
		slaForm =slaService.findSlaSetting(id);
		mav.addObject("ticketGroupList", codeService.getTicketGroupList());
		if(slaForm.getTicketGroupId()!=null){
		mav.addObject("ticketCategoryList", codeService.getTicketCategoryList(slaForm.getTicketGroupId()));
		}
		mav.addObject("escalationList",codeService.getEscalationList());
		mav.addObject("statusList", codeService.getStatusList());
		mav.addObject("priorityList", codeService.getPriorityList());
		mav.addObject("slaForm", slaForm);
		return mav;
	}
	
	@RequestMapping(value = "/administration/sla/delete/{id}", method = RequestMethod.GET)
	public @ResponseBody String delete(@PathVariable("id")Integer id) {
		slaService.deleteSetting(id);
		return "success";
	}
	
	@RequestMapping(value ="/administration/sla/save",method = RequestMethod.POST)
	public @ResponseBody String save(final Locale locale, @ModelAttribute("slaForm") SlaForm slaForm,BindingResult result) throws ParseException{
		if(slaForm.getId()!=null){
			System.out.println("UPDATE");
			slaService.updateSetting(slaForm);
		}else{
			System.out.println("SAVE");
			slaService.saveNewSetting(slaForm);
		}
		
		return "success";
	}
}
