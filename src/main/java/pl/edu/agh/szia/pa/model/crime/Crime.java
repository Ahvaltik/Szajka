/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.edu.agh.szia.pa.model.crime;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import pl.edu.agh.szia.pa.model.common.Address;

/**
 *
 * @author Dariusz Hudziak
 */
@Entity
@Table(name="TBL_CRIME")
public class Crime {
    private int id;
    private Date commited;
    private CrimeCategory category;
    private Address location;
    private String description;
    private int suspectCount;

    public Crime() {
    }
    
    public Crime(Date commited, CrimeCategory category, Address location, String description, int suspectCount) {
        //this.id = id;
        this.commited = commited;
        this.category = category;
        this.location = location;
        this.description = description;
        this.suspectCount = suspectCount;
    }

    @Id
    @Column(name = "FLD__ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    @Column(name="FLD_COMMITED")
    public Date getCommited() {
        return commited;
    }
    public void setCommited(Date commited) {
        this.commited = commited;
    }

    @ManyToOne
    @JoinColumn(name="FKF_CATEGORY",
                nullable=false)
    public CrimeCategory getCategory() {
        return category;
    }
    public void setCategory(CrimeCategory category) {
        this.category = category;
    }

    @ManyToOne
    @JoinColumn(name="FKF_LOCATION",
                nullable = false)
    public Address getLocation() {
        return location;
    }
    public void setLocation(Address location) {
        this.location = location;
    }

    @Column(name="FLD_DESCRIPTION",
            nullable=true,
            length=200)
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }

    @Column(name="FLD_SUSPECT_COUNT",
            nullable = false,
            length = 10,
            precision = 0)
    public int getSuspectCount() {
        return suspectCount;
    }
    public void setSuspectCount(int suspectCount) {
        this.suspectCount = suspectCount;
    }
    
    
    
}
