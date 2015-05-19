package com.huemedia.cms.model.entity;


import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Sla generated by hbm2java
 */
@Entity
@Table(name="sla"
)
public class Sla  implements java.io.Serializable {


     private Integer id;
     private String title;
     private String description;
     private String groupId;
     private String ticketGroup;
     private String ticketCategory;
     private String status;
     private String priority;
     private Integer reminder;
     private Integer time;
     private String reminderUnit;
     private String timeUnit;
     private String createBy;
     private Date createDate;
     private String updateBy;
     private Date updateDate;
     private Set<SlaTask> slaTasks = new HashSet<SlaTask>(0);
     private Set<SlaEscalation> slaEscalations = new HashSet<SlaEscalation>(0);

    public Sla() {
    }

    public Sla(String title, String description, String groupId, String ticketGroup, String ticketCategory, String status, String priority, Integer reminder, Integer time, String reminderUnit, String timeUnit, String createBy, Date createDate, String updateBy, Date updateDate, Set<SlaTask> slaTasks, Set<SlaEscalation> slaEscalations) {
       this.title = title;
       this.description = description;
       this.groupId = groupId;
       this.ticketGroup = ticketGroup;
       this.ticketCategory = ticketCategory;
       this.status = status;
       this.priority = priority;
       this.reminder = reminder;
       this.time = time;
       this.reminderUnit = reminderUnit;
       this.timeUnit = timeUnit;
       this.createBy = createBy;
       this.createDate = createDate;
       this.updateBy = updateBy;
       this.updateDate = updateDate;
       this.slaTasks = slaTasks;
       this.slaEscalations = slaEscalations;
    }
   
     @Id @GeneratedValue(strategy=IDENTITY)

    
    @Column(name="id", unique=true, nullable=false)
    public Integer getId() {
        return this.id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }

    
    @Column(name="title", length=200)
    public String getTitle() {
        return this.title;
    }
    
    public void setTitle(String title) {
        this.title = title;
    }

    
    @Column(name="description", length=500)
    public String getDescription() {
        return this.description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }

    
    @Column(name="group_id", length=200)
    public String getGroupId() {
        return this.groupId;
    }
    
    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    
    @Column(name="ticket_group", length=200)
    public String getTicketGroup() {
        return this.ticketGroup;
    }
    
    public void setTicketGroup(String ticketGroup) {
        this.ticketGroup = ticketGroup;
    }

    
    @Column(name="ticket_category", length=200)
    public String getTicketCategory() {
        return this.ticketCategory;
    }
    
    public void setTicketCategory(String ticketCategory) {
        this.ticketCategory = ticketCategory;
    }

    
    @Column(name="status", length=200)
    public String getStatus() {
        return this.status;
    }
    
    public void setStatus(String status) {
        this.status = status;
    }

    
    @Column(name="priority", length=200)
    public String getPriority() {
        return this.priority;
    }
    
    public void setPriority(String priority) {
        this.priority = priority;
    }

    
    @Column(name="reminder")
    public Integer getReminder() {
        return this.reminder;
    }
    
    public void setReminder(Integer reminder) {
        this.reminder = reminder;
    }

    
    @Column(name="time")
    public Integer getTime() {
        return this.time;
    }
    
    public void setTime(Integer time) {
        this.time = time;
    }

    
    @Column(name="reminder_unit", length=1)
    public String getReminderUnit() {
        return this.reminderUnit;
    }
    
    public void setReminderUnit(String reminderUnit) {
        this.reminderUnit = reminderUnit;
    }

    
    @Column(name="time_unit", length=1)
    public String getTimeUnit() {
        return this.timeUnit;
    }
    
    public void setTimeUnit(String timeUnit) {
        this.timeUnit = timeUnit;
    }

    
    @Column(name="create_by", length=50)
    public String getCreateBy() {
        return this.createBy;
    }
    
    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="create_date", length=19)
    public Date getCreateDate() {
        return this.createDate;
    }
    
    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    
    @Column(name="update_by", length=50)
    public String getUpdateBy() {
        return this.updateBy;
    }
    
    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="update_date", length=19)
    public Date getUpdateDate() {
        return this.updateDate;
    }
    
    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

@OneToMany(fetch=FetchType.LAZY, mappedBy="sla")
    public Set<SlaTask> getSlaTasks() {
        return this.slaTasks;
    }
    
    public void setSlaTasks(Set<SlaTask> slaTasks) {
        this.slaTasks = slaTasks;
    }

@OneToMany(fetch=FetchType.LAZY, mappedBy="sla")
    public Set<SlaEscalation> getSlaEscalations() {
        return this.slaEscalations;
    }
    
    public void setSlaEscalations(Set<SlaEscalation> slaEscalations) {
        this.slaEscalations = slaEscalations;
    }




}


