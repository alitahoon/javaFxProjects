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
import Models.nurses;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.animation.TranslateTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Duration;
import Models.nurses;

/**
 * FXML Controller class
 *
 * @author Ali Tahoon
 */
public class nursesViewController implements Initializable {
    
    @FXML
    private Label AvargeSalary;
    
    @FXML
    private Label avgPrise;
    
    @FXML
    private AnchorPane childPane;
    
    @FXML
    private TableColumn<nurses, String> tblAddress;
    
    
    @FXML
    private TableColumn<nurses, String> tblDepartment;
    
    @FXML
    private TableView<nurses> tblDoctorInfo;
    
    @FXML
    private TableColumn<nurses, String> tblGender;
    
    @FXML
    private TableColumn<nurses, Integer> tblID;
    
    @FXML
    private TableColumn<nurses, String> tblName;
    
    @FXML
    private TableColumn<nurses, Double> tblSalary;
    
    @FXML
    private TableColumn<nurses, String> tblShiftEnd;
    
    @FXML
    private TableColumn<nurses, String> tblShiftStart;
    
    @FXML
    private TableColumn<nurses, String> tblbirhDate;
    
    @FXML
    private TableColumn<nurses, String> tblgrade;
    
    @FXML
    private Label totClinics;
    
    @FXML
    private Label totDoctors;
    nurses nurModel = null;
    ObservableList<nurses> nurses = FXCollections.observableArrayList();
    
    @FXML
    private void handelViewData() {
        loadData();
        try {
            ResultSet r = Database.dbconnect.getAllData("nursesData");
            while (r.next()) {
                nurModel = new nurses(Integer.parseInt(r.getString("nurID")), r.getString("nurName"), Double.parseDouble(r.getString("nurSalary")),
                        r.getString("nurShiftStart"),r.getString("nurShiftEnd"), r.getString("depName"),
                        r.getString("nurGender"), r.getString("nurBirthDate"), r.getString("nurGrade"), r.getString("nurAddress"));
                nurses.add(nurModel);
                tblDoctorInfo.setItems(nurses);
                
            }
        } catch (SQLException ex) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText(ex.getMessage());
            alert.showAndWait();
        }
        
    }
    
    private void setStatistic() {
        totClinics.setText(Database.dbconnect.getMaxValue("nurses", "nurID"));
        avgPrise.setText(Database.dbconnect.getAvagrge("nurses", "nurSalary", "nurID").toString() + " $");
    }
    
    private void loadData() {
        tblName.setCellValueFactory(new PropertyValueFactory<>("name"));
        tblShiftEnd.setCellValueFactory(new PropertyValueFactory<>("shiftEnd"));
        tblShiftStart.setCellValueFactory(new PropertyValueFactory<>("shiftStart"));
        tblAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
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
