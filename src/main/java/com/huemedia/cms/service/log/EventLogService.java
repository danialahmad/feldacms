package com.huemedia.cms.service.log;

import java.util.List;
import java.util.Locale;

import com.huemedia.cms.model.entity.EventLog;
import com.huemedia.cms.model.entity.TicketActivity;
import com.huemedia.cms.web.form.ActivityForm;
import com.huemedia.cms.web.form.ComplaintForm;

public interface EventLogService {

	void createCase();
	void logTicketEvent(ComplaintForm complaintForm,Locale locale);
	void logAssignmentEvent(ComplaintForm complaintForm,Locale locale);
	void logActivityEvent(ActivityForm activityForm,TicketActivity ticketActivity);
	
	List<EventLog> findEventsByTicketID(String ticketId);
	List<EventLog> findEventsByAssignmentID(Integer id);
}
