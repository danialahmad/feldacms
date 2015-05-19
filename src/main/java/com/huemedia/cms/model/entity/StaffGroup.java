package com.huemedia.cms.model.entity;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * StaffGroup generated by hbm2java
 */
@Entity
@Table(name="staff_group"
)
public class StaffGroup  implements java.io.Serializable {


     private Integer id;
     private Group group;
     private Profile profile;
     private Unit unit;

    public StaffGroup() {
    }

    public StaffGroup(Group group, Profile profile, Unit unit) {
       this.group = group;
       this.profile = profile;
       this.unit = unit;
    }
   
     @Id @GeneratedValue(strategy=IDENTITY)

    
    @Column(name="id", unique=true, nullable=false)
    public Integer getId() {
        return this.id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="group_id")
    public Group getGroup() {
        return this.group;
    }
    
    public void setGroup(Group group) {
        this.group = group;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="profile_id")
    public Profile getProfile() {
        return this.profile;
    }
    
    public void setProfile(Profile profile) {
        this.profile = profile;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="unit_id")
    public Unit getUnit() {
        return this.unit;
    }
    
    public void setUnit(Unit unit) {
        this.unit = unit;
    }




}


