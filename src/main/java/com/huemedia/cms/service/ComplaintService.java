package com.huemedia.cms.service;

import java.util.Locale;

import com.huemedia.cms.model.entity.Ticket;
import com.huemedia.cms.model.entity.TicketAssignment;
import com.huemedia.cms.model.entity.TicketFile;
import com.huemedia.cms.web.form.ActivityForm;
import com.huemedia.cms.web.form.ComplaintForm;
import com.huemedia.cms.web.form.KnowledgeForm;

public interface ComplaintService {
    
	boolean requestNewComplaint(ComplaintForm complaintForm,Locale locale);
	boolean updateComplaint(ComplaintForm complaintForm,Locale locale);
	boolean saveActivity(ActivityForm activityForm,Locale locale);
	boolean updateActivity(ActivityForm activityForm,Locale locale);
	ActivityForm findActivityByID(Integer id);
	ComplaintForm findComplaintByID(String id);
	TicketFile getTicketFile(Integer id);
	
	boolean updateFeedback(ComplaintForm complaintForm,Locale locale);
	
	boolean saveCaseForHelpdesk(ComplaintForm complaintForm,Locale locale);
	
	boolean saveActions(ComplaintForm complaintForm,Locale locale);
	
	KnowledgeForm transferToKnowledgeBase(ComplaintForm complaintForm);
	
	boolean saveAssignment(ComplaintForm complaintForm,Locale locale);
	TicketAssignment updateAssignment(ComplaintForm complaintForm,Locale locale);
	
	Ticket updateComplaintStatus(ComplaintForm complaintForm,Locale locale);
	boolean updateAssignmentStatus(ComplaintForm complaintForm,Locale locale);
	
	ComplaintForm findAssignmentByID(String ticketId,Integer id);
}
