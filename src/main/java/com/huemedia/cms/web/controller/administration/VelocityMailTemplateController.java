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

import com.huemedia.cms.model.entity.RefVelocityMailTemplate;
import com.huemedia.cms.service.VelocityMailTemplateService;
import com.huemedia.cms.web.controller.administration.datatables.TemplateResult;
import com.huemedia.cms.web.controller.datatables.DataTablesRequest;
import com.huemedia.cms.web.controller.datatables.DataTablesResponse;
import com.huemedia.cms.web.form.MasterForm;

@Controller
public class VelocityMailTemplateController {
 @Autowired
 VelocityMailTemplateService velocityMailTemplateService;
 
 
	@RequestMapping(value = "/administration/notification/template", method = RequestMethod.GET)
	public ModelAndView user(Locale locale) {
		final ModelAndView mav = new ModelAndView("administration/notification/template");

	    mav.addObject("masterForm", new MasterForm());
		return mav;
	}
	@RequestMapping(value = "/administration/notification/template/add", method = RequestMethod.GET)
	public ModelAndView add(Locale locale) {
		final ModelAndView mav = new ModelAndView("administration/notification/form");
		mav.addObject("masterForm", new MasterForm());
		return mav;
	}
	
	@RequestMapping(value = "/administration/notification/template/edit/{id}", method = RequestMethod.GET)
	public ModelAndView edit(Locale locale,@PathVariable("id") String id) {
		final ModelAndView mav = new ModelAndView("administration/notification/form");
		RefVelocityMailTemplate template=velocityMailTemplateService.getVelocityMailTemplate(id);
		MasterForm form =  new MasterForm();
		form.setId(template.getId());
		form.setName(template.getName());
		form.setContent(template.getContent());
		mav.addObject("masterForm", form);
		return mav;
	}
	
	@RequestMapping(value ="/administration/notification/template/save",method = RequestMethod.POST)
	public @ResponseBody String save(final Locale locale, @ModelAttribute("masterForm") MasterForm form,BindingResult result) throws ParseException{
		RefVelocityMailTemplate template= new RefVelocityMailTemplate();
		template.setId(form.getId());
		template.setName(form.getName());
		template.setContent(form.getContent());
		velocityMailTemplateService.updateMailTemplate(template);
		return "success";
	}
	
	@RequestMapping(value ="/administration/notification/template/delete/{id}",method = RequestMethod.GET)
	public @ResponseBody String delete(@PathVariable("id") String id) throws ParseException{
		velocityMailTemplateService.deleteMailTemplate(id);
		return "success";
	}
	
	@RequestMapping("/administration/notification/template/list")
	public @ResponseBody DataTablesResponse<TemplateResult> getUserList(HttpServletRequest request, final DataTablesRequest dtRequest,@ModelAttribute("masterForm") final MasterForm form){
		DataTablesResponse<TemplateResult> dtResponse = new DataTablesResponse<TemplateResult>();
		final String echo = dtRequest.getsEcho();
		dtResponse.setsEcho(echo);
		final Long countAll = velocityMailTemplateService.countAll();
		final Long countSearch = velocityMailTemplateService.countSearch(form);
		try {
			dtResponse.setAaData(velocityMailTemplateService.getTemplates(form, dtRequest.getiDisplayStart(), dtRequest.getiDisplayLength()));
			dtResponse.setiTotalDisplayRecords(countSearch.intValue());
			dtResponse.setiTotalRecords(countAll.intValue());
		} catch (final RuntimeException e){
			e.printStackTrace();
		}
		
		return dtResponse;
	}
 
}
