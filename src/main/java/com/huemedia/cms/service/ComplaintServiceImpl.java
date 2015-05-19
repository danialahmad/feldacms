/**
 * 
 */
package com.huemedia.cms.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;

import org.apache.commons.lang.StringUtils;
import org.joda.time.Interval;
import org.joda.time.Period;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.huemedia.cms.model.dao.TicketAssignmentDAO;
import com.huemedia.cms.model.dao.TicketDAO;
import com.huemedia.cms.model.entity.ActivityType;
import com.huemedia.cms.model.entity.Category;
import com.huemedia.cms.model.entity.Country;
import com.huemedia.cms.model.entity.Group;
import com.huemedia.cms.model.entity.Originator;
import com.huemedia.cms.model.entity.PersonCategory;
import com.huemedia.cms.model.entity.Priority;
import com.huemedia.cms.model.entity.Profile;
import com.huemedia.cms.model.entity.Rating;
import com.huemedia.cms.model.entity.Region;
import com.huemedia.cms.model.entity.Relation;
import com.huemedia.cms.model.entity.State;
import com.huemedia.cms.model.entity.Status;
import com.huemedia.cms.model.entity.Ticket;
import com.huemedia.cms.model.entity.TicketActivity;
import com.huemedia.cms.model.entity.TicketAssignment;
import com.huemedia.cms.model.entity.TicketCategory;
import com.huemedia.cms.model.entity.TicketFile;
import com.huemedia.cms.model.entity.TicketGroup;
import com.huemedia.cms.model.repository.ActivityTypeRepository;
import com.huemedia.cms.model.repository.CategoryRepository;
import com.huemedia.cms.model.repository.CountryRepository;
import com.huemedia.cms.model.repository.GroupRepository;
import com.huemedia.cms.model.repository.OriginatorRepository;
import com.huemedia.cms.model.repository.PersonCategoryRepository;
import com.huemedia.cms.model.repository.PriorityRepository;
import com.huemedia.cms.model.repository.ProfileRepository;
import com.huemedia.cms.model.repository.RatingRepository;
import com.huemedia.cms.model.repository.RegionRepository;
import com.huemedia.cms.model.repository.RelationRepository;
import com.huemedia.cms.model.repository.StateRepository;
import com.huemedia.cms.model.repository.StatusRepository;
import com.huemedia.cms.model.repository.TicketActivityRepository;
import com.huemedia.cms.model.repository.TicketAssignmentRepository;
import com.huemedia.cms.model.repository.TicketCategoryRepository;
import com.huemedia.cms.model.repository.TicketFileRepository;
import com.huemedia.cms.model.repository.TicketGroupRepository;
import com.huemedia.cms.model.repository.TicketRepository;
import com.huemedia.cms.service.log.EventLogService;
import com.huemedia.cms.web.form.ActivityForm;
import com.huemedia.cms.web.form.ChatForm;
import com.huemedia.cms.web.form.ComplaintForm;
import com.huemedia.cms.web.form.KnowledgeForm;

/**
 * @author Danial
 *
 */
@Service
public class ComplaintServiceImpl implements ComplaintService {

	
	@Autowired
	CategoryRepository categoryRepository;
	@Autowired
	TicketRepository ticketRepository;
	@Autowired
	TicketGroupRepository ticketGroupRepository;
	@Autowired
	TicketCategoryRepository ticketCategoryRepository;
	@Autowired
	TicketAssignmentRepository ticketAssignmentRepository;
	@Autowired
	TicketFileRepository ticketFileRepository;
	@Autowired
	OriginatorRepository originatorRepository;
	@Autowired
	StatusRepository statusRepository;
	@Autowired
	PriorityRepository priorityRepository;
	@Autowired
	ProfileRepository profileRepository;
	@Autowired
	StateRepository stateRepository;
	@Autowired
	GroupRepository groupRepository;
	@Autowired
	TicketActivityRepository ticketActivityRepository;
	@Autowired
	IDService idService;
	@Autowired
	TicketDAO ticketDAO;
	@Autowired
	TicketAssignmentDAO ticketAssignmentDAO;
	@Autowired
	ActivityTypeRepository activityTypeRepository;
	@Autowired
	EmailService emailService;
	@Autowired
	RatingRepository ratingRepository;
	@Autowired
	CountryRepository countryRepository;
	@Autowired
	SLAService slaService;
	@Autowired
	EventLogService eventLogService;
	@Autowired
	RegionRepository regionRepository;
	@Autowired
	ChatService chatService;
	@Autowired
	PersonCategoryRepository personCategoryRepository;
	@Autowired
	RelationRepository relationRepository;
	
