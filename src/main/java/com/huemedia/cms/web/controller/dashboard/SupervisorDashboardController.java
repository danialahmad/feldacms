package com.huemedia.cms.web.controller.dashboard;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
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
import com.huemedia.cms.web.controller.complaint.datatables.TicketResult;
import com.huemedia.cms.web.controller.datatables.DataTablesRequest;
import com.huemedia.cms.web.controller.datatables.DataTablesResponse;
import com.huemedia.cms.web.form.SearchTicketForm;

@Controller
public class SupervisorDashboardController {
	@Autowired
	TicketService ticketService;
	@Autowired
	DashboardService dashboardService;
	
	@RequestMapping(value = "/dashboard/supervisor", method = RequestMethod.GET)
	public ModelAndView viewSupervisor(Locale locale) {
		ModelAndView mav = new ModelAndView("dashboard/supervisor");
		return mav;
	}
	
	@RequestMapping(value = "/dashboard/caseInfoBySupervisor", method = RequestMethod.GET)
    @ResponseBody
    public Map caseInfo(Model model) throws JSONException {
	 	return this.dashboardService.caseCountInfoBySupervisor();
     }
	
	@RequestMapping("/dashboard/list/case/info/supervisor/day")
	public @ResponseBody DataTablesResponse<TicketResult> getMygroupTickets(HttpServletRequest request, final DataTablesRequest dtRequest,@ModelAttribute("searchTicketForm") final SearchTicketForm searchTicketForm){
		DataTablesResponse<TicketResult> dtResponse = new DataTablesResponse<TicketResult>();
		final String echo = dtRequest.getsEcho();
		dtResponse.setsEcho(echo);
		SecurityContext securityContext = SecurityContextHolder.getContext();
		UserDetail u=(UserDetail) securityContext.getAuthentication().getPrincipal();
		Profile p=u.getProfile();
		searchTicketForm.setSupervisorId(p.getId());
		searchTicketForm.setDay(Calendar.getInstance().get(Calendar.DAY_OF_YEAR));
		searchTicketForm.setYear(Calendar.getInstance().get(Calendar.YEAR));
		
		final Long countAll = dashboardService.countAllTicketByTime(searchTicketForm);
		final Long countSearch = dashboardService.countSearchTicketByTime(searchTicketForm);
		try {
			dtResponse.setAaData(dashboardService.getTicketByTime(searchTicketForm,dtRequest.getiDisplayStart(), dtRequest.getiDisplayLength()));
			dtResponse.setiTotalDisplayRecords(countSearch.intValue());
			dtResponse.setiTotalRecords(countAll.intValue());
		} catch (final RuntimeException e){
			e.printStackTrace();
		}
		
		return dtResponse;
	}
	@RequestMapping("/dashboard/list/case/info/supervisor/week")
	public @ResponseBody DataTablesResponse<TicketResult> getInfoWeek(HttpServletRequest request, final DataTablesRequest dtRequest,@ModelAttribute("searchTicketForm") final SearchTicketForm searchTicketForm){
		DataTablesResponse<TicketResult> dtResponse = new DataTablesResponse<TicketResult>();
		final String echo = dtRequest.getsEcho();
		dtResponse.setsEcho(echo);
		SecurityContext securityContext = SecurityContextHolder.getContext();
		UserDetail u=(UserDetail) securityContext.getAuthentication().getPrincipal();
		Profile p=u.getProfile();
		searchTicketForm.setSupervisorId(p.getId());
		searchTicketForm.setWeek(Calendar.getInstance().get(Calendar.WEEK_OF_YEAR)-1);
		searchTicketForm.setYear(Calendar.getInstance().get(Calendar.YEAR));
		
		final Long countAll = dashboardService.countAllTicketByTime(searchTicketForm);
		final Long countSearch = dashboardService.countSearchTicketByTime(searchTicketForm);
		try {
			dtResponse.setAaData(dashboardService.getTicketByTime(searchTicketForm,dtRequest.getiDisplayStart(), dtRequest.getiDisplayLength()));
			dtResponse.setiTotalDisplayRecords(countSearch.intValue());
			dtResponse.setiTotalRecords(countAll.intValue());
		} catch (final RuntimeException e){
			e.printStackTrace();
		}
		
		return dtResponse;
	}
	
	@RequestMapping("/dashboard/list/case/info/supervisor/month")
	public @ResponseBody DataTablesResponse<TicketResult> getInfoMonth(HttpServletRequest request, final DataTablesRequest dtRequest,@ModelAttribute("searchTicketForm") final SearchTicketForm searchTicketForm){
		DataTablesResponse<TicketResult> dtResponse = new DataTablesResponse<TicketResult>();
		final String echo = dtRequest.getsEcho();
		dtResponse.setsEcho(echo);
		SecurityContext securityContext = SecurityContextHolder.getContext();
		UserDetail u=(UserDetail) securityContext.getAuthentication().getPrincipal();
		Profile p=u.getProfile();
		searchTicketForm.setSupervisorId(p.getId());
		searchTicketForm.setMonth(Calendar.getInstance().get(Calendar.MONTH)+1);
		searchTicketForm.setYear(Calendar.getInstance().get(Calendar.YEAR));
		
		final Long countAll = dashboardService.countAllTicketByTime(searchTicketForm);
		final Long countSearch = dashboardService.countSearchTicketByTime(searchTicketForm);
		try {
			dtResponse.setAaData(dashboardService.getTicketByTime(searchTicketForm,dtRequest.getiDisplayStart(), dtRequest.getiDisplayLength()));
			dtResponse.setiTotalDisplayRecords(countSearch.intValue());
			dtResponse.setiTotalRecords(countAll.intValue());
		} catch (final RuntimeException e){
			e.printStackTrace();
		}
		
		return dtResponse;
	}
	
	@RequestMapping(value = "/latestCasesBySupervisor", method = RequestMethod.GET)
	public @ResponseBody List latestCasesBySupervisor(Locale locale) {
		List<Map> list = new ArrayList<Map>();
		SecurityContext securityContext = SecurityContextHolder.getContext();
		UserDetail u=(UserDetail) securityContext.getAuthentication().getPrincipal();
		Profile p=u.getProfile();
		List<TicketAssignment> tickets= ticketService.getTicketsBySupervisor(p.getId(), 0, 10);
		SimpleDateFormat sdfFull = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		for(TicketAssignment t:tickets){
			Map map = new HashMap();
			map.put("date", sdfFull.format(t.getCreateDate()));
			map.put("title", t.getTicket().getTicketTitle());
			map.put("priority", t.getTicket().getPriority().getName());
			map.put("status", t.getStatus().getName());
			list.add(map);
		}
		
		return list;
	}
	
	
	
	@RequestMapping(value = "/dashboard/caseInfoByPriorityBySupervisor", method = RequestMethod.GET)
    @ResponseBody
    public Map caseInfoByPriorityBySupervisor(Model model) throws JSONException {
	 	return this.dashboardService.caseInfoByPriorityBySupervisor();
     }
}
