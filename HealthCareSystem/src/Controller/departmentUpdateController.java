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
import javafx.scene.control.Button;
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
public class departmentUpdateController implements Initializable {
    
    @FXML
    private Button btnClear;
    
    @FXML
    private Button btnUpdate;
    
    @FXML
    private AnchorPane childPane;
    
    @FXML
    private AnchorPane departmentPane;
    
    @FXML
    private TableColumn<department, Integer> id;
    
    @FXML
    private TableColumn<department, String> manAssitName;
    
    @FXML
    private TableColumn<department, String> manName;
    
    @FXML
    private TableColumn<department, String> name;
    
    @FXML
    private TableColumn<department, Integer> numOfBeds;
    
    @FXML
    private TableView<department> tblDepartmentInfo;
    
    @FXML
    private TextField txtBedsNum;
    
    @FXML
    private TextField txtID;
    
    @FXML
    private TextField txtManagerName;
    
    @FXML
    private TextField txtMangerAssitant;
    
    @FXML
    private TextField txtName;
    department dep = null;
    ObservableList<department> deplist = FXCollections.observableArrayList();
    
    @FXML
    private void handelViewData() {
        deplist.clear();
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
    
    @FXML
    private void handleUpdateButton(javafx.scene.input.MouseEvent event) {
        String query = "UPDATE `department` SET "
                + "`depName`='" + txtName.getText() + "',`depbedsNum`='" + txtBedsNum.getText() + "',`depManager`= '" + txtManagerName.getText()
                + "',`depAssitantManager`=' " + txtMangerAssitant.getText() + " ' WHERE depid = " + txtID.getText();
        System.out.println(dep.getName());
        Database.dbconnect.excuteQueryOndatabase(query);
        handelViewData();
    }
    
    @FXML
    private void handleLoadData(javafx.scene.input.MouseEvent event) {
        dep = tblDepartmentInfo.getSelectionModel().getSelectedItem();
        txtName.setText(dep.getName());
        txtManagerName.setText(dep.getManagerName());
        txtMangerAssitant.setText(dep.getManagerAssitName());
        txtBedsNum.setText(String.valueOf(dep.getNumOfBeds()));
        txtID.setText(String.valueOf(dep.getId()));
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
        movePane.setToX(1245);
        movePane.play();
    }
    
}
