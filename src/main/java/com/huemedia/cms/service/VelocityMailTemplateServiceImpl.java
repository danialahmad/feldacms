package com.huemedia.cms.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.huemedia.cms.model.dao.VelocityMailTemplateDAO;
import com.huemedia.cms.model.entity.RefVelocityMailTemplate;
import com.huemedia.cms.model.repository.VelocityMailTemplateRepository;
import com.huemedia.cms.web.controller.administration.datatables.TemplateResult;
import com.huemedia.cms.web.form.MasterForm;

@Service
public class VelocityMailTemplateServiceImpl implements VelocityMailTemplateService {

	private final VelocityMailTemplateRepository velocityMailTemplateRepository;
	private final VelocityMailTemplateDAO velocityMailTemplateDAO;
	
	@Autowired
	public VelocityMailTemplateServiceImpl(
			final VelocityMailTemplateRepository velocityMailTemplateRepository, final VelocityMailTemplateDAO velocityMailTemplateDAO
			) {
		this.velocityMailTemplateRepository = velocityMailTemplateRepository;
		this.velocityMailTemplateDAO=velocityMailTemplateDAO;
	}

	@Override
	@Transactional(readOnly = true)
	public List<RefVelocityMailTemplate> listAll() {
		return velocityMailTemplateRepository.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public RefVelocityMailTemplate getVelocityMailTemplate(final String id) {
		final RefVelocityMailTemplate mailTemplate = velocityMailTemplateRepository.findOne(id);
		return mailTemplate;
	}

	@Override
	@Transactional
	public void updateMailTemplate(final RefVelocityMailTemplate template) {
		RefVelocityMailTemplate copy = new RefVelocityMailTemplate();
		if(StringUtils.isNotEmpty(template.getId()) && StringUtils.isNotBlank(template.getId())){
			copy = velocityMailTemplateRepository.findOne(template.getId());
			if(copy==null){
				copy =new RefVelocityMailTemplate();
				copy.setId(template.getId());
			}
		}
		copy.setContent(template.getContent());
		copy.setTimestamp(new Date());
		copy.setName(template.getName());
		velocityMailTemplateRepository.save(copy);
	}

	@Override
	@Transactional
	public void saveMailTemplate(final RefVelocityMailTemplate template) {
		template.setTimestamp(new Date());
		velocityMailTemplateRepository.save(template);
	}

	@Override
	@Transactional
	public void deleteMailTemplate(final String[] ids) {
		for (final String id : ids) {
			velocityMailTemplateRepository.delete(id);
		}
	}

	@Override
	public Long countAll() {
		// TODO Auto-generated method stub
		return velocityMailTemplateDAO.countAll();
	}

	@Override
	public Long countSearch(MasterForm form) {
		// TODO Auto-generated method stub
		return velocityMailTemplateDAO.countSearch(form);
	}

	
	@Override
	public List<TemplateResult> getTemplates(MasterForm form,
			Integer iDisplayStart, Integer iDisplayLength) {
		SimpleDateFormat sdfFull = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		List<RefVelocityMailTemplate> templates= velocityMailTemplateDAO.search(form, iDisplayStart, iDisplayLength);
		List<TemplateResult> list=new ArrayList<TemplateResult>();
		for(RefVelocityMailTemplate p:templates){
			TemplateResult result=new TemplateResult();
			String createBy="";
			String createDate="";
			
			result.setName(p.getName());
			result.setId(p.getId());
			if(p.getCreateBy()!=null){
				createBy=p.getCreateBy();
			}
			if(p.getCreateDate()!=null){
				createDate=sdfFull.format(p.getCreateDate());
			}
			
			result.setCreateBy(createBy);
			result.setCreateDate(createDate);
			
			list.add(result);
		}
		
		
		return list;
	}

	@Override
	public void deleteMailTemplate(String id) {
		velocityMailTemplateRepository.delete(id);
	}

}
