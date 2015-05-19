package com.huemedia.cms.model.dao;

import java.util.List;

import com.huemedia.cms.model.entity.TicketActivity;
import com.huemedia.cms.web.form.ActivityForm;

public interface TicketActivityDAO {
	  Long countAll(Integer assignmentId);
	   Long countSearch(ActivityForm activityForm,Integer assignmentId);
	   List<TicketActivity> search(ActivityForm activityForm,Integer assignmentId,Integer iDisplayStart,
				Integer iDisplayLength);
	   
	   Long countAll(String ticketId);
	   Long countSearch(ActivityForm activityForm,String ticketId);
	   List<TicketActivity> search(ActivityForm activityForm,String ticketId,Integer iDisplayStart,
				Integer iDisplayLength);
}
