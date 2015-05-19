package com.huemedia.cms.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.huemedia.cms.model.dao.GroupDAO;
import com.huemedia.cms.model.entity.Group;
import com.huemedia.cms.model.entity.Priority;
import com.huemedia.cms.model.repository.GroupRepository;
import com.huemedia.cms.web.controller.administration.datatables.GroupResult;
import com.huemedia.cms.web.form.MasterForm;

@Service
public class GroupServiceImpl implements GroupService {

	@Autowired
	GroupDAO groupDAO;
	@Autowired
	GroupRepository groupRepository;
	
	@Override
	public Long countAll() {
		// TODO Auto-generated method stub
		return groupDAO.countAll();
	}

	@Override
	public Long countSearch(MasterForm form) {
		// TODO Auto-generated method stub
		return groupDAO.countSearch(form);
	}

	@Override
	public List<GroupResult> getGroups(MasterForm form, Integer iDisplayStart,
			Integer iDisplayLength) {
		SimpleDateFormat sdfFull = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		List<Group> groups= groupDAO.search(form, iDisplayStart, iDisplayLength);
		List<GroupResult> list=new ArrayList<GroupResult>();
		for(Group p:groups){
			GroupResult result=new GroupResult();
			String createBy="";
			String createDate="";
			String updateBy="";
			String updateDate="";
			
			result.setName(p.getName());
			result.setId(p.getId());
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
		Group group =new Group();
		if(StringUtils.isNotEmpty(form.getId()) && StringUtils.isNotBlank(form.getId())){
			group = groupRepository.findOne(form.getId());
			if(group==null){
				group =new Group();
				group.setId(form.getId());
			}
		}
		
		group.setName(form.getName());
		
		
		groupRepository.save(group);
		return false;
	}

	@Override
	public MasterForm findGroup(String id) {
		MasterForm form = new MasterForm();
		Group group=groupRepository.findOne(id);
		form.setId(String.valueOf(group.getId()));
		form.setName(group.getName());
		return form;
	}

	@Override
	public void delete(String id) {
		groupRepository.delete(id);
	}

}
