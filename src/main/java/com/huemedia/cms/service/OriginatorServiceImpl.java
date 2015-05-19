package com.huemedia.cms.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.huemedia.cms.model.dao.OriginatorDAO;
import com.huemedia.cms.model.entity.Originator;
import com.huemedia.cms.model.entity.Priority;
import com.huemedia.cms.model.repository.OriginatorRepository;
import com.huemedia.cms.web.controller.administration.datatables.OriginatorResult;
import com.huemedia.cms.web.form.MasterForm;

@Service
public class OriginatorServiceImpl implements OriginatorService {

	@Autowired
	OriginatorDAO originatorDAO;
	@Autowired
	OriginatorRepository originatorRepository;
	@Override
	public Long countAll() {
		// TODO Auto-generated method stub
		return originatorDAO.countAll();
	}

	@Override
	public Long countSearch(MasterForm form) {
		// TODO Auto-generated method stub
		return originatorDAO.countSearch(form);
	}

	@Override
	public List<OriginatorResult> getOriginators(MasterForm form,
			Integer iDisplayStart, Integer iDisplayLength) {
		SimpleDateFormat sdfFull = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		List<Originator> originators= originatorDAO.search(form, iDisplayStart, iDisplayLength);
		List<OriginatorResult> list=new ArrayList<OriginatorResult>();
		for(Originator p:originators){
			OriginatorResult result=new OriginatorResult();
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
		Originator originator =new Originator();
		if(StringUtils.isNotEmpty(form.getId()) && StringUtils.isNotBlank(form.getId())){
			originator = originatorRepository.findOne(Integer.parseInt(form.getId()));
		}
		originator.setName(form.getName());
		
		originatorRepository.save(originator);
		return false;
	}

	@Override
	public MasterForm findOriginator(Integer id) {
		MasterForm form = new MasterForm();
		Originator originator=originatorRepository.findOne(id);
		form.setId(String.valueOf(originator.getId()));
		form.setName(originator.getName());
	
		return form;
	}

	@Override
	public void delete(Integer id) {
		originatorRepository.delete(id);
	}

}
