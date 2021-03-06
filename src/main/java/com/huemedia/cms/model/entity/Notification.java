package com.huemedia.cms.model.entity;


import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Notification generated by hbm2java
 */
@Entity
@Table(name="notification"
)
public class Notification  implements java.io.Serializable {


     private String id;
     private RefVelocityMailTemplate refVelocityMailTemplateByAllSupervisorTemplate;
     private RefVelocityMailTemplate refVelocityMailTemplateByManagerTemplate;
     private RefVelocityMailTemplate refVelocityMailTemplateBySupervisorTemplate;
     private RefVelocityMailTemplate refVelocityMailTemplateByHelpdeskTemplate;
     private RefVelocityMailTemplate refVelocityMailTemplateByComplainantTemplate;
     private RefVelocityMailTemplate refVelocityMailTemplateByAssigneeTemplate;
     private Integer rank;
     private Boolean sendToComplainant;
     private String complainantSubject;
     private Boolean sendToSupervisor;
     private String supervisorSubject;
     private Boolean sendAllSupervisor;
     private String allSupervisorSubject;
     private Boolean sendToAssignee;
     private String assigneeSubject;
     private Boolean sendToHelpdesk;
     private String helpdeskSubject;
     private Boolean sendToManager;
     private String managerSubject;
     private String createBy;
     private Date createDate;
     private String updateBy;
     private Date updateDate;

    public Notification() {
    }

	
    public Notification(String id) {
        this.id = id;
    }
    public Notification(String id, RefVelocityMailTemplate refVelocityMailTemplateByAllSupervisorTemplate, RefVelocityMailTemplate refVelocityMailTemplateByManagerTemplate, RefVelocityMailTemplate refVelocityMailTemplateBySupervisorTemplate, RefVelocityMailTemplate refVelocityMailTemplateByHelpdeskTemplate, RefVelocityMailTemplate refVelocityMailTemplateByComplainantTemplate, RefVelocityMailTemplate refVelocityMailTemplateByAssigneeTemplate, Integer rank, Boolean sendToComplainant, String complainantSubject, Boolean sendToSupervisor, String supervisorSubject, Boolean sendAllSupervisor, String allSupervisorSubject, Boolean sendToAssignee, String assigneeSubject, Boolean sendToHelpdesk, String helpdeskSubject, Boolean sendToManager, String managerSubject, String createBy, Date createDate, String updateBy, Date updateDate) {
       this.id = id;
       this.refVelocityMailTemplateByAllSupervisorTemplate = refVelocityMailTemplateByAllSupervisorTemplate;
       this.refVelocityMailTemplateByManagerTemplate = refVelocityMailTemplateByManagerTemplate;
       this.refVelocityMailTemplateBySupervisorTemplate = refVelocityMailTemplateBySupervisorTemplate;
       this.refVelocityMailTemplateByHelpdeskTemplate = refVelocityMailTemplateByHelpdeskTemplate;
       this.refVelocityMailTemplateByComplainantTemplate = refVelocityMailTemplateByComplainantTemplate;
       this.refVelocityMailTemplateByAssigneeTemplate = refVelocityMailTemplateByAssigneeTemplate;
       this.rank = rank;
       this.sendToComplainant = sendToComplainant;
       this.complainantSubject = complainantSubject;
       this.sendToSupervisor = sendToSupervisor;
       this.supervisorSubject = supervisorSubject;
       this.sendAllSupervisor = sendAllSupervisor;
       this.allSupervisorSubject = allSupervisorSubject;
       this.sendToAssignee = sendToAssignee;
       this.assigneeSubject = assigneeSubject;
       this.sendToHelpdesk = sendToHelpdesk;
       this.helpdeskSubject = helpdeskSubject;
       this.sendToManager = sendToManager;
       this.managerSubject = managerSubject;
       this.createBy = createBy;
       this.createDate = createDate;
       this.updateBy = updateBy;
       this.updateDate = updateDate;
    }
   
     @Id 

    
    @Column(name="id", unique=true, nullable=false, length=50)
    public String getId() {
        return this.id;
    }
    
    public void setId(String id) {
        this.id = id;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="all_supervisor_template")
    public RefVelocityMailTemplate getRefVelocityMailTemplateByAllSupervisorTemplate() {
        return this.refVelocityMailTemplateByAllSupervisorTemplate;
    }
    
    public void setRefVelocityMailTemplateByAllSupervisorTemplate(RefVelocityMailTemplate refVelocityMailTemplateByAllSupervisorTemplate) {
        this.refVelocityMailTemplateByAllSupervisorTemplate = refVelocityMailTemplateByAllSupervisorTemplate;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="manager_template")
    public RefVelocityMailTemplate getRefVelocityMailTemplateByManagerTemplate() {
        return this.refVelocityMailTemplateByManagerTemplate;
    }
    
    public void setRefVelocityMailTemplateByManagerTemplate(RefVelocityMailTemplate refVelocityMailTemplateByManagerTemplate) {
        this.refVelocityMailTemplateByManagerTemplate = refVelocityMailTemplateByManagerTemplate;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="supervisor_template")
    public RefVelocityMailTemplate getRefVelocityMailTemplateBySupervisorTemplate() {
        return this.refVelocityMailTemplateBySupervisorTemplate;
    }
    
