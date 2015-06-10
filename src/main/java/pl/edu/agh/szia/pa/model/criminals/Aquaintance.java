/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.edu.agh.szia.pa.model.criminals;

import javax.persistence.Column;
import javax.persistence.Entity;
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
@Table(name="TBL_AQUAINTANCE")
public class Aquaintance {
    private int id;
    private Person from;
    private Person to;
    private double level;

    public Aquaintance() {
    }
    public Aquaintance(Person from, Person to) {
        this.from = from;
        this.to = to;
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

    //<editor-fold defaultstate="collapsed" desc="From">
    @ManyToOne()
    @JoinColumn(name = "FKF_FROM",nullable = true)
    public Person getFrom() {
        return from;
    }
    public void setFrom(Person from) {
        this.from = from;
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="To">
    @ManyToOne()
    @JoinColumn(name = "FKF_TO",nullable = true)
    public Person getTo() {
        return to;
    }
    
    public void setTo(Person to) {
        this.to = to;
    }
   //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Level">
    @Column(name="FLD_LEVEL")
    public double getLevel() {
        return level;
    }
    
    public void setLevel(double level) {
        this.level = level;
    }
   //</editor-fold>
} 
