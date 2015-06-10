/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.edu.agh.szia.pa.controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.util.StringConverter;
import pl.edu.agh.szia.pa.dao.CommonDAO;
import pl.edu.agh.szia.pa.dao.PersonDAO;
import pl.edu.agh.szia.pa.model.criminals.Aquaintance;
import pl.edu.agh.szia.pa.model.criminals.Person;

/**
 *
 * @author uriel
 */
public class PersonAuqaintanceController implements TabbedController {

    @FXML private ComboBox<Person> fromPerson;
    @FXML private ComboBox<Person> toPerson;
    @FXML private TextField level;
    @FXML private TableView<Aquaintance> aquaintanceTable;
   
    private MainController mc;
    private PersonDAO  personDAO;
    private ObservableList<Person> personList = FXCollections.observableArrayList();
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       
    }

    @Override
    public void setMainController(MainController mc) {
        this.mc = mc;
        personDAO = new PersonDAO(new CommonDAO(mc.getFactory()));
        
        personList.addAll(personDAO.listPersons());
        fromPerson.setItems(personList);
        toPerson.setItems(personList);
        
        aquaintanceTable.getColumns().clear();
        
        fromPerson.getSelectionModel().selectedItemProperty().addListener((ov,op,np)->{
            aquaintanceTable.getItems().clear();
            aquaintanceTable.getItems().addAll(personDAO.listAquaintance(np));
        });
        
        
        TableColumn<Aquaintance,String> tc = new TableColumn<>("Nazwisko");
        tc.setCellValueFactory((p)->{
            return new SimpleStringProperty(p.getValue().getTo().getLastName());
        });
        aquaintanceTable.getColumns().add(tc);
        
        tc = new TableColumn<>("Imie");
        tc.setCellValueFactory((p)->{
            return new SimpleStringProperty(p.getValue().getTo().getFirstName());
        });
        aquaintanceTable.getColumns().add(tc);
        
        tc = new TableColumn<>("Zażyłość");
        tc.setCellValueFactory((p)->{
            return new SimpleStringProperty(p.getValue().getLevel()+"");
        });
        aquaintanceTable.getColumns().add(tc);
        
    }
    
    public void addAquaintance(ActionEvent ae) {
        Aquaintance a =
        personDAO.saveAquaintance(fromPerson.getValue(), 
                                  toPerson.getValue(), 
                                  Double.parseDouble(level.getText()));
        
        aquaintanceTable.getItems().add(a);
    }
            
    
}
