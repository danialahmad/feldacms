package com.huemedia.cms.web.controller.complaint;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.GrantedAuthorityImpl;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.support.ByteArrayMultipartFileEditor;
import org.springframework.web.servlet.ModelAndView;

import com.huemedia.cms.model.entity.Profile;
import com.huemedia.cms.model.entity.StaffGroup;
import com.huemedia.cms.model.entity.State;
import com.huemedia.cms.model.entity.Ticket;
import com.huemedia.cms.model.entity.TicketAssignment;
import com.huemedia.cms.model.entity.TicketFile;
import com.huemedia.cms.security.service.UserDetail;
import com.huemedia.cms.service.CodeService;
import com.huemedia.cms.service.ComplaintService;
import com.huemedia.cms.service.TicketService;
import com.huemedia.cms.service.UserService;
import com.huemedia.cms.web.controller.complaint.datatables.ActivityResult;
import com.huemedia.cms.web.controller.complaint.datatables.TicketResult;
import com.huemedia.cms.web.controller.datatables.DataTablesRequest;
import com.huemedia.cms.web.controller.datatables.DataTablesResponse;
import com.huemedia.cms.web.form.ActivityForm;
import com.huemedia.cms.web.form.ChatForm;
import com.huemedia.cms.web.form.ComplaintForm;
import com.huemedia.cms.web.form.KnowledgeForm;
import com.huemedia.cms.web.form.SearchTicketForm;

@Controller
public class ComplaintAdminController {
	@Autowired
	CodeService codeService;
	@Autowired
	ComplaintService complaintService;
	@Autowired
	TicketService ticketService;
	@Autowired
	UserService userService;
	
	@Secured({"ROLE_ADMIN","ROLE_SUPPORTER","ROLE_SUPERVISOR","ROLE_MANAGER","ROLE_HELPDESK"})
	@RequestMapping(value = "/case/edit/{id}", method = RequestMethod.GET)
	public ModelAndView view(Locale locale,@PathVariable("id")String id) {
		ModelAndView mav = new ModelAndView("case/editForm");
		
		ComplaintForm complaintForm = new ComplaintForm();
		complaintForm = complaintService.findComplaintByID(id);
		mav.addObject("ticketGroupList", codeService.getTicketGroupList());
		//mav.addObject("ticketCategoryList", codeService.getTicketCategoryList(complaintForm.getTicketGroupId()));
		List<String> statuses=new ArrayList<String>();
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		
		List<GrantedAuthority> authorities =(List<GrantedAuthority>) authentication.getAuthorities();
		
		if(authorities.contains(new GrantedAuthorityImpl("ROLE_HELPDESK"))){
			
			
			
			if(complaintForm.getStatusId().equals("NEW")||complaintForm.getStatusId().equals("RECEIVED")){
				statuses.add("RECEIVED");
				statuses.add("COMPLETED1");
				statuses.add("COMPLETED2");
				statuses.add("CLOSED");
				statuses.add("UNRELATED");
			}
			if(complaintForm.getStatusId().equals("CLOSED")||complaintForm.getStatusId().equals("UNRELATED")||complaintForm.getStatusId().equals("COMPLETED1")||complaintForm.getStatusId().equals("COMPLETED2")||complaintForm.getStatusId().equals("RE-OPEN")){
				statuses.add("CLOSED");
				statuses.add("COMPLETED1");
				statuses.add("COMPLETED2");
				statuses.add("RE-OPEN");
				statuses.add("UNRELATED");
			}
			
			
			
		}else if(authorities.contains(new GrantedAuthorityImpl("ROLE_SUPPORTER"))){
			statuses.add("IN PROGRESS");
			statuses.add("RETURNED");
			statuses.add("WAITING");
			statuses.add("COMPLETED");
			
			if(complaintForm.getStatusId().equals("ASSIGNED")
					||complaintForm.getStatusId().equals("RE-ASSIGNED")
					||complaintForm.getStatusId().equals("IN PROGRESS")
					||complaintForm.getStatusId().equals("WAITING")
					||complaintForm.getStatusId().equals("COMPLETED")){
				
				if(complaintForm.getAssigneeId()!=null){
				complaintForm.setStatusReadOnly(false);
				}
			}
			
		}else{
			if(complaintForm.getStatusId().equals("RECEIVED")){
				statuses.add("ASSIGNED");
			}else{
				if(complaintForm.getAssigneeId()==null){
					statuses.add("ASSIGNED");
				}else{
					statuses.add("RE-ASSIGNED");
				}
				
				
			}
		
			statuses.add("IN PROGRESS");
			statuses.add("WAITING");
			statuses.add("COMPLETED");
			statuses.add("CLOSED");	
			statuses.add("REJECTED");
			
			complaintForm.setStatusReadOnly(false);
			complaintForm.setPriorityReadOnly(false);
		}
		
		
		mav.addObject("statusList", codeService.getStatusList(statuses));
		mav.addObject("originatorList", codeService.getOriginatorList());
		mav.addObject("priorityList", codeService.getPriorityList());
		mav.addObject("groupList", codeService.getGroupList());
		
		if(complaintForm.getGroup()!=null){
			if(complaintForm.getRegion()!=null){
				if(complaintForm.getRegion().getId()!=null){
					mav.addObject("staffList", codeService.getProfileList(complaintForm.getGroup(),complaintForm.getRegion().getId()));
				}else{
					mav.addObject("staffList", codeService.getProfileList(complaintForm.getGroup(),null));
				}
			}else{
				mav.addObject("staffList", codeService.getProfileList(complaintForm.getGroup(),null));
			}
		
		}
		mav.addObject("regionList", codeService.getRegionList());
		mav.addObject("complaintForm", complaintForm);
		ChatForm chatForm=new ChatForm();
		chatForm.setTicketId(complaintForm.getId());
		mav.addObject("chatForm", chatForm);
		
		ActivityForm activityForm = new ActivityForm();
		mav.addObject("activityForm", activityForm);
		return mav;
	}
	
	
	@RequestMapping(value = "/case/assignment/edit/{ticketId}/{id}", method = RequestMethod.GET)
	public ModelAndView viewAssignment(Locale locale,@PathVariable("ticketId")String ticketId,@PathVariable("id")Integer id) {
		ModelAndView mav = new ModelAndView("case/aForm");
		ComplaintForm complaintForm = new ComplaintForm();
		complaintForm = complaintService.findAssignmentByID(ticketId, id);
		mav.addObject("ticketGroupList", codeService.getTicketGroupList());
		//mav.addObject("ticketCategoryList", codeService.getTicketCategoryList(complaintForm.getTicketGroupId()));
Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		
		List<GrantedAuthority> authorities =(List<GrantedAuthority>) authentication.getAuthorities();
		
	
		
		List<String> statuses=new ArrayList<String>();
		statuses.add("IN PROGRESS");
		statuses.add("WAITING");
		statuses.add("SOLVED");
		
		if(authorities.contains(new GrantedAuthorityImpl("ROLE_SUPERVISOR"))){
		statuses.add("REJECTED");
		}
		
		if(authorities.contains(new GrantedAuthorityImpl("ROLE_SUPPORTER"))){
		statuses.add("RETURNED");
		}
		mav.addObject("statusList", codeService.getStatusList(statuses));
		mav.addObject("originatorList", codeService.getOriginatorList());
		mav.addObject("priorityList", codeService.getPriorityList());
		mav.addObject("groupList", codeService.getGroupList());
		
		if(complaintForm.getGroup()!=null){
			if(complaintForm.getRegion()!=null){
				if(complaintForm.getRegion().getId()!=null){
					mav.addObject("staffList", codeService.getProfileList(complaintForm.getGroup(),complaintForm.getRegion().getId()));
				}else{
					mav.addObject("staffList", codeService.getProfileList(complaintForm.getGroup(),null));
				}
			}else{
				mav.addObject("staffList", codeService.getProfileList(complaintForm.getGroup(),null));
			}
		
		}
		mav.addObject("regionList", codeService.getRegionList());
		
		mav.addObject("complaintForm", complaintForm);
		return mav;
	}
	
