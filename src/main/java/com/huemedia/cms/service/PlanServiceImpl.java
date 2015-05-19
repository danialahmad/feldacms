package com.huemedia.cms.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.huemedia.cms.model.dao.PlanDAO;
import com.huemedia.cms.model.entity.Plan;
import com.huemedia.cms.model.repository.CountryRepository;
import com.huemedia.cms.model.repository.PlanRepository;
import com.huemedia.cms.web.controller.administration.datatables.PlanResult;
import com.huemedia.cms.web.form.MasterForm;

@Service
public class PlanServiceImpl implements PlanService {
	@Autowired
	PlanDAO planDAO;
	@Autowired
	PlanRepository planRepository;
	@Autowired
	CountryRepository countryRepository;
	
	@Override
	public Long countAll() {
		// TODO Auto-generated method stub
		return planDAO.countAll();
	}

	@Override
	public Long countSearch(MasterForm form) {
		// TODO Auto-generated method stub
		return planDAO.countSearch(form);
	}

	@Override
	public List<PlanResult> getPlans(MasterForm form,
			Integer iDisplayStart, Integer iDisplayLength) {
		SimpleDateFormat sdfFull = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		List<Plan> plans= planDAO.search(form, iDisplayStart, iDisplayLength);
		List<PlanResult> list=new ArrayList<PlanResult>();
		for(Plan p:plans){
			PlanResult result=new PlanResult();
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
			result.setRegion(p.getRegion().getName());
			result.setState(p.getState().getName());
			String add=p.getAddress1()+", "+p.getAddress2()+", "+p.getCity()+". "+p.getState().getName();
			result.setAddress(add);
			list.add(result);
		}
		
		
		return list;
	}

	@Override
	public boolean save(MasterForm form) {
		Plan plan =new Plan();
		if(StringUtils.isNotEmpty(form.getId()) && StringUtils.isNotBlank(form.getId())){
			plan = planRepository.findOne(Integer.parseInt(form.getId()));
		}
		plan.setName(form.getName());
		plan.setState(form.getState());
		plan.setRegion(form.getRegion());
		plan.setAddress1(form.getAddress1());
		plan.setAddress2(form.getAddress2());
		plan.setCity(form.getCity());
		plan.setCountry(countryRepository.findOne(458));
		
		planRepository.save(plan);
		return false;
	}

	@Override
	public MasterForm findPlan(Integer id) {
		MasterForm form = new MasterForm();
		Plan plan=planRepository.findOne(id);
		form.setId(String.valueOf(plan.getId()));
		form.setName(plan.getName());
		form.setState(plan.getState());
		form.setRegion(plan.getRegion());
		form.setAddress1(plan.getAddress1());
		form.setAddress2(plan.getAddress2());
		form.setCity(plan.getCity());
	
		return form;
	}

	@Override
	public void delete(Integer id) {
		planRepository.delete(id);
	}

}