	private Ticket save(ComplaintForm complaintForm,Locale locale){
		Ticket ticket = new Ticket();
		String id= idService.generateSEQID("F1CC");
		
		if(complaintForm.getTicketGroupId()!=null){
		TicketGroup ticketGroup = ticketGroupRepository.findOne(complaintForm.getTicketGroupId());
		ticket.setTicketGroup(ticketGroup);
		}
		if(complaintForm.getTicketCategoryId()!=null&&complaintForm.getTicketCategoryId()!=-1){
		TicketCategory ticketCategory= ticketCategoryRepository.findOne(complaintForm.getTicketCategoryId());
		ticket.setTicketCategory(ticketCategory);
		}else{
			ticket.setRemarks(complaintForm.getRemarks());
		}
		if(complaintForm.getPriorityId()!=null){
			Priority priority= priorityRepository.findOne(complaintForm.getPriorityId());
			ticket.setPriority(priority);
		}
		if(complaintForm.getCategory()!=null){
			if(complaintForm.getCategory().getId()!=null){
				Category c=categoryRepository.findOne(complaintForm.getCategory().getId());
				ticket.setCategory(c);
			}
		}
		Status status= statusRepository.findOne(complaintForm.getStatusId());
		Originator originator= originatorRepository.findOne(complaintForm.getOriginatorId());
		complaintForm.setId(id);	
		ticket.setId(id);
		ticket.setTicketTitle(complaintForm.getTicketTitle());
		ticket.setDescription(complaintForm.getDescription());
		ticket.setAction(complaintForm.getAction());
		ticket.setTicketType(complaintForm.getTicketType());
		
		ticket.setStatus(status);
		ticket.setOriginator(originator);
		ticket.setProfile(complaintForm.getProfile());
		
		List<TicketFile> listFiles=new ArrayList<TicketFile>();
		try {
			   for(MultipartFile file:complaintForm.getFiles()){
				   TicketFile tf=new TicketFile();
				   tf.setTicket(ticket);
				   tf.setName(file.getOriginalFilename());
				   tf.setData(file.getBytes());
				   tf.setMime(file.getContentType());
				   System.out.println("CONTENT TYPE :"+file.getContentType());
				   listFiles.add(tf);
			   }
		   } catch (IOException e) {
			e.printStackTrace();
		}		

		eventLogService.logTicketEvent(complaintForm,locale);
		
		ticketRepository.save(ticket);
		ticketFileRepository.save(listFiles);
		
	//	SlaTask slaTask=slaService.createSlaTask(ticket);
		//emailService.sendNotiNewCaseToComplainant(complaintForm.getProfile(), ticket);
	//	emailService.sendNotiNewCaseToRole(ticket,slaTask,"ROLE_HELPDESK");
		return ticket;
	}
	
	
	@Override
	@Transactional
	public boolean requestNewComplaint(ComplaintForm complaintForm,Locale locale) {
		Ticket ticket=this.save(complaintForm,locale);
		emailService.sendComplaintEmail(ticket);
		return false;
	}
	

	@Override
	public ComplaintForm findComplaintByID(String id) {
		ComplaintForm complaintForm = new ComplaintForm();
		Ticket ticket = ticketRepository.findOne(id);
		complaintForm.setId(id);
		complaintForm.setTicketTitle(ticket.getTicketTitle());
		complaintForm.setDescription(ticket.getDescription());
		complaintForm.setAction(ticket.getAction());
		if(ticket.getTicketGroup()!=null){
		complaintForm.setTicketGroupId(ticket.getTicketGroup().getId());
		TicketGroup ticketGroup = ticketGroupRepository.findOne(ticket.getTicketGroup().getId());
		complaintForm.setTicketGroup(ticketGroup);
		}
		if(ticket.getTicketCategory()!=null){
		complaintForm.setTicketCategoryId(ticket.getTicketCategory().getId());
		TicketCategory ticketCategory= ticketCategoryRepository.findOne(ticket.getTicketCategory().getId());
		complaintForm.setTicketCategory(ticketCategory);
		}
		complaintForm.setRemarks(ticket.getRemarks());
		complaintForm.setStatusId(ticket.getStatus().getId());
		complaintForm.setStatus(statusRepository.findOne(ticket.getStatus().getId()));
		complaintForm.setLastUpdate(ticket.getLastStatusUpdate());
		complaintForm.setInterval(ticket.getIntervalX());
		
		complaintForm.setTicketType(ticket.getTicketType());
		
		
		complaintForm.setCreateDate(ticket.getCreateDate());
		Profile profile = profileRepository.findOne(ticket.getProfile().getId());
		if(profile.getState()!=null){
			State state=stateRepository.findOne(profile.getState().getId());
			profile.setState(state);
		}
		
		
		if(ticket.getSupervisorId()!=null){
		Profile supervisor=profileRepository.findOne(ticket.getSupervisorId());
		complaintForm.setSupervisor(supervisor);
		complaintForm.setSupervisorId(ticket.getSupervisorId());
		complaintForm.setAssignDate(ticket.getAssignDate());
		}
		if(ticket.getRegionId()!=null){
			Region region = regionRepository.findOne(ticket.getRegionId());
			complaintForm.setRegion(region);
		}
			
			
		complaintForm.setProfile(profile);
		
		if(ticket.getGroupId()!=null){
			Group dept=groupRepository.findOne(ticket.getGroupId());
			complaintForm.setGroup(ticket.getGroupId());
			complaintForm.setDepartment(dept);
		
		}
		
		
		
		if(ticket.getAssigneeId()!=null){
			Profile assignee=profileRepository.findOne(ticket.getAssigneeId());
			complaintForm.setAssignee(assignee);
			complaintForm.setAssigneeId(ticket.getAssigneeId());
		}
		
		
		if(ticket.getRating()!=null){
			Rating rating=ratingRepository.findOne(ticket.getRating().getId());
		complaintForm.setRating(rating);
		complaintForm.setComment(ticket.getComment());
		}
		if(ticket.getOriginator()!=null){
			complaintForm.setOriginatorId(ticket.getOriginator().getId());
			Originator originator=originatorRepository.findOne(complaintForm.getOriginatorId());
			complaintForm.setOriginator(originator);
		}
		if(ticket.getPriority()!=null){
			complaintForm.setPriorityId(ticket.getPriority().getId());
			Priority priority=priorityRepository.findOne(complaintForm.getPriorityId());
			complaintForm.setPriority(priority);
		}
		Category c=new Category();
		if(ticket.getCategory()!=null){
			c=categoryRepository.findOne(ticket.getCategory().getId());
		}
		complaintForm.setCategory(c);
		List<TicketFile> listFiles= ticketFileRepository.findIdAndNameByTicket(ticket);
		complaintForm.setTicketFiles(listFiles);
		
		return complaintForm;
	}