	@RequestMapping(value = "/case/assignment/view/{ticketId}/{id}", method = RequestMethod.GET)
	public ModelAndView readAssignment(Locale locale,@PathVariable("ticketId")String ticketId,@PathVariable("id")Integer id) {
		ModelAndView mav = new ModelAndView("case/readForm");
		ComplaintForm complaintForm = new ComplaintForm();
		complaintForm = complaintService.findAssignmentByID(ticketId, id);
		mav.addObject("ticketGroupList", codeService.getTicketGroupList());
		//mav.addObject("ticketCategoryList", codeService.getTicketCategoryList(complaintForm.getTicketGroupId()));
Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		
		List<GrantedAuthority> authorities =(List<GrantedAuthority>) authentication.getAuthorities();
		
	
		
		List<String> statuses=new ArrayList<String>();
		statuses.add("IN PROGRESS");
		statuses.add("WAITING");
		statuses.add("SOLVED");
		
		if(authorities.contains(new GrantedAuthorityImpl("ROLE_SUPERVISOR"))){
		statuses.add("REJECTED");
		}
		
		if(authorities.contains(new GrantedAuthorityImpl("ROLE_SUPPORTER"))){
		statuses.add("RETURNED");
		}
		mav.addObject("statusList", codeService.getStatusList(statuses));
		mav.addObject("originatorList", codeService.getOriginatorList());
		mav.addObject("priorityList", codeService.getPriorityList());
		mav.addObject("groupList", codeService.getGroupList());
		
		if(complaintForm.getGroup()!=null){
			if(complaintForm.getRegion()!=null){
				if(complaintForm.getRegion().getId()!=null){
					mav.addObject("staffList", codeService.getProfileList(complaintForm.getGroup(),complaintForm.getRegion().getId()));
				}else{
					mav.addObject("staffList", codeService.getProfileList(complaintForm.getGroup(),null));
				}
			}else{
				mav.addObject("staffList", codeService.getProfileList(complaintForm.getGroup(),null));
			}
		
		}
		mav.addObject("regionList", codeService.getRegionList());
		
		mav.addObject("complaintForm", complaintForm);
		return mav;
	}
	
	
	@RequestMapping(value = "/case/rating/{ticketId}", method = RequestMethod.GET)
	public ModelAndView rate(Locale locale,@PathVariable("ticketId")String ticketId) {
		ModelAndView mav = new ModelAndView("case/ratingForm");
		ComplaintForm complaintForm = new ComplaintForm();
		complaintForm = complaintService.findComplaintByID(ticketId);
		mav.addObject("ratingList", codeService.getRatingList());
		mav.addObject("complaintForm", complaintForm);
		return mav;
	}
	@RequestMapping(value ="/case/rating/update",method = RequestMethod.POST)
	public @ResponseBody String getRequestUpdate(final Locale locale, @ModelAttribute("complaintForm") ComplaintForm complaintForm,BindingResult result) throws ParseException{
		complaintService.updateFeedback(complaintForm,locale);
		return "success";
	}
	
	@RequestMapping(value = "/case/supporter/edit/{id}", method = RequestMethod.GET)
	public ModelAndView viewSupporter(Locale locale,@PathVariable("id")String id) {
		ModelAndView mav = new ModelAndView("case/editSupporter");
		
		ComplaintForm complaintForm = new ComplaintForm();
		complaintForm = complaintService.findComplaintByID(id);
		mav.addObject("ticketGroupList", codeService.getTicketGroupList());
		mav.addObject("ticketCategoryList", codeService.getTicketCategoryList(complaintForm.getTicketGroupId()));
		List<String> statuses=new ArrayList<String>();
		statuses.add("IN PROGRESS");
		statuses.add("WAITING");
		statuses.add("COMPLETED");
		mav.addObject("statusList", codeService.getStatusList(statuses));
		mav.addObject("originatorList", codeService.getOriginatorList());
		mav.addObject("priorityList", codeService.getPriorityList());
		mav.addObject("groupList", codeService.getGroupList());
	//	mav.addObject("staffList", codeService.getProfileList(complaintForm.getGroup()));
		mav.addObject("complaintForm", complaintForm);
		ChatForm chatForm=new ChatForm();
		chatForm.setTicketId(complaintForm.getId());
		mav.addObject("chatForm", chatForm);
		
		ActivityForm activityForm = new ActivityForm();
		mav.addObject("activityForm", activityForm);
		return mav;
	}
	
	@RequestMapping(value = "/case/helpdesk/add", method = RequestMethod.GET)
	public ModelAndView viewHelp(Locale locale) {
		ModelAndView mav = new ModelAndView("case/newForm");
		
		ComplaintForm complaintForm = new ComplaintForm();
		mav.addObject("ticketGroupList", codeService.getTicketGroupList());
		mav.addObject("statusList", codeService.getStatusList());
		mav.addObject("originatorList", codeService.getOriginatorList());
		mav.addObject("priorityList", codeService.getPriorityList());
		mav.addObject("groupList", codeService.getGroupList());
		mav.addObject("countryList", codeService.getCountryList());
		mav.addObject("complaintForm", complaintForm);
		mav.addObject("regionList", codeService.getRegionList());
		mav.addObject("personCategoryList", codeService.getPersonCategoryList());
		mav.addObject("relationList", codeService.getRelationList());
		mav.addObject("categoryList", codeService.getCategoryList());
		
		ActivityForm activityForm = new ActivityForm();
		mav.addObject("activityForm", activityForm);
		return mav;
	}
	
	
	
