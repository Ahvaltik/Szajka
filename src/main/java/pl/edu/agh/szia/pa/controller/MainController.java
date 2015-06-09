/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.edu.agh.szia.pa.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.SplitPane;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableView;
import javafx.scene.web.WebView;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.classic.Session;
import pl.edu.agh.szia.pa.dao.CommonDAO;
import pl.edu.agh.szia.pa.model.common.Address;
import pl.edu.agh.szia.pa.model.crime.CrimeReport;

/**
 *
 * @author uriel
 */
public class MainController implements Initializable {

    private  SessionFactory factory;
    
    @FXML
    private TabPane tabPane;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        factory = new AnnotationConfiguration().configure().buildSessionFactory();
        
        
        
        tabPane.getTabs().clear();
    }

    public SessionFactory getFactory() {
        return factory;
    }
    
   
    
    public void registerCrime(ActionEvent ae) {
        
        CommonDAO common = new CommonDAO(factory);
        
        
        
       // common.storeAddress(new Address(new Town("Kraków"),"Czarnowiejska","5"));
        
        Address a = common.findAddress("Kraków", "Czarnowiejska", "5");
        
//        CrimeDAO cdao = new CrimeDAO(new AddressDAO(factory));
//     
//        Crime c = new Crime(new Date(System.currentTimeMillis()),
//                            new CrimeCategory(0, "włamanie"),
//                            new Address(new Town("Kraków"),"Czarnowiejska","10"),
//                            "Klasyczne",
//                            2);
//        
//        cdao.storeCrime(c);
//        
//        c = new Crime(new Date(System.currentTimeMillis()),
//                            new CrimeCategory(0, "kradzieź"),
//                            new Address(new Town("Kraków"),"Smoleńsk","1"),
//                            "Klasyczne",
//                            2);
//        
//        cdao.storeCrime(c);
//        
//        Session s = factory.openSession();
//        if(s.getTransaction().isActive()){
//            s.getTransaction().commit();
//        }
//        
//        System.out.println(s.createCriteria(Crime.class).list());
//        
//        s.close();
        
        loadView("AddCrimeView","Rejestracja Przestępswa");
    }
    
    public void reportCrime(ActionEvent ae) {
        loadView("AddCrimeReportView", "Zgłoszenie Przestępswa");
    }
    
    protected void loadView(String vn,String tn) {
        Tab t = new Tab(tn);
        t.setClosable(true);
       
        
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/"+vn+".fxml"));
        try{
            t.setContent(loader.load());
        }catch(Exception e){
            t.setContent(new Label(e.getMessage()));
        }
        
        TabbedController ac = (TabbedController) loader.getController();
        ac.setMainController(this);
        
        tabPane.getTabs().add(t);
    }
    
    public void showCrimes(ActionEvent ae) {
        Tab t = new Tab("Przestępstwa");
        t.setClosable(true);
       
        Session s = factory.openSession();
        final TableView<CrimeReport> tv = new TableView<>(FXCollections.observableArrayList(s.createCriteria(CrimeReport.class).list()));
        s.close();
        
        t.setOnSelectionChanged((e)->{
           if(t.isSelected()){
               Session se = factory.openSession();
               tv.setItems(FXCollections.observableArrayList(se.createCriteria(CrimeReport.class).list()));
               se.close();
           }
        });
       
     
//        TableColumn<CrimeReport,String> tc = new TableColumn<>("Popełniono");
//        tc.setCellValueFactory((TableColumn.CellDataFeatures<CrimeReport, String> p) -> {
//            
//            return new SimpleObjectProperty<String>(new SimpleDateFormat("yyyy-mm-dd").format(p.getValue().getCommited()));
//        });
//        tv.getColumns().add(tc);
//        tc = new TableColumn<>("Kategoria");
//        tc.setCellValueFactory((TableColumn.CellDataFeatures<CrimeReport, String> p)->{
//            return new SimpleObjectProperty<>(p.getValue().getCategory().getName());
//        });
//        tv.getColumns().add(tc);
//        tc = new TableColumn<>("Opis");
//        tc.setCellValueFactory((TableColumn.CellDataFeatures<CrimeReport, String> p)->{
//            return new SimpleObjectProperty<>(p.getValue().getDescription());
//        });
//        tv.getColumns().add(tc);
//        tc = new TableColumn<>("W okolicy");
//        tc.setCellValueFactory((TableColumn.CellDataFeatures<CrimeReport, String> p)->{
//            Address a = p.getValue().getLocation();
//            return new SimpleObjectProperty<>(a.getTown().getName()+", "+a.getStreet()+" "+a.getHouse());
//        });
//        tv.getColumns().add(tc);
//        tc = new TableColumn<>("Uczestników");
//        tc.setCellValueFactory((TableColumn.CellDataFeatures<CrimeReport, String> p)->{
//            return new SimpleObjectProperty<>(p.getValue().getSuspectCount()+"");
//        });
//        tv.getColumns().add(tc);
        
         final WebView webView = new WebView();
        
        String path = getClass().getResource("/html/googleMap.html").toString();
        System.out.println(path);
        webView.getEngine().load(path);
      
        tv.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        tv.getSelectionModel().getSelectedItems().addListener(new ListChangeListener() {

            @Override
            public void onChanged(ListChangeListener.Change change) {
                webView.getEngine().executeScript("document.clearMarkers()");
               for( CrimeReport c : tv.getSelectionModel().getSelectedItems())
               {
                   Address a = c.getLocation();
                   String add = a.getTown().getName()+", "+a.getStreet()+" "+a.getHouse();
                   String name = a.getTown().getName()+" "+a.getStreet()+" "+a.getHouse();
                   webView.getEngine().executeScript("document.markLocation(\""+add+"\",\""+name+"\")");
               }
            }
        });
        
        
        t.setContent(new SplitPane(tv,webView));
        tabPane.getTabs().add(t);
    }
   
    public void close(ActionEvent ae){
        System.exit(0);
    }
    
}
