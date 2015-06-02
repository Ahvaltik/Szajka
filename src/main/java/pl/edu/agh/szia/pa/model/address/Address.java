/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.edu.agh.szia.pa.model.address;

import javax.annotation.Generated;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author Dariusz Hudziak
 */
@Entity
@Table(name = "TBL_ADDRESS")
public class Address {
    
    private int id;
    private String street;
    private String house;
    
    @Id
    @Column(name = "FLD__ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    public int getID(){
        return id;
    }
    public void setID(int id){
        this.id = id;
    }

    @Column(name = "FLD_STREET")
    public String getStreet() {
        return street;
    }
    public void setStreet(String street) {
        this.street = street;
    }
    @Column(name = "FLD_HOUSE")
    public String getHouse() {
        return house;
    }
    public void setHouse(String house) {
        this.house = house;
    }
    
    
    
    
}
