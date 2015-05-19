package com.huemedia.cms.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.huemedia.cms.model.dao.ActivityTypeDAO;
import com.huemedia.cms.model.entity.ActivityType;
import com.huemedia.cms.model.repository.ActivityTypeRepository;
import com.huemedia.cms.web.controller.administration.datatables.ActivityTypeResult;
import com.huemedia.cms.web.form.MasterForm;

@Service
public class ActivityTypeServiceImpl implements ActivityTypeService {

	@Autowired
	ActivityTypeDAO activityTypeDAO;
	@Autowired
	ActivityTypeRepository activityTypeRepository; 
	
	@Override
	public Long countAll() {
		// TODO Auto-generated method stub
		return activityTypeDAO.countAll();
	}

	@Override
	public Long countSearch(MasterForm form) {
		// TODO Auto-generated method stub
		return activityTypeDAO.countSearch(form);
	}

	@Override
	public List<ActivityTypeResult> getActivityTypes(MasterForm form,
			Integer iDisplayStart, Integer iDisplayLength) {
		SimpleDateFormat sdfFull = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		List<ActivityType> activityTypes= activityTypeDAO.search(form, iDisplayStart, iDisplayLength);
		List<ActivityTypeResult> list=new ArrayList<ActivityTypeResult>();
		for(ActivityType p:activityTypes){
			ActivityTypeResult result=new ActivityTypeResult();
			String createBy="";
			String createDate="";
			String updateBy="";
			String updateDate="";
			
			result.setName(p.getName());
			result.setId(String.valueOf(p.getId()));
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
		ActivityType activityType =new ActivityType();
		if(StringUtils.isNotEmpty(form.getId()) && StringUtils.isNotBlank(form.getId())){
			activityType = activityTypeRepository.findOne(Integer.parseInt(form.getId()));
		}
		activityType.setName(form.getName());
		
		activityTypeRepository.save(activityType);
		return false;
	}

	@Override
	public MasterForm findActivityType(Integer id) {
		MasterForm form = new MasterForm();
		ActivityType activityType=activityTypeRepository.findOne(id);
		form.setId(String.valueOf(activityType.getId()));
		form.setName(activityType.getName());
		
		return form;	
		}

	@Override
	public void delete(Integer id) {
		activityTypeRepository.delete(id);
	}
	
	
	

}
