package com.huemedia.cms.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.huemedia.cms.model.dao.PersonCategoryDAO;
import com.huemedia.cms.model.entity.PersonCategory;
import com.huemedia.cms.model.repository.PersonCategoryRepository;
import com.huemedia.cms.web.controller.administration.datatables.PersonCategoryResult;
import com.huemedia.cms.web.form.MasterForm;

@Service
public class PersonCategoryServiceImpl implements PersonCategoryService {

	@Autowired
	PersonCategoryDAO personCategoryDAO;
	@Autowired
	PersonCategoryRepository personCategoryRepository;
	
	@Override
	public Long countAll() {
		// TODO Auto-generated method stub
		return personCategoryDAO.countAll();
	}

	@Override
	public Long countSearch(MasterForm form) {
		// TODO Auto-generated method stub
		return personCategoryDAO.countSearch(form);
	}

	@Override
	public List<PersonCategoryResult> getPersonCategories(MasterForm form,
			Integer iDisplayStart, Integer iDisplayLength) {
		SimpleDateFormat sdfFull = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		List<PersonCategory> personCategories= personCategoryDAO.search(form, iDisplayStart, iDisplayLength);
		List<PersonCategoryResult> list=new ArrayList<PersonCategoryResult>();
		for(PersonCategory p:personCategories){
			PersonCategoryResult result=new PersonCategoryResult();
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
		PersonCategory personCategory =new PersonCategory();
		if(StringUtils.isNotEmpty(form.getId()) && StringUtils.isNotBlank(form.getId())){
			personCategory = personCategoryRepository.findOne(Integer.parseInt(form.getId()));
		}
		personCategory.setName(form.getName());
		
		personCategoryRepository.save(personCategory);
		return false;
	}

	@Override
	public MasterForm findPersonCategory(Integer id) {
		MasterForm form = new MasterForm();
		PersonCategory personCategory=personCategoryRepository.findOne(id);
		form.setId(String.valueOf(personCategory.getId()));
		form.setName(personCategory.getName());
	
		return form;
	}

	@Override
	public void delete(Integer id) {
		personCategoryRepository.delete(id);
	}

}
