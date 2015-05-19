package com.huemedia.cms.web.form;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.huemedia.cms.model.entity.Country;
import com.huemedia.cms.model.entity.Notification;
import com.huemedia.cms.model.entity.Region;
import com.huemedia.cms.model.entity.State;
import com.huemedia.cms.model.entity.TicketGroup;

public class MasterForm implements Serializable{
	String  id;
	String name;
	TicketGroup ticketGroup;
	Country country;
	Region region;
	State state;
	String address1;
	String address2;
	String city;
	String content;
	Integer rank;
	List<Notification> listNoti;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public TicketGroup getTicketGroup() {
		return ticketGroup;
	}
	public void setTicketGroup(TicketGroup ticketGroup) {
		this.ticketGroup = ticketGroup;
	}
	public Country getCountry() {
		return country;
	}
	public void setCountry(Country country) {
		this.country = country;
	}
	public Integer getRank() {
		return rank;
	}
	public void setRank(Integer rank) {
		this.rank = rank;
	}
	public Region getRegion() {
		return region;
	}
	public void setRegion(Region region) {
		this.region = region;
	}
	public State getState() {
		return state;
	}
	public void setState(State state) {
		this.state = state;
	}
	public String getAddress1() {
		return address1;
	}
	public void setAddress1(String address1) {
		this.address1 = address1;
	}
	public String getAddress2() {
		return address2;
	}
	public void setAddress2(String address2) {
		this.address2 = address2;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public List<Notification> getListNoti() {
		return listNoti;
	}
	public void setListNoti(List<Notification> listNoti) {
		this.listNoti = listNoti;
	}
	
	
}
