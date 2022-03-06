/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import Models.doctor;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.animation.TranslateTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Duration;

/**
 * FXML Controller class
 *
 * @author Ali Tahoon
 */
public class doctorViewController implements Initializable {
    
    @FXML
    private Label AvargeSalary;
    
    @FXML
    private Label avgPrise;
    
    @FXML
    private AnchorPane childPane;
    
    @FXML
    private TableColumn<doctor, String> tblAddress;
    
    @FXML
    private TableColumn<doctor, String> tblClinics;
    
    @FXML
    private TableColumn<doctor, String> tblDepartment;
    
    @FXML
    private TableView<doctor> tblDoctorInfo;
    
    @FXML
    private TableColumn<doctor, String> tblGender;
    
    @FXML
    private TableColumn<doctor, Integer> tblID;
    
    @FXML
    private TableColumn<doctor, String> tblName;
    
    @FXML
    private TableColumn<doctor, Double> tblSalary;
    
    @FXML
    private TableColumn<doctor, String> tblShiftEnd;
    
    @FXML
    private TableColumn<doctor, String> tblShiftStart;
    
    @FXML
    private TableColumn<doctor, String> tblbirhDate;
    
    @FXML
    private TableColumn<doctor, String> tblgrade;
    
    @FXML
    private Label totClinics;
    
    @FXML
    private Label totDoctors;
    doctor docModel = null;
    ObservableList<doctor> doctor = FXCollections.observableArrayList();
    
    @FXML
    private void handelViewData() {
        loadData();
        try {
            ResultSet r = Database.dbconnect.getAllData("doctorData");
            while (r.next()) {
                docModel = new doctor(Integer.parseInt(r.getString("docID")), r.getString("docName"), Double.parseDouble(r.getString("docSalary")),
                        r.getString("docShiftStart"),r.getString("docShiftEnd"), r.getString("depName"), r.getString("cliName"),
                        r.getString("docGender"), r.getString("docBirthdate"), r.getString("docGrade"), r.getString("docAddress"));
                doctor.add(docModel);
                tblDoctorInfo.setItems(doctor);
                
            }
        } catch (SQLException ex) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText(ex.getMessage());
            alert.showAndWait();
        }
        
    }
    
    private void setStatistic() {
        totClinics.setText(Database.dbconnect.getMaxValue("doctor", "docID"));
        avgPrise.setText(Database.dbconnect.getAvagrge("doctor", "docSalary", "docID").toString() + " $");
    }
    
    private void loadData() {
        tblName.setCellValueFactory(new PropertyValueFactory<>("name"));
        tblShiftEnd.setCellValueFactory(new PropertyValueFactory<>("shiftEnd"));
        tblShiftStart.setCellValueFactory(new PropertyValueFactory<>("shiftStart"));
        tblAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        tblClinics.setCellValueFactory(new PropertyValueFactory<>("clinicName"));
        tblGender.setCellValueFactory(new PropertyValueFactory<>("gender"));
        tblSalary.setCellValueFactory(new PropertyValueFactory<>("salary"));
        tblbirhDate.setCellValueFactory(new PropertyValueFactory<>("Birthdate"));
        tblID.setCellValueFactory(new PropertyValueFactory<>("id"));
        tblDepartment.setCellValueFactory(new PropertyValueFactory<>("depName"));
        tblgrade.setCellValueFactory(new PropertyValueFactory<>("grade"));
        
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        handelViewData();
        setStatistic();
        TranslateTransition movePane = new TranslateTransition(Duration.seconds(0.5), childPane);
        movePane.setToY(700);
        movePane.play();
    }
    
    
}
