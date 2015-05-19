package com.huemedia.cms.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.huemedia.cms.model.dao.SlaDAO;
import com.huemedia.cms.model.entity.Escalation;
import com.huemedia.cms.model.entity.Priority;
import com.huemedia.cms.model.entity.Sla;
import com.huemedia.cms.model.entity.SlaEscalation;
import com.huemedia.cms.model.entity.SlaTask;
import com.huemedia.cms.model.entity.Status;
import com.huemedia.cms.model.entity.Ticket;
import com.huemedia.cms.model.entity.TicketCategory;
import com.huemedia.cms.model.entity.TicketGroup;
import com.huemedia.cms.model.repository.EscalationRepository;
import com.huemedia.cms.model.repository.PriorityRepository;
import com.huemedia.cms.model.repository.SlaEscalationRepository;
import com.huemedia.cms.model.repository.SlaRepository;
import com.huemedia.cms.model.repository.SlaTaskRepository;
import com.huemedia.cms.model.repository.StatusRepository;
import com.huemedia.cms.model.repository.TicketCategoryRepository;
import com.huemedia.cms.model.repository.TicketGroupRepository;
import com.huemedia.cms.web.controller.administration.datatables.SlaResult;
import com.huemedia.cms.web.form.EscalationForm;
import com.huemedia.cms.web.form.SlaForm;

@Service
public class SLAServiceImpl implements SLAService {

	@Autowired
	SlaRepository slaRepository;
	@Autowired
	EscalationRepository escalationRepository;
	@Autowired
	TicketGroupRepository ticketGroupRepository;
	@Autowired
	TicketCategoryRepository ticketCategoryRepository;
	@Autowired
	StatusRepository statusRepository;
	@Autowired
	PriorityRepository priorityRepository;
	@Autowired
	SlaEscalationRepository slaEscalationRepository;
	@Autowired
	SlaDAO slaDAO;
	@Autowired
	SlaTaskRepository slaTaskRepository;
	
	@Override
	@Transactional
	public boolean saveNewSetting(SlaForm slaForm) {
		Sla sla= new Sla();
		if(slaForm.getTicketGroupItems().size()!=0){
		JSONArray tGroups= new JSONArray(slaForm.getTicketGroupItems());
		sla.setTicketGroup(tGroups.toString());
		}
		if(slaForm.getTicketCategoryItems().size()!=0){
		JSONArray tCategory= new JSONArray(slaForm.getTicketCategoryItems());
		sla.setTicketCategory(tCategory.toString());
		}
		if(slaForm.getStatusItems().size()!=0){
		JSONArray status= new JSONArray(slaForm.getStatusItems());
		sla.setStatus(status.toString());
		}
		if(slaForm.getPriorityItems().size()!=0){
		JSONArray priority= new JSONArray(slaForm.getPriorityItems());
		sla.setPriority(priority.toString());
		}
		
		
		
		
		sla.setTitle(slaForm.getTitle());
		sla.setDescription(slaForm.getDescription());
		
		sla.setTime(slaForm.getTime());
		sla.setTimeUnit(slaForm.getTimeUnit());
		sla.setReminder(slaForm.getReminder());
		sla.setReminderUnit(slaForm.getReminderUnit());
		
		slaRepository.save(sla);
		for(Integer id:slaForm.getCheckedItems()){
			Escalation escalation= escalationRepository.findOne(id);
			SlaEscalation sle= new SlaEscalation();
			sle.setEscalation(escalation);
			sle.setSla(sla);
			
			 if(id==1){
				sle.setSendToAssignee(slaForm.getSendToAssignee());
				 sle.setSendToSupervisor(slaForm.getSendToSupervisor());
				 sle.setSendToOwner(slaForm.getSendToOwner());
			  }
			  if(id==2){
				 if(slaForm.getStatusId()!=null){
					 Status s=new Status();
					 s.setId(slaForm.getStatusId());
					 sle.setStatus(s);
				 }
			  }
			  if(id==3){
				  if(slaForm.getPriorityId()!=null){
					  Priority p= new Priority();
					  p.setId(slaForm.getPriorityId());
					  sle.setPriority(p);
				  }
			  }
			
			
			slaEscalationRepository.save(sle);
		}
		
		return false;
	}

