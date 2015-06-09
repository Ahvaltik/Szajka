/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.edu.agh.szia.pa.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;
import pl.edu.agh.szia.pa.model.common.Town;

/**
 *
 * @author Dariusz Hudziak
 */
public class AddPersonController implements Initializable {

    @FXML
    private TextField firstName;
    @FXML
    private TextField lastName;
    @FXML
    private TextArea  description;
    @FXML
    private ComboBox<Town> town;
    @FXML
    private TextField street;
    @FXML
    private TextField house;

    private MainController mc;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       
    }

    public void setMainController(MainController mc){
//        this.mc = mc;
//
//        town.setConverter(new StringConverter<Town>() {
//
//            @Override
//            public String toString(Town t) {
//                if(t==null) return "";
//            return t.getName();
//            }
//
//            @Override
//            public Town fromString(String string) {
//                if(string==null) return null;
//                return new Town(string);
//            }
//        });
//        
//        Session s  = mc.getFactory().openSession();
//        town.setItems(FXCollections.observableArrayList(s.createCriteria(Town.class).list()));
//        s.close();
        
    }
    
    public void save(ActionEvent e) {
//        SimpleDateFormat f = new SimpleDateFormat("yyyy-mm-dd");
//
//        Person p = new Person(
//           firstName.getText(),
//           lastName.getText(),
//           new Address(
//                   town.getValue(),
//                   street.getText(),
//                   house.getText()
//           ),
//           description.getText()
//        );
//        
//        new PersonDAO(new AddressDAO(mc.getFactory())).storePerson(p);

        setMainController(mc);
    }
    
}