	@RequestMapping(value ="/case/assignment/save",method = RequestMethod.POST)
	public @ResponseBody String caseAssignSave(final Locale locale, @ModelAttribute("complaintForm") ComplaintForm complaintForm,BindingResult result) throws ParseException{
		complaintService.saveAssignment(complaintForm,locale);
		return "success";
	}
	
	
	@RequestMapping(value ="/case/helpdesk/save",method = RequestMethod.POST)
	public @ResponseBody String caseSave(final Locale locale, @ModelAttribute("complaintForm") ComplaintForm complaintForm,BindingResult result) throws ParseException{
		complaintService.saveCaseForHelpdesk(complaintForm,locale);
		return "success";
	}
	
	@RequestMapping("/case/helpdesk/list/state/{countryId}")
	public @ResponseBody String[][] getStateList(@PathVariable("countryId") Integer countryId){
		List<String[]> list = new ArrayList<String[]>();
		
		List<State> listState = codeService.getStateList(countryId);
		for(State obj:listState){
			String id = obj.getId().toString();
			String label = obj.getName();
			String[] row = {id, label};
			list.add(row);
		}
		return list.toArray(new String[list.size()][2]);
	}
	
	@RequestMapping(value = "/case/activity/add/{ticketId}/{assignmentId}", method = RequestMethod.GET)
	public ModelAndView activities(Locale locale,@PathVariable("ticketId") String ticketId,@PathVariable("assignmentId") Integer assignmentId) {
		final ModelAndView mav = new ModelAndView("case/activity");
		ActivityForm activityForm = new ActivityForm();
		Ticket t=new Ticket();
		t.setId(ticketId);
		TicketAssignment a=new TicketAssignment();
		a.setId(assignmentId);
		activityForm.setTicket(t);
		activityForm.setAssignment(a);
		mav.addObject("activityTypeList", codeService.getActivityTypeList());
		mav.addObject("contactList", codeService.getContactList());
		mav.addObject("activityForm", activityForm);
		mav.addObject("action", "add");
		
		
		return mav;
	}
	@RequestMapping(value = "/case/activity/add/{ticketId}", method = RequestMethod.GET)
	public ModelAndView activities(Locale locale,@PathVariable("ticketId") String ticketId) {
		final ModelAndView mav = new ModelAndView("case/activity");
		ActivityForm activityForm = new ActivityForm();
		Ticket t=new Ticket();
		t.setId(ticketId);
		activityForm.setTicket(t);
		mav.addObject("activityTypeList", codeService.getActivityTypeList());
		mav.addObject("contactList", codeService.getContactList());
		mav.addObject("activityForm", activityForm);
		mav.addObject("action", "add");
		
		
		return mav;
	}
	@RequestMapping(value = "/case/activity/edit/{id}", method = RequestMethod.GET)
	public ModelAndView activitiesEdit(Locale locale,@PathVariable("id") Integer id) {
		final ModelAndView mav = new ModelAndView("case/activity");
		ActivityForm activityForm = complaintService.findActivityByID(id);
		mav.addObject("activityTypeList", codeService.getActivityTypeList());
		mav.addObject("contactList", codeService.getContactList());
		mav.addObject("activityForm", activityForm);
		mav.addObject("action", "edit");
		
		
		return mav;
	}
	@RequestMapping(value = "/case/activity/view/{id}", method = RequestMethod.GET)
	public ModelAndView activitiesView(Locale locale,@PathVariable("id") Integer id) {
		final ModelAndView mav = new ModelAndView("case/activityRead");
		ActivityForm activityForm = complaintService.findActivityByID(id);
		mav.addObject("activityTypeList", codeService.getActivityTypeList());
		mav.addObject("contactList", codeService.getContactList());
		mav.addObject("activityForm", activityForm);
		mav.addObject("action", "edit");
		
		
		return mav;
	}
	
	
	@RequestMapping(value ="/case/assignment/reassign/{id}",method = RequestMethod.GET)
	public @ResponseBody String reassign(final Locale locale,@PathVariable("id") Integer id) throws ParseException{
		ComplaintForm complaintForm=new ComplaintForm();
		complaintForm.setAssigmentId(id);
		complaintForm.setStatusId("RE-ASSIGNED");
		complaintService.updateAssignmentStatus(complaintForm,locale);
		return "success";
	}
	
	@RequestMapping(value ="/case/activity/save",method = RequestMethod.POST)
	public @ResponseBody String saveActivity(final Locale locale, @ModelAttribute("activityForm") ActivityForm activityForm,BindingResult result) throws ParseException{
		complaintService.saveActivity(activityForm,locale);
		return "success";
	}
	@RequestMapping(value ="/case/activity/update",method = RequestMethod.POST)
	public @ResponseBody String updateActivity(final Locale locale, @ModelAttribute("activityForm") ActivityForm activityForm,BindingResult result) throws ParseException{
		complaintService.updateActivity(activityForm,locale);
		return "success";
	}
	
	@RequestMapping(value ="/case/update",method = RequestMethod.POST)
	public @ResponseBody Map getRequestSave(final Locale locale, @ModelAttribute("complaintForm") ComplaintForm complaintForm,BindingResult result) throws ParseException{
		UserDetail userDetail = (UserDetail) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		
		List<GrantedAuthority> authorities =(List<GrantedAuthority>) authentication.getAuthorities();
		
		if(authorities.contains(new GrantedAuthorityImpl("ROLE_SUPERVISOR")) || authorities.contains(new GrantedAuthorityImpl("ROLE_ADMIN")) || authorities.contains(new GrantedAuthorityImpl("ROLE_MANAGER"))){
		 complaintForm.setSupervisorId(userDetail.getProfile().getId());
		}
		
		
		Ticket t=complaintService.updateComplaintStatus(complaintForm,locale);
		//complaintService.updateComplaint(complaintForm);
		SimpleDateFormat sdfFull = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss a");
		
		Map<String,String> map = new HashMap<String,String>();
		map.put("msg", "success");
		String last=null;
		if(t.getLastStatusUpdate()!=null){
			last=sdfFull.format(t.getLastStatusUpdate());
		}
		map.put("lastUpdate", last);
		map.put("updateBy", t.getUpdateBy());
		return map;
	}
	@RequestMapping(value ="/case/assignment/update",method = RequestMethod.POST)
	public @ResponseBody Map getAssUpdate(final Locale locale, @ModelAttribute("complaintForm") ComplaintForm complaintForm,BindingResult result) throws ParseException{
		UserDetail userDetail = (UserDetail) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		
		List<GrantedAuthority> authorities =(List<GrantedAuthority>) authentication.getAuthorities();
		
		if(authorities.contains(new GrantedAuthorityImpl("ROLE_SUPERVISOR")) || authorities.contains(new GrantedAuthorityImpl("ROLE_ADMIN")) || authorities.contains(new GrantedAuthorityImpl("ROLE_MANAGER"))){
		 complaintForm.setSupervisorId(userDetail.getProfile().getId());
		}
		
		
		TicketAssignment a=complaintService.updateAssignment(complaintForm,locale);
		//complaintService.updateComplaint(complaintForm);
SimpleDateFormat sdfFull = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss a");
		
		Map<String,String> map = new HashMap<String,String>();
		map.put("msg", "success");
		String last=null;
		if(a.getLastStatusUpdate()!=null){
			last=sdfFull.format(a.getLastStatusUpdate());
		}
		map.put("lastUpdate", last);
		map.put("updateBy", a.getUpdateBy());
		return map;
	}
	@RequestMapping("/case/list/profiles/{groupId}/{regionId}")
	public @ResponseBody String[][] getStaffs(@PathVariable("groupId") String groupId,@PathVariable("regionId") Integer regionId){
		List<String[]> list = new ArrayList<String[]>();
		
		List<Profile> listStaff = codeService.getProfileList(groupId,regionId);
		for(Profile obj:listStaff){
			String id = obj.getId().toString();
			String label = obj.getName();
			String[] row = {id, label};
			list.add(row);
		}
		return list.toArray(new String[list.size()][2]);
	}
	
	
	@RequestMapping(value = "/case/new", method = RequestMethod.GET)
	public ModelAndView newList(Locale locale) {
		final ModelAndView mav = new ModelAndView("case/newList");
		SearchTicketForm searchTicketForm = new SearchTicketForm();
		mav.addObject("statusList", codeService.getStatusList());
		mav.addObject("searchTicketForm", searchTicketForm);
		mav.addObject("url", "/case/list/hp/new");
		return mav;
	}
	
