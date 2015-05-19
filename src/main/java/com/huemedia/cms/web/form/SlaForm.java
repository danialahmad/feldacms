package com.huemedia.cms.web.form;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class SlaForm implements Serializable {

	private Integer id;
	private String title;
	private String description;
	private String statusId;
	private Integer ticketGroupId;
	private Integer ticketCategoryId;
	private String priorityId;
	private Integer time;
	private String timeUnit;
	private Integer reminder;
	private String reminderUnit;
	private List<Integer> checkedItems = new ArrayList<Integer>();
	private List<Integer> ticketGroupItems = new ArrayList<Integer>();
	private List<Integer> ticketCategoryItems = new ArrayList<Integer>();
	private List<String> statusItems = new ArrayList<String>();
	private List<String> priorityItems = new ArrayList<String>();
	private Boolean sendToOwner;
	private Boolean sendToAssignee;
	private Boolean sendToSupervisor;

	public Boolean getSendToOwner() {
		return sendToOwner;
	}

	public void setSendToOwner(Boolean sendToOwner) {
		this.sendToOwner = sendToOwner;
	}

	public Boolean getSendToAssignee() {
		return sendToAssignee;
	}

	public void setSendToAssignee(Boolean sendToAssignee) {
		this.sendToAssignee = sendToAssignee;
	}

	public Boolean getSendToSupervisor() {
		return sendToSupervisor;
	}

	public void setSendToSupervisor(Boolean sendToSupervisor) {
		this.sendToSupervisor = sendToSupervisor;
	}

	public String getStatusId() {
		return statusId;
	}

	public void setStatusId(String statusId) {
		this.statusId = statusId;
	}

	public Integer getTicketGroupId() {
		return ticketGroupId;
	}

	public void setTicketGroupId(Integer ticketGroupId) {
		this.ticketGroupId = ticketGroupId;
	}

	public Integer getTicketCategoryId() {
		return ticketCategoryId;
	}

	public void setTicketCategoryId(Integer ticketCategoryId) {
		this.ticketCategoryId = ticketCategoryId;
	}

	public String getPriorityId() {
		return priorityId;
	}

	public void setPriorityId(String priorityId) {
		this.priorityId = priorityId;
	}

	public List<Integer> getCheckedItems() {
		return checkedItems;
	}

	public void setCheckedItems(List<Integer> checkedItems) {
		this.checkedItems = checkedItems;
	}

	public Integer getTime() {
		return time;
	}

	public void setTime(Integer time) {
		this.time = time;
	}

	public String getTimeUnit() {
		return timeUnit;
	}

	public void setTimeUnit(String timeUnit) {
		this.timeUnit = timeUnit;
	}

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

	public Integer getReminder() {
		return reminder;
	}

	public void setReminder(Integer reminder) {
		this.reminder = reminder;
	}

	public String getReminderUnit() {
		return reminderUnit;
	}

	public void setReminderUnit(String reminderUnit) {
		this.reminderUnit = reminderUnit;
	}

	public List<Integer> getTicketGroupItems() {
		return ticketGroupItems;
	}

	public void setTicketGroupItems(List<Integer> ticketGroupItems) {
		this.ticketGroupItems = ticketGroupItems;
	}

	public List<String> getStatusItems() {
		return statusItems;
	}

	public void setStatusItems(List<String> statusItems) {
		this.statusItems = statusItems;
	}

	public List<String> getPriorityItems() {
		return priorityItems;
	}

	public void setPriorityItems(List<String> priorityItems) {
		this.priorityItems = priorityItems;
	}

	public List<Integer> getTicketCategoryItems() {
		return ticketCategoryItems;
	}

	public void setTicketCategoryItems(List<Integer> ticketCategoryItems) {
		this.ticketCategoryItems = ticketCategoryItems;
	}

}
