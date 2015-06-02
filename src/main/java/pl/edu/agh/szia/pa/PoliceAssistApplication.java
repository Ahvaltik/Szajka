package pl.edu.agh.szia.pa;

import com.lynden.gmapsfx.GoogleMapView;
import com.lynden.gmapsfx.javascript.object.GoogleMap;
import com.lynden.gmapsfx.javascript.object.LatLong;
import com.lynden.gmapsfx.javascript.object.MapOptions;
import com.lynden.gmapsfx.javascript.object.MapTypeIdEnum;
import com.lynden.gmapsfx.javascript.object.Marker;
import com.lynden.gmapsfx.javascript.object.MarkerOptions;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.classic.Session;
import pl.edu.agh.szia.pa.model.address.Address;
import pl.edu.agh.szia.pa.model.address.Town;


public class PoliceAssistApplication extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/Scene.fxml"));
        AnchorPane ap  = (AnchorPane)root;
       
        final WebView webView = new WebView();
        
        String path = getClass().getResource("/html/googleMap.html").toString();
        System.out.println(path);
        webView.getEngine().load(path);
        
        VBox vb = new VBox();
        vb.getChildren().add(webView);
        Button b = new Button("Search");
        b.setOnAction((e)->{
            webView.getEngine().executeScript("document.goToLocation(\"Kraków, wygoda 7\")");
        });
        vb.getChildren().add(b);
        b = new Button("Hibernate");
        b.setOnAction((e)->{
            
            SessionFactory factory = new AnnotationConfiguration().configure().buildSessionFactory();
            
            Session s = factory.openSession();
            Address a  = new Address();
            
            a.setStreet("Wygoda");
            a.setHouse("7");
            
            Transaction t = s.getTransaction();
            t.begin();
            System.out.println(s.createCriteria(Town.class).list());
            t.commit();
            
            s.close();
        });
        vb.getChildren().add(b);
        
        ap.getChildren().add(vb);
        
        
        Scene scene = new Scene(root);
        scene.getStylesheets().add("/styles/Styles.css");
        
        stage.setTitle("JavaFX and Maven");
        stage.setScene(scene);
        stage.show();
    }
    
    private Node createMap(){
        GoogleMapView gm = new GoogleMapView();
        gm.addMapInializedListener(()->{
           
            //Set the initial properties of the map.
    MapOptions mapOptions = new MapOptions();

    mapOptions.center(new LatLong(47.6097, -122.3331))
            .mapType(MapTypeIdEnum.ROADMAP)
            .overviewMapControl(false)
            .panControl(false)
            .rotateControl(false)
            .scaleControl(false)
            .streetViewControl(false)
            .zoomControl(false)
            .zoom(12);

    
    GoogleMap map = gm.createMap(mapOptions);
    

    //Add a marker to the map
    MarkerOptions markerOptions = new MarkerOptions();

    markerOptions.position( new LatLong(47.6, -122.3) )
                .visible(Boolean.TRUE)
                .title("My Marker");

    Marker marker = new Marker( markerOptions );

    map.addMarker(marker);

            
        });
        return gm;
    }

    /**
     * The main() method is ignored in correctly deployed JavaFX application.
     * main() serves only as fallback in case the application can not be
     * launched through deployment artifacts, e.g., in IDEs with limited FX
     * support. NetBeans ignores main().
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
