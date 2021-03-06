package com.huemedia.cms.model.entity;


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

/**
 * Rating generated by hbm2java
 */
@Entity
@Table(name="rating"
)
public class Rating  implements java.io.Serializable {


     private Integer id;
     private String name;
     private Integer rank;
     private Set<Ticket> tickets = new HashSet<Ticket>(0);

    public Rating() {
    }

	
    public Rating(String name, Integer rank) {
        this.name = name;
        this.rank = rank;
    }
    public Rating(String name, Integer rank, Set<Ticket> tickets) {
       this.name = name;
       this.rank = rank;
       this.tickets = tickets;
    }
   
     @Id @GeneratedValue(strategy=IDENTITY)

    
    @Column(name="id", unique=true, nullable=false)
    public Integer getId() {
        return this.id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }

    
    @Column(name="name", nullable=false, length=50)
    public String getName() {
        return this.name;
    }
    
    public void setName(String name) {
        this.name = name;
    }

    
    @Column(name="rank", nullable=false)
    public Integer getRank() {
        return this.rank;
    }
    
    public void setRank(Integer rank) {
        this.rank = rank;
    }

@OneToMany(fetch=FetchType.LAZY, mappedBy="rating")
    public Set<Ticket> getTickets() {
        return this.tickets;
    }
    
    public void setTickets(Set<Ticket> tickets) {
        this.tickets = tickets;
    }




}