	@Override
	public TicketFile getTicketFile(Integer id) {
		TicketFile file= ticketFileRepository.findOne(id);
		return file;
	}


	
	
	@Override
	@Transactional
	public boolean updateComplaint(ComplaintForm complaintForm,Locale locale) {
		Ticket ticket = ticketRepository.findOne(complaintForm.getId());
		Status status= statusRepository.findOne(complaintForm.getStatusId());
		
		//eventLogService.logTicketEvent(complaintForm,ticket);
		boolean send=sendEmail(complaintForm,ticket);
		if(complaintForm.getOriginatorId()!=null){
			System.out.println("ORI:"+complaintForm.getOriginatorId());
		Originator originator= originatorRepository.findOne(complaintForm.getOriginatorId());
		ticket.setOriginator(originator);
		}
		
		if(complaintForm.getPriorityId()!=null){
		Priority priority= priorityRepository.findOne(complaintForm.getPriorityId());
		ticket.setPriority(priority);
		}
		
		if(complaintForm.getTicketGroupId()!=null){
		TicketGroup ticketGroup = ticketGroupRepository.findOne(complaintForm.getTicketGroupId());
		ticket.setTicketGroup(ticketGroup);
		}
		
		if(complaintForm.getTicketCategoryId()!=null && complaintForm.getTicketCategoryId()!=-1){
		TicketCategory ticketCategory= ticketCategoryRepository.findOne(complaintForm.getTicketCategoryId());
		ticket.setTicketCategory(ticketCategory);
		ticket.setRemarks(null);
		}else{
			ticket.setRemarks(complaintForm.getRemarks());
		}
		
		
		if(ticket.getStatus()!=null){
			if(!complaintForm.getStatusId().equals(ticket.getStatus().getId())){
				ticket.setLastStatusUpdate(new Date());
				
				if(complaintForm.getStatusId().equals("RECEIVED")){
					if(complaintForm.getGroup()!=null){
						ticket.setGroupId(complaintForm.getGroup());
					}
					if(complaintForm.getRegion()!=null){
						if(complaintForm.getRegion().getId()!=null){
							ticket.setRegionId(complaintForm.getRegion().getId());
						}
					}
					if(ticket.getStatus().equals("NEW")){
						ticket.setReplyDate(new Date());
					}
					
				}
				
				if(complaintForm.getStatusId().equals("WAITING")){
					if(ticket.getStatus().equals("NEW")){
						ticket.setReplyDate(new Date());
					}
				}
				
				if(complaintForm.getStatusId().equals("REJECTED")){
					if(ticket.getStatus().equals("NEW")){
						ticket.setReplyDate(new Date());
					}
					if(complaintForm.getGroup()!=null){
						ticket.setGroupId(null);
					}
					if(complaintForm.getSupervisorId()!=null){
						ticket.setSupervisorId(null);
					}
					
					if(complaintForm.getAssigneeId()!=null){
						ticket.setAssigneeId(null);
					}
					ticket.setAssignDate(null);
					ticket.setResolutionDate(null);
					ticket.setIntervalX(null);
					ticket.setDuration(null);
					ticket.setRating(null);
					ticket.setComment(null);
				}
				
				
				if(complaintForm.getStatusId().equals("RETURNED")){
					
					if(ticket.getSupervisorId()!=null){
						 ticket.setSupervisorId(null);
						
					}
					if(complaintForm.getAssigneeId()!=null){
						ticket.setAssigneeId(null);
					}
					ticket.setAssignDate(null);
					ticket.setResolutionDate(null);
					ticket.setIntervalX(null);
					ticket.setDuration(null);
					ticket.setRating(null);
					ticket.setComment(null);
				
				}
				if(complaintForm.getStatusId().equals("COMPLETED")){
					
					ticket.setResolutionDate(new Date());
					ticket.setIntervalX(this.calculateBetweenDate(ticket.getCreateDate(), new Date()));
					long newD=new Date().getTime();
					Integer interval =(int) (newD-ticket.getCreateDate().getTime());
					ticket.setDuration(interval);
				}
		
			}
		
		}
		
		if(complaintForm.getStatusId().equals("ASSIGNED")||complaintForm.getStatusId().equals("RE-ASSIGNED")){
			
			
			if(complaintForm.getSupervisorId()!=null){
				ticket.setSupervisorId(complaintForm.getSupervisorId());
			}
			
			if(complaintForm.getAssigneeId()!=null){
				if(ticket.getAssigneeId()!=null){
					if(!ticket.getAssigneeId().equals(complaintForm.getAssigneeId())){
						ticket.setAssigneeId(complaintForm.getAssigneeId());
						ticket.setAssignDate(new Date());
					}else{
						if(ticket.getStatus()!=null){
							if(!complaintForm.getStatusId().equals(ticket.getStatus().getId())){
								ticket.setAssigneeId(complaintForm.getAssigneeId());
								ticket.setAssignDate(new Date());
							}
						}
					}
					
				}else{
					ticket.setAssigneeId(complaintForm.getAssigneeId());
					ticket.setAssignDate(new Date());
				}
				
				
			}
			
		
		}
		
		ticket.setStatus(status);	
		ticketRepository.save(ticket);
	//	SlaTask slaTask=slaService.updateSlaTask(ticket, "ON");
		
		
		if(send){
			try{
				emailService.sendComplaintEmail(ticket);	
			}catch(Exception e){
				e.printStackTrace();
			}
			
		}
		
		return false;
	}
	