	@RequestMapping(value = "/case/unassigned", method = RequestMethod.GET)
	public ModelAndView unassignedList(Locale locale) {
		final ModelAndView mav = new ModelAndView("case/unassignedList");
		SearchTicketForm searchTicketForm = new SearchTicketForm();
		mav.addObject("statusList", codeService.getStatusList());
		mav.addObject("searchTicketForm", searchTicketForm);
		mav.addObject("url", "/case/list/unassigned");
		return mav;
	}
	
	@RequestMapping(value = "/case/open", method = RequestMethod.GET)
	public ModelAndView openList(Locale locale) {
		final ModelAndView mav = new ModelAndView("case/newList");
		SearchTicketForm searchTicketForm = new SearchTicketForm();
		mav.addObject("statusList", codeService.getStatusList());
		mav.addObject("searchTicketForm", searchTicketForm);
	//	mav.addObject("url", "/case/list/open/manager");
		mav.addObject("url", "/case/list/hp/open");
		
		return mav;
	}
	@RequestMapping(value = "/case/solved", method = RequestMethod.GET)
	public ModelAndView solvedList(Locale locale) {
		final ModelAndView mav = new ModelAndView("case/newList");
		SearchTicketForm searchTicketForm = new SearchTicketForm();
		mav.addObject("statusList", codeService.getStatusList());
		mav.addObject("searchTicketForm", searchTicketForm);
		//mav.addObject("url", "/case/list/solved/manager");
		mav.addObject("url", "/case/list/hp/solve");
		return mav;
	}
	@RequestMapping(value = "/case/closed", method = RequestMethod.GET)
	public ModelAndView closedList(Locale locale) {
		final ModelAndView mav = new ModelAndView("case/newList");
		SearchTicketForm searchTicketForm = new SearchTicketForm();
		mav.addObject("searchTicketForm", searchTicketForm);
		//mav.addObject("url", "/case/list/closed/manager");
		mav.addObject("url", "/case/list/hp/close");
		return mav;
	}
	
	@RequestMapping(value = "/case/supervisor/unassigned", method = RequestMethod.GET)
	public ModelAndView unassignedSvList(Locale locale) {
		final ModelAndView mav = new ModelAndView("case/unassignedList");
		SearchTicketForm searchTicketForm = new SearchTicketForm();
		mav.addObject("searchTicketForm", searchTicketForm);
		mav.addObject("url", "/case/list/unassigned");
		return mav;
	}
	@RequestMapping(value = "/case/supervisor/open", method = RequestMethod.GET)
	public ModelAndView openSvList(Locale locale) {
		final ModelAndView mav = new ModelAndView("case/openList");
		SearchTicketForm searchTicketForm = new SearchTicketForm();
		mav.addObject("searchTicketForm", searchTicketForm);
		mav.addObject("url", "/case/list/open/supervisor");
		
		return mav;
	}
	@RequestMapping(value = "/case/supervisor/solved", method = RequestMethod.GET)
	public ModelAndView solvedSvList(Locale locale) {
		final ModelAndView mav = new ModelAndView("case/solvedList");
		SearchTicketForm searchTicketForm = new SearchTicketForm();
		mav.addObject("searchTicketForm", searchTicketForm);
		mav.addObject("url", "/case/list/solved/supervisor");
		return mav;
	}
	@RequestMapping(value = "/case/supervisor/closed", method = RequestMethod.GET)
	public ModelAndView closedSvList(Locale locale) {
		final ModelAndView mav = new ModelAndView("case/closedList");
		SearchTicketForm searchTicketForm = new SearchTicketForm();
		mav.addObject("searchTicketForm", searchTicketForm);
		mav.addObject("url", "/case/list/closed/supervisor");
		return mav;
	}
	
	
	
	@RequestMapping(value = "/case/supporter/open", method = RequestMethod.GET)
	public ModelAndView openSpList(Locale locale) {
		final ModelAndView mav = new ModelAndView("case/openList");
		SearchTicketForm searchTicketForm = new SearchTicketForm();
		mav.addObject("searchTicketForm", searchTicketForm);
		mav.addObject("url", "/case/list/open/supporter");
		
		return mav;
	}
	@RequestMapping(value = "/case/supporter/solved", method = RequestMethod.GET)
	public ModelAndView solvedSpList(Locale locale) {
		final ModelAndView mav = new ModelAndView("case/solvedList");
		SearchTicketForm searchTicketForm = new SearchTicketForm();
		mav.addObject("searchTicketForm", searchTicketForm);
		mav.addObject("url", "/case/list/solved/supporter");
		return mav;
	}
	
	
	
	
	
	
	@RequestMapping(value = "/case/mygroup", method = RequestMethod.GET)
	public ModelAndView mygroupList(Locale locale) {
		final ModelAndView mav = new ModelAndView("case/mygroupList");
		SearchTicketForm searchTicketForm = new SearchTicketForm();
		mav.addObject("searchTicketForm", searchTicketForm);
		return mav;
	}
	@RequestMapping(value = "/case/mycases", method = RequestMethod.GET)
	public ModelAndView mycasesList(Locale locale) {
		final ModelAndView mav = new ModelAndView("case/mycasesList");
		SearchTicketForm searchTicketForm = new SearchTicketForm();
		mav.addObject("searchTicketForm", searchTicketForm);
		return mav;
	}
	
	
	@RequestMapping("/case/list/new")
	public @ResponseBody DataTablesResponse<TicketResult> getNewTickets(HttpServletRequest request, final DataTablesRequest dtRequest,@ModelAttribute("searchTicketForm") final SearchTicketForm searchTicketForm){
		DataTablesResponse<TicketResult> dtResponse = new DataTablesResponse<TicketResult>();
		final String echo = dtRequest.getsEcho();
		dtResponse.setsEcho(echo);
		final String[] status = {"NEW","REJECTED"};
		final Long countAll = ticketService.countAll();
		final Long countSearch = ticketService.countSearch(searchTicketForm);
		try {
//			dtResponse.setAaData(ticketService.getTickets(searchTicketForm,status, dtRequest.getiDisplayStart(), dtRequest.getiDisplayLength()));
			dtResponse.setAaData(ticketService.searchAll(searchTicketForm, dtRequest.getiDisplayStart(), dtRequest.getiDisplayLength()));
			dtResponse.setiTotalDisplayRecords(countSearch.intValue());
			dtResponse.setiTotalRecords(countAll.intValue());
		} catch (final RuntimeException e){
			e.printStackTrace();
		}
		
		return dtResponse;
	}
	
