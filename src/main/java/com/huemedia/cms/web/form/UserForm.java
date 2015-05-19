package com.huemedia.cms.web.form;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.huemedia.cms.model.entity.Country;
import com.huemedia.cms.model.entity.PersonCategory;
import com.huemedia.cms.model.entity.Plan;
import com.huemedia.cms.model.entity.Region;
import com.huemedia.cms.model.entity.Relation;
import com.huemedia.cms.model.entity.Role;
import com.huemedia.cms.model.entity.StaffGroup;
import com.huemedia.cms.model.entity.State;
import com.huemedia.cms.model.entity.UserRole;

public class UserForm implements Serializable{
	
	 /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer profileId;
	private Relation relation;
	 private String name;
     private String email;
     private String icNo;
     private String company;
     private String address1;
     private String address2;
     private String city;
     private String phoneNo;
     private String mobileNo;
     private String faxNo;
     private Country country;
     private State state;
     private MultipartFile file; 
     private String username;
     private String password;
     private String currPassword;
     private String groups;
     private String[] groupIds;
     private Date createDate;
     private PersonCategory personCategory;
     private Plan plan;
     private String settlerNo;
     private String photo;
     private Date lastLogin;
     private Region region;
     private Region regionStaff;
     private String staffNo;
     private boolean status;
     
     private String lastIp;
     
     private Role role;
     
     private List<StaffGroup> staffGroups;
     private List<UserRole> userRoles;
	public Integer getProfileId() {
		return profileId;
	}
	public void setProfileId(Integer profileId) {
		this.profileId = profileId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getIcNo() {
		return icNo;
	}
	public void setIcNo(String icNo) {
		this.icNo = icNo;
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
	public String getPhoneNo() {
		return phoneNo;
	}
	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}
	public String getMobileNo() {
		return mobileNo;
	}
	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public List<StaffGroup> getStaffGroups() {
		return staffGroups;
	}
	public void setStaffGroups(List<StaffGroup> staffGroups) {
		this.staffGroups = staffGroups;
	}
	public List<UserRole> getUserRoles() {
		return userRoles;
	}
	public void setUserRoles(List<UserRole> userRoles) {
		this.userRoles = userRoles;
	}
	public Country getCountry() {
		return country;
	}
	public void setCountry(Country country) {
		this.country = country;
	}
	public State getState() {
		return state;
	}
	public void setState(State state) {
		this.state = state;
	}
	public Role getRole() {
		return role;
	}
	public void setRole(Role role) {
		this.role = role;
	}
	public String getCompany() {
		return company;
	}
	public void setCompany(String company) {
		this.company = company;
	}
	public String getFaxNo() {
		return faxNo;
	}
	public void setFaxNo(String faxNo) {
		this.faxNo = faxNo;
	}
	public MultipartFile getFile() {
		return file;
	}
	public void setFile(MultipartFile file) {
		this.file = file;
	}
	public String getPhoto() {
		return photo;
	}
	public void setPhoto(String photo) {
		this.photo = photo;
	}
	public Date getLastLogin() {
		return lastLogin;
	}
	public void setLastLogin(Date lastLogin) {
		this.lastLogin = lastLogin;
	}
	public boolean isStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}
	public String getLastIp() {
		return lastIp;
	}
	public void setLastIp(String lastIp) {
		this.lastIp = lastIp;
	}
	public String[] getGroupIds() {
		return groupIds;
	}
	public void setGroupIds(String[] groupIds) {
		this.groupIds = groupIds;
	}
	public String getGroups() {
		return groups;
	}
	public void setGroups(String groups) {
		this.groups = groups;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public String getCurrPassword() {
		return currPassword;
	}
	public void setCurrPassword(String currPassword) {
		this.currPassword = currPassword;
	}
	public PersonCategory getPersonCategory() {
		return personCategory;
	}
	public void setPersonCategory(PersonCategory personCategory) {
		this.personCategory = personCategory;
	}
	public Plan getPlan() {
		return plan;
	}
	public void setPlan(Plan plan) {
		this.plan = plan;
	}
	public String getSettlerNo() {
		return settlerNo;
	}
	public void setSettlerNo(String settlerNo) {
		this.settlerNo = settlerNo;
	}
	public Region getRegion() {
		return region;
	}
	public void setRegion(Region region) {
		this.region = region;
	}
	public String getStaffNo() {
		return staffNo;
	}
	public void setStaffNo(String staffNo) {
		this.staffNo = staffNo;
	}
	public Region getRegionStaff() {
		return regionStaff;
	}
	public void setRegionStaff(Region regionStaff) {
		this.regionStaff = regionStaff;
	}
	public Relation getRelation() {
		return relation;
	}
	public void setRelation(Relation relation) {
		this.relation = relation;
	}
	
	
	
}
