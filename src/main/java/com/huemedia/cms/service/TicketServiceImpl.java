package com.huemedia.cms.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.huemedia.cms.model.dao.TicketActivityDAO;
import com.huemedia.cms.model.dao.TicketAssignmentDAO;
import com.huemedia.cms.model.dao.TicketDAO;
import com.huemedia.cms.model.entity.Group;
import com.huemedia.cms.model.entity.Profile;
import com.huemedia.cms.model.entity.StaffGroup;
import com.huemedia.cms.model.entity.Ticket;
import com.huemedia.cms.model.entity.TicketActivity;
import com.huemedia.cms.model.entity.TicketAssignment;
import com.huemedia.cms.model.entity.User;
import com.huemedia.cms.model.repository.GroupRepository;
import com.huemedia.cms.model.repository.ProfileRepository;
import com.huemedia.cms.model.repository.StatusRepository;
import com.huemedia.cms.model.repository.TicketAssignmentRepository;
import com.huemedia.cms.model.repository.TicketRepository;
import com.huemedia.cms.web.controller.complaint.datatables.ActivityResult;
import com.huemedia.cms.web.controller.complaint.datatables.TicketResult;
import com.huemedia.cms.web.form.ActivityForm;
import com.huemedia.cms.web.form.SearchTicketForm;

@Service
public class TicketServiceImpl implements TicketService{
	@Autowired
	TicketDAO ticketDAO;
	@Autowired
	TicketActivityDAO ticketActivityDAO;
	@Autowired
	TicketAssignmentDAO ticketAssignmentDAO;
	@Autowired
	GroupRepository groupRepository;
	@Autowired
	ProfileRepository profileRepository;
	@Autowired
	StatusRepository statusRepository;
	@Autowired
	UserService userService;
	@Autowired
	TicketRepository ticketRepository;
	@Autowired
	TicketAssignmentRepository ticketAssignmentRepository;
	
	@Override
	public List<TicketResult> getTickets(SearchTicketForm form,String[] status,
			Integer iDisplayStart, Integer iDisplayLength,boolean unassigned) {
		
		form.setStatusList(status);
		List<TicketAssignment> tickets= ticketAssignmentDAO.search(form,iDisplayStart, iDisplayLength);
		List<TicketResult> list = new ArrayList<TicketResult>();
		SimpleDateFormat sdfFull = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		
		for(TicketAssignment a:tickets){
		Ticket t= a.getTicket();
		TicketResult tr= new TicketResult();
		tr.setId(t.getId());
		tr.setAssignmentId(a.getId());
		tr.setStatus(a.getStatus().getName());
		tr.setCreateDate(sdfFull.format(t.getCreateDate()));
		tr.setTitle(t.getTicketTitle());
		if(t.getPriority()!=null){
			tr.setPriority(t.getPriority().getName());
		}
		if(a.getGroup()!=null){
			Group g= groupRepository.findOne(a.getGroup().getId());
			tr.setGroup(g.getName());
		}
		if(a.getAssigneeId()!=null){
			Profile p=profileRepository.findOne(a.getAssigneeId());
			tr.setAssignee(p.getName());
		}
		if(t.getIntervalX()!=null){
			tr.setDuration(t.getIntervalX());
		}
		
		if(t.getRating()!=null){
			tr.setRate(t.getRating().getName());
		}
		if(a.getTicketGroup()!=null){
			tr.setTicketGroup(a.getTicketGroup().getName());
		}
		
		list.add(tr);
		}
		return list;
	}
	