	@RequestMapping("/case/list/hp/new")
	public @ResponseBody DataTablesResponse<TicketResult> getNewTicketshp(HttpServletRequest request, final DataTablesRequest dtRequest,@ModelAttribute("searchTicketForm") final SearchTicketForm searchTicketForm){
		DataTablesResponse<TicketResult> dtResponse = new DataTablesResponse<TicketResult>();
		final String echo = dtRequest.getsEcho();
		dtResponse.setsEcho(echo);
		final String[] status = {"NEW"};
		searchTicketForm.setStatusList(status);
		final Long countAll = ticketService.countAll();
		final Long countSearch = ticketService.countSearch(searchTicketForm);
		try {
//			dtResponse.setAaData(ticketService.getTickets(searchTicketForm,status, dtRequest.getiDisplayStart(), dtRequest.getiDisplayLength()));
			dtResponse.setAaData(ticketService.searchAll(searchTicketForm, dtRequest.getiDisplayStart(), dtRequest.getiDisplayLength()));
			dtResponse.setiTotalDisplayRecords(countSearch.intValue());
			dtResponse.setiTotalRecords(countAll.intValue());
		} catch (final RuntimeException e){
			e.printStackTrace();
		}
		
		return dtResponse;
	}
	@RequestMapping("/case/list/hp/open")
	public @ResponseBody DataTablesResponse<TicketResult> getNewTicketsOpen(HttpServletRequest request, final DataTablesRequest dtRequest,@ModelAttribute("searchTicketForm") final SearchTicketForm searchTicketForm){
		DataTablesResponse<TicketResult> dtResponse = new DataTablesResponse<TicketResult>();
		final String echo = dtRequest.getsEcho();
		dtResponse.setsEcho(echo);
		final String[] status = {"RECEIVED","RE-OPEN"};
		searchTicketForm.setStatusList(status);
		final Long countAll = ticketService.countAll();
		final Long countSearch = ticketService.countSearch(searchTicketForm);
		try {
//			dtResponse.setAaData(ticketService.getTickets(searchTicketForm,status, dtRequest.getiDisplayStart(), dtRequest.getiDisplayLength()));
			dtResponse.setAaData(ticketService.searchAll(searchTicketForm, dtRequest.getiDisplayStart(), dtRequest.getiDisplayLength()));
			dtResponse.setiTotalDisplayRecords(countSearch.intValue());
			dtResponse.setiTotalRecords(countAll.intValue());
		} catch (final RuntimeException e){
			e.printStackTrace();
		}
		
		return dtResponse;
	}
	
	@RequestMapping("/case/list/hp/solve")
	public @ResponseBody DataTablesResponse<TicketResult> getNewTicketsSolve(HttpServletRequest request, final DataTablesRequest dtRequest,@ModelAttribute("searchTicketForm") final SearchTicketForm searchTicketForm){
		DataTablesResponse<TicketResult> dtResponse = new DataTablesResponse<TicketResult>();
		final String echo = dtRequest.getsEcho();
		dtResponse.setsEcho(echo);
		final String[] status = {"COMPLETED1","COMPLETED2","CLOSED","UNRELATED"};
		searchTicketForm.setStatusList(status);
		final Long countAll = ticketService.countAll();
		final Long countSearch = ticketService.countSearch(searchTicketForm);
		try {
//			dtResponse.setAaData(ticketService.getTickets(searchTicketForm,status, dtRequest.getiDisplayStart(), dtRequest.getiDisplayLength()));
			dtResponse.setAaData(ticketService.searchAll(searchTicketForm, dtRequest.getiDisplayStart(), dtRequest.getiDisplayLength()));
			dtResponse.setiTotalDisplayRecords(countSearch.intValue());
			dtResponse.setiTotalRecords(countAll.intValue());
		} catch (final RuntimeException e){
			e.printStackTrace();
		}
		
		return dtResponse;
	}
	
	@RequestMapping("/case/list/hp/close")
	public @ResponseBody DataTablesResponse<TicketResult> getNewTicketsClose(HttpServletRequest request, final DataTablesRequest dtRequest,@ModelAttribute("searchTicketForm") final SearchTicketForm searchTicketForm){
		DataTablesResponse<TicketResult> dtResponse = new DataTablesResponse<TicketResult>();
		final String echo = dtRequest.getsEcho();
		dtResponse.setsEcho(echo);
		final String[] status = {"CLOSED"};
		searchTicketForm.setStatusList(status);
		final Long countAll = ticketService.countAll();
		final Long countSearch = ticketService.countSearch(searchTicketForm);
		try {
//			dtResponse.setAaData(ticketService.getTickets(searchTicketForm,status, dtRequest.getiDisplayStart(), dtRequest.getiDisplayLength()));
			dtResponse.setAaData(ticketService.searchAll(searchTicketForm, dtRequest.getiDisplayStart(), dtRequest.getiDisplayLength()));
			dtResponse.setiTotalDisplayRecords(countSearch.intValue());
			dtResponse.setiTotalRecords(countAll.intValue());
		} catch (final RuntimeException e){
			e.printStackTrace();
		}
		
		return dtResponse;
	}
	
	
	@RequestMapping("/case/list/unassigned")
	public @ResponseBody DataTablesResponse<TicketResult> getUnassignedTickets(HttpServletRequest request, final DataTablesRequest dtRequest,@ModelAttribute("searchTicketForm") final SearchTicketForm searchTicketForm){
		DataTablesResponse<TicketResult> dtResponse = new DataTablesResponse<TicketResult>();
		final String echo = dtRequest.getsEcho();
		dtResponse.setsEcho(echo);
		
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		List<GrantedAuthority> authorities =(List<GrantedAuthority>) authentication.getAuthorities();
		
		
			UserDetail userDetail = (UserDetail) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			List<StaffGroup> sgList= userService.getStaffGroups(userDetail.getProfile());
			String[] groups=new String[sgList.size()];
			int i=0;
			Integer reg=null;
			for(StaffGroup sg:sgList){
				System.out.println("GROUP NAME :"+sg.getGroup().getId());
				groups[i]=sg.getGroup().getId();
				if(sg.getGroup().getId().equals("024")){
					reg=userDetail.getProfile().getRegion().getId();
				}
				i++;
			}
	//		final String[] status = {"RECEIVED","RETURNED","WAITING"};
			final String[] status = {"ASSIGNED","RETURNED","RE-ASSIGNED"};
			System.out.println("GROUP NAME SIZE :"+groups.length);
			Long countAll = new Long(0);
			Long countSearch = new Long(0);
			try {
				countAll = ticketService.countAllUnassigned(groups,reg,status);
				countSearch = ticketService.countSearchUnassigned(searchTicketForm,groups,reg,status);
				dtResponse.setAaData(ticketService.getTicketsUnassigned(searchTicketForm,groups,reg,status, dtRequest.getiDisplayStart(), dtRequest.getiDisplayLength()));
				dtResponse.setiTotalDisplayRecords(countSearch.intValue());
				dtResponse.setiTotalRecords(countAll.intValue());
			} catch (final RuntimeException e){
				e.printStackTrace();
			}
		
		return dtResponse;
	}
	
