package com.huemedia.cms.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.huemedia.cms.model.dao.KnowledgeCategoryDAO;
import com.huemedia.cms.model.dao.KnowledgeDAO;
import com.huemedia.cms.model.entity.Knowledge;
import com.huemedia.cms.model.entity.KnowledgeCategory;
import com.huemedia.cms.model.repository.KnowledgeCategoryRepository;
import com.huemedia.cms.model.repository.KnowledgeRepository;
import com.huemedia.cms.web.controller.knowledge.datatables.KnowledgeCatResult;
import com.huemedia.cms.web.controller.knowledge.datatables.KnowledgeResult;
import com.huemedia.cms.web.form.KnowledgeForm;

@Service
public class KnowledgeServiceImpl implements KnowledgeService{
    
	@Autowired
	KnowledgeRepository knowledgeRepository;
	@Autowired
	KnowledgeDAO knowledgeDAO;
	@Autowired
	KnowledgeCategoryDAO knowledgeCategoryDAO;
	@Autowired
	KnowledgeCategoryRepository knowledgeCategoryRepository;
	
	@Override
	public KnowledgeForm findByID(Integer id) {
		KnowledgeForm knowledgeForm = new KnowledgeForm();
		Knowledge k= knowledgeRepository.findOne(id);
		knowledgeForm.setId(k.getId());
		KnowledgeCategory kc=knowledgeCategoryRepository.findOne(k.getKnowledgeCategory().getId());
		knowledgeForm.setKnowledgeCategory(kc);
		knowledgeForm.setTitle(k.getTitle());
		knowledgeForm.setDescription(k.getDescription());
		knowledgeForm.setSolution(k.getSolution());
		knowledgeForm.setStaffOnly(k.getStaffOnly());
		knowledgeForm.setStatus(k.getStatus());
		return knowledgeForm;
	}

	@Override
	public boolean save(KnowledgeForm form) {
		Knowledge k= new Knowledge();
		if(form.getId()!=null){
			k= knowledgeRepository.findOne(form.getId());
		}
		k.setTitle(form.getTitle());
		k.setKnowledgeCategory(form.getKnowledgeCategory());
		k.setDescription(form.getDescription());
		k.setSolution(form.getSolution());
		k.setStatus(form.getStatus());
		if(form.getStaffOnly()!=null){
			k.setStaffOnly(form.getStaffOnly());
		}
		k.setApproved(false);
		knowledgeRepository.save(k);
		return false;
	}

	@Override
	public Long countAll() {
		// TODO Auto-generated method stub
		return knowledgeDAO.countAll();
	}

	@Override
	public Long countSearch(KnowledgeForm form) {
		// TODO Auto-generated method stub
		return knowledgeDAO.countSearch(form);
	}

	@Override
	public List<KnowledgeResult> getKnowledges(KnowledgeForm form,
			Integer iDisplayStart, Integer iDisplayLength) {
		SimpleDateFormat sdfFull = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		List<Knowledge> knowledges= knowledgeDAO.search(form, iDisplayStart, iDisplayLength);
		List<KnowledgeResult> list=new ArrayList<KnowledgeResult>();
		for(Knowledge p:knowledges){
			KnowledgeResult result=new KnowledgeResult();
			String createBy="";
			String createDate="";
			String updateBy="";
			String updateDate="";
			String staff="";
			String ticketGroup="";
			
			result.setTitle(p.getTitle());
			result.setId(p.getId());
			result.setStatus(p.getStatus());
			result.setApproved(p.getApproved());
			if(p.getKnowledgeCategory()!=null){
				ticketGroup= p.getKnowledgeCategory().getName();
			}
			result.setTicketGroup(ticketGroup);
			
			if(p.getStaffOnly()!=null){
				if(p.getStaffOnly()==true){
					staff="STAFF ONLY";
				}else{
					staff="ALL";
				}
			}
			result.setStaffOnly(staff);
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
	public boolean approve(Integer id) {
		Knowledge k= new Knowledge();
		k= knowledgeRepository.findOne(id);
		k.setApproved(true);
		knowledgeRepository.save(k);
		return false;
	}

	@Override
	public List<KnowledgeResult> getByKeyword(String search,
			Integer iDisplayStart, Integer iDisplayLength) {
		
		SimpleDateFormat sdfFull = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		List<Knowledge> knowledges= knowledgeDAO.searchByKeyword(search, iDisplayStart, iDisplayLength);
		List<KnowledgeResult> list=new ArrayList<KnowledgeResult>();
		for(Knowledge p:knowledges){
			KnowledgeResult result=new KnowledgeResult();
			String createBy="";
			String createDate="";
			String updateBy="";
			String updateDate="";
			String staff="";
			String ticketGroup="";
			
			result.setTitle(p.getTitle());
			result.setId(p.getId());
			result.setStatus(p.getStatus());
			result.setApproved(p.getApproved());
			result.setDescription(p.getDescription());
			if(p.getKnowledgeCategory()!=null){
				ticketGroup= p.getKnowledgeCategory().getName();
			}
			result.setTicketGroup(ticketGroup);
			
			if(p.getStaffOnly()!=null){
				if(p.getStaffOnly()==true){
					staff="STAFF ONLY";
				}else{
					staff="ALL";
				}
			}
			result.setStaffOnly(staff);
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
	public Long countSearchByKeyword(String search) {
		// TODO Auto-generated method stub
		return knowledgeDAO.countSearchByKeyword(search);
	}

	@Override
	@Transactional(readOnly=true)
	public List<KnowledgeCatResult> getKnowledgeCategories(Integer iDisplayStart, Integer iDisplayLength) {
		// TODO Auto-generated method stub
		
		SimpleDateFormat sdfFull = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		List<KnowledgeCategory> knowledges= knowledgeCategoryDAO.search(null, iDisplayStart, iDisplayLength);
		List<KnowledgeCatResult> list=new ArrayList<KnowledgeCatResult>();
         for(KnowledgeCategory k: knowledges){
        	 KnowledgeCatResult r=new KnowledgeCatResult();
        	 r.setId(k.getId());
        	 r.setName(k.getName());
        	 List<String> titles = new ArrayList<String>();
        	 List<Integer> ids = new ArrayList<Integer>();
        	 for(Knowledge n:k.getKnowledges()){
        		 titles.add(n.getTitle()); 
        		 ids.add(n.getId());
        	 }
        	 r.setTitles(titles);
        	 r.setIds(ids);
        	 list.add(r);
         }
		
		
		return list;
	}

	@Override
	public void delete(Integer id) {
		knowledgeRepository.delete(id);
	}

	@Override
	public Long countKnowledgeCategories() {
		return knowledgeCategoryDAO.countAll();
	}

}
