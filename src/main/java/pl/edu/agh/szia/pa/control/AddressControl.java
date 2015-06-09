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
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
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
 
    private ObjectProperty<Address> address;

    public AddressControl() {
        
    }

    @Override
    protected Skin<AddressControl> createDefaultSkin() {
        return new AddressControlSkin(this);
    }
    
}