	@Override
	@Transactional
	public boolean updateSetting(SlaForm slaForm) {
		
		Sla sla= slaRepository.findOne(slaForm.getId());
		
			if(slaForm.getTicketGroupItems().size()!=0){
			JSONArray tGroups= new JSONArray(slaForm.getTicketGroupItems());
			sla.setTicketGroup(tGroups.toString());
			}
			if(slaForm.getTicketCategoryItems().size()!=0){
			JSONArray tCategory= new JSONArray(slaForm.getTicketCategoryItems());
			sla.setTicketCategory(tCategory.toString());
			}
			if(slaForm.getStatusItems().size()!=0){
			JSONArray status= new JSONArray(slaForm.getStatusItems());
			sla.setStatus(status.toString());
			}
			if(slaForm.getPriorityItems().size()!=0){
			JSONArray priority= new JSONArray(slaForm.getPriorityItems());
			sla.setPriority(priority.toString());
			}
		
		
		List<SlaEscalation> listSe= slaEscalationRepository.findBySla(sla);
		List<SlaEscalation> listDel= new ArrayList<SlaEscalation>();
		
		
		for(SlaEscalation se:listSe){
			boolean found=false;
			if(slaForm.getCheckedItems()!=null && slaForm.getCheckedItems().size()!=0){
			for(Integer id:slaForm.getCheckedItems()){
				if(se.getEscalation().getId().equals(id)){
					found=true;
					break;
				}
			}
			}
			if(!found){
				listDel.add(se);
			}
		}
		slaEscalationRepository.delete(listDel);
		
		if(slaForm.getCheckedItems()!=null && slaForm.getCheckedItems().size()!=0){
		for(Integer id:slaForm.getCheckedItems()){
			boolean found=false;
			for(SlaEscalation se:listSe){
				if(id.equals(se.getEscalation().getId())){
					found=true;
					 if(id==1){
						 se.setSendToAssignee(slaForm.getSendToAssignee());
						 se.setSendToSupervisor(slaForm.getSendToSupervisor());
						 se.setSendToOwner(slaForm.getSendToOwner());
						  }
						  if(id==2){
							 if(slaForm.getStatusId()!=null){
								 Status s=new Status();
								 s.setId(slaForm.getStatusId());
								 se.setStatus(s);
							 }
						  }
						  if(id==3){
							  if(slaForm.getPriorityId()!=null){
								  Priority p=new Priority();
								  p.setId(slaForm.getPriorityId());
								  se.setPriority(p);
							  }
						  }
					
						  slaEscalationRepository.save(se);
					break;
				}
			}
			if(!found){
			Escalation escalation= escalationRepository.findOne(id);
			SlaEscalation sle= new SlaEscalation();
			sle.setEscalation(escalation);
			sle.setSla(sla);
			 if(id==1){
					sle.setSendToAssignee(slaForm.getSendToAssignee());
					 sle.setSendToSupervisor(slaForm.getSendToSupervisor());
					 sle.setSendToOwner(slaForm.getSendToOwner());
				  }
				  if(id==2){
					 if(slaForm.getStatusId()!=null){
						 Status s=new Status();
						 s.setId(slaForm.getStatusId());
						 sle.setStatus(s);
					 }
				  }
				  if(id==3){
					  if(slaForm.getPriorityId()!=null){
						  Priority p=new Priority();
						  p.setId(slaForm.getPriorityId());
						  sle.setPriority(p);
					  }
				  }
			slaEscalationRepository.save(sle);
			}
		}
		}
		
		sla.setTitle(slaForm.getTitle());
		sla.setDescription(slaForm.getDescription());
	
		sla.setTime(slaForm.getTime());
		sla.setTimeUnit(slaForm.getTimeUnit());
		sla.setReminder(slaForm.getReminder());
		sla.setReminderUnit(slaForm.getReminderUnit());
	
		slaRepository.save(sla);
		
		
		return false;
	}

	@Override
	@Transactional
	public boolean deleteSetting(Integer id) {
		slaRepository.delete(id);
		return false;
	}

	@Override
	public Long countAll() {
		
		return slaDAO.countAll();
	}

	@Override
	public Long countSearch(SlaForm slaForm) {
		
		return slaDAO.countSearch(slaForm);
	}

	@Override
	public List<SlaResult> getSlas(SlaForm slaForm, Integer iDisplayStart, Integer iDisplayLength) {
	    List<Sla> list = slaDAO.search(slaForm, iDisplayStart, iDisplayLength);
	    SimpleDateFormat sdfFull = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
	    List<SlaResult> listResult=new ArrayList<SlaResult>();
	    for(Sla sla:list){
	    	SlaResult slaResult = new SlaResult();
	    	String createBy="";
			String createDate="";
			String updateBy="";
			String updateDate="";
	    	
	    	slaResult.setId(sla.getId());
	    	slaResult.setTitle(sla.getTitle());
	    	slaResult.setDescription(sla.getDescription());
	    	
	    	if(sla.getCreateBy()!=null){
				createBy=sla.getCreateBy();
			}
			if(sla.getCreateDate()!=null){
				createDate=sdfFull.format(sla.getCreateDate());
			}
			if(sla.getUpdateBy()!=null){
				updateBy=sla.getUpdateBy();
			}
			if(sla.getUpdateDate()!=null){
				updateDate=sdfFull.format(sla.getUpdateDate());
			}
			
			slaResult.setCreateBy(createBy);
			slaResult.setCreateDate(createDate);
			slaResult.setUpdateBy(updateBy);
			slaResult.setUpdateDate(updateDate);
	    	
	    	listResult.add(slaResult);
	    }
	    
		return listResult;
	}

