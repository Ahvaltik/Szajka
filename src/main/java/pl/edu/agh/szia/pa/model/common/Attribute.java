/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.edu.agh.szia.pa.model.common;

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
@Table(name = "TBL_ATTRIBUTE")
public class Attribute {
    
    private int id;
    private String name;
    
    //<editor-fold defaultstate="collapsed" desc="Constructor">
    public Attribute() {
    }
    public Attribute(String name) {
        this.name = name;
    }
    //</editor-fold>

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

    //<editor-fold defaultstate="collapsed" desc="Name">
    @Column(name="FLD_NAME",
            length = 50,
            nullable = false,
            unique = true)
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
   //</editor-fold>
    
    @Override
    public String toString() {
        return name;
    }
}
