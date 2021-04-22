/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import models.Patient;
import vistorsystem.VistorSystem;

/**
 * FXML Controller class
 *
 * @author HUAWEI
 */
public class VisitorInformationController implements Initializable {

    @FXML
    private TextField txtVistName;
    @FXML
    private TextField txtVistNumber;
    @FXML
    private Button btnBack;
    @FXML
    private Button btnAdd;
    @FXML
    private TableView<Patient> table;
    @FXML
    private TableColumn<Patient, Integer> colId;
    @FXML
    private TableColumn<Patient, String> colName;
    @FXML
    private TableColumn<Patient, String> colDepartment;
    @FXML
    private TableColumn<Patient, Integer> colRoom;

    @FXML
    private TextField txtSearch;
    Patient selected = null;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.btnAdd.setDisable(true);
        VistorSystem.loadPatients();
        this.table.setItems(VistorSystem.filteredPatients);

        this.colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        this.colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        this.colDepartment.setCellValueFactory(new PropertyValueFactory<>("department"));
        this.colRoom.setCellValueFactory(new PropertyValueFactory<>("room"));
        
        this.table.getSelectionModel().selectedIndexProperty().addListener(x -> {
            selected = this.table.getSelectionModel().getSelectedItem();
            if (selected != null) {
                this.btnAdd.setDisable(false);

            } else {
                this.btnAdd.setDisable(true);

            }
        });

        txtSearch.textProperty().addListener((observable, oldValue, newValue) -> {
            VistorSystem.filteredPatients.setPredicate(patient -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                String value = newValue.toLowerCase();
                if (patient.getName().toLowerCase().contains(value)) {
                    return true; // Filter by name
                }
                if (patient.getDepartment().contains(value)) {
                    return true; // Filter  by department
                }
                if ((patient.getRoom() + "").contains(value)) {
                    return true; // Filter by room
                }

                return false; // Does not match.
            });
        });
    }

    @FXML
    private void Back(ActionEvent event) throws IOException {
        VistorSystem.navigate("/views/MainPage.fxml", "Main Page");
    }

    public int insertVisit(String name, String phone, int patientId) {

        try {
            Connection con = VistorSystem.getConnection();
            String query = "INSERT INTO VisitorDetails ( name,phone,PatientId)VALUES (?,?,? )";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, name);
            ps.setString(2, phone);
            ps.setInt(3, patientId);
            int res = ps.executeUpdate();
            return res;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    @FXML
    private void addVisitor(ActionEvent event) {
        int patientId;
        String name, phone;
        name = this.txtVistName.getText();
        phone = this.txtVistNumber.getText();
        patientId = selected.getId();

        if (name.isEmpty()) {
            VistorSystem.showErrorAlert("Error", "Enter visitor name");
            return;
        }
        if (phone.isEmpty()) {
            VistorSystem.showErrorAlert("Error", "Enter visitor phone");
            return;
        }
        int res = this.insertVisit(name, phone, patientId);

        if (res == 1) {
            VistorSystem.showAlert("Success", "Visit is recorded successfully");
            this.txtVistName.setText("");
            this.txtVistNumber.setText("");
            this.btnAdd.setDisable(true);
        } else {
            VistorSystem.showErrorAlert("Error", "Visit is not recorded");
        }

    }

}
