/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.edu.agh.szia.pa.model.crime;

import java.util.Date;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import pl.edu.agh.szia.pa.model.common.Address;
import pl.edu.agh.szia.pa.model.criminals.Person;

/**
 *
 * @author Dariusz Hudziak
 */
@Entity
@Table(name="TBL_CRIME")
public class CrimeReport {
    private int id;
    private Date commited;
    private Crime crime;
    private Address location;
    private String description;
   

    public CrimeReport() {
    }
    
    public CrimeReport(Date commited, 
                       Crime crime, 
                       Address location, 
                       String description) {
        //this.id = id;
        this.commited = commited;
        this.crime = crime;
        this.location = location;
        this.description = description;
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
    @JoinColumn(name="FKF_CRIME",
                nullable=false)
    public Crime getCrime() {
        return crime;
    }
    public void setCrime(Crime crime) {
        this.crime = crime;
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
    
    public List<Person> determinCulprits(){
        
        
        
        return null;
    }
    
}
