package com.huemedia.cms.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.huemedia.cms.model.dao.StatusDAO;
import com.huemedia.cms.model.entity.Priority;
import com.huemedia.cms.model.entity.Status;
import com.huemedia.cms.model.repository.StatusRepository;
import com.huemedia.cms.web.controller.administration.datatables.StatusResult;
import com.huemedia.cms.web.form.MasterForm;

@Service
public class StatusServiceImpl implements StatusService {

	@Autowired
	StatusDAO statusDAO;
	@Autowired
	StatusRepository statusRepository;
	
	@Override
	public Long countAll() {
		// TODO Auto-generated method stub
		return statusDAO.countAll();
	}

	@Override
	public Long countSearch(MasterForm form) {
		// TODO Auto-generated method stub
		return statusDAO.countSearch(form);
	}

	@Override
	public List<StatusResult> getStatuses(MasterForm form,
			Integer iDisplayStart, Integer iDisplayLength) {
		SimpleDateFormat sdfFull = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		List<Status> statuses= statusDAO.search(form, iDisplayStart, iDisplayLength);
		List<StatusResult> list=new ArrayList<StatusResult>();
		for(Status p:statuses){
			StatusResult result=new StatusResult();
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
		Status status =new Status();
		if(StringUtils.isNotEmpty(form.getId()) && StringUtils.isNotBlank(form.getId())){
			status = statusRepository.findOne(form.getId());
			if(status==null){
				status =new Status();
				status.setId(form.getId());
			}
		}
		status.setName(form.getName());
		status.setRank(form.getRank());
		
		statusRepository.save(status);
		return false;
	}

	@Override
	public MasterForm findStatus(String id) {
		MasterForm form = new MasterForm();
		Status status=statusRepository.findOne(id);
		form.setId(String.valueOf(status.getId()));
		form.setName(status.getName());
		form.setRank(status.getRank());
		return form;
	}

	@Override
	public void delete(String id) {
		statusRepository.delete(id);
	}

}
