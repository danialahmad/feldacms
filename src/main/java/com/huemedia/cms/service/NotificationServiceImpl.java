package com.huemedia.cms.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;
import org.springframework.stereotype.Service;

import com.huemedia.cms.model.entity.Notification;
import com.huemedia.cms.model.repository.NotificationRepository;
import com.huemedia.cms.model.repository.VelocityMailTemplateRepository;
import com.huemedia.cms.web.form.MasterForm;

@Service
public class NotificationServiceImpl implements NotificationService{

	@Autowired
	NotificationRepository notificationRepository;
	@Autowired
	VelocityMailTemplateRepository velocityMailTemplateRepository;
	
	@Override
	public List<Notification> getNotifications() {
		Order o= new Order(Direction.ASC,"rank");
		return (List<Notification>) notificationRepository.findAll(new Sort(o));
	}

	@Override
	public boolean save(MasterForm form) {
		for(Notification noti:form.getListNoti()){
			Notification copy=notificationRepository.findOne(noti.getId());
			copy.setAllSupervisorSubject(noti.getAllSupervisorSubject());
			copy.setSendAllSupervisor(noti.getSendAllSupervisor());
			copy.setRefVelocityMailTemplateByAllSupervisorTemplate(velocityMailTemplateRepository.findOne(noti.getRefVelocityMailTemplateByAllSupervisorTemplate().getId()));
			
			copy.setSupervisorSubject(noti.getSupervisorSubject());
			copy.setSendToSupervisor(noti.getSendToSupervisor());
			copy.setRefVelocityMailTemplateBySupervisorTemplate(velocityMailTemplateRepository.findOne(noti.getRefVelocityMailTemplateBySupervisorTemplate().getId()));
			
			copy.setComplainantSubject(noti.getComplainantSubject());
			copy.setSendToComplainant(noti.getSendToComplainant());
			copy.setRefVelocityMailTemplateByComplainantTemplate(velocityMailTemplateRepository.findOne(noti.getRefVelocityMailTemplateByComplainantTemplate().getId()));
			
			copy.setHelpdeskSubject(noti.getHelpdeskSubject());
			copy.setSendToHelpdesk(noti.getSendToHelpdesk());
			copy.setRefVelocityMailTemplateByHelpdeskTemplate(velocityMailTemplateRepository.findOne(noti.getRefVelocityMailTemplateByHelpdeskTemplate().getId()));
			
			copy.setAssigneeSubject(noti.getAssigneeSubject());
			copy.setSendToAssignee(noti.getSendToAssignee());
			copy.setRefVelocityMailTemplateByAssigneeTemplate(velocityMailTemplateRepository.findOne(noti.getRefVelocityMailTemplateByAssigneeTemplate().getId()));
			
			copy.setManagerSubject(noti.getManagerSubject());
			copy.setSendToManager(noti.getSendToManager());
			copy.setRefVelocityMailTemplateByManagerTemplate(velocityMailTemplateRepository.findOne(noti.getRefVelocityMailTemplateByManagerTemplate().getId()));
			
			notificationRepository.save(copy);
		}
		return false;
	}
	
	

}
