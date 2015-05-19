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
 * Region generated by hbm2java
 */
@Entity
@Table(name="region"
)
public class Region  implements java.io.Serializable {


     private Integer id;
     private String name;
     private String createBy;
     private Date createDate;
     private String updateBy;
     private Date updateDate;
     private Set<Plan> plans = new HashSet<Plan>(0);
     private Set<Profile> profiles = new HashSet<Profile>(0);

    public Region() {
    }

	
    public Region(String name) {
        this.name = name;
    }
    public Region(String name, String createBy, Date createDate, String updateBy, Date updateDate, Set<Plan> plans, Set<Profile> profiles) {
       this.name = name;
       this.createBy = createBy;
       this.createDate = createDate;
       this.updateBy = updateBy;
       this.updateDate = updateDate;
       this.plans = plans;
       this.profiles = profiles;
    }
   
     @Id @GeneratedValue(strategy=IDENTITY)

    
    @Column(name="id", unique=true, nullable=false)
    public Integer getId() {
        return this.id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }

    
    @Column(name="name", nullable=false, length=200)
    public String getName() {
        return this.name;
    }
    
    public void setName(String name) {
        this.name = name;
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

@OneToMany(fetch=FetchType.LAZY, mappedBy="region")
    public Set<Plan> getPlans() {
        return this.plans;
    }
    
    public void setPlans(Set<Plan> plans) {
        this.plans = plans;
    }

@OneToMany(fetch=FetchType.LAZY, mappedBy="region")
    public Set<Profile> getProfiles() {
        return this.profiles;
    }
    
    public void setProfiles(Set<Profile> profiles) {
        this.profiles = profiles;
    }




}


