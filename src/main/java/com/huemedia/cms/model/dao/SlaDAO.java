package com.huemedia.cms.model.dao;

import java.util.List;

import com.huemedia.cms.model.entity.Sla;
import com.huemedia.cms.web.form.SlaForm;

public interface SlaDAO {
   Long countAll();
   Long countSearch(SlaForm slaForm);
   List<Sla> search(SlaForm slaForm,Integer iDisplayStart,
			Integer iDisplayLength);
}
