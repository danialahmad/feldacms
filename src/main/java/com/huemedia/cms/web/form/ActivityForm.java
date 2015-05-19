package com.huemedia.cms.web.form;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.huemedia.cms.model.entity.ActivityType;
import com.huemedia.cms.model.entity.Ticket;
import com.huemedia.cms.model.entity.TicketAssignment;

public class ActivityForm implements Serializable{
	 private Integer id;
     private Ticket ticket;
     private TicketAssignment assignment;
     private Integer activityTypeId;
     private Date date;
     private Date startTime;
     private Date endTime;
     private String description;
     private Byte sendEmail;
     private String subject;
     private String message;
     private String location;
     private String agenda;
     private String[] to;
     private ActivityType activityType;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Ticket getTicket() {
		return ticket;
	}
	public void setTicket(Ticket ticket) {
		this.ticket = ticket;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public Date getStartTime() {
		return startTime;
	}
	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}
	public Date getEndTime() {
		return endTime;
	}
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Byte getSendEmail() {
		return sendEmail;
	}
	public void setSendEmail(Byte sendEmail) {
		this.sendEmail = sendEmail;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
     
	public Integer getActivityTypeId() {
		return activityTypeId;
	}
	public void setActivityTypeId(Integer activityTypeId) {
		this.activityTypeId = activityTypeId;
	}
	public static void main(String[] args){
		Date d=new Date();
		final SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		System.out.println(dateFormat.format(d));
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getAgenda() {
		return agenda;
	}
	public void setAgenda(String agenda) {
		this.agenda = agenda;
	}
	public String[] getTo() {
		return to;
	}
	public void setTo(String[] to) {
		this.to = to;
	}
	public TicketAssignment getAssignment() {
		return assignment;
	}
	public void setAssignment(TicketAssignment assignment) {
		this.assignment = assignment;
	}
	public ActivityType getActivityType() {
		return activityType;
	}
	public void setActivityType(ActivityType activityType) {
		this.activityType = activityType;
	}
     
}
