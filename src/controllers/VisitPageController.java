package controllers;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import models.PatientVisit;
import vistorsystem.VistorSystem;

public class VisitPageController implements Initializable {

    @FXML
    private Button btnHome;
    @FXML
    private TableView<PatientVisit> table;
    @FXML
    private TableColumn<PatientVisit, Integer> colId;
    @FXML
    private TableColumn<PatientVisit, String> colPatient;
    @FXML
    private TableColumn<PatientVisit, String> colDepartment;
    @FXML
    private TableColumn<PatientVisit, String> colVisitor;
    @FXML
    private TableColumn<PatientVisit, String> colPhone;
    @FXML
    private TableColumn<PatientVisit, String> colDate;
    @FXML
    private TextField txtSearch;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        VistorSystem.loadVisits();
        this.table.setItems(VistorSystem.filteredVisits);

        this.colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        this.colPatient.setCellValueFactory(new PropertyValueFactory<>("patient"));
        this.colDepartment.setCellValueFactory(new PropertyValueFactory<>("department"));
        this.colVisitor.setCellValueFactory(new PropertyValueFactory<>("visitor"));
        this.colPhone.setCellValueFactory(new PropertyValueFactory<>("phone"));
        this.colDate.setCellValueFactory(new PropertyValueFactory<>("visitdate"));

        txtSearch.textProperty().addListener((observable, oldValue, newValue) -> {
            VistorSystem.filteredVisits.setPredicate(pv -> {

                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
               // System.out.println(pv);
                String value = newValue.toLowerCase();
                System.out.println("value " + value);
                if (pv.getPatient().toLowerCase().contains(value)) {
                    System.out.println("getPatient " + true);
                    return true; // Filter by patient name
                }
                System.out.println("pv.getPatient()" + pv.getPatient());
                if (pv.getDepartment().toLowerCase().contains(value)) {
                    System.out.println("getDepartment " + true);
                    return true; // Filter  by department
                }
                if (pv.getVisitor().toLowerCase().contains(value)) {
                    System.out.println("getVisitor " + true);
                    return true; // Filter by visitor name
                }
                  System.out.println("pv.getVisitor()" + pv.getVisitor());
                if (pv.getPhone().toLowerCase().contains(value)) {
                    System.out.println("getPhone " + true);
                    return true; // Filter  by phone
                }
                if (pv.getVisitdate().toLowerCase().contains(value)) {
                    System.out.println("getVisitdate " + true);
                    return true; // Filter  by visit date.
                }
                System.out.println("no mathch " + value + "    " + false);
                return false; // Does not match.
            });
        });
    }

    @FXML
    private void goToHomePage(ActionEvent event) {
        VistorSystem.navigate("/views/MainPage.fxml", "Home Page");
    }

}
