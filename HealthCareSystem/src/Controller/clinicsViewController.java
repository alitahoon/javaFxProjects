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
import Models.clinics;
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
public class clinicsViewController implements Initializable {

    @FXML
    private Label avgPrise;

    @FXML
    private AnchorPane childPane;

    @FXML
    private TableColumn<clinics, String> closeTime;

    @FXML
    private TableColumn<clinics, Integer> id;

    @FXML
    private TableColumn<clinics, String> name;

    @FXML
    private TableColumn<clinics, String> openTime;

    @FXML
    private TableColumn<clinics, String> depName;
    @FXML
    private TableView<clinics> tblClinicsInfo;

    @FXML
    private TableColumn<clinics, Double> ticketPrise;
    ObservableList<clinics> cliList = FXCollections.observableArrayList();

    @FXML
    private Label totClinics;

    @FXML
    private void handelViewData() {
        loadData();
        try {
            ResultSet r = Database.dbconnect.getAllData("clinicsdata");
            while (r.next()) {
                Models.clinics model = new Models.clinics(r.getString("cliName"), Integer.parseInt(r.getString("cliID")), r.getString("cliOpenTime"),
                        r.getString("cliCloseTime"), Double.parseDouble(r.getString("cliTicketPrice")), r.getString("depName"));
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

    private void setStatistic() {
        totClinics.setText(Database.dbconnect.getMaxValue("clinics", "cliID"));
        avgPrise.setText(Database.dbconnect.getAvagrge("clinics", "cliTicketPrice", "cliID").toString() + " $");
    }

    private void loadData() {
        name.setCellValueFactory(new PropertyValueFactory<>("name"));
        openTime.setCellValueFactory(new PropertyValueFactory<>("openTime"));
        closeTime.setCellValueFactory(new PropertyValueFactory<>("closeTime"));
        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        ticketPrise.setCellValueFactory(new PropertyValueFactory<>("ticketPrise"));
        depName.setCellValueFactory(new PropertyValueFactory<>("department"));

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        handelViewData();
        setStatistic();
        TranslateTransition movePane = new TranslateTransition(Duration.seconds(0.5), childPane);
        movePane.setToY(600);
        movePane.play();
    }

}