	private boolean sendEmail(ComplaintForm form,Ticket ticket){
		boolean send=false;
		if(form.getStatusId().equals(ticket.getStatus().getId())){
			if(form.getStatusId().equals("ASSIGNED")||form.getStatusId().equals("RE-ASSIGNED")){
				if(ticket.getAssigneeId()!=null){
					if(!ticket.getAssigneeId().equals(form.getAssigneeId())){
						send=true;
					}
					
				}
			}
		}else{
			send=true;
		}
		
		
		
		return send;
	}


	 private int daysBetween(Date startDate, Date endDate){
		 Calendar startCal=new GregorianCalendar();
		 Calendar endCal=new GregorianCalendar();

		 startCal.setTime(startDate);
		 endCal.setTime(endDate);

		 endCal.add(Calendar.YEAR,-startCal.get(Calendar.YEAR));
		 endCal.add(Calendar.MONTH,-startCal.get(Calendar.MONTH));
		 endCal.add(Calendar.DATE,-startCal.get(Calendar.DATE));

		 int daysDifference=endCal.get(Calendar.DAY_OF_YEAR);
		 return daysDifference;
	}
	 
	 private String calculateBetweenDate(Date startDate, Date endDate){
			String result=null;
			 Interval interval = new Interval(startDate.getTime(),endDate.getTime());
			 Period period = interval.toPeriod();
			 StringBuilder builder = new StringBuilder();
			 if(period.getYears()!=0){
				 result=period.getYears()+" Year(s), ";
				 builder.append(result);
			 }
			 if(period.getMonths()!=0){
				 result=period.getMonths()+" Month(s), ";
				 builder.append(result);
			 }
			 if(period.getDays()!=0){
				 result=period.getDays()+" Day(s), ";
				 builder.append(result);
			 }
			 if(period.getHours()!=0){
				 result=period.getHours()+" Hour(s), ";
				 builder.append(result);
			 }
			 result=period.getMinutes()+" Minute(s)";
			 builder.append(result);
			 
			 
			
			return builder.toString();
		}
	
	
	@Override
	@Transactional
	public boolean saveActivity(ActivityForm activityForm,Locale locale) {
		TicketActivity ticketActivity = new TicketActivity();
		Ticket ticket= ticketRepository.findOne(activityForm.getTicket().getId());
		ticketActivity.setTicket(ticket);
		if(activityForm.getAssignment()!=null){
			if(activityForm.getAssignment().getId()!=null){
				TicketAssignment ta= ticketAssignmentRepository.findOne(activityForm.getAssignment().getId());
				ticketActivity.setTicketAssignment(ta);
			}
		}
		ticketActivity.setDate(activityForm.getDate());
		ticketActivity.setDescription(activityForm.getDescription());
		
		ActivityType activityType=activityTypeRepository.findOne(activityForm.getActivityTypeId());
		
		ticketActivity.setActivityType(activityType);
		ticketActivity.setStartTime(activityForm.getStartTime());
		ticketActivity.setEndTime(activityForm.getEndTime());
		
		ticketActivity.setLocation(activityForm.getLocation());
		ticketActivity.setAgenda(activityForm.getAgenda());
		
		if(activityForm.getTo()!=null&&activityForm.getTo().length>0){
			String to= StringUtils.join(activityForm.getTo(),',');
			ticketActivity.setSendTo(to);
			}
	    ticketActivity.setSubject(activityForm.getSubject());
		ticketActivity.setMessage(activityForm.getMessage());
		eventLogService.logActivityEvent(activityForm, ticketActivity);
		ticketActivityRepository.save(ticketActivity);
		if(activityForm.getActivityTypeId().equals(3)){
			emailService.sendEmailForActivity(ticketActivity);
		}
		return false;
	}


