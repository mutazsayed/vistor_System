 
package vistorsystem;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import models.Patient;
import models.PatientVisit;

/**
 *
 * @author HUAWEI
 */
public class VistorSystem extends Application {

    static Connection connection = null;

    static Stage stage;
    public static ObservableList<Patient> patients = FXCollections.observableArrayList();
    public static FilteredList<Patient> filteredPatients = new FilteredList(patients, p -> true);
   
    public static ObservableList<PatientVisit> visits = FXCollections.observableArrayList();
    public static FilteredList<PatientVisit> filteredVisits = new FilteredList(visits, p -> true);

    @Override
    public void start(Stage primaryStage) throws Exception {
        stage = primaryStage;

        navigate("/views/MainPage.fxml", "Main Page");

    }

    public static void navigate(String viewPath, String title) {

        Parent root;
        try {
            root = FXMLLoader.load(VistorSystem.class.getClass().getResource(viewPath));
            Scene scene = new Scene(root);

            stage.setScene(scene);
            stage.setTitle(title);
            stage.show();

        } catch (IOException ex) {
            ex.printStackTrace();
        }

    }

    public static Connection getConnection() {
        if (connection != null) {
            return connection;
        }
        try {
            //jdbc:sqlite:
            connection = DriverManager.getConnection("jdbc:sqlite:vistorSys.db");
            return connection;

        } catch (Exception e) {
            System.out.println(e);

        }
        return null;
    }

    public static void showErrorAlert(String title, String content) {

        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setContentText(content);
        alert.showAndWait();

    }

    public static void showAlert(String title, String content) {

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setContentText(content);
        alert.showAndWait();

    }

    public static void loadPatients() {
        int id, room;
        String department, name;
        patients.clear();
        try {

            Connection con = getConnection();
            String query = "SELECT id, Name,Department,Room FROM PatientDetails  ";

            PreparedStatement ps = con.prepareStatement(query);

            ResultSet results = ps.executeQuery();
            while (results.next()) {
                id = results.getInt("id");
                room = results.getInt("room");

                name = results.getString("name");
                department = results.getString("department");
                Patient p = new Patient(id, room, name, department);

                patients.add(p);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void loadVisits() {
        int id;
        String department, patient, visitor, phone, visitdate;
        visits.clear();
        try {

            Connection con = getConnection();
            String query = " SELECT VisitorDetails. ID,VisitorDetails.Name as visitor,VisitorDetails.phone, ";
            query += "   VisitorDetails.visitdate,PatientDetails.Name as patient, PatientDetails.Department  ";
            query += "   FROM VisitorDetails ";
            query += "   inner join PatientDetails  on VisitorDetails. PatientId=PatientDetails.id ";

            PreparedStatement ps = con.prepareStatement(query);

            ResultSet results = ps.executeQuery();
            //id, String patient, String department, String visitor, String phone, String visitdate) {
            while (results.next()) {
                id = results.getInt("id");
                patient = results.getString("patient");
                visitor = results.getString("visitor");
                phone = results.getString("phone");
                visitdate = results.getString("visitdate");
                department = results.getString("department");
                PatientVisit pv = new PatientVisit(id, patient, department, visitor, phone, visitdate);

                visits.add(pv);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
