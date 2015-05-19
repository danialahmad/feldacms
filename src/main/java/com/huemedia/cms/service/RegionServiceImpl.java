package com.huemedia.cms.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.huemedia.cms.model.dao.RegionDAO;
import com.huemedia.cms.model.entity.Region;
import com.huemedia.cms.model.repository.RegionRepository;
import com.huemedia.cms.web.controller.administration.datatables.RegionResult;
import com.huemedia.cms.web.form.MasterForm;

@Service
public class RegionServiceImpl implements RegionService {

	@Autowired
	RegionDAO regionDAO;
	@Autowired
	RegionRepository regionRepository;
	
	@Override
	public Long countAll() {
		// TODO Auto-generated method stub
		return regionDAO.countAll();
	}

	@Override
	public Long countSearch(MasterForm form) {
		// TODO Auto-generated method stub
		return regionDAO.countSearch(form);
	}

	@Override
	public List<RegionResult> getRegions(MasterForm form,
			Integer iDisplayStart, Integer iDisplayLength) {
		SimpleDateFormat sdfFull = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		List<Region> regions= regionDAO.search(form, iDisplayStart, iDisplayLength);
		List<RegionResult> list=new ArrayList<RegionResult>();
		for(Region p:regions){
			RegionResult result=new RegionResult();
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
		Region region =new Region();
		if(StringUtils.isNotEmpty(form.getId()) && StringUtils.isNotBlank(form.getId())){
			region = regionRepository.findOne(Integer.parseInt(form.getId()));
		}
		region.setName(form.getName());
		
		regionRepository.save(region);
		return false;
	}

	@Override
	public MasterForm findRegion(Integer id) {
		MasterForm form = new MasterForm();
		Region region=regionRepository.findOne(id);
		form.setId(String.valueOf(region.getId()));
		form.setName(region.getName());
	
		return form;
	}

	@Override
	public void delete(Integer id) {
		regionRepository.delete(id);
	}

}