	@Override
	@Transactional
	public boolean updateActivity(ActivityForm activityForm,Locale locale) {
		TicketActivity ticketActivity = ticketActivityRepository.findOne(activityForm.getId());
		ticketActivity.setDate(activityForm.getDate());
		ticketActivity.setDescription(activityForm.getDescription());
		
		ActivityType activityType=activityTypeRepository.findOne(activityForm.getActivityTypeId());
		
		ticketActivity.setActivityType(activityType);
		ticketActivity.setStartTime(activityForm.getStartTime());
		ticketActivity.setEndTime(activityForm.getEndTime());
		
		ticketActivity.setLocation(activityForm.getLocation());
		ticketActivity.setAgenda(activityForm.getAgenda());
		if(activityForm.getTo()!=null&&activityForm.getTo().length>0){
		String to= StringUtils.join(activityForm.getTo(),',');
		ticketActivity.setSendTo(to);
		}
	    ticketActivity.setSubject(activityForm.getSubject());
		ticketActivity.setMessage(activityForm.getMessage());
		
		
		ticketActivityRepository.save(ticketActivity);
		if(activityForm.getActivityTypeId().equals(3)){
			emailService.sendEmailForActivity(ticketActivity);
		}
		return false;
	}


	@Override
	public ActivityForm findActivityByID(Integer id) {
		TicketActivity ticketActivity = ticketActivityRepository.findOne(id);
		ActivityForm activityForm = new ActivityForm();
		Ticket t=new Ticket();
		t.setId(ticketActivity.getTicket().getId());
		activityForm.setTicket(t);
		activityForm.setId(ticketActivity.getId());
		activityForm.setDescription(ticketActivity.getDescription());
		activityForm.setActivityTypeId(ticketActivity.getActivityType().getId());
		activityForm.setStartTime(ticketActivity.getStartTime());
		activityForm.setEndTime(ticketActivity.getEndTime());
		activityForm.setLocation(ticketActivity.getLocation());
		activityForm.setAgenda(ticketActivity.getAgenda());
		if(ticketActivity.getSendTo()!=null){
		String[] to = ticketActivity.getSendTo().split(",");
		activityForm.setTo(to);
		}
		
		activityForm.setSubject(ticketActivity.getSubject());
		activityForm.setMessage(ticketActivity.getMessage());
		//TODO
		ActivityType activityType=activityTypeRepository.findOne(ticketActivity.getActivityType().getId());
		activityForm.setActivityType(activityType);
		
		return activityForm;
	}


	@Override
	@Transactional
	public boolean updateFeedback(ComplaintForm complaintForm,Locale locale) {
		Ticket ticket = ticketRepository.findOne(complaintForm.getId());
		if(complaintForm.getRating()!=null){
		Rating rating =ratingRepository.findOne(complaintForm.getRating().getId());
		ticket.setRating(rating);
		}
		ticket.setComment(complaintForm.getComment());
		if(ticket.getStatus().equals("COMPLETED")){
			Status s=statusRepository.findOne("CLOSED");
			ticket.setStatus(s);
		}
		ticketRepository.save(ticket);
		return false;
	}


	@Override
	@Transactional
	public boolean saveCaseForHelpdesk(ComplaintForm complaintForm,Locale locale) {
		Profile profile=profileRepository.findByIcNo(complaintForm.getIcNo());
		if(profile==null){
			profile= new Profile();
		}
		profile.setName(complaintForm.getName());
		profile.setEmail(complaintForm.getEmail());
	    if(complaintForm.getIcNo().length()<=14){
		profile.setIcNo(complaintForm.getIcNo());
	    }
		profile.setAddress1(complaintForm.getAddress1());
		profile.setAddress2(complaintForm.getAddress2());
		
		if(complaintForm.getPersonCategory()!=null){
			PersonCategory personCategory=personCategoryRepository.findOne(complaintForm.getPersonCategory().getId());
			profile.setPersonCategory(personCategory);
		}
		if(complaintForm.getRelation()!=null){
			Relation relation =relationRepository.findOne(complaintForm.getRelation().getId());
			profile.setRelation(relation);
		}
		
		
		if(complaintForm.getCountry()!=null){
			Country c=null;
			if(complaintForm.getCountry().getId()!=null){
			c=countryRepository.findOne(complaintForm.getCountry().getId());
			}
			profile.setCountry(c);
		}
		if(complaintForm.getState()!=null){
			State s =null;
			if(complaintForm.getState().getId()!=null){
			s = stateRepository.findOne(complaintForm.getState().getId());
			}
			profile.setState(s);
		}
		
		profile.setCity(complaintForm.getCity());
		profile.setPhoneNo(complaintForm.getPhoneNo());
		profile.setMobileNo(complaintForm.getMobileNo());
		profile=profileRepository.save(profile);
		
		
		complaintForm.setProfile(profile);
		complaintForm.setStatusId("NEW");
		this.save(complaintForm,locale);
		return false;
	}


