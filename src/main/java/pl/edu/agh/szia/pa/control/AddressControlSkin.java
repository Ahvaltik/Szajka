/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.edu.agh.szia.pa.control;

import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Skin;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.util.StringConverter;
import pl.edu.agh.szia.pa.model.common.Town;

/**
 *
 * @author Dariusz Hudziak
 */
public class AddressControlSkin extends GridPane implements Skin<AddressControl> {
    
    private final AddressControl control;
    

    public AddressControlSkin(AddressControl ac) {
        this.control = ac;
        
        Label l = new Label("Town: ");
        l.setPadding(new Insets(0, 5, 0, 5));
        add(l, 0,0);
        
        ComboBox<Town> c = new ComboBox<>();
        c.setEditable(true);
        c.setConverter(new StringConverter<Town>() {
            @Override
            public String toString(Town t) {
                if(t==null) return "";
                return t.getName();
            }

            @Override
            public Town fromString(String string) {
                if(string==null) return null;
                return new Town(string);
            }
        });
        ac.townProperty().bind(c.getSelectionModel().selectedItemProperty());
        c.setPrefWidth(150);
        c.setMaxWidth(Double.MAX_VALUE);
        add(c, 1,0);
        
        l = new Label("Ulica: ");
        l.setPadding(new Insets(0, 5, 0, 5));
        add(l,2,0);
        TextField t= new TextField();
        ac.streetProperty().bindBidirectional(t.textProperty());
        add(t,3,0);
        
        l = new Label("Numer: ");
        l.setPadding(new Insets(0, 5, 0, 5));
        add(l,4,0);
        TextField f = new TextField();
        f.setPrefWidth(50);
        ac.houseProperty().bindBidirectional(f.textProperty());
        add(f,5,0);
    }
   
    @Override
    public AddressControl getSkinnable() {
        return control;
    }

    @Override
    public Node getNode() {
        return this;
    }

    @Override
    public void dispose() {
        
    }
    
} 
