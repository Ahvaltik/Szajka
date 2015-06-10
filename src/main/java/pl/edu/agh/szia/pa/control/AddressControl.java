/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.edu.agh.szia.pa.control;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Control;
import javafx.scene.control.Skin;
import pl.edu.agh.szia.pa.model.common.Address;
import pl.edu.agh.szia.pa.model.common.Town;

/**
 *
 * @author Dariusz Hudziak
 */
public class AddressControl extends Control {
    
    private final ObjectProperty<Town> town = new SimpleObjectProperty<>();
    public ObjectProperty<Town> townProperty(){
        return town;
    }
    public Town getTown(){
        return town.get();
    }
    public void setTown(Town t) {
        town.set(t);
    }
    
    private final StringProperty street = new SimpleStringProperty();
    public StringProperty streetProperty() {
        return street;
    }
    public String getStreet() {
        return street.get();
    }
    public void setStreet(String n) {
        street.set(n);
    }
    
    private final StringProperty house = new SimpleStringProperty();
    public StringProperty houseProperty() {
        return house;
    }
    public String getHouse() {
        return house.get();
    }
    public void setHouse(String n) {
        house.set(n);
    }
 
    private ObjectProperty<Address> address = new SimpleObjectProperty<>();
    public Address getAddress() {
        return new Address(town.getValue(),street.get(),house.get());
    }
    public void setAddress(Address a) {
        address.set(a);
    }
    
    private ObservableList<Town> availableTowns = FXCollections.observableArrayList();
    public ObservableList<Town> getAvailableTowns() {
        return availableTowns;
    }
    
    
    public AddressControl() {
        town.addListener((ov,ot,nt)->{
            Address a = address.get();
            if(a==null) {
                a = new Address(null, null, null);
            }
            a.setTown(nt);
            address.set(a);
        });
        street.addListener((ov,os,ns)->{
            Address a = address.get();
            if(a==null) {
                a = new Address(null, null, null);
            }
            a.setStreet(ns);
            address.set(a);
        });
        house.addListener((ov,oh,nh)->{
             Address a = address.get();
            if(a==null) {
                a = new Address(null, null, null);
            }
            a.setHouse(nh);
            address.set(a);
        });
    }

    @Override
    protected Skin<AddressControl> createDefaultSkin() {
        return new AddressControlSkin(this);
    }
    
}
