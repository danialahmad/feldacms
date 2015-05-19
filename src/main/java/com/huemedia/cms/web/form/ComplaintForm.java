package com.huemedia.cms.web.form;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.huemedia.cms.model.entity.Category;
import com.huemedia.cms.model.entity.Country;
import com.huemedia.cms.model.entity.Group;
import com.huemedia.cms.model.entity.Originator;
import com.huemedia.cms.model.entity.PersonCategory;
import com.huemedia.cms.model.entity.Plan;
import com.huemedia.cms.model.entity.Priority;
import com.huemedia.cms.model.entity.Profile;
import com.huemedia.cms.model.entity.Rating;
import com.huemedia.cms.model.entity.Region;
import com.huemedia.cms.model.entity.Relation;
import com.huemedia.cms.model.entity.State;
import com.huemedia.cms.model.entity.Status;
import com.huemedia.cms.model.entity.TicketCategory;
import com.huemedia.cms.model.entity.TicketFile;
import com.huemedia.cms.model.entity.TicketGroup;

public class ComplaintForm implements Serializable{
	 private String id;
	 private Integer AssigmentId;
	 private Profile profile;
     private Integer originatorId;
     private String statusId;
     private Integer ticketCategoryId;
     private TicketCategory ticketCategory;
     private Integer ticketGroupId;
     private TicketGroup ticketGroup;
     private boolean readOnly;

	private Group department;
     private String action;
     private String priorityId;
     private Priority priority;
     private Status status;
     private String remarks;
     private Category category;
     
     private String interval;
     
     private String ticketTitle;
     private String description;
     private Date resolutionDate;
     private Date createDate;
     private Date assignDate;
     private Date lastUpdate;
     private List<TicketFile> ticketFiles;
     private List<MultipartFile> files;
     private String group;
     private Integer assigneeId;
     private Integer supervisorId;
     private Profile supervisor;
     private Profile assignee;
     
     private Integer ratingId;
     private Rating rating;
     private String comment;
     
     
     private Country country;
     private State state;
     private Integer profileId;
     private String name;
     private String email;
     private String icNo;
     private String address1;
     private String address2;
     private String city;
     private String phoneNo;
     private String mobileNo;
     private PersonCategory personCategory;
     private Relation relation;
     private Plan plan;
     private String settlerNo;
     private Region region;
     private String staffNo;
     private Originator originator;
     private String ticketType;
     private Integer statusSub1;
     private Integer statusSub2;
     
     private boolean statusReadOnly=true;
     private boolean priorityReadOnly=true;
     private boolean categoryReadOnly=true;
     private boolean originatorReadOnly=true;
     
     private String message;
     
