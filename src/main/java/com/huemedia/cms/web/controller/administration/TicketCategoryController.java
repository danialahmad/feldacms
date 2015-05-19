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
import com.huemedia.cms.service.TicketCategoryService;
import com.huemedia.cms.web.controller.administration.datatables.TicketCategoryResult;
import com.huemedia.cms.web.controller.administration.datatables.TicketGroupResult;
import com.huemedia.cms.web.controller.datatables.DataTablesRequest;
import com.huemedia.cms.web.controller.datatables.DataTablesResponse;
import com.huemedia.cms.web.form.MasterForm;

@Controller
public class TicketCategoryController {
	@Autowired
	TicketCategoryService ticketCategoryService;
	@Autowired
	CodeService codeService;
	
	@RequestMapping(value = "/administration/md/ticketCategory", method = RequestMethod.GET)
	public ModelAndView user(Locale locale) {
		final ModelAndView mav = new ModelAndView("administration/md/ticketCategory");

		mav.addObject("ticketGroupList", codeService.getTicketGroupList());
		mav.addObject("masterForm", new MasterForm());
		return mav;
	}
	
	
	@RequestMapping(value = "/administration/md/ticketCategory/add", method = RequestMethod.GET)
	public ModelAndView add(Locale locale) {
		final ModelAndView mav = new ModelAndView("administration/md/form");
		mav.addObject("ticketGroupList", codeService.getTicketGroupList());
		mav.addObject("title", "Case Category");
		mav.addObject("id", false);
		mav.addObject("name", true);
		mav.addObject("rank", false);
		mav.addObject("caseGroup", true);
		mav.addObject("address", false);
		mav.addObject("region", false);
		mav.addObject("action", "/administration/md/ticketCategory/save");
		
		mav.addObject("ticketGroupList", codeService.getTicketGroupList());
		mav.addObject("masterForm", new MasterForm());
		return mav;
	}
	@RequestMapping(value = "/administration/md/ticketCategory/edit/{id}", method = RequestMethod.GET)
	public ModelAndView edit(Locale locale,@PathVariable("id") Integer id) {
		final ModelAndView mav = new ModelAndView("administration/md/form");
		mav.addObject("title", "Case Category");
		mav.addObject("id", false);
		mav.addObject("name", true);
		mav.addObject("rank", false);
		mav.addObject("caseGroup", true);
		mav.addObject("address", false);
		mav.addObject("region", false);
		mav.addObject("action", "/administration/md/ticketCategory/save");
		MasterForm form= new MasterForm();
		form=ticketCategoryService.findTicketCategory(id);
		
		mav.addObject("ticketGroupList", codeService.getTicketGroupList());
		mav.addObject("masterForm", form);
		return mav;
	}
	
	@RequestMapping(value ="/administration/md/ticketCategory/save",method = RequestMethod.POST)
	public @ResponseBody String save(final Locale locale, @ModelAttribute("masterForm") MasterForm form,BindingResult result) throws ParseException{
		ticketCategoryService.save(form);
		return "success";
	}
	
	@RequestMapping(value ="/administration/md/ticketCategory/delete/{id}",method = RequestMethod.GET)
	public @ResponseBody String delete(@PathVariable("id") Integer id) throws ParseException{
		ticketCategoryService.delete(id);
		return "success";
	}
	
	@RequestMapping("/administration/md/ticketCategory/list")
	public @ResponseBody DataTablesResponse<TicketCategoryResult> getUserList(HttpServletRequest request, final DataTablesRequest dtRequest,@ModelAttribute("masterForm") final MasterForm form){
		DataTablesResponse<TicketCategoryResult> dtResponse = new DataTablesResponse<TicketCategoryResult>();
		final String echo = dtRequest.getsEcho();
		dtResponse.setsEcho(echo);
		final Long countAll = ticketCategoryService.countAll();
		final Long countSearch = ticketCategoryService.countSearch(form);
		try {
			dtResponse.setAaData(ticketCategoryService.getTicketCategories(form, dtRequest.getiDisplayStart(), dtRequest.getiDisplayLength()));
			dtResponse.setiTotalDisplayRecords(countSearch.intValue());
			dtResponse.setiTotalRecords(countAll.intValue());
		} catch (final RuntimeException e){
			e.printStackTrace();
		}
		
		return dtResponse;
	}
}
