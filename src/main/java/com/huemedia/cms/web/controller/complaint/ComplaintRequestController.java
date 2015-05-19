package com.huemedia.cms.web.controller.complaint;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.beans.propertyeditors.CustomNumberEditor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
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

import com.huemedia.cms.model.entity.TicketCategory;
import com.huemedia.cms.security.service.UserDetail;
import com.huemedia.cms.service.CodeService;
import com.huemedia.cms.service.ComplaintService;
import com.huemedia.cms.service.TicketService;
import com.huemedia.cms.web.controller.complaint.datatables.TicketResult;
import com.huemedia.cms.web.controller.datatables.DataTablesRequest;
import com.huemedia.cms.web.controller.datatables.DataTablesResponse;
import com.huemedia.cms.web.form.ActivityForm;
import com.huemedia.cms.web.form.ChatForm;
import com.huemedia.cms.web.form.ComplaintForm;
import com.huemedia.cms.web.form.SearchTicketForm;

@Controller
public class ComplaintRequestController {
	
	@Autowired
	CodeService codeService;
	@Autowired
	ComplaintService complaintService;
	@Autowired
	TicketService ticketService;
	
	@RequestMapping(value = "/complaint/request", method = RequestMethod.GET)
	public ModelAndView home(Locale locale) {
		final ModelAndView mav = new ModelAndView("complaint/request/form");
		ComplaintForm complaintForm = new ComplaintForm();
		mav.addObject("complaintForm", complaintForm);
		mav.addObject("ticketGroupList", codeService.getTicketGroupList());
		mav.addObject("priorityList", codeService.getPriorityList());
		mav.addObject("categoryList", codeService.getCategoryList());
		return mav;
	}
	
	@RequestMapping(value = "/complaint/request/view/{id}", method = RequestMethod.GET)
	public ModelAndView view(Locale locale,@PathVariable("id") String id) {
		final ModelAndView mav = new ModelAndView("complaint/request/view");
		ComplaintForm complaintForm = new ComplaintForm();
		complaintForm = complaintService.findComplaintByID(id);
		mav.addObject("ticketGroupList", codeService.getTicketGroupList());
	//	mav.addObject("ticketCategoryList", codeService.getTicketCategoryList(complaintForm.getTicketGroupId()));
		mav.addObject("priorityList", codeService.getPriorityList());
		mav.addObject("statusList", codeService.getStatusList());
		mav.addObject("originatorList", codeService.getOriginatorList());
		mav.addObject("priorityList", codeService.getPriorityList());
		mav.addObject("groupList", codeService.getGroupList());
		mav.addObject("ratingList", codeService.getRatingList());
		
	
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
		
		ChatForm chatForm=new ChatForm();
		chatForm.setTicketId(complaintForm.getId());
		mav.addObject("chatForm", chatForm);
		mav.addObject("complaintForm", complaintForm);
		
		ActivityForm activityForm = new ActivityForm();
		mav.addObject("activityForm", activityForm);
		return mav;
	}
	@RequestMapping("/complaint/request/list/ticketCategory/{ticketGroupId}")
	public @ResponseBody String[][] getTimeEndList(@PathVariable("ticketGroupId") Integer ticketGroupId){
		List<String[]> list = new ArrayList<String[]>();
		
		List<TicketCategory> listTicketCategory = codeService.getTicketCategoryList(ticketGroupId);
		for(TicketCategory obj:listTicketCategory){
			String id = obj.getId().toString();
			String label = obj.getName();
			String[] row = {id, label};
			list.add(row);
		}
		return list.toArray(new String[list.size()][2]);
	}
	
	@RequestMapping(value ="/complaint/request/save",method = RequestMethod.POST)
	public @ResponseBody String getRequestSave(final Locale locale, @ModelAttribute("complaintForm") ComplaintForm complaintForm,BindingResult result) throws ParseException{
		complaintForm.setStatusId("NEW");
		complaintForm.setOriginatorId(6);
		//complaintForm.setPriorityId("L");
		UserDetail userDetail = (UserDetail) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		complaintForm.setProfile(userDetail.getProfile());
		complaintService.requestNewComplaint(complaintForm,locale);
		return "success";
	}
	
	@RequestMapping(value ="/complaint/request/update",method = RequestMethod.POST)
	public @ResponseBody String getRequestUpdate(final Locale locale, @ModelAttribute("complaintForm") ComplaintForm complaintForm,BindingResult result) throws ParseException{
		complaintService.updateFeedback(complaintForm,locale);
		return "success";
	}
	
	@RequestMapping(value = "/complaint/request/mycomplaints", method = RequestMethod.GET)
	public ModelAndView mycasesList(Locale locale) {
		final ModelAndView mav = new ModelAndView("complaint/request/mycomplaintsList");
		SearchTicketForm searchTicketForm = new SearchTicketForm();
		mav.addObject("searchTicketForm", searchTicketForm);
		return mav;
	}
	@RequestMapping("/complaint/request/list/mycomplaints")
	public @ResponseBody DataTablesResponse<TicketResult> getMycomplaints(HttpServletRequest request, final DataTablesRequest dtRequest,@ModelAttribute("searchTicketForm") final SearchTicketForm searchTicketForm){
		DataTablesResponse<TicketResult> dtResponse = new DataTablesResponse<TicketResult>();
		final String echo = dtRequest.getsEcho();
		dtResponse.setsEcho(echo);
		UserDetail userDetail = (UserDetail) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		
		final Long countAll = ticketService.countAllByProfileId(userDetail.getProfile().getId());
		final Long countSearch = ticketService.countSearchByProfileId(searchTicketForm, userDetail.getProfile().getId());
		try {
			dtResponse.setAaData(ticketService.getTicketsByProfileId(searchTicketForm,userDetail.getProfile().getId(), dtRequest.getiDisplayStart(), dtRequest.getiDisplayLength()));
			dtResponse.setiTotalDisplayRecords(countSearch.intValue());
			dtResponse.setiTotalRecords(countAll.intValue());
		} catch (final RuntimeException e){
			e.printStackTrace();
		}
		
		return dtResponse;
	}
	@RequestMapping("/complaint/count/mycomplaints")
	public @ResponseBody String countMycomplaints(){
		UserDetail userDetail = (UserDetail) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		final Long countAll = ticketService.countAllByProfileId(userDetail.getProfile().getId());
		return countAll.toString();
	}
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		final SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		dateFormat.setLenient(false);
		binder.registerCustomEditor(Date.class, new CustomDateEditor(
				dateFormat, true));
		binder.registerCustomEditor(byte[].class,
				new ByteArrayMultipartFileEditor());	
        DecimalFormat formatter = new DecimalFormat("#,###.00");
        binder.registerCustomEditor(Double.class,
        		new CustomNumberEditor(Double.class, formatter, true));
	}
}