	public String getGroup() {
		return group;
	}
	public void setGroup(String group) {
		this.group = group;
	}
	public Integer getAssigneeId() {
		return assigneeId;
	}
	public void setAssigneeId(Integer assigneeId) {
		this.assigneeId = assigneeId;
	}
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
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Date getResolutionDate() {
		return resolutionDate;
	}
	public void setResolutionDate(Date resolutionDate) {
		this.resolutionDate = resolutionDate;
	}
	public Integer getOriginatorId() {
		return originatorId;
	}
	public void setOriginatorId(Integer originatorId) {
		this.originatorId = originatorId;
	}
	public String getStatusId() {
		return statusId;
	}
	public void setStatusId(String statusId) {
		this.statusId = statusId;
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
	public List<MultipartFile> getFiles() {
		return files;
	}
	public void setFiles(List<MultipartFile> files) {
		this.files = files;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public List<TicketFile> getTicketFiles() {
		return ticketFiles;
	}
	public void setTicketFiles(List<TicketFile> ticketFiles) {
		this.ticketFiles = ticketFiles;
	}
	public Profile getProfile() {
		return profile;
	}
	public void setProfile(Profile profile) {
		this.profile = profile;
	}
	public Rating getRating() {
		return rating;
	}
	public void setRating(Rating rating) {
		this.rating = rating;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public Integer getRatingId() {
		return ratingId;
	}
	public void setRatingId(Integer ratingId) {
		this.ratingId = ratingId;
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
	public Integer getSupervisorId() {
		return supervisorId;
	}
	public void setSupervisorId(Integer supervisorId) {
		this.supervisorId = supervisorId;
	}
	public TicketCategory getTicketCategory() {
		return ticketCategory;
	}
	public void setTicketCategory(TicketCategory ticketCategory) {
		this.ticketCategory = ticketCategory;
	}
	public TicketGroup getTicketGroup() {
		return ticketGroup;
	}
	public void setTicketGroup(TicketGroup ticketGroup) {
		this.ticketGroup = ticketGroup;
	}
	public Priority getPriority() {
		return priority;
	}
	public void setPriority(Priority priority) {
		this.priority = priority;
	}
	public Date getAssignDate() {
		return assignDate;
	}
	public void setAssignDate(Date assignDate) {
		this.assignDate = assignDate;
	}
	public Profile getSupervisor() {
		return supervisor;
	}
	public void setSupervisor(Profile supervisor) {
		this.supervisor = supervisor;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public Region getRegion() {
		return region;
	}
	public void setRegion(Region region) {
		this.region = region;
	}
	public Status getStatus() {
		return status;
	}
	public void setStatus(Status status) {
		this.status = status;
	}
	public Date getLastUpdate() {
		return lastUpdate;
	}
	public void setLastUpdate(Date lastUpdate) {
		this.lastUpdate = lastUpdate;
	}
	public String getInterval() {
		return interval;
	}
	public void setInterval(String interval) {
		this.interval = interval;
	}
	public Originator getOriginator() {
		return originator;
	}
	public void setOriginator(Originator originator) {
		this.originator = originator;
	}
	public Group getDepartment() {
		return department;
	}
	public void setDepartment(Group department) {
		this.department = department;
	}
	public Profile getAssignee() {
		return assignee;
	}
	public void setAssignee(Profile assignee) {
		this.assignee = assignee;
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
	public String getStaffNo() {
		return staffNo;
	}
	public void setStaffNo(String staffNo) {
		this.staffNo = staffNo;
	}
    public boolean isReadOnly() {
		return readOnly;
	}
	public void setReadOnly(boolean readOnly) {
		this.readOnly = readOnly;
	}
	public boolean isStatusReadOnly() {
		return statusReadOnly;
	}
	public void setStatusReadOnly(boolean statusReadOnly) {
		this.statusReadOnly = statusReadOnly;
	}
	public boolean isPriorityReadOnly() {
		return priorityReadOnly;
	}
	public void setPriorityReadOnly(boolean priorityReadOnly) {
		this.priorityReadOnly = priorityReadOnly;
	}
	public boolean isCategoryReadOnly() {
		return categoryReadOnly;
	}
	public void setCategoryReadOnly(boolean categoryReadOnly) {
		this.categoryReadOnly = categoryReadOnly;
	}
	public boolean isOriginatorReadOnly() {
		return originatorReadOnly;
	}
	public void setOriginatorReadOnly(boolean originatorReadOnly) {
		this.originatorReadOnly = originatorReadOnly;
	}
	public Integer getAssigmentId() {
		return AssigmentId;
	}
	public void setAssigmentId(Integer assigmentId) {
		AssigmentId = assigmentId;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Relation getRelation() {
		return relation;
	}
	public void setRelation(Relation relation) {
		this.relation = relation;
	}
	public String getAction() {
		return action;
	}
	public void setAction(String action) {
		this.action = action;
	}
	public String getTicketType() {
		return ticketType;
	}
	public void setTicketType(String ticketType) {
		this.ticketType = ticketType;
	}
	public Integer getStatusSub1() {
		return statusSub1;
	}
	public void setStatusSub1(Integer statusSub1) {
		this.statusSub1 = statusSub1;
	}
	public Integer getStatusSub2() {
		return statusSub2;
	}
	public void setStatusSub2(Integer statusSub2) {
		this.statusSub2 = statusSub2;
	}
	public Category getCategory() {
		return category;
	}
	public void setCategory(Category category) {
		this.category = category;
	}
}
