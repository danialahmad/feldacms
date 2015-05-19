package com.huemedia.cms.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.huemedia.cms.model.dao.TicketCategoryDAO;
import com.huemedia.cms.model.entity.TicketCategory;
import com.huemedia.cms.model.repository.TicketCategoryRepository;
import com.huemedia.cms.web.controller.administration.datatables.TicketCategoryResult;
import com.huemedia.cms.web.form.MasterForm;

@Service
public class TicketCategoryServiceImpl implements TicketCategoryService{

	@Autowired
	TicketCategoryDAO ticketCategoryDAO;
	@Autowired
	TicketCategoryRepository ticketCategoryRepository;
	
	@Override
	public Long countAll() {
		// TODO Auto-generated method stub
		return ticketCategoryDAO.countAll();
	}

	@Override
	public Long countSearch(MasterForm form) {
		// TODO Auto-generated method stub
		return ticketCategoryDAO.countSearch(form);
	}

	@Override
	public List<TicketCategoryResult> getTicketCategories(MasterForm form,
			Integer iDisplayStart, Integer iDisplayLength) {
		SimpleDateFormat sdfFull = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		List<TicketCategory> tg=ticketCategoryDAO.search(form, iDisplayStart, iDisplayLength);
		List<TicketCategoryResult> list=new ArrayList<TicketCategoryResult>();
		for(TicketCategory t:tg){
			TicketCategoryResult result=new TicketCategoryResult();
			result.setName(t.getName());
			result.setId(String.valueOf(t.getId()));
			String createBy="";
			String createDate="";
			String updateBy="";
			String updateDate="";
			String ticketGroup="";
			if(t.getTicketGroup()!=null){
				ticketGroup=t.getTicketGroup().getName();
			}
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
			result.setTicketGroup(ticketGroup);
			list.add(result);
		}
		return list;
	}

	@Override
	public boolean save(MasterForm form) {
		
		TicketCategory ticketCategory =new TicketCategory();
		if(StringUtils.isNotEmpty(form.getId()) && StringUtils.isNotBlank(form.getId())){
			ticketCategory = ticketCategoryRepository.findOne(Integer.parseInt(form.getId()));
		}
		ticketCategory.setTicketGroup(form.getTicketGroup());
		
		ticketCategory.setName(form.getName());
		
		ticketCategoryRepository.save(ticketCategory);
		return false;
	}
	
	@Override
	public MasterForm findTicketCategory(Integer id) {
		MasterForm form = new MasterForm();
		TicketCategory ticketCategory=ticketCategoryRepository.findOne(id);
		form.setId(String.valueOf(ticketCategory.getId()));
		form.setName(ticketCategory.getName());
		form.setTicketGroup(ticketCategory.getTicketGroup());
		return form;
	}

	@Override
	public void delete(Integer id) {
		ticketCategoryRepository.delete(id);
	}

}