    public void setRefVelocityMailTemplateBySupervisorTemplate(RefVelocityMailTemplate refVelocityMailTemplateBySupervisorTemplate) {
        this.refVelocityMailTemplateBySupervisorTemplate = refVelocityMailTemplateBySupervisorTemplate;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="helpdesk_template")
    public RefVelocityMailTemplate getRefVelocityMailTemplateByHelpdeskTemplate() {
        return this.refVelocityMailTemplateByHelpdeskTemplate;
    }
    
    public void setRefVelocityMailTemplateByHelpdeskTemplate(RefVelocityMailTemplate refVelocityMailTemplateByHelpdeskTemplate) {
        this.refVelocityMailTemplateByHelpdeskTemplate = refVelocityMailTemplateByHelpdeskTemplate;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="complainant_template")
    public RefVelocityMailTemplate getRefVelocityMailTemplateByComplainantTemplate() {
        return this.refVelocityMailTemplateByComplainantTemplate;
    }
    
    public void setRefVelocityMailTemplateByComplainantTemplate(RefVelocityMailTemplate refVelocityMailTemplateByComplainantTemplate) {
        this.refVelocityMailTemplateByComplainantTemplate = refVelocityMailTemplateByComplainantTemplate;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="assignee_template")
    public RefVelocityMailTemplate getRefVelocityMailTemplateByAssigneeTemplate() {
        return this.refVelocityMailTemplateByAssigneeTemplate;
    }
    
    public void setRefVelocityMailTemplateByAssigneeTemplate(RefVelocityMailTemplate refVelocityMailTemplateByAssigneeTemplate) {
        this.refVelocityMailTemplateByAssigneeTemplate = refVelocityMailTemplateByAssigneeTemplate;
    }

    
    @Column(name="rank")
    public Integer getRank() {
        return this.rank;
    }
    
    public void setRank(Integer rank) {
        this.rank = rank;
    }

    
    @Column(name="send_to_complainant")
    public Boolean getSendToComplainant() {
        return this.sendToComplainant;
    }
    
    public void setSendToComplainant(Boolean sendToComplainant) {
        this.sendToComplainant = sendToComplainant;
    }

    
    @Column(name="complainant_subject", length=100)
    public String getComplainantSubject() {
        return this.complainantSubject;
    }
    
    public void setComplainantSubject(String complainantSubject) {
        this.complainantSubject = complainantSubject;
    }

    
    @Column(name="send_to_supervisor")
    public Boolean getSendToSupervisor() {
        return this.sendToSupervisor;
    }
    
    public void setSendToSupervisor(Boolean sendToSupervisor) {
        this.sendToSupervisor = sendToSupervisor;
    }

    
    @Column(name="supervisor_subject", length=100)
    public String getSupervisorSubject() {
        return this.supervisorSubject;
    }
    
    public void setSupervisorSubject(String supervisorSubject) {
        this.supervisorSubject = supervisorSubject;
    }

    
    @Column(name="send_all_supervisor")
    public Boolean getSendAllSupervisor() {
        return this.sendAllSupervisor;
    }
    
    public void setSendAllSupervisor(Boolean sendAllSupervisor) {
        this.sendAllSupervisor = sendAllSupervisor;
    }

    
    @Column(name="all_supervisor_subject", length=100)
    public String getAllSupervisorSubject() {
        return this.allSupervisorSubject;
    }
    
    public void setAllSupervisorSubject(String allSupervisorSubject) {
        this.allSupervisorSubject = allSupervisorSubject;
    }

    
    @Column(name="send_to_assignee")
    public Boolean getSendToAssignee() {
        return this.sendToAssignee;
    }
    
    public void setSendToAssignee(Boolean sendToAssignee) {
        this.sendToAssignee = sendToAssignee;
    }

    
    @Column(name="assignee_subject", length=100)
    public String getAssigneeSubject() {
        return this.assigneeSubject;
    }
    
    public void setAssigneeSubject(String assigneeSubject) {
        this.assigneeSubject = assigneeSubject;
    }

    
    @Column(name="send_to_helpdesk")
    public Boolean getSendToHelpdesk() {
        return this.sendToHelpdesk;
    }
    
    public void setSendToHelpdesk(Boolean sendToHelpdesk) {
        this.sendToHelpdesk = sendToHelpdesk;
    }

    
    @Column(name="helpdesk_subject", length=100)
    public String getHelpdeskSubject() {
        return this.helpdeskSubject;
    }
    
    public void setHelpdeskSubject(String helpdeskSubject) {
        this.helpdeskSubject = helpdeskSubject;
    }

    
    @Column(name="send_to_manager")
    public Boolean getSendToManager() {
        return this.sendToManager;
    }
    
    public void setSendToManager(Boolean sendToManager) {
        this.sendToManager = sendToManager;
    }

    
    @Column(name="manager_subject", length=100)
    public String getManagerSubject() {
        return this.managerSubject;
    }
    
    public void setManagerSubject(String managerSubject) {
        this.managerSubject = managerSubject;
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




}


