/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.edu.agh.szia.pa.controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.util.StringConverter;
import org.hibernate.classic.Session;
import pl.edu.agh.szia.pa.model.common.Attribute;
import pl.edu.agh.szia.pa.model.common.Town;
import pl.edu.agh.szia.pa.model.crime.CrimeCategory;

/**
 *
 * @author Dariusz Hudziak
 */
public class AddCrimeController implements TabbedController {

    @FXML private ComboBox<CrimeCategory> category;
    @FXML private TextField name;
    @FXML private ListView<Attribute> attributeListView;
    @FXML private ComboBox<Attribute> attribute;
    
    private MainController mc;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       
    }

    public void setMainController(MainController mc){
        this.mc = mc;
        
    }
    
    public void addAttribute(ActionEvent e) {
        
    }
    
    public void save(ActionEvent e) {
//        SimpleDateFormat f = new SimpleDateFormat("yyyy-mm-dd");
//        try{
//        CrimeReport c = new CrimeReport(
//           f.parse(commitedDate.getText()),
//           category.getValue(),
//           new Address(
//                   town.getValue(),
//                   street.getText(),
//                   house.getText()
//           ),
//           description.getText(),
//           Integer.parseInt(involved.getText())     
//        );
//        
//         new CrimeDAO(new AddressDAO(mc.getFactory())).storeCrime(c); 
//        
//        }catch(ParseException ex){
//            
//        }
//        setMainController(mc);
    }
    
    
}
