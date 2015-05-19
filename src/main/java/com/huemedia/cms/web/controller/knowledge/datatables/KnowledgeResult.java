package com.huemedia.cms.web.controller.knowledge.datatables;

import com.huemedia.cms.model.entity.TicketGroup;

public class KnowledgeResult {
	private Integer no;
	private Integer id;
    private String ticketGroup;
    private String title;
    private String description;
    private String solution;
    private String staffOnly;
    private String status;
    private String createBy;
    private String createDate;
    private String updateBy;
    private String updateDate;
    private Boolean approved;
	public Integer getNo() {
		return no;
	}
	public void setNo(Integer no) {
		this.no = no;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getTicketGroup() {
		return ticketGroup;
	}
	public void setTicketGroup(String ticketGroup) {
		this.ticketGroup = ticketGroup;
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
	public String getStaffOnly() {
		return staffOnly;
	}
	public void setStaffOnly(String staffOnly) {
		this.staffOnly = staffOnly;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getCreateBy() {
		return createBy;
	}
	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}
	public String getCreateDate() {
		return createDate;
	}
	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}
	public String getUpdateBy() {
		return updateBy;
	}
	public void setUpdateBy(String updateBy) {
		this.updateBy = updateBy;
	}
	public String getUpdateDate() {
		return updateDate;
	}
	public void setUpdateDate(String updateDate) {
		this.updateDate = updateDate;
	}
	public Boolean getApproved() {
		return approved;
	}
	public void setApproved(Boolean approved) {
		this.approved = approved;
	}
    
    
}
