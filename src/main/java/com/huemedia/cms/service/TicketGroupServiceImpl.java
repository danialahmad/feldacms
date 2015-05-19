package com.huemedia.cms.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.huemedia.cms.model.dao.TicketGroupDAO;
import com.huemedia.cms.model.entity.TicketGroup;
import com.huemedia.cms.model.repository.TicketGroupRepository;
import com.huemedia.cms.web.controller.administration.datatables.TicketGroupResult;
import com.huemedia.cms.web.form.MasterForm;

@Service
public class TicketGroupServiceImpl implements TicketGroupService{

	@Autowired 
	TicketGroupDAO ticketGroupDAO;
	@Autowired 
	TicketGroupRepository ticketGroupRepository;
	
	@Override
	public Long countAll() {
		// TODO Auto-generated method stub
		return ticketGroupDAO.countAll();
	}

	@Override
	public Long countSearch(MasterForm form) {
		// TODO Auto-generated method stub
		return ticketGroupDAO.countSearch(form);
	}

	@Override
	public List<TicketGroupResult> getTicketGroups(MasterForm form,
			Integer iDisplayStart, Integer iDisplayLength) {
		SimpleDateFormat sdfFull = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		List<TicketGroup> tg=ticketGroupDAO.search(form, iDisplayStart, iDisplayLength);
		List<TicketGroupResult> list=new ArrayList<TicketGroupResult>();
		for(TicketGroup t:tg){
			TicketGroupResult result=new TicketGroupResult();
			result.setName(t.getName());
			result.setId(String.valueOf(t.getId()));
			String createBy="";
			String createDate="";
			String updateBy="";
			String updateDate="";
			
			if(t.getCreateBy()!=null){
				createBy=t.getCreateBy();
			}
			if(t.getCreateDate()!=null){
				createDate=sdfFull.format(t.getCreateDate());
			}
			if(t.getUpdateBy()!=null){
				updateBy=t.getUpdateBy();
			}
			if(t.getUpdateDate()!=null){
				updateDate=sdfFull.format(t.getUpdateDate());
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
		
		TicketGroup ticketGroup =new TicketGroup();
		if(StringUtils.isNotEmpty(form.getId()) && StringUtils.isNotBlank(form.getId())){
			ticketGroup = ticketGroupRepository.findOne(Integer.parseInt(form.getId()));
		}
		ticketGroup.setName(form.getName());
		
		ticketGroupRepository.save(ticketGroup);
		return false;
	}

	@Override
	public MasterForm findTicketGroup(Integer id) {
		MasterForm form = new MasterForm();
		TicketGroup ticketGroup=ticketGroupRepository.findOne(id);
		form.setId(String.valueOf(ticketGroup.getId()));
		form.setName(ticketGroup.getName());
		return form;
	}

	@Override
	public void delete(Integer id) {
		
		 ticketGroupRepository.delete(id);
	}

}
