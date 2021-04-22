/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
 
/**
 *
 * @author HUAWEI
 */
import vistorsystem.VistorSystem;
public class MainPageController implements Initializable {

    @FXML
    private Button btnVisting;
    @FXML
    private Button btnVisits;
    @FXML
    private Button btnPatient;
   
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
 

    @FXML
    private void goToVisits(ActionEvent event) {
          VistorSystem.navigate("/views/VisitorInformation.fxml","Visitor Information");
    }

    @FXML
    private void goToVisitsHistory(ActionEvent event) {
          VistorSystem.navigate("/views/VisitPage.fxml","Visit Page History");
    }

    @FXML
    private void goToPatientRegistering(ActionEvent event) {
         VistorSystem.navigate("/views/PatientPage.fxml","Patient Page");
    }
    
}
