package com.huemedia.cms.web.form;

import com.huemedia.cms.model.entity.KnowledgeCategory;

public class KnowledgeForm {
	 private Integer id;
     private KnowledgeCategory knowledgeCategory;
     private String title;
     private String description;
     private String solution;
     private Boolean staffOnly;
     private String status;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getSolution() {
		return solution;
	}
	public void setSolution(String solution) {
		this.solution = solution;
	}
	public Boolean getStaffOnly() {
		return staffOnly;
	}
	public void setStaffOnly(Boolean staffOnly) {
		this.staffOnly = staffOnly;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public KnowledgeCategory getKnowledgeCategory() {
		return knowledgeCategory;
	}
	public void setKnowledgeCategory(KnowledgeCategory knowledgeCategory) {
		this.knowledgeCategory = knowledgeCategory;
	}
     
     
}
