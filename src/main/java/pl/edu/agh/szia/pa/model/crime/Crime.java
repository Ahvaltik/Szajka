/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.edu.agh.szia.pa.model.crime;

import java.util.LinkedList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import pl.edu.agh.szia.pa.model.common.Attribute;

/**
 *
 * @author Dariusz Hudziak
 */
@Entity
@Table(name="TBL_CRIME",uniqueConstraints = {
    @UniqueConstraint(columnNames = {"FLD_NAME","FKF_CATEGORY"})
})
public class Crime {
    private int id;
    private CrimeCategory category;
    private String name;
    private List<Attribute> requierments;

    //<editor-fold defaultstate="collapsed" desc="Contructors">
    public Crime() {
        requierments = new LinkedList<>();
    }
    public Crime(CrimeCategory category, String name) {
        this.category = category;
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

    //<editor-fold defaultstate="collapsed" desc="Category">
    @ManyToOne
    @JoinColumn(name = "FKF_CATEGORY",
                nullable = false)
    public CrimeCategory getCategory() {
        return category;
    }
    public void setCategory(CrimeCategory category) {
        this.category = category;
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Name">
    @Column(name="FLD_NAME",
            length = 150,
            nullable = false)
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Requierments">
    @ManyToMany
    @JoinTable(name = "TBL_CRIME_ATTRIBUTE",
               joinColumns = {
                   @JoinColumn(name="FKF_CRIME")
               },
               inverseJoinColumns = {
                   @JoinColumn(name="FKF_ATTRIBUTE")
               }) 
    public List<Attribute> getRequierments() {
        return requierments;
    }
    //</editor-fold>
    public void setRequierments(List<Attribute> requierments) {
        this.requierments = requierments;
    }
    
    public String toString(){
        return category.getName()+", "+name;
    }
    
    
    
}
