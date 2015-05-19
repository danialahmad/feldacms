package com.huemedia.cms.service;

import java.util.List;

import com.huemedia.cms.model.entity.SlaTask;
import com.huemedia.cms.model.entity.Status;
import com.huemedia.cms.model.entity.Ticket;
import com.huemedia.cms.web.controller.administration.datatables.SlaResult;
import com.huemedia.cms.web.form.EscalationForm;
import com.huemedia.cms.web.form.SlaForm;

public interface SLAService {
   
	boolean saveNewSetting(SlaForm slaForm);
	SlaForm findSlaSetting(Integer id);
	boolean updateSetting(SlaForm slaForm);
	boolean deleteSetting(Integer id);
	
	Long countAll();
	Long countSearch(SlaForm slaForm);
	List<SlaResult> getSlas(SlaForm slaForm, Integer iDisplayStart, Integer iDisplayLength);
	
	EscalationForm findSlaById(Integer id);
	boolean saveEscalation(EscalationForm escForm);
	
	SlaTask createSlaTask(Ticket ticket);
	SlaTask updateSlaTask(Ticket ticket,String status);
}
