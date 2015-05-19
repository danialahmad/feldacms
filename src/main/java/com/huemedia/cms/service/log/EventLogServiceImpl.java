package com.huemedia.cms.service.log;

import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.huemedia.cms.model.entity.ActivityType;
import com.huemedia.cms.model.entity.EventLog;
import com.huemedia.cms.model.entity.Group;
import com.huemedia.cms.model.entity.Profile;
import com.huemedia.cms.model.entity.Ticket;
import com.huemedia.cms.model.entity.TicketActivity;
import com.huemedia.cms.model.entity.TicketAssignment;
import com.huemedia.cms.model.repository.ActivityTypeRepository;
import com.huemedia.cms.model.repository.EventLogRepository;
import com.huemedia.cms.model.repository.GroupRepository;
import com.huemedia.cms.model.repository.ProfileRepository;
import com.huemedia.cms.model.repository.TicketActivityRepository;
import com.huemedia.cms.model.repository.TicketAssignmentRepository;
import com.huemedia.cms.model.repository.TicketRepository;
import com.huemedia.cms.web.form.ActivityForm;
import com.huemedia.cms.web.form.ComplaintForm;

@Service
public class EventLogServiceImpl implements EventLogService {

	@Autowired
	EventLogRepository eventLogRepository;
	@Autowired
	TicketRepository ticketRepository;
	@Autowired
	TicketAssignmentRepository ticketAssignmentRepository;
	@Autowired
	TicketActivityRepository ticketActivityRepository;
	@Autowired
	ProfileRepository profileRepository;
	@Autowired
	ActivityTypeRepository activityTypeRepository;
	@Autowired
	GroupRepository groupRepository;

	@Autowired
    MessageSource messageSource;
	
	@Override
	public void createCase() {
		
	}

	@Override
	@Transactional
	public void logTicketEvent(ComplaintForm complaintForm,Locale locale) {
		EventLog log =new EventLog();
		
		boolean exist=ticketRepository.exists(complaintForm.getId());
		if(exist){
			Ticket t=ticketRepository.findOne(complaintForm.getId());
			log.setTicketId(complaintForm.getId());
			if(!t.getStatus().getId().equals(complaintForm.getStatusId())){
				if(complaintForm.getStatusId().equals("RECEIVED")){
					log.setAction(getLanguage("case.received",locale,complaintForm.getStatus().getName()));
				}
				if(complaintForm.getStatusId().equals("COMPLETED")){
					log.setAction(getLanguage("case.completed",locale,complaintForm.getStatus().getName()));
				}
				if(complaintForm.getStatusId().equals("RE-OPEN")){
					log.setAction(getLanguage("case.reopen",locale,complaintForm.getStatus().getName()));
				}
				if(complaintForm.getStatusId().equals("CLOSED")){
					log.setAction(getLanguage("case.closed",locale,complaintForm.getStatus().getName()));
				}
				
				
					if(log.getTicketId()!=null)
						eventLogRepository.save(log);
				
			}
			
		}else{
			log.setTicketId(complaintForm.getId());
			log.setAction(getLanguage("case.created",locale,complaintForm.getId()));
			eventLogRepository.save(log);
		}
		
		

	}
    
	private String getLanguage(String code,Locale locale,Object... o){
		String s=messageSource.getMessage(code, o, locale);
		return s;
	}
	@Override
	@Transactional
	public void logActivityEvent(ActivityForm activityForm,TicketActivity ticketActivity) {
		EventLog log =new EventLog();
		if(activityForm.getId()!=null){
			
		}else{
			ActivityType at=activityTypeRepository.findOne(activityForm.getActivityTypeId());
			log.setTicketId(activityForm.getTicket().getId());
			log.setAction("New activity created :"+at.getName());
		}
		if(log!=null){
			if(log.getTicketId()!=null){
				eventLogRepository.save(log);
			}
		}
	}

	@Override
	public List<EventLog> findEventsByTicketID(String ticketId) {
		// TODO Auto-generated method stub
		return eventLogRepository.findByTicketId(ticketId);
	}

	@Override
	@Transactional
	public void logAssignmentEvent(ComplaintForm complaintForm, Locale locale) {
		EventLog log =new EventLog();
		log.setTicketId(complaintForm.getId());
		log.setAssignmentId(complaintForm.getAssigmentId());
		
		 
		
		if(complaintForm.getStatusId().equals("ASSIGNED")){
			Group g=groupRepository.findOne(complaintForm.getGroup());
			log.setAction(getLanguage("case.assigned",locale,g.getName(),complaintForm.getStatus().getName()));
			eventLogRepository.save(log);
		}else{
			
			if(complaintForm.getAssigmentId()!=null){
				 TicketAssignment assignment=ticketAssignmentRepository.findOne(complaintForm.getAssigmentId());
					
					if(!complaintForm.getStatusId().equals(assignment.getStatus().getId())){
						if(complaintForm.getStatusId().equals("RE-ASSIGNED")){
							Group g=groupRepository.findOne(complaintForm.getGroup());
							log.setAction(getLanguage("case.reassigned",locale,g.getName(),complaintForm.getStatus().getName()));
						}
						if(complaintForm.getStatusId().equals("REJECTED")){
							Group g=groupRepository.findOne(complaintForm.getGroup());
							log.setAction(getLanguage("case.rejected",locale,g.getName(),complaintForm.getStatus().getName()));
						}
						if(complaintForm.getStatusId().equals("IN PROGRESS")){
							Group g=groupRepository.findOne(complaintForm.getGroup());
							log.setAction(getLanguage("case.inprogress",locale,g.getName(),complaintForm.getStatus().getName()));
						}
						if(complaintForm.getStatusId().equals("WAITING")){
							Group g=groupRepository.findOne(complaintForm.getGroup());
							log.setAction(getLanguage("case.waiting",locale,g.getName(),complaintForm.getStatus().getName()));
						}
						if(complaintForm.getStatusId().equals("RETURNED")){
							Group g=groupRepository.findOne(complaintForm.getGroup());
							log.setAction(getLanguage("case.returned",locale,g.getName(),complaintForm.getStatus().getName()));
						}
						if(complaintForm.getStatusId().equals("SOLVED")){
							Group g=groupRepository.findOne(complaintForm.getGroup());
							log.setAction(getLanguage("case.solved",locale,g.getName(),complaintForm.getStatus().getName()));
						}
						eventLogRepository.save(log);
					}
			
			}
			
		}
		
		
		   
		
		if(complaintForm.getAssigneeId()!=null){
			 TicketAssignment assignment=ticketAssignmentRepository.findOne(complaintForm.getAssigmentId());
		
			 if(!complaintForm.getAssigneeId().equals(assignment.getAssigneeId())){
					Group g=groupRepository.findOne(complaintForm.getGroup());
					EventLog log2 =new EventLog();
					log2.setTicketId(complaintForm.getId());
					log2.setAssignmentId(complaintForm.getAssigmentId());
					Profile p =profileRepository.findOne(complaintForm.getAssigneeId());
			        log2.setAction(getLanguage("case.assignto",locale,p.getName(),p.getStaffNo(),g.getName()));
			        eventLogRepository.save(log2);
				}
		 
			
		}
		

		
		
		
	}

	@Override
	public List<EventLog> findEventsByAssignmentID(Integer id) {
		// TODO Auto-generated method stub
		return eventLogRepository.findByAssignmentId(id);
	}

}
