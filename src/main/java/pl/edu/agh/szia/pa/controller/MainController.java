/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.edu.agh.szia.pa.controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Orientation;
import javafx.scene.control.Label;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.SplitPane;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.web.WebView;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.classic.Session;
import pl.edu.agh.szia.pa.dao.CommonDAO;
import pl.edu.agh.szia.pa.dao.PersonDAO;
import pl.edu.agh.szia.pa.model.common.Address;
import pl.edu.agh.szia.pa.model.crime.CrimeReport;
import pl.edu.agh.szia.pa.model.criminals.Aquaintance;
import pl.edu.agh.szia.pa.model.criminals.Person;

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
            e.printStackTrace();
            t.setContent(new Label(e.getMessage()));
        }
        
        TabbedController ac = (TabbedController) loader.getController();
        ac.setMainController(this);

        tabPane.getTabs().add(t);
    }

    public void addPerson(ActionEvent ae) {

        loadView("AddPersonView", "Dodawanie osoby");
    }

    public void addAquaintance(ActionEvent ae) {
        loadView("PersonAquaintanceView", "Znajomości");
    }
    
    public void showPeople(){
        
        Tab t = new Tab("Osoby");
        
        PersonDAO dao = new PersonDAO(new CommonDAO(factory));
        
        TableView<Person> tv = new TableView<Person>(FXCollections.observableArrayList(dao.listPersons()));
        
        TableColumn<Person,String> tc = new TableColumn<>("PESEL");
        tc.setCellValueFactory((p)->{
            return new SimpleStringProperty(p.getValue().getPESEL());
        });
        tv.getColumns().add(tc);
        
        tc = new TableColumn<>("Nazwisko");
        tc.setCellValueFactory((p)->{
            return new SimpleStringProperty(p.getValue().getLastName());
        });
        tv.getColumns().add(tc);
        
        tc = new TableColumn<>("Imie");
        tc.setCellValueFactory((p)->{
            return new SimpleStringProperty(p.getValue().getFirstName());
        });
        tv.getColumns().add(tc);
        
        
        tc = new TableColumn<>("Data Urodzenia");
        tc.setCellValueFactory((p)->{
            return new SimpleStringProperty(p.getValue().getSecondName().toString());
        });
        tv.getColumns().add(tc);
        
        tc = new TableColumn<>("Adres");
        tc.setCellValueFactory((p)->{
            return new SimpleStringProperty(p.getValue().getAddress().toString());
        });
        tv.getColumns().add(tc);
        
        t.setContent(tv);
        
        
        
        TableView<Aquaintance> t1 = new TableView<>();
        
       
        
        TableColumn<Aquaintance,String> tc1 = new TableColumn<>("Nazwisko");
        tc1.setCellValueFactory((p)->{
            return new SimpleStringProperty(p.getValue().getTo().getLastName());
        });
        t1.getColumns().add(tc1);
        
        tc1 = new TableColumn<>("Imie");
        tc1.setCellValueFactory((p)->{
            return new SimpleStringProperty(p.getValue().getTo().getFirstName());
        });
        t1.getColumns().add(tc1);
        
        tc1 = new TableColumn<>("Level");
        tc1.setCellValueFactory((p)->{
            return new SimpleStringProperty(""+p.getValue().getLevel());
        });
        t1.getColumns().add(tc1);
        
        tc1 = new TableColumn<>("Nazwisko");
        tc1.setCellValueFactory((p)->{
            return new SimpleStringProperty(p.getValue().getFrom().getLastName());
        });
        t1.getColumns().add(tc1);
        
        SplitPane sp = new SplitPane(tv,t1);
        sp.setOrientation(Orientation.VERTICAL);
        
        final WebView webView = new WebView();
        
        String path = getClass().getResource("/html/googleMap.html").toString();
        System.out.println(path);
        webView.getEngine().load(path);
        
         tv.getSelectionModel().selectedItemProperty().addListener((ov,op,np)->{
            t1.getItems().clear();
            t1.getItems().addAll(dao.listAquaintance(np));
            Address a = np.getAddress();
            
            webView.getEngine().executeScript("document.clearMarkers();");
            webView.getEngine().executeScript(String.format("document.centerMap(%s,%s);",a.getLongtitude(),a.getLatitude()));
            webView.getEngine().executeScript(String.format("document.mark(%s,%s,\"%s\",\"%s\");",a.getLongtitude(),a.getLatitude(),"Mark","990000"));
            
            for(Aquaintance aq : t1.getItems()) {
                Address ad = aq.getTo().getAddress();
                webView.getEngine().executeScript(String.format("document.mark(%s,%s,\"%s\",\"%s\");",ad.getLongtitude(),ad.getLatitude(),aq.getTo().toString(),"009900"));
            
            }
            
        });
        
        SplitPane root = new SplitPane(sp,webView);
        
        t.setContent(root);
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
    
    public void closeTab(ActionEvent ae) {
        Tab t = tabPane.getSelectionModel().getSelectedItem();
        if(t!=null){
            tabPane.getTabs().remove(t);
        }
    }
    
}
