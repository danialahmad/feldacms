package com.huemedia.cms.web.controller.searching;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.huemedia.cms.service.CodeService;
import com.huemedia.cms.service.TicketService;
import com.huemedia.cms.web.controller.complaint.datatables.TicketResult;
import com.huemedia.cms.web.controller.datatables.DataTablesRequest;
import com.huemedia.cms.web.controller.datatables.DataTablesResponse;
import com.huemedia.cms.web.form.ComplaintForm;
import com.huemedia.cms.web.form.SearchTicketForm;
import com.huemedia.cms.web.form.UserForm;

@Controller
public class SearchController {
	
	@Autowired
	CodeService codeService;
	@Autowired
	TicketService ticketService;
	
	@RequestMapping(value = "/search/user", method = RequestMethod.GET)
	public ModelAndView user(Locale locale) {
		final ModelAndView mav = new ModelAndView("searching/user");
		mav.addObject("countryList", codeService.getCountryList());
		mav.addObject("roleList", codeService.getRoleList());
		mav.addObject("groupList", codeService.getGroupList());
		mav.addObject("userForm", new UserForm());
		return mav;
	}
	
	@RequestMapping(value = "/search/case", method = RequestMethod.GET)
	public ModelAndView sCase(Locale locale) {
		final ModelAndView mav = new ModelAndView("searching/case");
		mav.addObject("ticketGroupList", codeService.getTicketGroupList());
		mav.addObject("statusList", codeService.getStatusList());
		mav.addObject("originatorList", codeService.getOriginatorList());
		mav.addObject("priorityList", codeService.getPriorityList());
		mav.addObject("groupList", codeService.getGroupList());
		SearchTicketForm searchTicketForm = new SearchTicketForm();
		mav.addObject("searchTicketForm", searchTicketForm);
		return mav;
	}
	
	@RequestMapping("/search/case/list")
	public @ResponseBody DataTablesResponse<TicketResult> getUnassignedTickets(HttpServletRequest request, final DataTablesRequest dtRequest,@ModelAttribute("searchTicketForm") final SearchTicketForm searchTicketForm){
		DataTablesResponse<TicketResult> dtResponse = new DataTablesResponse<TicketResult>();
		final String echo = dtRequest.getsEcho();
		dtResponse.setsEcho(echo);
		final Long countAll = ticketService.countAll();
		final Long countSearch = ticketService.countSearch(searchTicketForm);
		try {
			dtResponse.setAaData(ticketService.searchAll(searchTicketForm, dtRequest.getiDisplayStart(), dtRequest.getiDisplayLength()));
			dtResponse.setiTotalDisplayRecords(countSearch.intValue());
			dtResponse.setiTotalRecords(countAll.intValue());
		} catch (final RuntimeException e){
			e.printStackTrace();
		}
		
		return dtResponse;
	}
}
