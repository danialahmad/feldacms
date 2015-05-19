package com.huemedia.cms.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.huemedia.cms.model.dao.PriorityDAO;
import com.huemedia.cms.model.entity.Priority;
import com.huemedia.cms.model.entity.TicketGroup;
import com.huemedia.cms.model.repository.PriorityRepository;
import com.huemedia.cms.web.controller.administration.datatables.PriorityResult;
import com.huemedia.cms.web.form.MasterForm;

@Service
public class PriorityServiceImpl implements PriorityService {

	@Autowired
	PriorityDAO priorityDAO;
	@Autowired
	PriorityRepository priorityRepository;
	
	@Override
	public Long countAll() {
		// TODO Auto-generated method stub
		return priorityDAO.countAll();
	}

	@Override
	public Long countSearch(MasterForm form) {
		// TODO Auto-generated method stub
		return priorityDAO.countSearch(form);
	}

	@Override
	public List<PriorityResult> getTicketGroups(MasterForm form,
			Integer iDisplayStart, Integer iDisplayLength) {
		SimpleDateFormat sdfFull = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		List<Priority> priorities= priorityDAO.search(form, iDisplayStart, iDisplayLength);
		List<PriorityResult> list=new ArrayList<PriorityResult>();
		for(Priority p:priorities){
			PriorityResult result=new PriorityResult();
			String createBy="";
			String createDate="";
			String updateBy="";
			String updateDate="";
			
			result.setName(p.getName());
			result.setId(String.valueOf(p.getId()));
			result.setRank(p.getRank().toString());
			if(p.getCreateBy()!=null){
				createBy=p.getCreateBy();
			}
			if(p.getCreateDate()!=null){
				createDate=sdfFull.format(p.getCreateDate());
			}
			if(p.getUpdateBy()!=null){
				updateBy=p.getUpdateBy();
			}
			if(p.getUpdateDate()!=null){
				updateDate=sdfFull.format(p.getUpdateDate());
			}
			
			result.setCreateBy(createBy);
			result.setCreateDate(createDate);
			result.setUpdateBy(updateBy);
			result.setUpdateDate(updateDate);
			list.add(result);
		}
		
		
		return list;
	}

	@Override
	public boolean save(MasterForm form) {
		Priority priority =new Priority();
		if(StringUtils.isNotEmpty(form.getId()) && StringUtils.isNotBlank(form.getId())){
			priority = priorityRepository.findOne(form.getId());
			if(priority==null){
				 priority =new Priority();
				 priority.setId(form.getId());
			}
		}
		
		priority.setName(form.getName());
		priority.setRank(form.getRank());
		
		priorityRepository.save(priority);
		return false;
	}

	@Override
	public MasterForm findPriority(String id) {
		MasterForm form = new MasterForm();
		Priority priority=priorityRepository.findOne(id);
		form.setId(String.valueOf(priority.getId()));
		form.setName(priority.getName());
		form.setRank(priority.getRank());
		return form;
	}

	@Override
	public void delete(String id) {
		priorityRepository.delete(id);
	}

}
