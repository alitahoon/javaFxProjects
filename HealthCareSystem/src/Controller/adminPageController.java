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
import javafx.scene.control.TextField;
import Database.dbconnect;
import java.io.IOException;
import javafx.animation.FadeTransition;
import javafx.animation.TranslateTransition;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * FXML Controller class
 *
 * @author Ali Tahoon
 */
public class adminPageController implements Initializable {

    @FXML
    private AnchorPane admin;
    @FXML
    private AnchorPane adminComponent;

    @FXML
    private Button btnAdd;
    @FXML
    private TextField txtAdminUsername;

    @FXML
    private PasswordField txtAdminPassword;

    @FXML
    private PasswordField txtconfirmnewpassword;

    @FXML
    private PasswordField txtnewpassword;

    @FXML
    private TextField txtnewusername;
    @FXML
    private ImageView UserAdded;
    AnchorPane loginPage;

    //Handle Login
    @FXML
    private void checkRegisterInfo() {
    }
    //close the window
    @FXML
    private void getClose(javafx.scene.input.MouseEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }
    //handle unhide Password
    @FXML
    private void hanldeUnhidePassword(javafx.scene.input.MouseEvent event){
    }
    

    @FXML
    private void HandleAddButton(javafx.scene.input.MouseEvent event) {
        if (txtAdminPassword.getText().isEmpty() || txtconfirmnewpassword.getText().isEmpty() || txtnewpassword.getText().isEmpty() || txtnewusername.getText().isEmpty() || txtAdminUsername.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Please Enter All Data");
            alert.showAndWait();

        } else {
            //Handle imageView userAdded
            FadeTransition moveUserAdded = new FadeTransition(Duration.seconds(0.5), UserAdded);
            moveUserAdded.setFromValue(0);
            moveUserAdded.setToValue(1);
            moveUserAdded.play();
            //End Handelling
            
            //connect to database to check if the user is Admin User if right insert new User Data 
            dbconnect.isAdmin(txtAdminUsername.getText(), txtAdminPassword.getText());

        }
    }

    @FXML
    private void HandleBackButton(javafx.scene.input.MouseEvent event) {
        try {
            AnchorPane main = FXMLLoader.load(getClass().getResource("/Views/LoginPage.fxml"));
            admin.getChildren().setAll(main);
        } catch (IOException ex) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText(ex.getMessage());
            alert.showAndWait();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        TranslateTransition moveAdminComponent=new TranslateTransition(Duration.seconds(0.3), adminComponent);
        moveAdminComponent.setToX(1200);
        moveAdminComponent.play();
    }

}
