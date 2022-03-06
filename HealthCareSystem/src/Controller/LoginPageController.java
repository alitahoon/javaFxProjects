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
import java.io.File;
import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.StageStyle;

/**
 * FXML Controller class
 *
 * @author Ali Tahoon
 */
public class LoginPageController implements Initializable {

    @FXML
    TextField txtUsername;
    @FXML
    PasswordField txtPassword;
    @FXML
    AnchorPane loginPage;
    @FXML
    AnchorPane imagepPane;
    @FXML
    Button btnLogin;
    @FXML
    Button btnRegister;
    //Handle Login
    @FXML
    private void checkLoginInfo(javafx.scene.input.MouseEvent event) {
        if (dbconnect.checkInfo(txtUsername.getText(), txtPassword.getText())) {
            try {
                Parent root = FXMLLoader.load(getClass().getResource("/Views/Home.fxml"));

                Scene scene = new Scene(root);
                Stage stage = new Stage();
                stage.setScene(scene);
                stage.initStyle(StageStyle.UNDECORATED);
                stage.show();
                ((Stage) (((Button) event.getSource()).getScene().getWindow())).close();

            } catch (Exception ex) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText(null);
                alert.setContentText(ex.getMessage());
                alert.showAndWait();
            }

        }

    }
    public static void playSound(String path){
        Media newMedia=new Media(new File(path).toURI().toString());
        MediaPlayer newPlayer=new MediaPlayer(newMedia);
        newPlayer.play();
    }
    @FXML
    private void getClose(javafx.scene.input.MouseEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }

    @FXML
    private void HandleRegisterButton(javafx.scene.input.MouseEvent event) {
        try {
            playSound("C:\\Users\\Ali Tahoon\\Documents\\NetBeansProjects\\HealthCareSystem\\src\\Sounds\\singleClick.mp3");
            AnchorPane main = FXMLLoader.load(getClass().getResource("/Views/adminLogin.fxml"));
            loginPage.getChildren().setAll(main);
        } catch (IOException ex) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText(ex.getMessage());
            alert.showAndWait();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

}
