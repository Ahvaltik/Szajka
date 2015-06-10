/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.edu.agh.szia.pa.model.criminals;

import java.util.Date;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import pl.edu.agh.szia.pa.model.common.Address;
import pl.edu.agh.szia.pa.model.common.Attribute;

/**
 *
 * @author Dariusz Hudziak
 */
@Entity
@Table(name = "TBL_PERSON")
public class Person {
    private int id;
    private String PESEL;
    private Date birthday;
    private String lastName;
    private String firstName;
    private Address address;
    
    private List<Aquaintance> aquaintances;
    private List<Attribute> attributes;

    public Person() {
    }

    public Person(String PESEL, 
                  Date birthday,
                  String lastName, 
                  String firstName, 
                  Address address) {
        this.PESEL = PESEL;
        this.lastName = lastName;
        this.firstName = firstName;
        this.birthday = birthday;
        this.address = address;
    }

    //<editor-fold defaultstate="collapsed" desc="ID">
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="FLD__ID")
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="PESEL">
    @Column(name="FLD_PESEL",
            length = 11,
            nullable = false,
            unique = true)
    public String getPESEL() {
        return PESEL;
    }
    public void setPESEL(String PESEL) {
        this.PESEL = PESEL;
    }
//</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Birthday">
    @Column(name="FLD_BIRTHDAY",
            length = 20,
            nullable = true,
            unique = false)
    public Date getSecondName() {
        return birthday;
    }
    public void setSecondName(Date birthday) {
        this.birthday = birthday;
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="LastName">
    @Column(name="FLD_LAST_NAME",
            length = 40,
            nullable = false,
            unique = false)
    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
//</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="FirstName">
    @Column(name="FLD_FIRST_NAME",
            length = 20,
            nullable = false,
            unique = false)
    public String getFirstName() {
        return firstName;
    }
    
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
   //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Address">
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="FKF_ADDRESS")
    public Address getAddress() {
        return address;
    }
    public void setAddress(Address address) {
        this.address = address;
    }
//</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Aquaintances">
    @OneToMany(mappedBy = "from")
    public List<Aquaintance> getAquaintances() {
        return aquaintances;
    }
    public void setAquaintances(List<Aquaintance> aquaintances) {
        this.aquaintances = aquaintances;
    }
//</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Attributes">
    @ManyToMany()
    @JoinTable(name = "TBL_PERSON_ATTRIBUTES",
               joinColumns = {
                   @JoinColumn(name="FKF_PERSON",nullable = false)
               },
               inverseJoinColumns = {
                   @JoinColumn(name="FKF_ATTRIBUTE",nullable = false)
               })
    public List<Attribute> getAttributes() {
        return attributes;
    }
//</editor-fold>
    public void setAttributes(List<Attribute> attributes) {
        this.attributes = attributes;
    }

    @Override
    public String toString() {
        return getLastName()+" "+getFirstName();
    }
    
    
    
    
    
}