	@Override
	public boolean saveActions(ComplaintForm complaintForm,Locale locale) {
		Ticket ticket = ticketRepository.findOne(complaintForm.getId());
		   if(complaintForm.getOriginatorId()!=null){
			Originator originator= originatorRepository.findOne(complaintForm.getOriginatorId());
			ticket.setOriginator(originator);
			}
			
			if(complaintForm.getPriorityId()!=null){
			Priority priority= priorityRepository.findOne(complaintForm.getPriorityId());
			ticket.setPriority(priority);
			}
			
			if(complaintForm.getTicketGroupId()!=null){
			TicketGroup ticketGroup = ticketGroupRepository.findOne(complaintForm.getTicketGroupId());
			ticket.setTicketGroup(ticketGroup);
			}

			
			if(complaintForm.getStatusId()!=null){
				Status status= statusRepository.findOne(complaintForm.getStatusId());
				if(ticket.getStatus()!=null){
					if(!complaintForm.getStatusId().equals(ticket.getStatus().getId())){
						ticket.setLastStatusUpdate(new Date());
					}
				}
				if(complaintForm.getStatusId().equals("ASSIGNED")){
					if(complaintForm.getSupervisorId()!=null){
						ticket.setSupervisorId(complaintForm.getSupervisorId());
					}
					if(complaintForm.getGroup()!=null){
						ticket.setGroupId(complaintForm.getGroup());
					}
					if(complaintForm.getAssigneeId()!=null){
						ticket.setAssigneeId(complaintForm.getAssigneeId());
					}
					ticket.setAssignDate(new Date());
				
				}
				ticket.setStatus(status);
			}
		return false;
	}


	@Override
	public KnowledgeForm transferToKnowledgeBase(ComplaintForm complaintForm) {
		KnowledgeForm knowledgeForm = new KnowledgeForm();
		knowledgeForm.setTitle(complaintForm.getTicketTitle());
		TicketGroup ticketGroup =new TicketGroup();
		ticketGroup.setId(complaintForm.getTicketGroupId());
		//knowledgeForm.setTicketGroup(ticketGroup);
		knowledgeForm.setDescription(complaintForm.getDescription());
		knowledgeForm.setStaffOnly(false);
		return knowledgeForm;
	}


	@Override
	public boolean saveAssignment(ComplaintForm complaintForm,Locale locale) {
		
		if(!isGroupExist(complaintForm)){
			Ticket t = ticketRepository.findOne(complaintForm.getId());
			//if(!t.getStatus().getId().equals("NEW")){
			TicketAssignment assignment=new TicketAssignment();
			checkAllAssignmentsReOpen(complaintForm.getId(),locale);
			Group g=groupRepository.findOne(complaintForm.getGroup());
			Status s=statusRepository.findOne("ASSIGNED");
			assignment.setTicket(t);
			assignment.setGroup(g);
			assignment.setStatus(s);
			assignment.setLastStatusUpdate(new Date());
			
			complaintForm.setStatus(s);
			complaintForm.setStatusId(s.getId());
		
			
			assignment=ticketAssignmentRepository.save(assignment);
			complaintForm.setAssigmentId(assignment.getId());
			eventLogService.logAssignmentEvent(complaintForm, locale);
			emailService.sendComplaintEmail(assignment);
			//}
		}
		
		return false;
	}

     
	private boolean isGroupExist(ComplaintForm complaintForm){
	   boolean e=false;
	   Ticket ticket= ticketRepository.findOne(complaintForm.getId());
	   List<TicketAssignment> assignments = ticketAssignmentRepository.findByTicket(ticket);
	   for(TicketAssignment a:assignments){
		   String g=a.getGroup().getId();
			if(a.getGroup().getId().equals(complaintForm.getGroup())){
				e=true;
			}
		}
	   return e;
	}
	
	@Override
	@Transactional
	public Ticket updateComplaintStatus(ComplaintForm complaintForm,Locale locale) {
		Ticket ticket = ticketRepository.findOne(complaintForm.getId());
		Status status= statusRepository.findOne(complaintForm.getStatusId());
		Priority priority = priorityRepository.findOne(complaintForm.getPriorityId());
		complaintForm.setStatus(status);
		eventLogService.logTicketEvent(complaintForm,locale);
		String s=ticket.getStatus().getId();
		boolean sendStatus=false;
		if(!status.getId().equals(s)){
		ticket.setLastStatusUpdate(new Date());
		sendStatus=true;
		}
		if(ticket.getStatus().getId().equals("NEW")){
			ticket.setReplyDate(new Date());
		}
		ticket.setStatus(status);
		ticket.setPriority(priority);
		
		
		ticketRepository.save(ticket);
		if(sendStatus){
		emailService.sendComplaintEmail(ticket);
		}
		if(!StringUtils.isEmpty(complaintForm.getMessage())){
			
			ChatForm cF=new ChatForm();
			cF.setTicketId(ticket.getId());
			cF.setMessage(complaintForm.getMessage());
			chatService.save(cF);
		}
		return ticket;
	}


