package com.huemedia.cms.service;

import java.util.List;

import com.huemedia.cms.model.entity.RefVelocityMailTemplate;
import com.huemedia.cms.web.controller.administration.datatables.TemplateResult;
import com.huemedia.cms.web.form.MasterForm;

public interface VelocityMailTemplateService {

	List<RefVelocityMailTemplate> listAll();

	RefVelocityMailTemplate getVelocityMailTemplate(String id);

	void updateMailTemplate(RefVelocityMailTemplate template);

	void saveMailTemplate(RefVelocityMailTemplate template);

	void deleteMailTemplate(String[] ids);
	void deleteMailTemplate(String id);
	
	Long countAll();
	Long countSearch(MasterForm form);
	List<TemplateResult> getTemplates(MasterForm form, Integer iDisplayStart, Integer iDisplayLength);
	
}
