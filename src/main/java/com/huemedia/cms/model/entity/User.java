package com.huemedia.cms.model.entity;


import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * User generated by hbm2java
 */
@Entity
@Table(name="user"
)
public class User  implements java.io.Serializable {


     private String username;
     private Profile profile;
     private String password;
     private String createBy;
     private Date createDate;
     private String updateBy;
     private Date updateDate;
     private String lastIp;
     private Date lastLogin;
     private String activationId;
     private String status;
     private Set<UserRole> userRoles = new HashSet<UserRole>(0);

    public User() {
    }

	
    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }
    public User(String username, Profile profile, String password, String createBy, Date createDate, String updateBy, Date updateDate, String lastIp, Date lastLogin, String activationId, String status, Set<UserRole> userRoles) {
       this.username = username;
       this.profile = profile;
       this.password = password;
       this.createBy = createBy;
       this.createDate = createDate;
       this.updateBy = updateBy;
       this.updateDate = updateDate;
       this.lastIp = lastIp;
       this.lastLogin = lastLogin;
       this.activationId = activationId;
       this.status = status;
       this.userRoles = userRoles;
    }
   
     @Id 

    
    @Column(name="username", unique=true, nullable=false, length=50)
    public String getUsername() {
        return this.username;
    }
    
    public void setUsername(String username) {
        this.username = username;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="profile_id")
    public Profile getProfile() {
        return this.profile;
    }
    
    public void setProfile(Profile profile) {
        this.profile = profile;
    }

    
    @Column(name="password", nullable=false, length=200)
    public String getPassword() {
        return this.password;
    }
    
    public void setPassword(String password) {
        this.password = password;
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

    
    @Column(name="last_ip", length=20)
    public String getLastIp() {
        return this.lastIp;
    }
    
    public void setLastIp(String lastIp) {
        this.lastIp = lastIp;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="last_login", length=19)
    public Date getLastLogin() {
        return this.lastLogin;
    }
    
    public void setLastLogin(Date lastLogin) {
        this.lastLogin = lastLogin;
    }

    
    @Column(name="activation_id", length=50)
    public String getActivationId() {
        return this.activationId;
    }
    
    public void setActivationId(String activationId) {
        this.activationId = activationId;
    }

    
    @Column(name="status", length=2)
    public String getStatus() {
        return this.status;
    }
    
    public void setStatus(String status) {
        this.status = status;
    }

@OneToMany(fetch=FetchType.LAZY, mappedBy="user")
    public Set<UserRole> getUserRoles() {
        return this.userRoles;
    }
    
    public void setUserRoles(Set<UserRole> userRoles) {
        this.userRoles = userRoles;
    }




}


