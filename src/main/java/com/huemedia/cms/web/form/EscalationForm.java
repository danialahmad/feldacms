package com.huemedia.cms.web.form;

import java.io.Serializable;
import java.util.List;

import com.huemedia.cms.model.entity.Priority;
import com.huemedia.cms.model.entity.SlaEscalation;
import com.huemedia.cms.model.entity.Status;
import com.huemedia.cms.model.entity.TicketCategory;
import com.huemedia.cms.model.entity.TicketGroup;

public class EscalationForm implements Serializable{
  private Integer id;
  private String title;
  private String description;
  private Integer time;
  private String timeUnit;
  private Integer reminder;
  private String reminderUnit;
  private TicketGroup ticketGroup;
  private TicketCategory ticketCategory;
  private Status status;
  private Priority priority;
  private List<SlaEscalation> listSe;
  
  
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
public TicketGroup getTicketGroup() {
	return ticketGroup;
}
public void setTicketGroup(TicketGroup ticketGroup) {
	this.ticketGroup = ticketGroup;
}
public TicketCategory getTicketCategory() {
	return ticketCategory;
}
public void setTicketCategory(TicketCategory ticketCategory) {
	this.ticketCategory = ticketCategory;
}
public Status getStatus() {
	return status;
}
public void setStatus(Status status) {
	this.status = status;
}
public Priority getPriority() {
	return priority;
}
public void setPriority(Priority priority) {
	this.priority = priority;
}
public List<SlaEscalation> getListSe() {
	return listSe;
}
public void setListSe(List<SlaEscalation> listSe) {
	this.listSe = listSe;
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

}
