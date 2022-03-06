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
import javafx.util.Duration;

/**
 * FXML Controller class
 *
 * @author Ali Tahoon
 */
public class departmentDeleteController implements Initializable {

    @FXML
    private AnchorPane childPane;
    @FXML
    private TextField searchKey;
    @FXML
    private TableView<department> tblDepartmentInfo;
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
    Models.department departmentModel=null;
    ObservableList<department> deplist = FXCollections.observableArrayList();

    @FXML
    private void handelViewData() {
        loadData();
        deplist.clear();
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
    @FXML
    private void searchOnDatabase() {
        try {
            ResultSet record = Database.dbconnect.getRecord("department", searchKey.getText(),"depID");
            deplist.clear();
            while (record.next()) {
                deplist.add(new department(record.getString("depName"), record.getString("depManager"), record.getString("depAssitantManager"),
                        Integer.parseInt(record.getString("depID")), Integer.parseInt(record.getString("depbedsNum"))));
                tblDepartmentInfo.setItems(deplist);
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
       departmentModel= tblDepartmentInfo.getSelectionModel().getSelectedItem();
       Database.dbconnect.deleteRecord("department", String.valueOf(departmentModel.getId()),"depID");
       deplist.clear();
       handelViewData();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        handelViewData();
        TranslateTransition movePane = new TranslateTransition(Duration.seconds(0.5), childPane);
        movePane.setToY(700);
        movePane.play();
    }

}
