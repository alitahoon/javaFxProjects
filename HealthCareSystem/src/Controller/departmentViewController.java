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
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;

/**
 * FXML Controller class
 *
 * @author Ali Tahoon
 */
public class departmentViewController implements Initializable {
    @FXML
    private Label totalBeds;

    @FXML
    private Label totalDep;

    @FXML
    private Label totalManagerAssit;

    @FXML
    private Label totalManagers;
    @FXML
    AnchorPane childPane;
    @FXML
    private TableView tblDepartmentInfo;
    @FXML
    private TableColumn<department, String> name;
    @FXML
    private TableColumn<department, String> manAssitName;
    @FXML
    private TableColumn<department, String> manName;
    @FXML
    private TableColumn<department, Integer> id;
    @FXML
    private TableColumn<department, Integer> numOfBeds;
    ObservableList<department> deplist = FXCollections.observableArrayList();

    @FXML
    private void handelViewData() {
        totalManagers.setText(Database.dbconnect.getMaxValue("department", "depID"));
        totalManagerAssit.setText(Database.dbconnect.getMaxValue("department", "depID"));
        totalDep.setText(Database.dbconnect.getMaxValue("department", "depID"));
        totalBeds.setText(Database.dbconnect.getTotalValues("department", "depbedsNum","depID").toString());
        loadData();
        try {
            ResultSet r = Database.dbconnect.getAllData("Department");
            while (r.next()) {
                Models.department model = new Models.department(r.getString("depName"), r.getString("depManager"), r.getString("depAssitantManager"),
                        Integer.parseInt(r.getString("depID")), Integer.parseInt(r.getString("depbedsNum")));
                deplist.add(model);
                tblDepartmentInfo.setItems(deplist);
                
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
        manAssitName.setCellValueFactory(new PropertyValueFactory<>("managerName"));
        manName.setCellValueFactory(new PropertyValueFactory<>("managerAssitName"));
        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        numOfBeds.setCellValueFactory(new PropertyValueFactory<>("numOfBeds"));

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        handelViewData();
        TranslateTransition movePane = new TranslateTransition(Duration.seconds(0.5), childPane);
        movePane.setToY(700);
        movePane.play();
    }

}
