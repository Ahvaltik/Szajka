/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.edu.agh.szia.pa.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;

import java.net.URL;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import org.hibernate.SessionFactory;
import pl.edu.agh.szia.pa.control.AddressControl;
import pl.edu.agh.szia.pa.dao.CommonDAO;
import pl.edu.agh.szia.pa.dao.PersonDAO;
import pl.edu.agh.szia.pa.model.criminals.Person;

/**
 *
 * @author Dariusz Hudziak
 */
public class AddPersonController implements TabbedController {

    @FXML private TextField pesel;
    @FXML private DatePicker birthday;
    @FXML private TextField  firstName;
    @FXML private TextField  lastName;
    @FXML private AddressControl address;
    
    private MainController mc;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       
        pesel.textProperty().addListener((ov,op,np) -> {
            
            int year = 1900+Integer.parseInt(np.substring(0,2));
            int month = Integer.parseInt(np.substring(2,4));
            int day = Integer.parseInt(np.substring(4,6));
            
            birthday.setValue(LocalDate.of(year, month, day));
        });
        
    }

    public void setMainController(MainController mc){
        this.mc = mc;
         CommonDAO cdao = new CommonDAO(mc.getFactory());
         address.getAvailableTowns().addAll(cdao.listTowns());
    }
    
    public void save(ActionEvent e) {
        
       
        SessionFactory factory = mc.getFactory();
        
        CommonDAO cdao = new CommonDAO(factory);
        PersonDAO pdao = new PersonDAO(cdao);
        Person p = new Person(pesel.getText(),
                              Date.from(birthday.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant()),
                              lastName.getText(),
                              firstName.getText(),
                              address.getAddress());
        
        System.out.println("save: "+p);
        
        try{
            pdao.storePerson(p);
        }catch(Exception ex) {
            ex.printStackTrace();
            Alert a = new Alert(Alert.AlertType.ERROR);
            a.setContentText(ex.getMessage());
            a.showAndWait();
        }
    }
    
}
