/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.edu.agh.szia.pa.model.crime;

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
@Table(name="TBL_CRIME_CATEGORY")
public class CrimeCategory {
    private int id;
    private String name;

    public CrimeCategory() {
    }

    public CrimeCategory(int id, String name) {
        this.id = id;
        this.name = name;
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

    @Column(name="FLD_NAME")
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
    
    
    
}
