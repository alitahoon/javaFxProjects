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
import Models.department;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.animation.TranslateTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.util.Duration;
import Models.clinics;
/**
 * FXML Controller class
 *
 * @author Ali Tahoon
 */
public class clinicsDeleteController implements Initializable {

    @FXML
    private AnchorPane childPane;

    @FXML
    private TableColumn<clinics, String> closeTime;

    @FXML
    private TableColumn<clinics, String> depName;

    @FXML
    private AnchorPane departmentPane;

    @FXML
    private TableColumn<clinics, Integer> id;

    @FXML
    private TableColumn<clinics, String> name;

    @FXML
    private TableColumn<clinics, String> openTime;

    @FXML
    private HBox search;

    @FXML
    private TextField searchKey;

    @FXML
    private TableView<clinics> tblClinicsInfo;

    @FXML
    private TableColumn<clinics, Double> ticketPrise;

    clinics cliModel=null;
    ObservableList<clinics> cliList = FXCollections.observableArrayList();

    @FXML
    private void handelViewData() {      
        loadData();
        try {
            ResultSet r = Database.dbconnect.getAllData("clinicsdata");
            while (r.next()) {
                Models.clinics model = new Models.clinics(r.getString("cliName"),Integer.parseInt(r.getString("cliID")),r.getString("cliOpenTime"),
                        r.getString("cliCloseTime"),Double.parseDouble(r.getString("cliTicketPrice")),r.getString("depName"));
                cliList.add(model);
                tblClinicsInfo.setItems(cliList);
                
            }
        } catch (SQLException ex) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText(ex.getMessage());
            alert.showAndWait();
        }

    }

    private void loadData() {
        name.setCellValueFactory(new PropertyValueFactory<>("name"));
        openTime.setCellValueFactory(new PropertyValueFactory<>("openTime"));
        closeTime.setCellValueFactory(new PropertyValueFactory<>("closeTime"));
        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        ticketPrise.setCellValueFactory(new PropertyValueFactory<>("ticketPrise"));
        depName.setCellValueFactory(new PropertyValueFactory<>("department"));

    }
    @FXML
    private void searchOnDatabase() {
        try {
            ResultSet record = Database.dbconnect.getRecord("clinicsData", searchKey.getText(),"cliID");
            cliList.clear();
            while (record.next()) {
                cliList.add(new clinics(record.getString("cliName"),record.getShort("cliID"), record.getString("cliOpenTime"), record.getString("cliCloseTime"),
                        Double.parseDouble(record.getString("cliTicketPrice")),record.getString("depName")));
                tblClinicsInfo.setItems(cliList);
            }
        } catch (SQLException ex) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText(ex.getMessage());
            alert.showAndWait();
        }

    }
    @FXML
    private void deleteRecord(javafx.scene.input.MouseEvent event){
       cliModel= tblClinicsInfo.getSelectionModel().getSelectedItem();
       Database.dbconnect.deleteRecord("clinics", String.valueOf(cliModel.getId()),"cliID");
       cliList.clear();
       handelViewData();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        handelViewData();
        TranslateTransition movePane = new TranslateTransition(Duration.seconds(0.5), childPane);
        movePane.setToY(600);
        movePane.play();
    }

}
