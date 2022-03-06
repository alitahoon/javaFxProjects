/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import static Controller.HomePageController.nur;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;

/**
 * FXML Controller class
 *
 * @author Ali Tahoon
 */
public class departmentAddController implements Initializable {

    @FXML
    private Button btnClear;

    @FXML
    private Button btnSave;

    @FXML
    private AnchorPane departmentPane;

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
    @FXML
    private AnchorPane childPane;

    @FXML
    private void handelSaveButton() {
        String query = "INSERT INTO `department`(`depName`, `depID`, `depbedsNum`, `depManager`, `depAssitantManager`) VALUES"
                + " (" + "'" + txtName.getText() + "'" + ","
                + Database.dbconnect.autoNumber("department", "depID") + ","
                + txtBedsNum.getText() + ","
                + "'" + txtManagerName.getText() + "'" + ","
                + "'" + txtMangerAssitant.getText() + "'" + ")";
        Database.dbconnect.insertData("department", query);
        txtID.setText(Database.dbconnect.autoNumber("department", "depID"));
        clearText();
    }

    private void clearText() {
        txtBedsNum.setText("");
        txtManagerName.setText("");
        txtMangerAssitant.setText("");
        txtName.setText("");
        txtName.requestFocus();

    }

    @FXML
    private void handelClearButton() {
        clearText();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        txtID.setText(Database.dbconnect.autoNumber("department", "depID"));
        TranslateTransition movePane = new TranslateTransition(Duration.seconds(0.3), childPane);
        movePane.setToX(1245);
        movePane.play();
    }

}
