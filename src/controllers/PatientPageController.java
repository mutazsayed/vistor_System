package controllers;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import vistorsystem.VistorSystem;

public class PatientPageController implements Initializable {

    @FXML
    private TextField txtName;
    @FXML
    private TextField txtDepartment;
    @FXML
    private Button btnBack;
    @FXML
    private Button btnAdd;
    @FXML
    private TextField txtRoom;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    @FXML
    private void Back(ActionEvent event) {
        VistorSystem.navigate("/views/MainPage.fxml", "Main Page");
    }

    @FXML
    private void addPatient(ActionEvent event) {

        int room = 0;
        String name, department;

        name = this.txtName.getText();
        department = this.txtDepartment.getText();
        try {
            room = Integer.parseInt(this.txtRoom.getText());
        } catch (Exception e) {
            room = 0;
        }

        if (name.isEmpty()) {
            VistorSystem.showErrorAlert("Error", "Enter name");
            return;
        }
        if (department.isEmpty()) {
            VistorSystem.showErrorAlert("Error", "Enter department");
            return;
        }

        if (room <= 0) {
            VistorSystem.showErrorAlert("Error", "Enter room");
            return;
        }
        int res = insertPatient(name, department, room);
        if (res == 1) {
            VistorSystem.showAlert("Success", "Patient is recorded successfully");
            this.txtName.setText("");
            this.txtDepartment.setText("");
            this.txtRoom.setText("");
        } else {
            VistorSystem.showErrorAlert("Error", "Patient is not recorded");
        }

    }

    public int insertPatient(String name, String department, int room) {

        try {
            Connection con = VistorSystem.getConnection();
            String query = "INSERT INTO PatientDetails (Name,Department,Room)VALUES (?,?,?)";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, name);
            ps.setString(2, department);
            ps.setInt(3, room);
            
            int res = ps.executeUpdate();
            return res;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }
}