	@Override
	public ComplaintForm findAssignmentByID(String ticketId,Integer id) {
		ComplaintForm complaintForm = new ComplaintForm();
		TicketAssignment ass= ticketAssignmentRepository.findOne(id);
		Ticket ticket = ticketRepository.findOne(ticketId);
		complaintForm.setId(ticketId);
		complaintForm.setAssigmentId(id);
		complaintForm.setTicketTitle(ticket.getTicketTitle());
		complaintForm.setDescription(ticket.getDescription());
		
		if(ass.getTicketGroup()!=null){
		complaintForm.setTicketGroupId(ass.getTicketGroup().getId());
		TicketGroup ticketGroup = ticketGroupRepository.findOne(ass.getTicketGroup().getId());
		complaintForm.setTicketGroup(ticketGroup);
		}
		
		complaintForm.setRemarks(ticket.getRemarks());
		complaintForm.setStatusId(ass.getStatus().getId());
		complaintForm.setStatus(statusRepository.findOne(ass.getStatus().getId()));
		complaintForm.setLastUpdate(ass.getUpdateDate());
		complaintForm.setInterval(ticket.getIntervalX());
		
		complaintForm.setCreateDate(ass.getCreateDate());
		Profile profile = profileRepository.findOne(ticket.getProfile().getId());
		if(profile.getState()!=null){
			State state=stateRepository.findOne(profile.getState().getId());
			profile.setState(state);
		}
		
		
		if(ass.getSupervisorId()!=null){
		Profile supervisor=profileRepository.findOne(ass.getSupervisorId());
		complaintForm.setSupervisor(supervisor);
		complaintForm.setSupervisorId(ticket.getSupervisorId());
		complaintForm.setAssignDate(ticket.getAssignDate());
		}
		
			
			
		complaintForm.setProfile(profile);
		
		if(ass.getGroup()!=null){
			Group dept=groupRepository.findOne(ass.getGroup().getId());
			complaintForm.setGroup(ass.getGroup().getId());
			complaintForm.setDepartment(dept);
		
		}
		
		
		
		if(ass.getAssigneeId()!=null){
			Profile assignee=profileRepository.findOne(ass.getAssigneeId());
			complaintForm.setAssignee(assignee);
			complaintForm.setAssigneeId(ass.getAssigneeId());
		}
		
		
		if(ticket.getRating()!=null){
			Rating rating=ratingRepository.findOne(ticket.getRating().getId());
		complaintForm.setRating(rating);
		complaintForm.setComment(ticket.getComment());
		}
		if(ticket.getOriginator()!=null){
			complaintForm.setOriginatorId(ticket.getOriginator().getId());
			Originator originator=originatorRepository.findOne(complaintForm.getOriginatorId());
			complaintForm.setOriginator(originator);
		}
		if(ticket.getPriority()!=null){
			complaintForm.setPriorityId(ticket.getPriority().getId());
			Priority priority=priorityRepository.findOne(complaintForm.getPriorityId());
			complaintForm.setPriority(priority);
		}
		
		List<TicketFile> listFiles= ticketFileRepository.findIdAndNameByTicket(ticket);
		complaintForm.setTicketFiles(listFiles);
		
		return complaintForm;
	}


