/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.edu.agh.szia.pa.controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import pl.edu.agh.szia.pa.control.AddressControl;
import pl.edu.agh.szia.pa.model.crime.Crime;

/**
 *
 * @author uriel
 */
public class AddCrimeReportController implements TabbedController
{
    @FXML private ComboBox<Crime> crime;
    @FXML private DatePicker date;
    @FXML private AddressControl address;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
      
    }

    @Override
    public void setMainController(MainController mc) {
        
    }
    
    
    
    public void save(ActionEvent ae) {
        
    }
   
}
