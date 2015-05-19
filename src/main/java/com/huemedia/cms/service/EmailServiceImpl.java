package com.huemedia.cms.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.velocity.app.VelocityEngine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.ui.velocity.VelocityEngineUtils;

import com.huemedia.cms.component.mail.EmailSender;
import com.huemedia.cms.model.entity.Group;
import com.huemedia.cms.model.entity.Notification;
import com.huemedia.cms.model.entity.Profile;
import com.huemedia.cms.model.entity.StaffGroup;
import com.huemedia.cms.model.entity.Ticket;
import com.huemedia.cms.model.entity.TicketActivity;
import com.huemedia.cms.model.entity.TicketAssignment;
import com.huemedia.cms.model.entity.User;
import com.huemedia.cms.model.entity.UserRole;
import com.huemedia.cms.model.repository.GroupRepository;
import com.huemedia.cms.model.repository.NotificationRepository;
import com.huemedia.cms.model.repository.ProfileRepository;
import com.huemedia.cms.model.repository.StaffGroupRepository;
import com.huemedia.cms.model.repository.StatusRepository;
import com.huemedia.cms.model.repository.TicketAssignmentRepository;
import com.huemedia.cms.model.repository.TicketRepository;
import com.huemedia.cms.model.repository.UserRoleRepository;