	@Override
	public Long countAll(String[] status,boolean unassigned) {
		// TODO Auto-generated method stub
		SearchTicketForm form =new SearchTicketForm();
		form.setStatusList(status);
		return ticketAssignmentDAO.countSearch(form);
	}
	@Override
	public Long countSearch(SearchTicketForm form,String[] status,boolean unassigned) {
		// TODO Auto-generated method stub
		form.setStatusList(status);
		return ticketAssignmentDAO.countSearch(form);
	}
	@Override
	public Long countAllByGroup(String[] group,Integer region,String[] status) {
		SearchTicketForm form =new SearchTicketForm();
		form.setGroup(group);
		form.setStatusList(status);
		return ticketAssignmentDAO.countSearch(form);
	}
	@Override
	public Long countSearchByGroup(SearchTicketForm form, String[] group,Integer region,String[] status) {
		// TODO Auto-generated method stub
		form.setGroup(group);
		form.setStatusList(status);
		return ticketAssignmentDAO.countSearch(form);
	}
	@Override
	public List<TicketResult> getTicketsByGroup(SearchTicketForm form,
			String[] group,Integer region,String[] status, Integer iDisplayStart, Integer iDisplayLength) {
		
		form.setGroup(group);
		form.setStatusList(status);
		
		List<TicketAssignment> tickets= ticketAssignmentDAO.search(form,iDisplayStart, iDisplayLength);
		List<TicketResult> list = new ArrayList<TicketResult>();
		SimpleDateFormat sdfFull = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		for(TicketAssignment a:tickets){
			Ticket t=a.getTicket();
		TicketResult tr= new TicketResult();
		tr.setAssignmentId(a.getId());
		tr.setId(t.getId());
		tr.setStatus(a.getStatus().getName());
		tr.setCreateDate(sdfFull.format(t.getCreateDate()));
		tr.setTitle(t.getTicketTitle());
		if(t.getPriority()!=null){
			tr.setPriority(t.getPriority().getName());
		}
		
		if(t.getRating()!=null){
			tr.setRate(t.getRating().getName());
		}
		if(a.getTicketGroup()!=null){
			tr.setTicketGroup(a.getTicketGroup().getName());
		}
	
			list.add(tr);
		
		
		}
		return list;
	}
	@Override
	public Long countAllByAssignee(Integer assigneeId) {
		// TODO Auto-generated method stub
		SearchTicketForm form=new SearchTicketForm();
		form.setAssigneeId(assigneeId);
		return ticketAssignmentDAO.countSearch(form);
	}
	@Override
	public Long countSearchByAssignee(SearchTicketForm form, Integer assigneeId) {
		// TODO Auto-generated method stub
		form.setAssigneeId(assigneeId);
		return ticketAssignmentDAO.countSearch(form);
	}
	@Override
	public List<TicketResult> getTicketsByAssignee(SearchTicketForm form,
			Integer assigneeId, Integer iDisplayStart, Integer iDisplayLength) {
		
		form.setAssigneeId(assigneeId);
		
		List<TicketAssignment> tickets= ticketAssignmentDAO.search(form, iDisplayStart, iDisplayLength);
		List<TicketResult> list = new ArrayList<TicketResult>();
		SimpleDateFormat sdfFull = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		for(TicketAssignment a:tickets){
			Ticket t=a.getTicket();
		TicketResult tr= new TicketResult();
		tr.setId(t.getId());
		tr.setAssignmentId(a.getId());
		tr.setStatus(a.getStatus().getName());
		tr.setCreateDate(sdfFull.format(t.getCreateDate()));
		tr.setTitle(t.getTicketTitle());
		if(t.getPriority()!=null){
			tr.setPriority(t.getPriority().getName());
		}
		if(t.getRating()!=null){
			tr.setRate(t.getRating().getName());
		}
		if(a.getTicketGroup()!=null){
			tr.setTicketGroup(a.getTicketGroup().getName());
		}
		
		list.add(tr);
		}
		return list;
	}
	@Override
	public Long countAllByProfileId(Integer profileId) {
		// TODO Auto-generated method stub
		return ticketDAO.countAllByByProfileID(profileId);
	}
	@Override
	public Long countSearchByProfileId(SearchTicketForm form, Integer profileId) {
		// TODO Auto-generated method stub
		return ticketDAO.countSearchByProfileID(form, profileId);
	}
	@Override
	public List<TicketResult> getTicketsByProfileId(SearchTicketForm form,
			Integer profileId, Integer iDisplayStart, Integer iDisplayLength) {
		List<Ticket> tickets= ticketDAO.searchByProfileID(form, profileId, iDisplayStart, iDisplayLength);
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
	public Long countAllActivities(Integer assignmentId) {
		// TODO Auto-generated method stub
		return ticketActivityDAO.countAll(assignmentId);
	}
	@Override
	public Long countSearchActivities(ActivityForm form,Integer assignmentId) {
		// TODO Auto-generated method stub
		return ticketActivityDAO.countSearch(form,assignmentId);
	}
	@Override
	public List<ActivityResult> getTicketActivities(ActivityForm form,Integer assignmentId,
			Integer iDisplayStart, Integer iDisplayLength) {
		List<TicketActivity> ticketActivities= ticketActivityDAO.search(form,assignmentId, iDisplayStart, iDisplayLength);
		List<ActivityResult> list = new ArrayList<ActivityResult>();
		SimpleDateFormat sdfFull = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		for(TicketActivity t:ticketActivities){
			ActivityResult tr= new ActivityResult();
		tr.setId(t.getId());
		if(t.getActivityType()!=null){
			tr.setActivityType(t.getActivityType().getName());
		}
		tr.setDate(sdfFull.format(t.getCreateDate()));
		tr.setDescription(t.getDescription());
		list.add(tr);
		}
		return list;
	}
	
	@Override
	public List<Ticket> getAllTickets(Integer iDisplayStart, Integer iDisplayLength) {
		// TODO Auto-generated method stub
		return ticketDAO.findAll(iDisplayLength, iDisplayStart);
	}
	@Override
	public List<TicketAssignment> getTicketsByAssignee(Integer assigneeId,
			Integer iDisplayStart, Integer iDisplayLength) {
		// TODO Auto-generated method stub
		SearchTicketForm form=new SearchTicketForm();
		form.setAssigneeId(assigneeId);
		return ticketAssignmentDAO.search(form,iDisplayStart, iDisplayLength);
	}
	
	@Override
	public List<TicketAssignment> getTicketsBySupervisor(Integer supervisorId,
			Integer iDisplayStart, Integer iDisplayLength) {
		// TODO Auto-generated method stub
		SearchTicketForm form=new SearchTicketForm();
		form.setSupervisorId(supervisorId);
		return ticketAssignmentDAO.search(form,iDisplayStart, iDisplayLength);
	}
	
	@Override
	public List<Ticket> getTicketsByGroup(String[] group,Integer region,String[] status,
			Integer iDisplayStart, Integer iDisplayLength) {
		// TODO Auto-generated method stub
		return ticketDAO.searchByGroup(new SearchTicketForm(), group,region,status, iDisplayStart, iDisplayLength);
	}
	@Override
	public Long countAll(String[] status, Integer supervisorId) {
		SearchTicketForm form=new SearchTicketForm();
		form.setStatusList(status);
		form.setSupervisorId(supervisorId);
		return ticketAssignmentDAO.countSearch(form);
	}
	@Override
	public Long countSearch(SearchTicketForm form, String[] status,
			Integer supervisorId) {
		form.setStatusList(status);
		form.setSupervisorId(supervisorId);
		return ticketAssignmentDAO.countSearch(form);
	}
	@Override
	public List<TicketResult> getTickets(SearchTicketForm form,
			String[] status, Integer supervisorId, Integer iDisplayStart,
			Integer iDisplayLength) {
		form.setStatusList(status);
		form.setSupervisorId(supervisorId);
		
		List<TicketAssignment> tickets= ticketAssignmentDAO.search(form,iDisplayStart, iDisplayLength);
		List<TicketResult> list = new ArrayList<TicketResult>();
		SimpleDateFormat sdfFull = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		for(TicketAssignment a:tickets){
		Ticket t=a.getTicket();
		TicketResult tr= new TicketResult();
		tr.setId(t.getId());
		tr.setAssignmentId(a.getId());
		tr.setStatus(a.getStatus().getName());
		tr.setCreateDate(sdfFull.format(a.getCreateDate()));
		tr.setTitle(t.getTicketTitle());
		if(t.getPriority()!=null){
			tr.setPriority(t.getPriority().getName());
		}
		if(a.getGroup()!=null){
			Group g= groupRepository.findOne(a.getGroup().getId());
			tr.setGroup(g.getName());
		}
		if(a.getAssigneeId()!=null){
			Profile p=profileRepository.findOne(a.getAssigneeId());
			tr.setAssignee(p.getName());
		}else{
			tr.setAssignee("-");
		}
		if(a.getCaseInterval()!=null){
			tr.setDuration(a.getCaseInterval());
		}
		if(t.getRating()!=null){
			tr.setRate(t.getRating().getName());
		}
		if(a.getTicketGroup()!=null){
			tr.setTicketGroup(a.getTicketGroup().getName());
		}
		list.add(tr);
		}
		return list;
	}
	@Override
	public Long countAllBySupervisor(Integer supervisorId) {
		// TODO Auto-generated method stub
		return ticketDAO.countAllBySupervisorID(supervisorId);
	}
	@Override
	public Long countSearchBySupervisor(SearchTicketForm form,
			Integer supervisorId) {
		// TODO Auto-generated method stub
		return ticketDAO.countSearchBySupervisorID(form, supervisorId);
	}

	@Override
	public Long countAll() {
		// TODO Auto-generated method stub
		return ticketDAO.countAll();
	}

	@Override
	public Long countSearch(SearchTicketForm form) {
		// TODO Auto-generated method stub
		return ticketDAO.countSearch(form);
	}

	@Override
	public List<TicketResult> searchAll(SearchTicketForm form, Integer iDisplayStart,
			Integer iDisplayLength) {
		List<Ticket> tickets= ticketDAO.search(form,iDisplayStart, iDisplayLength);
		List<TicketResult> list = new ArrayList<TicketResult>();
		SimpleDateFormat sdfFull = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		for(Ticket t:tickets){
		TicketResult tr= new TicketResult();
		tr.setId(t.getId());
		tr.setStatus(t.getStatus().getName());
		tr.setCreateDate(sdfFull.format(t.getCreateDate()));
		tr.setTitle(t.getTicketTitle());
		String rate="";
		if(t.getPriority()!=null){
			tr.setPriority(t.getPriority().getName());
		}
		if(t.getGroupId()!=null){
			Group g= groupRepository.findOne(t.getGroupId());
			tr.setGroup(g.getName());
		}
		if(t.getAssigneeId()!=null){
			Profile p=profileRepository.findOne(t.getAssigneeId());
			tr.setAssignee(p.getName());
		}
		if(t.getIntervalX()!=null){
			tr.setDuration(t.getIntervalX());
		}
		if(t.getRating()!=null){
			tr.setRate(t.getRating().getName());
		}
		
		list.add(tr);
		}
		return list;
	}

	@Override
	public Long countAllUnassigned(String[] group, Integer region,
			String[] status) {
		SearchTicketForm form=new SearchTicketForm();
		form.setStatusList(status);
		form.setGroup(group);
		return ticketAssignmentDAO.countSearch(form);
	}

	@Override
	public Long countSearchUnassigned(SearchTicketForm form, String[] group,
			Integer region, String[] status) {
		// TODO Auto-generated method stub
		form.setStatusList(status);
		form.setGroup(group);
		return ticketAssignmentDAO.countSearch(form);
	}

	@Override
	public List<TicketResult> getTicketsUnassigned(SearchTicketForm form,
			String[] group, Integer region, String[] status,
			Integer iDisplayStart, Integer iDisplayLength) {
		form.setStatusList(status);
		form.setGroup(group);
		List<TicketAssignment> tickets= ticketAssignmentDAO.search(form, iDisplayStart, iDisplayLength);
		List<TicketResult> list = new ArrayList<TicketResult>();
		SimpleDateFormat sdfFull = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		for(TicketAssignment a:tickets){
		Ticket t= a.getTicket();	
		TicketResult tr= new TicketResult();
		tr.setId(t.getId());
		tr.setAssignmentId(a.getId());
		tr.setStatus(a.getStatus().getName());
		tr.setCreateDate(sdfFull.format(t.getCreateDate()));
		tr.setTitle(t.getTicketTitle());
		if(t.getPriority()!=null){
			tr.setPriority(t.getPriority().getName());
		}
		
		if(t.getRating()!=null){
			tr.setRate(t.getRating().getName());
		}
		if(a.getTicketGroup()!=null){
			tr.setTicketGroup(a.getTicketGroup().getName());
		}
			list.add(tr);
		
		
		}
		return list;
	}

	@Override
	public Long countAllAssignments(String ticketId) {
		SearchTicketForm form =new SearchTicketForm();
		form.setId(ticketId);
		return ticketAssignmentDAO.countSearch(form);
	}

	@Override
	public Long countSearchAssignments(SearchTicketForm form, String ticketId) {
		form.setId(ticketId);
		return ticketAssignmentDAO.countSearch(form);
	}

	@Override
	public List<TicketResult> getTicketAssignments(SearchTicketForm form,
			String ticketId, Integer iDisplayStart, Integer iDisplayLength) {
		List<TicketAssignment> tickets= ticketAssignmentDAO.search(form,iDisplayStart, iDisplayLength);
		List<TicketResult> list = new ArrayList<TicketResult>();
		SimpleDateFormat sdfFull = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		for(TicketAssignment t:tickets){
			TicketResult tr= new TicketResult();
			tr.setId(t.getTicket().getId());
			tr.setAssignmentId(t.getId());
			String group="-";
			String lo="-";
			if(t.getGroup()!=null){
				Group g= groupRepository.findOne(t.getGroup().getId());
				group=g.getName();
			}
			if(t.getSupervisorId()!=null){
				Profile p=profileRepository.findOne(t.getSupervisorId());
				lo=p.getName();
			}
			tr.setGroup(group);
			tr.setLo(lo);
			tr.setStatus(t.getStatus().getId());
			tr.setLastUpdate(sdfFull.format(t.getLastStatusUpdate()));
			tr.setStatusM(t.getTicket().getStatus().getId());
			list.add(tr);
		}
		return list;
	}

	@Override
	public Long countAllActivities(String ticketId) {
		// TODO Auto-generated method stub
		return ticketActivityDAO.countAll(ticketId);
	}

	@Override
	public Long countSearchActivities(ActivityForm form, String ticketId) {
		// TODO Auto-generated method stub
		return ticketActivityDAO.countSearch(form, ticketId);
	}

	@Override
	public List<ActivityResult> getTicketActivities(ActivityForm form,
			String ticketId, Integer iDisplayStart, Integer iDisplayLength) {
		List<TicketActivity> ticketActivities= ticketActivityDAO.search(form,ticketId, iDisplayStart, iDisplayLength);
		List<ActivityResult> list = new ArrayList<ActivityResult>();
		SimpleDateFormat sdfFull = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		for(TicketActivity t:ticketActivities){
			ActivityResult tr= new ActivityResult();
		tr.setId(t.getId());
		String at="";
		
		
		User u=userService.getByUsername(t.getCreateBy());
		
		
		Profile p=u.getProfile();
		List<StaffGroup> lsg=userService.getStaffGroups(p);
		StaffGroup sg = lsg.get(0);
		Group g= groupRepository.findOne(sg.getGroup().getId());
		StringBuilder b=new StringBuilder();
		b.append(p.getName());
		if(p.getStaffNo()!=null){
			b.append(" ("+p.getStaffNo()+")");
		}
		tr.setCreateBy(b.toString());
		tr.setGroup(g.getName());
		
		
		if(t.getActivityType()!=null){
			if(t.getActivityType().getId()!=null){
			at=t.getActivityType().getName();
			}
		}
		
		tr.setActivityType(at);
		tr.setDate(sdfFull.format(t.getCreateDate()));
		tr.setDescription(t.getDescription());
		list.add(tr);
		}
		return list;
	}

	@Override
	public Long countAllByAssignee(String[] status, Integer assigneeId) {
		SearchTicketForm form=new SearchTicketForm();
		form.setStatusList(status);
		form.setAssigneeId(assigneeId);
		return ticketAssignmentDAO.countSearch(form);
	}

	@Override
	public Long countSearchByAssignee(SearchTicketForm form, String[] status,
			Integer assigneeId) {
		form.setStatusList(status);
		form.setAssigneeId(assigneeId);
		return ticketAssignmentDAO.countSearch(form);
	}

	@Override
	public List<TicketResult> getTicketsByAssignee(SearchTicketForm form,
			String[] status, Integer assigneeId, Integer iDisplayStart,
			Integer iDisplayLength) {
		form.setStatusList(status);
		form.setAssigneeId(assigneeId);
		
		List<TicketAssignment> tickets= ticketAssignmentDAO.search(form,iDisplayStart, iDisplayLength);
		List<TicketResult> list = new ArrayList<TicketResult>();
		SimpleDateFormat sdfFull = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		for(TicketAssignment a:tickets){
		Ticket t=a.getTicket();
		TicketResult tr= new TicketResult();
		tr.setId(t.getId());
		tr.setAssignmentId(a.getId());
		tr.setStatus(a.getStatus().getName());
		tr.setCreateDate(sdfFull.format(a.getCreateDate()));
		tr.setTitle(t.getTicketTitle());
		if(t.getPriority()!=null){
			tr.setPriority(t.getPriority().getName());
		}
		if(a.getGroup()!=null){
			Group g= groupRepository.findOne(a.getGroup().getId());
			tr.setGroup(g.getName());
		}
		if(a.getAssigneeId()!=null){
			Profile p=profileRepository.findOne(a.getAssigneeId());
			tr.setAssignee(p.getName());
		}else{
			tr.setAssignee("-");
		}
		if(a.getCaseInterval()!=null){
			tr.setDuration(a.getCaseInterval());
		}
		if(t.getRating()!=null){
			tr.setRate(t.getRating().getName());
		}
		if(a.getTicketGroup()!=null){
			tr.setTicketGroup(a.getTicketGroup().getName());
		}
		list.add(tr);
		}
		return list;
	}
	
	


}
