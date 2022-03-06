/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Models.department;
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
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.util.Duration;

/**
 * FXML Controller class
 *
 * @author Ali Tahoon
 */
public class doctorDeleteController implements Initializable {

    @FXML
    private HBox search;

    @FXML
    private TextField searchKey;

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
    ObservableList<doctor> docList = FXCollections.observableArrayList();

    @FXML
    private void searchOnDatabase() {
        try {
            ResultSet r = Database.dbconnect.getRecord("doctordata", searchKey.getText(), "docID");
            docList.clear();
            while (r.next()) {
                 docModel = new doctor(Integer.parseInt(r.getString("docID")), r.getString("docName"), Double.parseDouble(r.getString("docSalary")),
                        r.getString("docShiftStart"), r.getString("docShiftEnd"), r.getString("DepName"), r.getString("CliName"),
                        r.getString("docGender"), r.getString("docBirthdate"), r.getString("docGrade"), r.getString("docAddress"));
                 docList.add(docModel);
                tblDoctorInfo.setItems(docList);
            }
        } catch (SQLException ex) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText(ex.getMessage());
            alert.showAndWait();
        }

    }

    @FXML
    private void deleteRecord(javafx.scene.input.MouseEvent event) {
        docModel = tblDoctorInfo.getSelectionModel().getSelectedItem();
        Database.dbconnect.deleteRecord("doctor", String.valueOf(docModel.getId()), "docID");
        docList.clear();
        handelViewData();
    }

    @FXML
    private void handelViewData() {
        loadData();
        try {
            ResultSet r = Database.dbconnect.getAllData("doctorData");
            while (r.next()) {
                docModel = new doctor(Integer.parseInt(r.getString("docID")), r.getString("docName"), Double.parseDouble(r.getString("docSalary")),
                        r.getString("docShiftStart"), r.getString("docShiftEnd"), r.getString("depName"), r.getString("cliName"),
                        r.getString("docGender"), r.getString("docBirthdate"), r.getString("docGrade"), r.getString("docAddress"));
                docList.add(docModel);
                tblDoctorInfo.setItems(docList);

            }
        } catch (SQLException ex) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText(ex.getMessage());
            alert.showAndWait();
        }

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
        TranslateTransition movePane = new TranslateTransition(Duration.seconds(0.5), childPane);
        movePane.setToY(700);
        movePane.play();
    }

}