	@RequestMapping("/case/list/open/{role}")
	public @ResponseBody DataTablesResponse<TicketResult> getOpenTickets(HttpServletRequest request, final DataTablesRequest dtRequest,@PathVariable("role") String role,@ModelAttribute("searchTicketForm") final SearchTicketForm searchTicketForm){
		DataTablesResponse<TicketResult> dtResponse = new DataTablesResponse<TicketResult>();
		final String echo = dtRequest.getsEcho();
		dtResponse.setsEcho(echo);
		
		UserDetail userDetail = (UserDetail) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		final String[] status = {"IN PROGRESS","WAITING"};
		Long countAll = new Long(0);
	    Long countSearch =new Long(0);
	    System.out.println("kolo");
	    try {
	    if(role.equals("supervisor")){
	    	
			countAll = ticketService.countAll(status,userDetail.getProfile().getId());
			countSearch = ticketService.countSearch(searchTicketForm,status,userDetail.getProfile().getId());
			dtResponse.setAaData(ticketService.getTickets(searchTicketForm,status,userDetail.getProfile().getId(),dtRequest.getiDisplayStart(), dtRequest.getiDisplayLength()));
		}
	    if(role.equals("supporter")){
	    	countAll = ticketService.countAllByAssignee(status,userDetail.getProfile().getId());
			countSearch = ticketService.countSearchByAssignee(searchTicketForm,status,userDetail.getProfile().getId());
			dtResponse.setAaData(ticketService.getTicketsByAssignee(searchTicketForm,status,userDetail.getProfile().getId(),dtRequest.getiDisplayStart(), dtRequest.getiDisplayLength()));

	    }
		if(role.equals("manager")){
			
			countAll = ticketService.countAll(status,false);
			countSearch = ticketService.countSearch(searchTicketForm,status,false);
			dtResponse.setAaData(ticketService.getTickets(searchTicketForm,status, dtRequest.getiDisplayStart(), dtRequest.getiDisplayLength(),false));
		}	
			dtResponse.setiTotalDisplayRecords(countSearch.intValue());
			dtResponse.setiTotalRecords(countAll.intValue());
		} catch (final RuntimeException e){
			e.printStackTrace();
		}
		
		return dtResponse;
	}
	@RequestMapping("/case/list/solved/{role}")
	public @ResponseBody DataTablesResponse<TicketResult> getSolvedTickets(HttpServletRequest request, final DataTablesRequest dtRequest,@PathVariable("role") String role,@ModelAttribute("searchTicketForm") final SearchTicketForm searchTicketForm){
		DataTablesResponse<TicketResult> dtResponse = new DataTablesResponse<TicketResult>();
		final String echo = dtRequest.getsEcho();
		dtResponse.setsEcho(echo);
		final String[] status = {"SOLVED"};
		UserDetail userDetail = (UserDetail) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		
		Long countAll = new Long(0);
	    Long countSearch =new Long(0);
	    try {
	    	if(role.equals("supervisor")){
	    		countAll = ticketService.countAll(status,userDetail.getProfile().getId());
	    		countSearch = ticketService.countSearch(searchTicketForm,status,userDetail.getProfile().getId());
	    		dtResponse.setAaData(ticketService.getTickets(searchTicketForm,status,userDetail.getProfile().getId(), dtRequest.getiDisplayStart(), dtRequest.getiDisplayLength()));
	    	}
		    if(role.equals("supporter")){
		    	countAll = ticketService.countAllByAssignee(status,userDetail.getProfile().getId());
				countSearch = ticketService.countSearchByAssignee(searchTicketForm,status,userDetail.getProfile().getId());
				dtResponse.setAaData(ticketService.getTicketsByAssignee(searchTicketForm,status,userDetail.getProfile().getId(),dtRequest.getiDisplayStart(), dtRequest.getiDisplayLength()));

		    }
	    	if(role.equals("manager")){
	    		countAll = ticketService.countAll(status,false);
	    		countSearch = ticketService.countSearch(searchTicketForm,status,false);
	    		dtResponse.setAaData(ticketService.getTickets(searchTicketForm,status, dtRequest.getiDisplayStart(), dtRequest.getiDisplayLength(),false));
	    	}
			
			dtResponse.setiTotalDisplayRecords(countSearch.intValue());
			dtResponse.setiTotalRecords(countAll.intValue());
		} catch (final RuntimeException e){
			e.printStackTrace();
		}
		
		return dtResponse;
	}
	@RequestMapping("/case/list/closed/{role}")
	public @ResponseBody DataTablesResponse<TicketResult> getClosedTickets(HttpServletRequest request, final DataTablesRequest dtRequest,@PathVariable("role") String role,@ModelAttribute("searchTicketForm") final SearchTicketForm searchTicketForm){
		DataTablesResponse<TicketResult> dtResponse = new DataTablesResponse<TicketResult>();
		final String echo = dtRequest.getsEcho();
		dtResponse.setsEcho(echo);
		final String[] status = {"CLOSED"};
UserDetail userDetail = (UserDetail) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		
		Long countAll = new Long(0);
	    Long countSearch =new Long(0);
	    try {
	    	if(role.equals("supervisor")){
	    		countAll = ticketService.countAll(status,userDetail.getProfile().getId());
	    		countSearch = ticketService.countSearch(searchTicketForm,status,userDetail.getProfile().getId());
	    		dtResponse.setAaData(ticketService.getTickets(searchTicketForm,status,userDetail.getProfile().getId(), dtRequest.getiDisplayStart(), dtRequest.getiDisplayLength()));
	    	}
	    	if(role.equals("manager")){
	    		countAll = ticketService.countAll(status,false);
	    		countSearch = ticketService.countSearch(searchTicketForm,status,false);
	    		dtResponse.setAaData(ticketService.getTickets(searchTicketForm,status, dtRequest.getiDisplayStart(), dtRequest.getiDisplayLength(),false));
	    	}
			
			dtResponse.setiTotalDisplayRecords(countSearch.intValue());
			dtResponse.setiTotalRecords(countAll.intValue());
		} catch (final RuntimeException e){
			e.printStackTrace();
		}
		
		return dtResponse;
	}
	
