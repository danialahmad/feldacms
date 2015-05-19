package com.huemedia.cms.web.controller.complaint.datatables;

public class TicketResult {
	private Integer no;
	private String id;
	private Integer assignmentId;
	private String lo;
	private String title;
	private String createDate;
	private String status;
	private String priority;
	private String group;
	private String assignee;
	private String duration;
	private String rate;
	private String ticketGroup;
	private String lastUpdate;
	private String statusM;
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Integer getNo() {
		return no;
	}

	public void setNo(Integer no) {
		this.no = no;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getCreateDate() {
		return createDate;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}

	public String getPriority() {
		return priority;
	}

	public void setPriority(String priority) {
		this.priority = priority;
	}

	public String getGroup() {
		return group;
	}

	public void setGroup(String group) {
		this.group = group;
	}

	public String getAssignee() {
		return assignee;
	}

	public void setAssignee(String assignee) {
		this.assignee = assignee;
	}

	public String getDuration() {
		return duration;
	}

	public void setDuration(String duration) {
		this.duration = duration;
	}

	public String getRate() {
		return rate;
	}

	public void setRate(String rate) {
		this.rate = rate;
	}

	public Integer getAssignmentId() {
		return assignmentId;
	}

	public void setAssignmentId(Integer assignmentId) {
		this.assignmentId = assignmentId;
	}

	public String getLo() {
		return lo;
	}

	public void setLo(String lo) {
		this.lo = lo;
	}

	public String getTicketGroup() {
		return ticketGroup;
	}

	public void setTicketGroup(String ticketGroup) {
		this.ticketGroup = ticketGroup;
	}

	public String getLastUpdate() {
		return lastUpdate;
	}

	public void setLastUpdate(String lastUpdate) {
		this.lastUpdate = lastUpdate;
	}

	public String getStatusM() {
		return statusM;
	}

	public void setStatusM(String statusM) {
		this.statusM = statusM;
	}

}
