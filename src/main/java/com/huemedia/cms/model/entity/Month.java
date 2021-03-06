package com.huemedia.cms.model.entity;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Month generated by hbm2java
 */
@Entity
@Table(name="month"
)
public class Month  implements java.io.Serializable {


     private Integer id;
     private String nameEn;
     private String nameMs;

    public Month() {
    }

    public Month(String nameEn, String nameMs) {
       this.nameEn = nameEn;
       this.nameMs = nameMs;
    }
   
     @Id @GeneratedValue(strategy=IDENTITY)

    
    @Column(name="id", unique=true, nullable=false)
    public Integer getId() {
        return this.id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }

    
    @Column(name="name_en", length=50)
    public String getNameEn() {
        return this.nameEn;
    }
    
    public void setNameEn(String nameEn) {
        this.nameEn = nameEn;
    }

    
    @Column(name="name_ms", length=50)
    public String getNameMs() {
        return this.nameMs;
    }
    
    public void setNameMs(String nameMs) {
        this.nameMs = nameMs;
    }




}


