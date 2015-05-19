package com.huemedia.cms.web.form;

import java.io.Serializable;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

public class SearchTicketForm implements Serializable{
   private String id;
   private Integer assignmentId;
   private String ticketTitle;
   private Integer ticketCategoryId;
   private Integer ticketGroupId;
   private Integer ratingId;
   private String priorityId;
   private Integer assigneeId;
   private Integer supervisorId;
   private String icNo;
   private String phoneNo;
   private String status;
   private String[] statusList;
   private String[] group;
   private Integer day;
   private Integer week;
   private Integer month;
   private Integer year;
   private Boolean active;
  
   private Date dateFrom;
   
   private Date dateTo;
   
public String getId() {
	return id;
}
public void setId(String id) {
	this.id = id;
}
public String getTicketTitle() {
	return ticketTitle;
}
public void setTicketTitle(String ticketTitle) {
	this.ticketTitle = ticketTitle;
}
public Integer getTicketCategoryId() {
	return ticketCategoryId;
}
public void setTicketCategoryId(Integer ticketCategoryId) {
	this.ticketCategoryId = ticketCategoryId;
}
public Integer getTicketGroupId() {
	return ticketGroupId;
}
public void setTicketGroupId(Integer ticketGroupId) {
	this.ticketGroupId = ticketGroupId;
}
public String getPriorityId() {
	return priorityId;
}
public void setPriorityId(String priorityId) {
	this.priorityId = priorityId;
}
public String getStatus() {
	return status;
}
public void setStatus(String status) {
	this.status = status;
}
public Integer getDay() {
	return day;
}
public void setDay(Integer day) {
	this.day = day;
}
public Integer getWeek() {
	return week;
}
public void setWeek(Integer week) {
	this.week = week;
}
public Integer getMonth() {
	return month;
}
public void setMonth(Integer month) {
	this.month = month;
}
public Integer getYear() {
	return year;
}
public void setYear(Integer year) {
	this.year = year;
}
public Integer getAssigneeId() {
	return assigneeId;
}
public void setAssigneeId(Integer assigneeId) {
	this.assigneeId = assigneeId;
}
public Integer getSupervisorId() {
	return supervisorId;
}
public void setSupervisorId(Integer supervisorId) {
	this.supervisorId = supervisorId;
}
public Boolean getActive() {
	return active;
}
public void setActive(Boolean active) {
	this.active = active;
}
public Date getDateFrom() {
	return dateFrom;
}
public void setDateFrom(Date dateFrom) {
	this.dateFrom = dateFrom;
}
public Date getDateTo() {
	return dateTo;
}
public void setDateTo(Date dateTo) {
	this.dateTo = dateTo;
}
public Integer getAssignmentId() {
	return assignmentId;
}
public void setAssignmentId(Integer assignmentId) {
	this.assignmentId = assignmentId;
}
public String[] getStatusList() {
	return statusList;
}
public void setStatusList(String[] statusList) {
	this.statusList = statusList;
}
public String[] getGroup() {
	return group;
}
public void setGroup(String[] group) {
	this.group = group;
}
public Integer getRatingId() {
	return ratingId;
}
public void setRatingId(Integer ratingId) {
	this.ratingId = ratingId;
}
public String getIcNo() {
	return icNo;
}
public void setIcNo(String icNo) {
	this.icNo = icNo;
}
public String getPhoneNo() {
	return phoneNo;
}
public void setPhoneNo(String phoneNo) {
	this.phoneNo = phoneNo;
}

   
   
}
