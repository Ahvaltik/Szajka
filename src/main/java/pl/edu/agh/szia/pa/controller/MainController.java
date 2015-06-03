/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.edu.agh.szia.pa.controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableView;
import javafx.scene.web.WebView;

/**
 *
 * @author uriel
 */
public class MainController implements Initializable {

    @FXML
    private TabPane tabPane;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Tab tab = new Tab("Start");
        
        final WebView webView = new WebView();
        
        String path = getClass().getResource("/html/googleMap.html").toString();
        System.out.println(path);
        webView.getEngine().load(path);
        
        tab.setContent(webView);
        
        
        tabPane.getTabs().add(0, tab);
    }
    
    public void showEntityViewer() {
        Tab t = new Tab();
        
        TableView tv = new TableView();
        
        t.setContent(tv);
        tabPane.getTabs().add(t);
    }
   
    
}