	@RequestMapping("/case/list/mygroup")
	public @ResponseBody DataTablesResponse<TicketResult> getMygroupTickets(HttpServletRequest request, final DataTablesRequest dtRequest,@ModelAttribute("searchTicketForm") final SearchTicketForm searchTicketForm){
		DataTablesResponse<TicketResult> dtResponse = new DataTablesResponse<TicketResult>();
		final String echo = dtRequest.getsEcho();
		dtResponse.setsEcho(echo);
		UserDetail userDetail = (UserDetail) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		List<StaffGroup> sgList= userService.getStaffGroups(userDetail.getProfile());
		String[] groups=new String[sgList.size()];
		int i=0;
		Integer reg=null;
		for(StaffGroup sg:sgList){
			System.out.println("GROUP NAME :"+sg.getGroup().getId());
			groups[i]=sg.getGroup().getId();
			if(sg.getGroup().getId().equals("024")){
				reg=userDetail.getProfile().getRegion().getId();
			}
			i++;
		}
		final String[] status = {"ASSIGNED","IN PROGRESS","WAITING","SOLVED","RETURNED","REJECTED","RE-ASSIGNED"};
		System.out.println("GROUP NAME SIZE :"+groups.length);
		final Long countAll = ticketService.countAllByGroup(groups,reg,status);
		final Long countSearch = ticketService.countSearchByGroup(searchTicketForm,groups,reg,status);
		try {
			dtResponse.setAaData(ticketService.getTicketsByGroup(searchTicketForm,groups,reg,status, dtRequest.getiDisplayStart(), dtRequest.getiDisplayLength()));
			dtResponse.setiTotalDisplayRecords(countSearch.intValue());
			dtResponse.setiTotalRecords(countAll.intValue());
		} catch (final RuntimeException e){
			e.printStackTrace();
		}
		
		return dtResponse;
	}
	
	
	@RequestMapping("/case/list/mycases")
	public @ResponseBody DataTablesResponse<TicketResult> getMycasesTickets(HttpServletRequest request, final DataTablesRequest dtRequest,@ModelAttribute("searchTicketForm") final SearchTicketForm searchTicketForm){
		DataTablesResponse<TicketResult> dtResponse = new DataTablesResponse<TicketResult>();
		final String echo = dtRequest.getsEcho();
		dtResponse.setsEcho(echo);
		UserDetail userDetail = (UserDetail) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		
		final Long countAll = ticketService.countAllByAssignee(userDetail.getProfile().getId());
		final Long countSearch = ticketService.countSearchByAssignee(searchTicketForm, userDetail.getProfile().getId());
		try {
			dtResponse.setAaData(ticketService.getTicketsByAssignee(searchTicketForm,userDetail.getProfile().getId(), dtRequest.getiDisplayStart(), dtRequest.getiDisplayLength()));
			dtResponse.setiTotalDisplayRecords(countSearch.intValue());
			dtResponse.setiTotalRecords(countAll.intValue());
		} catch (final RuntimeException e){
			e.printStackTrace();
		}
		
		return dtResponse;
	}
	
	@RequestMapping("/case/list/activities/{assignmentId}")
	public @ResponseBody DataTablesResponse<ActivityResult> getActivityList(HttpServletRequest request,@PathVariable("assignmentId") Integer assignmentId, final DataTablesRequest dtRequest,@ModelAttribute("activityForm") final ActivityForm activityForm){
		DataTablesResponse<ActivityResult> dtResponse = new DataTablesResponse<ActivityResult>();
		final String echo = dtRequest.getsEcho();
		dtResponse.setsEcho(echo);
		
		final Long countAll = ticketService.countAllActivities(assignmentId);
		final Long countSearch = ticketService.countSearchActivities(activityForm,assignmentId);
		try {
			dtResponse.setAaData(ticketService.getTicketActivities(activityForm,assignmentId,dtRequest.getiDisplayStart(), dtRequest.getiDisplayLength()));
			dtResponse.setiTotalDisplayRecords(countSearch.intValue());
			dtResponse.setiTotalRecords(countAll.intValue());
		} catch (final RuntimeException e){
			e.printStackTrace();
		}
		
		return dtResponse;
	}
	
	@RequestMapping("/case/list/activitiesHD/{ticketId}")
	public @ResponseBody DataTablesResponse<ActivityResult> getActivityHDList(HttpServletRequest request,@PathVariable("ticketId") String ticketId, final DataTablesRequest dtRequest,@ModelAttribute("activityForm") final ActivityForm activityForm){
		DataTablesResponse<ActivityResult> dtResponse = new DataTablesResponse<ActivityResult>();
		final String echo = dtRequest.getsEcho();
		dtResponse.setsEcho(echo);
		
		final Long countAll = ticketService.countAllActivities(ticketId);
		final Long countSearch = ticketService.countSearchActivities(activityForm,ticketId);
		try {
			dtResponse.setAaData(ticketService.getTicketActivities(activityForm,ticketId,dtRequest.getiDisplayStart(), dtRequest.getiDisplayLength()));
			dtResponse.setiTotalDisplayRecords(countSearch.intValue());
			dtResponse.setiTotalRecords(countAll.intValue());
		} catch (final RuntimeException e){
			e.printStackTrace();
		}
		
		return dtResponse;
	}
	
	@RequestMapping("/case/list/assignments/{ticketId}")
	public @ResponseBody DataTablesResponse<TicketResult> getAssignmentList(HttpServletRequest request,@PathVariable("ticketId") String ticketId, final DataTablesRequest dtRequest,@ModelAttribute("searchTicketForm") final SearchTicketForm searchTicketForm){
		DataTablesResponse<TicketResult> dtResponse = new DataTablesResponse<TicketResult>();
		final String echo = dtRequest.getsEcho();
		dtResponse.setsEcho(echo);
		
		final Long countAll = ticketService.countAllAssignments(ticketId);
		final Long countSearch = ticketService.countSearchAssignments(searchTicketForm,ticketId);
		try {
			dtResponse.setAaData(ticketService.getTicketAssignments(searchTicketForm,ticketId,dtRequest.getiDisplayStart(), dtRequest.getiDisplayLength()));
			dtResponse.setiTotalDisplayRecords(countSearch.intValue());
			dtResponse.setiTotalRecords(countAll.intValue());
		} catch (final RuntimeException e){
			e.printStackTrace();
		}
		
		return dtResponse;
	}
	
