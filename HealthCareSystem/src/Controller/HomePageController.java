/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import static Controller.LoginPageController.playSound;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

/**
 *
 * @author Ali Tahoon
 */
public class HomePageController implements Initializable {

    static int doc = 0, dep = 0, nur = 0, dru = 0, pat = 0, cli = 0;
    @FXML
    ImageView drag;
    @FXML
    Pane animationPane;
    @FXML
    VBox menu;
    @FXML
    VBox docMenu;
    @FXML
    VBox depMenu;
    @FXML
    VBox nurMenu;
    @FXML
    VBox cliMenu;
    @FXML
    VBox druMenu;
    @FXML
    VBox patMenu;
    @FXML
    HBox addressMenu;
    @FXML
    Pane pan;
    @FXML
    AnchorPane inputPane;
    @FXML
    Label menuName;

    private void hanldeAnimationPane() {
        TranslateTransition movePane = new TranslateTransition(Duration.seconds(0.3), animationPane);
        movePane.setToY(200);
        movePane.setAutoReverse(true);
        movePane.setCycleCount(2);
        movePane.play();
    }

    @FXML
    private void handleDragDoctorMenu(javafx.scene.input.MouseEvent event) {
        playSound("C:\\Users\\Ali Tahoon\\Documents\\NetBeansProjects\\HealthCareSystem\\src\\Sounds\\clickm.mp3");
        TranslateTransition movedrag = new TranslateTransition(Duration.seconds(0.3), docMenu);

        if (doc == 0) {
            movedrag.setToX(280);
            movedrag.play();
            doc++;
        } else {
            movedrag.setToX(-280);
            movedrag.play();
            doc--;
        }
    }

    @FXML
    private void handleDragDepartmentMenu(javafx.scene.input.MouseEvent event) {
        playSound("C:\\Users\\Ali Tahoon\\Documents\\NetBeansProjects\\HealthCareSystem\\src\\Sounds\\clickm.mp3");
        TranslateTransition movedrag = new TranslateTransition(Duration.seconds(0.3), depMenu);
        if (dep == 0) {
            movedrag.setToX(280);
            movedrag.play();
            dep++;
        } else {
            movedrag.setToX(-280);
            movedrag.play();
            dep--;
        }
    }

    @FXML
    private void handleDragNurseMenu(javafx.scene.input.MouseEvent event) {
        playSound("C:\\Users\\Ali Tahoon\\Documents\\NetBeansProjects\\HealthCareSystem\\src\\Sounds\\clickm.mp3");
        TranslateTransition movedrag = new TranslateTransition(Duration.seconds(0.3), nurMenu);
        if (nur == 0) {
            movedrag.setToX(280);
            movedrag.play();
            nur++;
        } else {
            movedrag.setToX(-280);
            movedrag.play();
            nur--;
        }
    }

    @FXML
    private void handleDragDrugsMenu(javafx.scene.input.MouseEvent event) {
        playSound("C:\\Users\\Ali Tahoon\\Documents\\NetBeansProjects\\HealthCareSystem\\src\\Sounds\\clickm.mp3");
        TranslateTransition movedrag = new TranslateTransition(Duration.seconds(0.3), druMenu);
        if (dru == 0) {
            movedrag.setToX(280);
            movedrag.play();
            dru++;
        } else {
            movedrag.setToX(-280);
            movedrag.play();
            dru--;
        }
    }

    @FXML
    private void handleDragPatientMenu(javafx.scene.input.MouseEvent event) {
        playSound("C:\\Users\\Ali Tahoon\\Documents\\NetBeansProjects\\HealthCareSystem\\src\\Sounds\\clickm.mp3");
        menuName.setText("Patients Menu");
        TranslateTransition movedrag = new TranslateTransition(Duration.seconds(0.3), patMenu);
        if (pat == 0) {
            movedrag.setToX(280);
            movedrag.play();
            pat++;
        } else {
            movedrag.setToX(-280);
            movedrag.play();
            pat--;
        }
    }

    @FXML
    private void handleDragClinicMenu(javafx.scene.input.MouseEvent event) {
        playSound("C:\\Users\\Ali Tahoon\\Documents\\NetBeansProjects\\HealthCareSystem\\src\\Sounds\\clickm.mp3");
        TranslateTransition movedrag = new TranslateTransition(Duration.seconds(0.3), cliMenu);
        if (cli == 0) {
            movedrag.setToX(280);
            movedrag.play();
            cli++;
        } else {
            movedrag.setToX(-280);
            movedrag.play();
            cli--;
        }
    }

