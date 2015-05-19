package com.huemedia.cms.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.huemedia.cms.model.dao.TicketActivityDAO;
import com.huemedia.cms.model.dao.TicketDAO;
import com.huemedia.cms.model.entity.Profile;
import com.huemedia.cms.model.entity.Ticket;
import com.huemedia.cms.model.repository.TicketRepository;
import com.huemedia.cms.security.service.UserDetail;
import com.huemedia.cms.web.controller.complaint.datatables.TicketResult;
import com.huemedia.cms.web.form.SearchTicketForm;

@Service
public  class DashboardServiceImpl implements DashboardService {

	@Autowired
	TicketRepository ticketRepository;
	@Autowired
	TicketDAO ticketDAO;
	
	@Override
	public Map caseCountInfoByProfile() {
		SecurityContext securityContext = SecurityContextHolder.getContext();
		UserDetail u=(UserDetail) securityContext.getAuthentication().getPrincipal();
		Profile p=u.getProfile();
		 List<Object> listObj=new ArrayList<Object>();
		   List<Object> objs1= ticketRepository.todayCaseByProfile(p.getId());
		   String[] today = {"today",String.valueOf(extractSingelData(objs1))};
		   listObj.add(today);
		   
		   List<Object> objs2= ticketRepository.weekCaseByProfile(p.getId());
		   String[] week = {"week",String.valueOf(extractSingelData(objs2))};
		   listObj.add(week);
		   
		   List<Object> objs3= ticketRepository.monthCaseByProfile(p.getId());
		   String[] month = {"month",String.valueOf(extractSingelData(objs3))};
		   listObj.add(month);
		   
		   List<Object> objs4= ticketRepository.yearCaseByProfile(p.getId());
		   String[] year = {"year",String.valueOf(extractSingelData(objs4))};
		   listObj.add(year);
		   return this.generateMap(listObj);
	}
	
	@Override
	public Map caseCountInfoBySupervisor() {
		SecurityContext securityContext = SecurityContextHolder.getContext();
		UserDetail u=(UserDetail) securityContext.getAuthentication().getPrincipal();
		Profile p=u.getProfile();
		 List<Object> listObj=new ArrayList<Object>();
		   List<Object> objs1= ticketRepository.todayCaseBySupervisor(p.getId());
		   String[] today = {"today",String.valueOf(extractSingelData(objs1))};
		   listObj.add(today);
		   
		   List<Object> objs2= ticketRepository.weekCaseBySupervisor(p.getId());
		   String[] week = {"week",String.valueOf(extractSingelData(objs2))};
		   listObj.add(week);
		   
		   List<Object> objs3= ticketRepository.monthCaseBySupervisor(p.getId());
		   String[] month = {"month",String.valueOf(extractSingelData(objs3))};
		   listObj.add(month);
		   
		   List<Object> objs4= ticketRepository.yearCaseBySupervisor(p.getId());
		   String[] year = {"year",String.valueOf(extractSingelData(objs4))};
		   listObj.add(year);
		   return this.generateMap(listObj);
	}
	
	@Override
	public Map caseCountInfo() {
		SecurityContext securityContext = SecurityContextHolder.getContext();
		UserDetail u=(UserDetail) securityContext.getAuthentication().getPrincipal();
		Profile p=u.getProfile();
		 List<Object> listObj=new ArrayList<Object>();
		   List<Object> objs1= ticketRepository.todayCase();
		   String[] today = {"today",String.valueOf(extractSingelData(objs1))};
		   listObj.add(today);
		   
		   List<Object> objs2= ticketRepository.weekCase();
		   String[] week = {"week",String.valueOf(extractSingelData(objs2))};
		   listObj.add(week);
		   
		   List<Object> objs3= ticketRepository.monthCase();
		   String[] month = {"month",String.valueOf(extractSingelData(objs3))};
		   listObj.add(month);
		   
		   List<Object> objs4= ticketRepository.yearCase();
		   String[] year = {"year",String.valueOf(extractSingelData(objs4))};
		   listObj.add(year);
		   return this.generateMap(listObj);
	}
	
	 private Object  extractSingelData(List<Object> listObj){
		   Object ob = listObj.get(0);
		 //  Object[] j = (Object[]) ob;
		   return ob;
	   }
	  
	 private Map generateMap(List<Object> listObj){
		  List<Map> list = new ArrayList<Map>();
		  for(Object obj : listObj){
			  Object[] j = (Object[]) obj;
			  Map map = new HashMap();
				map=  addItem(j[0].toString(),Integer.parseInt(j[1].toString()));
			  list.add(map);
		  }
		  Map jmap= new HashMap();
		  jmap.put("aaData", list);
		  return jmap;
		  
	  }
	  
	  private Map addItem(String label, Integer total){
		  Map map = new HashMap();
		  map.put("label",label);
		  map.put("data",total);
		 return map;
	  }

	@Override
	public Map caseYearStatistics() {
		 List<Object> listObj= ticketRepository.yearStatistics();
		return generateMap(listObj);
	}

	@Override
	public Map caseInfoByStatus() {
		List<Object> listObj= ticketRepository.countStatus();
		return generateMap(listObj);
	}

	@Override
	public Map caseInfoByPriorityByProfile() {
		SecurityContext securityContext = SecurityContextHolder.getContext();
		UserDetail u=(UserDetail) securityContext.getAuthentication().getPrincipal();
		Profile p=u.getProfile();
		List<Object> listObj= ticketRepository.countPriorityByProfile(p.getId());
		return generateMap(listObj);
	}
	
	@Override
	public Map caseInfoByPriorityBySupervisor() {
		SecurityContext securityContext = SecurityContextHolder.getContext();
		UserDetail u=(UserDetail) securityContext.getAuthentication().getPrincipal();
		Profile p=u.getProfile();
		List<Object> listObj= ticketRepository.countPriorityBySupervisor(p.getId());
		return generateMap(listObj);
	}
	
	@Override
	public Map caseInfoByPriority() {
		SecurityContext securityContext = SecurityContextHolder.getContext();
		UserDetail u=(UserDetail) securityContext.getAuthentication().getPrincipal();
		Profile p=u.getProfile();
		List<Object> listObj= ticketRepository.countPriority();
		return generateMap(listObj);
	}
	@Override
	public Long countAllTicketByTime(SearchTicketForm form) {
		// TODO Auto-generated method stub
		return ticketDAO.countSearchAllTicketByTime(form);
	}
	@Override
	public Long countSearchTicketByTime(SearchTicketForm form) {
		// TODO Auto-generated method stub
		return ticketDAO.countSearchTicketByTime(form);
	}
	@Override
	public List<TicketResult> getTicketByTime(SearchTicketForm form,
			Integer iDisplayStart, Integer iDisplayLength) {
		
		List<Ticket> tickets= ticketDAO.searchTicketByTime(form, iDisplayStart, iDisplayLength);
		List<TicketResult> list = new ArrayList<TicketResult>();
		SimpleDateFormat sdfFull = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		for(Ticket t:tickets){
		TicketResult tr= new TicketResult();
		tr.setId(t.getId());
		tr.setStatus(t.getStatus().getName());
		tr.setCreateDate(sdfFull.format(t.getCreateDate()));
		tr.setTitle(t.getTicketTitle());
		if(t.getPriority()!=null){
			tr.setPriority(t.getPriority().getName());
		}
		
		list.add(tr);
		}
		return list;
	}

	@Override
	public Long countPriorityByGroup(String gId,String priorityId) {
		
		return ticketDAO.countPriorityByGroup(gId,priorityId);
	}

}