	@RequestMapping(value = "/complaint/file/{id}", method = RequestMethod.GET)
	public ResponseEntity<byte[]> file(Model model,@PathVariable("id") final int id,HttpSession session) throws Exception{
		TicketFile file= complaintService.getTicketFile(id);
		byte[] imageData = file.getData();
        final HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.parseMediaType(file.getMime()));
       //headers.setContentDispositionFormData("inline", listFiles.get(data).getName());
        return new ResponseEntity<byte[]>(imageData, headers, HttpStatus.CREATED);
	}
	
	@RequestMapping("/complaint/count/new")
	public @ResponseBody String countNew(){
		final String[] status = {"NEW"};
		SearchTicketForm form=new SearchTicketForm();
		form.setStatusList(status);
		final Long countAll = ticketService.countSearch(form);
		return countAll.toString();
	}
	
	@RequestMapping("/complaint/count/unassigned")
	public @ResponseBody String countUnassigned(){
		final String[] status = {"ASSIGNED","RE-ASSIGNED","RETURNED","REJECTED"};
		final Long countAll = ticketService.countAll(status,true);
		return countAll.toString();
	}
	@RequestMapping("/complaint/count/open")
	public @ResponseBody String countOpen(){
		final String[] status = {"RECEIVED","RE-OPEN"};
		//final Long countAll = ticketService.countAll(status,false);
		SearchTicketForm form=new SearchTicketForm();
		form.setStatusList(status);
		final Long countAll = ticketService.countSearch(form);
		return countAll.toString();
	}
	@RequestMapping("/complaint/count/solves")
	public @ResponseBody String countSolved(){
		final String[] status = {"COMPLETED1","COMPLETED2","CLOSED","UNRELATED"};
	//	final Long countAll = ticketService.countAll(status,false);
		SearchTicketForm form=new SearchTicketForm();
		form.setStatusList(status);
		final Long countAll = ticketService.countSearch(form);
		return countAll.toString();
	}
	@RequestMapping("/complaint/count/closed")
	public @ResponseBody String countClosed(){
		final String[] status = {"CLOSED"};
		//final Long countAll = ticketService.countAll(status,false);
		SearchTicketForm form=new SearchTicketForm();
		form.setStatusList(status);
		final Long countAll = ticketService.countSearch(form);
		return countAll.toString();
	}
	
	@RequestMapping("/complaint/supervisor/count/unassigned")
	public @ResponseBody String countSvUnassigned(){
		UserDetail userDetail = (UserDetail) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		List<StaffGroup> sgList= userService.getStaffGroups(userDetail.getProfile());
		String[] groups=new String[sgList.size()];
		int i=0;
		Integer reg=null;
		for(StaffGroup sg:sgList){
			groups[i]=sg.getGroup().getId();
			if(sg.getGroup().getId().equals("024")){
				reg=userDetail.getProfile().getRegion().getId();
			}
			i++;
		}
		final String[] status = {"ASSIGNED","RE-ASSIGNED","RETURNED","REJECTED"};
		final Long countAll = ticketService.countAllUnassigned(groups,reg,status);
		return countAll.toString();
	}
	
	@RequestMapping("/complaint/supervisor/count/open")
	public @ResponseBody String countSvOpen(){
		final String[] status = {"ASSIGNED","RETURNED","RE-ASSIGNED"};
		UserDetail userDetail = (UserDetail) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		final Long countAll = ticketService.countAll(status,userDetail.getProfile().getId());
		return countAll.toString();
	}
	@RequestMapping("/complaint/supervisor/count/solved")
	public @ResponseBody String countSvSolved(){
		final String[] status = {"SOLVED"};
		UserDetail userDetail = (UserDetail) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		final Long countAll = ticketService.countAll(status,userDetail.getProfile().getId());
		return countAll.toString();
	}
	
	@RequestMapping("/complaint/supporter/count/open")
	public @ResponseBody String countSpOpen(){
		final String[] status = {"IN PROGRESS","WAITING"};
		UserDetail userDetail = (UserDetail) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		final Long countAll = ticketService.countAllByAssignee(status,userDetail.getProfile().getId());
		return countAll.toString();
	}
	@RequestMapping("/complaint/supporter/count/solved")
	public @ResponseBody String countSpSolved(){
		final String[] status = {"SOLVED"};
		UserDetail userDetail = (UserDetail) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		final Long countAll = ticketService.countAllByAssignee(status,userDetail.getProfile().getId());
		return countAll.toString();
	}
	
	@RequestMapping("/complaint/supervisor/count/closed")
	public @ResponseBody String countSvClosed(){
		final String[] status = {"CLOSED"};
		UserDetail userDetail = (UserDetail) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		final Long countAll = ticketService.countAll(status,userDetail.getProfile().getId());
		return countAll.toString();
	}
	@RequestMapping("/complaint/count/mygroup")
	public @ResponseBody String countMygroup(){
		UserDetail userDetail = (UserDetail) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		List<StaffGroup> sgList= userService.getStaffGroups(userDetail.getProfile());
		String[] groups=new String[sgList.size()];
		int i=0;
		Integer reg=null;
		for(StaffGroup sg:sgList){
			groups[i]=sg.getGroup().getId();
			if(sg.getGroup().getId().equals("024")){
				reg=userDetail.getProfile().getRegion().getId();
			}
			i++;
		}
		final String[] status = {"ASSIGNED","IN PROGRESS","WAITING","SOLVED","RETURNED","REJECTED","RE-ASSIGNED"};
		final Long countAll = ticketService.countAllByGroup(groups,reg,status);
		return countAll.toString();
	}
	@RequestMapping("/complaint/count/mycases")
	public @ResponseBody String countMycases(){
		UserDetail userDetail = (UserDetail) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		final Long countAll = ticketService.countAllByAssignee(userDetail.getProfile().getId());
		return countAll.toString();
	}
	
	@RequestMapping(value = "/case/knowledge/transfer", method = RequestMethod.GET)
	public ModelAndView transfer(Locale locale, @ModelAttribute("complaintForm") ComplaintForm complaintForm,BindingResult result) {
		ModelAndView mav = new ModelAndView("knowledge/form");
		KnowledgeForm knowledgeForm= complaintService.transferToKnowledgeBase(complaintForm);
		
		mav.addObject("knowledgeCategoryList", codeService.getKnowledgeCategoryList());
		mav.addObject("knowledgeForm", knowledgeForm);
	
		return mav;
	}
	
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		final SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		dateFormat.setLenient(false);
		binder.registerCustomEditor(Date.class, new CustomDateEditor(
				dateFormat, true));
		
		
		binder.registerCustomEditor(byte[].class,
				new ByteArrayMultipartFileEditor());
	}
}
