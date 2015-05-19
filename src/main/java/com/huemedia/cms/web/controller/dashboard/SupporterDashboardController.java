package com.huemedia.cms.web.controller.dashboard;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.huemedia.cms.model.entity.Profile;
import com.huemedia.cms.model.entity.Ticket;
import com.huemedia.cms.model.entity.TicketAssignment;
import com.huemedia.cms.security.service.UserDetail;
import com.huemedia.cms.service.DashboardService;
import com.huemedia.cms.service.TicketService;

@Controller
public class SupporterDashboardController {
	
	@Autowired
	TicketService ticketService;
	@Autowired
	DashboardService dashboardService;
	
	
	@RequestMapping(value = "/dashboard/supporter", method = RequestMethod.GET)
	public ModelAndView viewSupporter(Locale locale) {
		ModelAndView mav = new ModelAndView("dashboard/supporter");
		return mav;
	}
	
	
	
	@RequestMapping(value = "/latestCasesByProfile", method = RequestMethod.GET)
	public @ResponseBody List latestCasesByProfile(Locale locale) {
		List<Map> list = new ArrayList<Map>();
		SecurityContext securityContext = SecurityContextHolder.getContext();
		UserDetail u=(UserDetail) securityContext.getAuthentication().getPrincipal();
		Profile p=u.getProfile();
		List<TicketAssignment> tickets= ticketService.getTicketsByAssignee(p.getId(), 0, 10);
		SimpleDateFormat sdfFull = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		for(TicketAssignment t:tickets){
			Map map = new HashMap();
			map.put("date", sdfFull.format(t.getAssignDate()));
			map.put("title", t.getTicket().getTicketTitle());
			map.put("priority", t.getTicket().getPriority().getName());
			map.put("status", t.getStatus().getName());
			list.add(map);
		}
		
		return list;
	}

	
	@RequestMapping(value = "/dashboard/caseInfoByProfile", method = RequestMethod.GET)
    @ResponseBody
    public Map caseInfoByProfile(Model model) throws JSONException {
	 	return this.dashboardService.caseCountInfoByProfile();
     }
	
	
	@RequestMapping(value = "/dashboard/caseInfoByPriorityByProfile", method = RequestMethod.GET)
    @ResponseBody
    public Map caseInfoByPriorityByProfile(Model model) throws JSONException {
	 	return this.dashboardService.caseInfoByPriorityByProfile();
     }
}