@Service
public class EmailServiceImpl implements EmailService {
	@Autowired
	EmailSender emailSender;
	@Autowired
	VelocityEngine velocityEngine;
	@Autowired
	HttpServletRequest request;
	@Autowired
	ProfileRepository profileRepository;
	@Autowired
	UserRoleRepository userRoleRepository;
	@Autowired
	StatusRepository statusRepository;
	@Autowired
	StaffGroupRepository staffGroupRepository;
	@Autowired
	GroupRepository groupRepository;
	@Autowired
	NotificationRepository notificationRepository;
	@Autowired
	TicketRepository ticketRepository;
	@Autowired
	TicketAssignmentRepository ticketAssignmentRepository;
	@Autowired
	UserService userService;
	@Value("#{globalProperties['contextPath']}")
	String contextPath;
	@Value("#{globalProperties['email']}")
	String email;
	
	
	private List<Profile> getProfileByRole(String role){
		List<Profile> sv= new ArrayList<Profile>();
		List<UserRole> listUserRole= userRoleRepository.findByRoleId(role);
		for(UserRole ur:listUserRole){
			User user=userService.getByUsername(ur.getUser().getUsername());
			Profile p= user.getProfile(); 
		sv.add(p);
		}
		
		return sv;
	}
	private List<Profile> getProfileByRoleAndGroup(String role,String group){
		List<Profile> sv= new ArrayList<Profile>();
		Group g = groupRepository.findOne(group);
		List<UserRole> listUserRole= userRoleRepository.findByRoleId(role);
		for(UserRole ur:listUserRole){
			User user=userService.getByUsername(ur.getUser().getUsername());
			Profile p= user.getProfile();
			List<StaffGroup> listSG= staffGroupRepository.findByProfileAndGroup(p, g);
			System.out.print("Size Email:"+listSG.size());
			if(listSG.size()!=0){
				System.out.print("TEST PROFILEEE");
				sv.add(p);	
			}
			
		}
		
		return sv;
	}
	
	
	@Override
	public void sendEmailForActivity(TicketActivity ticketActivity) {
		try{
			String text = ticketActivity.getMessage();
			emailSender.sendEmail(email,ticketActivity.getSendTo(), ticketActivity.getSubject(), text);
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	@Override
	public void sendEmailForActivation(User user,String url) {
		try{
			Map model = new HashMap();
			model.put("receiver",user.getProfile().getName());
			model.put("actUrl", url);
			model.put("sysAddress", contextPath);
			String text = VelocityEngineUtils.mergeTemplateIntoString(velocityEngine,"ACT001",model);
			emailSender.sendEmail(email,user.getProfile().getEmail(), "ACCOUNT ACTIVATION", text);
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	@Override
	public void sendChatMessage(Ticket t, Profile sender,String msg) {
		try{
			
			List<Map<String,String>> rList= new ArrayList<Map<String,String>>();
			if(t.getSupervisorId()!=null){
				Profile p=profileRepository.findOne(t.getSupervisorId());
				if(!sender.getId().equals(p.getId())){
				Map<String,String> rec = new HashMap<String,String>();
				rec.put("receiver",p.getName());
				rec.put("email", p.getEmail());
				rList.add(rec);
				}
			}else{
				if(getProfileByRole("ROLE_SUPERVISOR").size()!=0){
					for(Profile c:getProfileByRole("ROLE_SUPERVISOR")){
						if(!sender.getId().equals(c.getId())){
							Map<String,String> rec = new HashMap<String,String>();
							rec.put("receiver",c.getName());
							rec.put("email", c.getEmail());
							rList.add(rec);
						}
					}
				}
			}
			
			if(t.getAssigneeId()!=null){
				Profile p=profileRepository.findOne(t.getAssigneeId());
				if(!sender.getId().equals(p.getId())){
				Map<String,String> rec = new HashMap<String,String>();
				rec.put("receiver",p.getName());
				rec.put("email", p.getEmail());
				rList.add(rec);
				}
			}
			Profile p=profileRepository.findOne(t.getProfile().getId());
			if(!sender.getId().equals(p.getId())){
				Map<String,String> rec = new HashMap<String,String>();
				rec.put("receiver",p.getName());
				rec.put("email", p.getEmail());
				rList.add(rec);
			}
			
			if(rList.size()!=0){
				for(Map<String,String> m:rList){
				Map<String,Object> model = new HashMap<String,Object>();
				model.put("receiver",m.get("receiver"));
				model.put("sysAddress", contextPath);
				model.put("msg", msg);
				model.put("sender", sender.getName());
				model.put("application", t);
				String text = VelocityEngineUtils.mergeTemplateIntoString(velocityEngine,"message.vm",model);
				emailSender.sendEmail(email,m.get("email"), "NEW MESSAGE ("+t.getId()+")", text);
			 
				}
			}
			
			}catch(Exception e){
			e.printStackTrace();
		}
	}

	@Override
	public void sendComplaintEmail(Ticket t) {
		try{
		Notification not=notificationRepository.findOne(t.getStatus().getId());
		if(not!=null){
			caseForTicket(not,t);
		}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	
	@Override
	public void sendComplaintEmail(TicketAssignment a){
		try{
			Notification not=notificationRepository.findOne(a.getStatus().getId());
			if(not!=null){
				caseForAssignment(not,a);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}
	
	
    @Override
	public void sendEmailForAssignment(Profile p,TicketAssignment a) {
		try{
			Ticket t=ticketRepository.findOne(a.getTicket().getId());
			a.setTicket(t);
			Map model = new HashMap();
			model.put("receiver",p.getName());
			model.put("application", a);
			model.put("sysAddress", contextPath);
			if(p.getEmail()!=null){
			String text = VelocityEngineUtils.mergeTemplateIntoString(velocityEngine,"004",model);
			emailSender.sendEmail(email,p.getEmail(), "Kes Ditugaskan Kepada Anda", text);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	
	private void caseForAssignment(Notification not,TicketAssignment a){
		Ticket t=ticketRepository.findOne(a.getTicket().getId());
		a.setTicket(t);
		a.setGroup(groupRepository.findOne(a.getGroup().getId()));
		if(not.getSendToComplainant()){
			Profile p=profileRepository.findOne(t.getProfile().getId());
			Map model = new HashMap();
			model.put("receiver",p.getName());
			model.put("application", a);
			model.put("sysAddress", contextPath);
			if(p.getEmail()!=null){
			String text = VelocityEngineUtils.mergeTemplateIntoString(velocityEngine,not.getRefVelocityMailTemplateByComplainantTemplate().getId(),model);
			emailSender.sendEmail(email,p.getEmail(), not.getComplainantSubject(), text);	
			}
		}
		if(not.getSendToHelpdesk()){
			List<Profile> profiles=getProfileByRole("ROLE_HELPDESK");
			if(profiles.size()!=0){
				for(Profile p:profiles){
					Map model = new HashMap();
					model.put("receiver",p.getName());
					model.put("application", a);
					model.put("sysAddress", contextPath);
					if(p.getEmail()!=null){
					String text = VelocityEngineUtils.mergeTemplateIntoString(velocityEngine,not.getRefVelocityMailTemplateByHelpdeskTemplate().getId(),model);
					emailSender.sendEmail(email,p.getEmail(), not.getHelpdeskSubject(), text);	
					}
				}
			
			
			}
			
		}
		if(not.getSendAllSupervisor()){
			List<Profile> profiles=this.getProfileByRoleAndGroup("ROLE_SUPERVISOR",a.getGroup().getId());
			if(profiles.size()!=0){
				for(Profile p:profiles){
					Map model = new HashMap();
					model.put("receiver",p.getName());
					model.put("application", a);
					model.put("sysAddress", contextPath);
					if(p.getEmail()!=null){
					String text = VelocityEngineUtils.mergeTemplateIntoString(velocityEngine,not.getRefVelocityMailTemplateByAllSupervisorTemplate().getId(),model);
					emailSender.sendEmail(email,p.getEmail(), not.getAllSupervisorSubject(), text);
					}
				}
			
			
			}
		}
		
		if(not.getSendToSupervisor()){
		
					Profile p=profileRepository.findOne(a.getSupervisorId());
					Map model = new HashMap();
					model.put("receiver",p.getName());
					model.put("application", a);
					model.put("sysAddress", contextPath);
					if(p.getEmail()!=null){
					String text = VelocityEngineUtils.mergeTemplateIntoString(velocityEngine,not.getRefVelocityMailTemplateBySupervisorTemplate().getId(),model);
					emailSender.sendEmail(email,p.getEmail(), not.getSupervisorSubject(), text);
					}
				
			
		}
		
		if(not.getSendToAssignee()){
			      if(a.getAssigneeId()!=null){
					Profile p=profileRepository.findOne(a.getAssigneeId());
					Map model = new HashMap();
					model.put("receiver",p.getName());
					model.put("application", a);
					model.put("sysAddress", contextPath);
					if(p.getEmail()!=null){
					String text = VelocityEngineUtils.mergeTemplateIntoString(velocityEngine,not.getRefVelocityMailTemplateByAssigneeTemplate().getId(),model);
					emailSender.sendEmail(email,p.getEmail(), not.getAssigneeSubject(), text);
					}
			      }
		}
		
		
		
	}
	
	
	private void caseForTicket(Notification not,Ticket t){
		if(not.getSendToComplainant()){
			Profile p=profileRepository.findOne(t.getProfile().getId());
			Map model = new HashMap();
			model.put("receiver",p.getName());
			model.put("application", t);
			model.put("sysAddress", contextPath);
			if(p.getEmail()!=null){
			String text = VelocityEngineUtils.mergeTemplateIntoString(velocityEngine,not.getRefVelocityMailTemplateByComplainantTemplate().getId(),model);
			emailSender.sendEmail(email,p.getEmail(), not.getComplainantSubject(), text);	
			}
		}
		
		if(not.getSendToHelpdesk()){
			List<Profile> profiles=getProfileByRole("ROLE_HELPDESK");
			if(profiles.size()!=0){
				for(Profile p:profiles){
					Map model = new HashMap();
					model.put("receiver",p.getName());
					model.put("application", t);
					model.put("sysAddress", contextPath);
					if(p.getEmail()!=null){
					String text = VelocityEngineUtils.mergeTemplateIntoString(velocityEngine,not.getRefVelocityMailTemplateByHelpdeskTemplate().getId(),model);
					emailSender.sendEmail(email,p.getEmail(), not.getHelpdeskSubject(), text);	
					}
				}
			
			
			}
			
		}
		
		if(not.getSendAllSupervisor()){
			List<Profile> profiles=getProfileByRole("ROLE_SUPERVISOR");
			if(profiles.size()!=0){
				for(Profile p:profiles){
					Map model = new HashMap();
					model.put("receiver",p.getName());
					model.put("application", t);
					model.put("sysAddress", contextPath);
					if(p.getEmail()!=null){
					String text = VelocityEngineUtils.mergeTemplateIntoString(velocityEngine,not.getRefVelocityMailTemplateByAllSupervisorTemplate().getId(),model);
					emailSender.sendEmail(email,p.getEmail(), not.getAllSupervisorSubject(), text);
					}
				}
			
			
			}
		}
		
		if(not.getSendToSupervisor()){
			List<TicketAssignment> assignments=ticketAssignmentRepository.findByTicket(t);
			if(assignments.size()!=0){
				for(TicketAssignment a:assignments){
					Profile p=profileRepository.findOne(a.getSupervisorId());
					Map model = new HashMap();
					model.put("receiver",p.getName());
					model.put("application", t);
					model.put("sysAddress", contextPath);
					if(p.getEmail()!=null){
					String text = VelocityEngineUtils.mergeTemplateIntoString(velocityEngine,not.getRefVelocityMailTemplateBySupervisorTemplate().getId(),model);
					emailSender.sendEmail(email,p.getEmail(), not.getSupervisorSubject(), text);
					}
				}
			}
			
			
		}
		
		if(not.getSendToAssignee()){
			List<TicketAssignment> assignments=ticketAssignmentRepository.findByTicket(t);
			if(assignments.size()!=0){
				for(TicketAssignment a:assignments){
					Profile p=profileRepository.findOne(a.getAssigneeId());
					Map model = new HashMap();
					model.put("receiver",p.getName());
					model.put("application", t);
					model.put("sysAddress", contextPath);
					if(p.getEmail()!=null){
					String text = VelocityEngineUtils.mergeTemplateIntoString(velocityEngine,not.getRefVelocityMailTemplateByAssigneeTemplate().getId(),model);
					emailSender.sendEmail(email,p.getEmail(), not.getAssigneeSubject(), text);
					}
				}
			}
			
		}
		if(not.getSendToManager()){
			List<Profile> profiles=getProfileByRole("ROLE_MANAGER");
			if(profiles.size()!=0){
				for(Profile p:profiles){
					Map model = new HashMap();
					model.put("receiver",p.getName());
					model.put("application", t);
					model.put("sysAddress", contextPath);
					String text = VelocityEngineUtils.mergeTemplateIntoString(velocityEngine,not.getRefVelocityMailTemplateByManagerTemplate().getId(),model);
					emailSender.sendEmail(email,p.getEmail(), not.getManagerSubject(), text);	
					
				}
			
			
			}
			
		}
	}
	
	
	@Override
	public boolean sendEmailForReset(User user,String password) {
		boolean result=false;
		try{
			Map model = new HashMap();
			model.put("receiver",user.getProfile().getName());
			model.put("sysAddress", contextPath);
			model.put("password", password);
			String text = VelocityEngineUtils.mergeTemplateIntoString(velocityEngine,"R001",model);
			emailSender.sendEmail(email,user.getProfile().getEmail(), "PASSWORD RESET", text);
			result=true;
		}catch(Exception e){
			e.printStackTrace();
		}
		return result;
	}
}
