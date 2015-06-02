/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.edu.agh.szia.pa.model.address;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author Dariusz Hudziak
 */
@Entity
@Table(name = "TBL_ADDRESS")
public class Address {
    
    private int id;
    private Town town;
    private String street;
    private String house;
    private Double latitude;
    private Double longtitude;
    
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

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "FKF_TOWN",
                nullable = false)
    public Town getTown() {
        return town;
    }
    public void setTown(Town town) {
        this.town = town;
    }

    @Column(name = "FLD_LATITUDE",
            nullable = true,
            length = 3,
            precision = 4)
    public Double getLatitude() {
        return latitude;
    }
    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    @Column(name = "FLD_LONGTITUDE",
            nullable = true,
            length = 3,
            precision = 4)
    public Double getLongtitude() {
        return longtitude;
    }

    public void setLongtitude(Double longtitude) {
        this.longtitude = longtitude;
    }
    
    
    
    
    
    
    
}