	@Override
	public SlaForm findSlaSetting(Integer id) {
		Sla sla= slaRepository.findOne(id);
		SlaForm form = new SlaForm();
		form.setId(sla.getId());
		form.setTitle(sla.getTitle());
		form.setDescription(sla.getDescription());
		
		try{
		if(sla.getTicketGroup()!=null){
		 JSONArray tGroups= new JSONArray(sla.getTicketGroup());
		 for(int i=0;i<tGroups.length();i++){
			 form.getTicketGroupItems().add(tGroups.getInt(i));
		 }
		
		}
		if(sla.getTicketCategory()!=null){
			JSONArray tCategory= new JSONArray(sla.getTicketCategory());
			 for(int i=0;i<tCategory.length();i++){
				 form.getTicketCategoryItems().add(tCategory.getInt(i));
			 }
		}
		if(sla.getStatus()!=null){
			JSONArray status= new JSONArray(sla.getStatus());
			 for(int i=0;i<status.length();i++){
				 form.getStatusItems().add(status.getString(i));
			 }
		}
		if(sla.getPriority()!=null){
			JSONArray priority= new JSONArray(sla.getPriority());
			 for(int i=0;i<priority.length();i++){
					form.getPriorityItems().add(priority.getString(i));
				
			 }
		}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		form.setTime(sla.getTime());
		form.setTimeUnit(sla.getTimeUnit());
		form.setReminder(sla.getReminder());
		form.setReminderUnit(sla.getReminderUnit());
		List<SlaEscalation> listSe= slaEscalationRepository.findBySla(sla);
		for(SlaEscalation se:listSe){
		  form.getCheckedItems().add(se.getEscalation().getId());
		  if(se.getEscalation().getId()==1){
			  form.setSendToAssignee(se.getSendToAssignee());
			  form.setSendToSupervisor(se.getSendToSupervisor());
			  form.setSendToOwner(se.getSendToOwner());
		  }
		  if(se.getEscalation().getId()==2){
			 if(se.getStatus()!=null){
			  form.setStatusId(se.getStatus().getId());
			 }
		  }
		  if(se.getEscalation().getId()==3){
			  if(se.getPriority()!=null){
			  form.setPriorityId(se.getPriority().getId());
			  }
		  }
		}
		return form;
	}

	@Override
	public EscalationForm findSlaById(Integer id) {
		EscalationForm escForm =new EscalationForm();
		Sla sla= slaRepository.findOne(id);
		
		
	
		
		
		
		escForm.setTitle(sla.getTitle());
		escForm.setDescription(sla.getDescription());
		escForm.setTime(sla.getTime());
		escForm.setTimeUnit(sla.getTimeUnit());
		escForm.setReminder(sla.getReminder());
		escForm.setReminderUnit(sla.getReminderUnit());
		//escForm.setTicketGroup(ticketGroup);
	//	escForm.setTicketCategory(ticketCategory);
		//escForm.setStatus(status);
		//escForm.setPriority(priority);
		
		List<SlaEscalation> listSe= slaEscalationRepository.findBySla(sla);
		
		for(SlaEscalation se:listSe){
			  Escalation escalation= escalationRepository.findOne(se.getEscalation().getId());
			  se.setEscalation(escalation);
	    }
		escForm.setListSe(listSe);
		return escForm;
	}

	@Override
	@Transactional
	public boolean saveEscalation(EscalationForm escForm) {
		for(SlaEscalation se:escForm.getListSe()){
			SlaEscalation seDB= slaEscalationRepository.findOne(se.getId());
			Priority p=null;
			Status s=null;
			if(se.getPriority()!=null){
				p=priorityRepository.findOne(se.getPriority().getId());
			}
			if(se.getStatus()!=null){
				s=statusRepository.findOne(se.getStatus().getId());
			}
			seDB.setPriority(p);
			seDB.setStatus(s);
			seDB.setSendToAssignee(se.getSendToAssignee());
			seDB.setSendToOwner(se.getSendToOwner());
			seDB.setSendToSupervisor(se.getSendToSupervisor());
			seDB.setSendTo(se.getSendTo());
			
			slaEscalationRepository.save(seDB);
		}
		return false;
	}

	@Override
	public SlaTask createSlaTask(Ticket ticket) {
		Sla sla=match(ticket);
		SlaTask slaTask=new SlaTask();
		if(sla!=null){
			slaTask=setTask(slaTask,sla,ticket);
			slaTaskRepository.save(slaTask);
		}
		
		
		return slaTask;
	}
	
	
	@Override
	public SlaTask updateSlaTask(Ticket ticket,String status) {
		List<SlaTask> listTask = slaTaskRepository.findByTicket(ticket);
		SlaTask task=new SlaTask();
		for(SlaTask slaTask:listTask){
			if(status.equals("ON")){
				System.out.println("SLA IS ON");
				Sla sla=match(ticket);
				if(sla!=null){
					System.out.println("SLA IS NOT NULL");
					if(!ticket.getStatus().getId().equals("CLOSED")){
						System.out.println("PREVIOUS SLA :-"+slaTask.getSla().getId());
						System.out.println("CURRENT SLA :-"+sla.getId());
						if(!sla.getId().equals(slaTask.getSla().getId())){
							slaTask=setTask(slaTask,sla,ticket);
						}
					}else{
						slaTask.setStatus("OFF");
					}
				}else{
					slaTask.setStatus("OFF");
				}
			}else{
				slaTask.setStatus("OFF");
			}
			task=slaTaskRepository.save(slaTask);
		}
		
		if(listTask.size()==0){
		  task=this.createSlaTask(ticket);
		}
		
		
		return task;
	}
	
	private Date getNextDateTime(long milis,int unit,int time){
		Calendar calendar = Calendar.getInstance();
		calendar.setTimeInMillis(milis);
		calendar.add(unit, time);
		return calendar.getTime();
	}
	
	private SlaTask setTask(SlaTask slaTask,Sla sla,Ticket ticket){
		slaTask.setTicket(ticket);
		Integer time=0;
		Integer reminder=0;
		if(sla.getTimeUnit().equals("H")){
			time=sla.getTime();
		}else if(sla.getTimeUnit().equals("D")){
			time = sla.getTime()*24;
		}
		
		if(sla.getReminderUnit().equals("M")){
			reminder=sla.getReminder();
		}else if(sla.getReminderUnit().equals("H")){
			reminder=sla.getReminder()*60;
		}else if(sla.getReminderUnit().equals("D")){
			reminder=sla.getReminder()*24*60;
		}
		reminder=(time*60)-reminder;
		
		final Date nextTrigger=getNextDateTime(new Date().getTime(),Calendar.HOUR,time);		
		final Date nextReminder=getNextDateTime(new Date().getTime(),Calendar.MINUTE,reminder);
		slaTask.setNextTrigger(nextTrigger);
		slaTask.setNextReminder(nextReminder);
		slaTask.setStatus("ON");
		slaTask.setCount(0);
		slaTask.setSla(sla);
		return slaTask;
	}
	
	private Sla match(Ticket t){
		List<Sla> listSla= (List<Sla>) slaRepository.findAll();
		Sla slaNew = null;
		return null;
		/**
		for(Sla sla:listSla){
		int matchPoint = 0;
		boolean modBiasa=true;
		
		
		if(sla.getTicketGroup()!=null){
			if(t.getTicketGroup().getId().equals(sla.getTicketGroup().getId())){
			matchPoint = matchPoint+1;
			System.out.println("GROUP IS MATCHED");
			}
		}else{
			matchPoint = matchPoint+1;
		}
		
		
		if(t.getTicketCategory()!=null){
			if(sla.getTicketCategory()!=null){
				if(t.getTicketCategory().getId().equals(sla.getTicketCategory().getId())){
					matchPoint = matchPoint+1;
					System.out.println("CATEGORY IS MATCHED");
				}
			
			}else{
				matchPoint = matchPoint+1;
		
			}
		}else{
			modBiasa=false;
		}
		
		if(sla.getStatus()!=null){
			if(t.getStatus().getId().equals(sla.getStatus().getId())){
			matchPoint = matchPoint+1;
			System.out.println("STATUS IS MATCHED");
			}
		}else{
			matchPoint = matchPoint+1;
		}
		if(sla.getPriority()!=null){
			if(t.getPriority().getId().equals(sla.getPriority().getId())){
				matchPoint = matchPoint+1;
				System.out.println("PRIORITY IS MATCHED");
			}
		}else{
			matchPoint = matchPoint+1;
		}
		
			if(modBiasa){
				if(matchPoint==4){
					slaNew=sla;
				}
			}else{
				if(matchPoint==3){
					slaNew=sla;
				}
			}
			
			
	   }
		return slaNew;**/
	}

}