	@Override
	public TicketAssignment updateAssignment(ComplaintForm complaintForm,Locale locale) {
		TicketAssignment assignment=ticketAssignmentRepository.findOne(complaintForm.getAssigmentId());
		Status status= statusRepository.findOne(complaintForm.getStatusId());
		complaintForm.setStatus(status);
		complaintForm.setGroup(assignment.getGroup().getId());
		complaintForm.setId(assignment.getTicket().getId());
		eventLogService.logAssignmentEvent(complaintForm, locale);
		if(complaintForm.getSupervisorId()!=null){
			assignment.setSupervisorId(complaintForm.getSupervisorId());
		}
		if(complaintForm.getAssigneeId()!=null){
			Profile p=profileRepository.findOne(complaintForm.getAssigneeId());
			if(assignment.getAssigneeId()!=null){
				if(!assignment.getAssigneeId().equals(complaintForm.getAssigneeId())){
					assignment.setAssigneeId(complaintForm.getAssigneeId());
					assignment.setAssignDate(new Date());
					emailService.sendEmailForAssignment(p, assignment);
				}else{
					if(assignment.getStatus()!=null){
						if(!complaintForm.getStatusId().equals(assignment.getStatus().getId())){
							assignment.setAssigneeId(complaintForm.getAssigneeId());
							assignment.setAssignDate(new Date());
							if(!assignment.getAssigneeId().equals(complaintForm.getAssigneeId())){
								emailService.sendEmailForAssignment(p, assignment);
							}
							
						}
					}
				}
				
			}else{
				assignment.setAssigneeId(complaintForm.getAssigneeId());
				assignment.setAssignDate(new Date());
				emailService.sendEmailForAssignment(p, assignment);
				
			}
			
			
		}
		if(assignment.getStatus().getId().equals("ASSIGNED")||assignment.getStatus().getId().equals("RE-ASSIGNED")){
			assignment.setResponseDate(new Date());
		}
		boolean sendStatus=false;
		if(!complaintForm.getStatusId().equals(assignment.getStatus().getId())){
			assignment.setLastStatusUpdate(new Date());
			sendStatus=true;
		}
		assignment.setStatus(status);
		if(complaintForm.getTicketGroupId()!=null){
			TicketGroup ticketGroup = ticketGroupRepository.findOne(complaintForm.getTicketGroupId());
			assignment.setTicketGroup(ticketGroup);
		}
		
		
		if(complaintForm.getStatusId().equals("SOLVED")){
			
			assignment.setResolutionDate(new Date());
			assignment.setCaseInterval(this.calculateBetweenDate(assignment.getCreateDate(), new Date()));
			long newD=new Date().getTime();
			Integer interval =(int) (newD-assignment.getCreateDate().getTime());
			assignment.setDuration(interval);
		}
		
		
		ticketAssignmentRepository.save(assignment);
		if(sendStatus){
		emailService.sendComplaintEmail(assignment);
		}
		checkAllAssignmentsToSolve(assignment.getTicket().getId(),locale);
		if(!StringUtils.isEmpty(complaintForm.getMessage())){
			
			ChatForm cF=new ChatForm();
			cF.setTicketId(assignment.getTicket().getId());
			cF.setMessage(complaintForm.getMessage());
			chatService.save(cF);
		}
		return assignment;
	}

	
	private void checkAllAssignmentsToSolve(String ticketId,Locale locale){
		Ticket ticket= ticketRepository.findOne(ticketId);
		List<TicketAssignment> assignments = ticketAssignmentRepository.findByTicket(ticket);
		int total = assignments.size();
		int counter=0;
		for(TicketAssignment a:assignments){
			if(a.getStatus().getId().equals("SOLVED")){
				counter=counter+1;
			}
		}
		
		if(counter==total){
			Status status=statusRepository.findOne("COMPLETED");
			ComplaintForm complaintForm= new ComplaintForm();
			complaintForm.setId(ticketId);
			complaintForm.setStatus(status);
			complaintForm.setStatusId(status.getId());
			eventLogService.logTicketEvent(complaintForm, locale);
			ticket.setStatus(status);
			ticketRepository.save(ticket);
			emailService.sendComplaintEmail(ticket);
		}
		
	}
	
	private void checkAllAssignmentsReOpen(String ticketId,Locale locale){
		Ticket ticket= ticketRepository.findOne(ticketId);
		List<TicketAssignment> assignments = ticketAssignmentRepository.findByTicket(ticket);
		int total = assignments.size();
		int counter=0;
		for(TicketAssignment a:assignments){
			if(a.getStatus().getId().equals("SOLVED")){
				counter=counter+1;
			}
		}
		
		if(total!=0){
		if(counter==total){
			Status status=statusRepository.findOne("RE-OPEN");
			ComplaintForm complaintForm= new ComplaintForm();
			complaintForm.setId(ticketId);
			complaintForm.setStatus(status);
			complaintForm.setStatusId(status.getId());
			eventLogService.logTicketEvent(complaintForm, locale);
			ticket.setStatus(status);
			ticketRepository.save(ticket);
			emailService.sendComplaintEmail(ticket);
		}
		}
		
	}

	@Override
	public boolean updateAssignmentStatus(ComplaintForm complaintForm,Locale locale) {
		TicketAssignment assignment=ticketAssignmentRepository.findOne(complaintForm.getAssigmentId());
		Status status= statusRepository.findOne(complaintForm.getStatusId());
		complaintForm.setStatus(status);
		complaintForm.setGroup(assignment.getGroup().getId());
		complaintForm.setId(assignment.getTicket().getId());
		updateMasterStatus(complaintForm.getId(),"RE-OPEN",locale);
		eventLogService.logAssignmentEvent(complaintForm, locale);
		if(complaintForm.getStatusId().equals("RE-ASSIGNED")){
			assignment.setAssignDate(null);
			assignment.setAssigneeId(null);
			assignment.setCaseInterval(null);
			assignment.setDuration(null);
			assignment.setResolutionDate(null);
			assignment.setSupervisorId(null);
			assignment.setTicketGroup(null);
			assignment.setUnit(null);
		}
		assignment.setStatus(status);
		assignment.setLastStatusUpdate(new Date());
		ticketAssignmentRepository.save(assignment);
		
		
		return false;
	}


	private void updateMasterStatus(String ticketId,String s,Locale locale){
		Ticket ticket=ticketRepository.findOne(ticketId);
		Status status=statusRepository.findOne(s);
		ComplaintForm complaintForm= new ComplaintForm();
		complaintForm.setId(ticketId);
		complaintForm.setStatus(status);
		complaintForm.setStatusId(status.getId());
		eventLogService.logTicketEvent(complaintForm, locale);
		ticket.setStatus(status);
		ticketRepository.save(ticket);
		emailService.sendComplaintEmail(ticket);
	}


	

}