    @FXML
    private void handleAddDepartmentButton(javafx.scene.input.MouseEvent event) {
        try {
            Controller.LoginPageController.playSound("C:\\Users\\Ali Tahoon\\Documents\\NetBeansProjects\\HealthCareSystem\\src\\Sounds\\operation.mp3");
            hanldeAnimationPane();
            AnchorPane main = FXMLLoader.load(getClass().getResource("/Views/departmentAdd.fxml"));
            inputPane.getChildren().setAll(main);
//            FadeTransition f=new FadeTransition(Duration.seconds(0.5), main);
//            f.setFromValue(0);
//            f.setToValue(1);
//            f.play();

        } catch (IOException ex) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText(ex.getMessage());
            alert.showAndWait();
        }
    }

    @FXML
    private void handleViewDepartmentButton(javafx.scene.input.MouseEvent event) {
        Controller.LoginPageController.playSound("C:\\Users\\Ali Tahoon\\Documents\\NetBeansProjects\\HealthCareSystem\\src\\Sounds\\operation.mp3");
        hanldeAnimationPane();
        try {
            AnchorPane main = FXMLLoader.load(getClass().getResource("/Views/departmentView.fxml"));
            inputPane.getChildren().setAll(main);
//            FadeTransition f=new FadeTransition(Duration.seconds(0.5), main);
//            f.setFromValue(0);
//            f.setToValue(1);
//            f.play();

        } catch (IOException ex) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText(ex.getMessage());
            alert.showAndWait();
        }
    }

    @FXML
    private void handleDeleteDepartmentButton(javafx.scene.input.MouseEvent event) {
        Controller.LoginPageController.playSound("C:\\Users\\Ali Tahoon\\Documents\\NetBeansProjects\\HealthCareSystem\\src\\Sounds\\operation.mp3");
        hanldeAnimationPane();
        try {
            AnchorPane main = FXMLLoader.load(getClass().getResource("/Views/departmentDelete.fxml"));
            inputPane.getChildren().setAll(main);
//            FadeTransition f=new FadeTransition(Duration.seconds(0.5), main);
//            f.setFromValue(0);
//            f.setToValue(1);
//            f.play();

        } catch (IOException ex) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText(ex.getMessage());
            alert.showAndWait();
        }
    }

    @FXML
    private void handleUpdateDepartmentButton(javafx.scene.input.MouseEvent event) {
        Controller.LoginPageController.playSound("C:\\Users\\Ali Tahoon\\Documents\\NetBeansProjects\\HealthCareSystem\\src\\Sounds\\operation.mp3");
        hanldeAnimationPane();
        try {
            AnchorPane main = FXMLLoader.load(getClass().getResource("/Views/departmentUpdate.fxml"));
            inputPane.getChildren().setAll(main);
//            FadeTransition f=new FadeTransition(Duration.seconds(0.5), main);
//            f.setFromValue(0);
//            f.setToValue(1);
//            f.play();

        } catch (IOException ex) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText(ex.getMessage());
            alert.showAndWait();
        }
    }

    @FXML
    private void handleViewClinicButton(javafx.scene.input.MouseEvent event) {
        Controller.LoginPageController.playSound("C:\\Users\\Ali Tahoon\\Documents\\NetBeansProjects\\HealthCareSystem\\src\\Sounds\\operation.mp3");
        try {
            hanldeAnimationPane();
            AnchorPane main = FXMLLoader.load(getClass().getResource("/Views/ClinicsView.fxml"));
            inputPane.getChildren().setAll(main);
//            FadeTransition f=new FadeTransition(Duration.seconds(0.5), main);
//            f.setFromValue(0);
//            f.setToValue(1);
//            f.play();

        } catch (IOException ex) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText(ex.getMessage());
            alert.showAndWait();
        }
    }

    @FXML
    private void handleAddClinicButton(javafx.scene.input.MouseEvent event) {
        Controller.LoginPageController.playSound("C:\\Users\\Ali Tahoon\\Documents\\NetBeansProjects\\HealthCareSystem\\src\\Sounds\\operation.mp3");
        try {
            hanldeAnimationPane();
            AnchorPane main = FXMLLoader.load(getClass().getResource("/Views/clinicsAdd.fxml"));
            inputPane.getChildren().setAll(main);
//            FadeTransition f=new FadeTransition(Duration.seconds(0.5), main);
//            f.setFromValue(0);
//            f.setToValue(1);
//            f.play();

        } catch (IOException ex) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText(ex.getMessage());
            alert.showAndWait();
        }
    }

    @FXML
    private void handleDeleteClinicButton(javafx.scene.input.MouseEvent event) {
        Controller.LoginPageController.playSound("C:\\Users\\Ali Tahoon\\Documents\\NetBeansProjects\\HealthCareSystem\\src\\Sounds\\operation.mp3");
        try {
            hanldeAnimationPane();
            AnchorPane main = FXMLLoader.load(getClass().getResource("/Views/ClinicsDelete.fxml"));
            inputPane.getChildren().setAll(main);
//            FadeTransition f=new FadeTransition(Duration.seconds(0.5), main);
//            f.setFromValue(0);
//            f.setToValue(1);
//            f.play();

        } catch (IOException ex) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText(ex.getMessage());
            alert.showAndWait();
        }
    }
    @FXML
    private void handleUpdateClinicButton(javafx.scene.input.MouseEvent event) {
        Controller.LoginPageController.playSound("C:\\Users\\Ali Tahoon\\Documents\\NetBeansProjects\\HealthCareSystem\\src\\Sounds\\operation.mp3");
        try {
            hanldeAnimationPane();
            AnchorPane main = FXMLLoader.load(getClass().getResource("/Views/clinicstUpdate.fxml"));
            inputPane.getChildren().setAll(main);
//            FadeTransition f=new FadeTransition(Duration.seconds(0.5), main);
//            f.setFromValue(0);
//            f.setToValue(1);
//            f.play();

        } catch (IOException ex) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText(ex.getMessage());
            alert.showAndWait();
        }
    }
     @FXML
    private void handleViewDoctorButton(javafx.scene.input.MouseEvent event) {
        Controller.LoginPageController.playSound("C:\\Users\\Ali Tahoon\\Documents\\NetBeansProjects\\HealthCareSystem\\src\\Sounds\\operation.mp3");
        try {
            hanldeAnimationPane();
            AnchorPane main = FXMLLoader.load(getClass().getResource("/Views/doctorView.fxml"));
            inputPane.getChildren().setAll(main);
//            FadeTransition f=new FadeTransition(Duration.seconds(0.5), main);
//            f.setFromValue(0);
//            f.setToValue(1);
//            f.play();

        } catch (IOException ex) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText(ex.getMessage());
            alert.showAndWait();
        }
    }
      @FXML
    private void handleAddDoctorButton(javafx.scene.input.MouseEvent event) {
        Controller.LoginPageController.playSound("C:\\Users\\Ali Tahoon\\Documents\\NetBeansProjects\\HealthCareSystem\\src\\Sounds\\operation.mp3");
        try {
            hanldeAnimationPane();
            AnchorPane main = FXMLLoader.load(getClass().getResource("/Views/DoctorAdd.fxml"));
            inputPane.getChildren().setAll(main);
//            FadeTransition f=new FadeTransition(Duration.seconds(0.5), main);
//            f.setFromValue(0);
//            f.setToValue(1);
//            f.play();

        } catch (IOException ex) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText(ex.getMessage());
            alert.showAndWait();
        }
    }
         @FXML
    private void handleDeleteDoctorButton(javafx.scene.input.MouseEvent event) {
        Controller.LoginPageController.playSound("C:\\Users\\Ali Tahoon\\Documents\\NetBeansProjects\\HealthCareSystem\\src\\Sounds\\operation.mp3");
        try {
            hanldeAnimationPane();
            AnchorPane main = FXMLLoader.load(getClass().getResource("/Views/doctorDelete.fxml"));
            inputPane.getChildren().setAll(main);
//            FadeTransition f=new FadeTransition(Duration.seconds(0.5), main);
//            f.setFromValue(0);
//            f.setToValue(1);
//            f.play();

        } catch (IOException ex) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText(ex.getMessage());
            alert.showAndWait();
        }
    }
    
          @FXML
    private void handleUpdateDoctorButton(javafx.scene.input.MouseEvent event) {
        Controller.LoginPageController.playSound("C:\\Users\\Ali Tahoon\\Documents\\NetBeansProjects\\HealthCareSystem\\src\\Sounds\\operation.mp3");
        try {
            hanldeAnimationPane();
            AnchorPane main = FXMLLoader.load(getClass().getResource("/Views/doctorUpdate.fxml"));
            inputPane.getChildren().setAll(main);
//            FadeTransition f=new FadeTransition(Duration.seconds(0.5), main);
//            f.setFromValue(0);
//            f.setToValue(1);
//            f.play();

        } catch (IOException ex) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText(ex.getMessage());
            alert.showAndWait();
        }
    }
        @FXML
    private void handleViewnursesButton(javafx.scene.input.MouseEvent event) {
        Controller.LoginPageController.playSound("C:\\Users\\Ali Tahoon\\Documents\\NetBeansProjects\\HealthCareSystem\\src\\Sounds\\operation.mp3");
        try {
            hanldeAnimationPane();
            AnchorPane main = FXMLLoader.load(getClass().getResource("/Views/nursesView.fxml"));
            inputPane.getChildren().setAll(main);
//            FadeTransition f=new FadeTransition(Duration.seconds(0.5), main);
//            f.setFromValue(0);
//            f.setToValue(1);
//            f.play();

        } catch (IOException ex) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText(ex.getMessage());
            alert.showAndWait();
        }
    }
      @FXML
    private void handleAddnursesButton(javafx.scene.input.MouseEvent event) {
        Controller.LoginPageController.playSound("C:\\Users\\Ali Tahoon\\Documents\\NetBeansProjects\\HealthCareSystem\\src\\Sounds\\operation.mp3");
        try {
            hanldeAnimationPane();
            AnchorPane main = FXMLLoader.load(getClass().getResource("/Views/nursesAdd.fxml"));
            inputPane.getChildren().setAll(main);
//            FadeTransition f=new FadeTransition(Duration.seconds(0.5), main);
//            f.setFromValue(0);
//            f.setToValue(1);
//            f.play();

        } catch (IOException ex) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText(ex.getMessage());
            alert.showAndWait();
        }
    }
         @FXML
    private void handleDeletenursesButton(javafx.scene.input.MouseEvent event) {
        Controller.LoginPageController.playSound("C:\\Users\\Ali Tahoon\\Documents\\NetBeansProjects\\HealthCareSystem\\src\\Sounds\\operation.mp3");
        try {
            hanldeAnimationPane();
            AnchorPane main = FXMLLoader.load(getClass().getResource("/Views/nursesDelete.fxml"));
            inputPane.getChildren().setAll(main);
//            FadeTransition f=new FadeTransition(Duration.seconds(0.5), main);
//            f.setFromValue(0);
//            f.setToValue(1);
//            f.play();

        } catch (IOException ex) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText(ex.getMessage());
            alert.showAndWait();
        }
    }
    
          @FXML
    private void handleUpdatenursesButton(javafx.scene.input.MouseEvent event) {
        Controller.LoginPageController.playSound("C:\\Users\\Ali Tahoon\\Documents\\NetBeansProjects\\HealthCareSystem\\src\\Sounds\\operation.mp3");
        try {
            hanldeAnimationPane();
            AnchorPane main = FXMLLoader.load(getClass().getResource("/Views/nursesUpdate.fxml"));
            inputPane.getChildren().setAll(main);
//            FadeTransition f=new FadeTransition(Duration.seconds(0.5), main);
//            f.setFromValue(0);
//            f.setToValue(1);
//            f.play();

        } catch (IOException ex) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText(ex.getMessage());
            alert.showAndWait();
        }
    }
    
    @FXML
    private void handleBackToLoginPage(javafx.scene.input.MouseEvent event) {
        hanldeAnimationPane();
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/Views/Login.fxml"));

            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.initStyle(StageStyle.UNDECORATED);
            stage.show();
            ((Stage) (((Button) event.getSource()).getScene().getWindow())).close();

        } catch (IOException ex) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText(ex.getMessage());
            alert.showAndWait();
        }

    }

    @FXML
    private void getClose(javafx.scene.input.MouseEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }

    @FXML
    private void handlePaneMouseEnter(javafx.scene.input.MouseEvent event) {
        pan.setMaxWidth(30);
    }

    @FXML
    private void handlePaneMouseReleased(javafx.scene.input.MouseEvent event) {
        pan.setMaxWidth(15);
    }

    @FXML
    private void Released(javafx.scene.input.MouseEvent event) {

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

}
