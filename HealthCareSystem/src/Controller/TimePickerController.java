/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * FXML Controller class
 *
 * @author Ali Tahoon
 */
public class TimePickerController implements Initializable {

    private int hou = 0, min = 0;
    public static String time = "", local = "", HO = " ", MI = " ";

    @FXML
    private Text AM;

    @FXML
    private Text PM;

    @FXML
    private Text ho;

    @FXML
    private AnchorPane mainPane;

    @FXML
    private Text mi;

    @FXML
    private Pane p1;

    @FXML
    private Pane p10;

    @FXML
    private Pane p11;

    @FXML
    private Pane p12;

    @FXML
    private Pane p2;

    @FXML
    private Pane p3;

    @FXML
    private Pane p4;

    @FXML
    private Pane p5;

    @FXML
    private Pane p6;

    @FXML
    private Pane p7;

    @FXML
    private Pane p8;

    @FXML
    private Pane p9;

    @FXML
    private void setLocalAM() {
        local = "AM";
        AM.setStyle("-fx-fill:#423d53;");
        PM.setStyle("-fx-fill:white;");
        mi.setStyle("-fx-fill:white;");
        ho.setStyle("-fx-fill:white;");
        local = "AM";

    }

    @FXML
    private void setLocalPM() {
        local = "PM";
        AM.setStyle("-fx-fill:white;");
        PM.setStyle("-fx-fill:#423d53;");
        mi.setStyle("-fx-fill:white;");
        ho.setStyle("-fx-fill:white;");
        local = "PM";
    }

    @FXML
    public  void setTime(javafx.scene.input.MouseEvent event) {
        time= HO + ":" + MI + " " + local;
        TranslateTransition moveAdminComponent = new TranslateTransition(Duration.seconds(0.3), mainPane);
        moveAdminComponent.setToX(-840);
        moveAdminComponent.play();
    }
    
    
    public static void gettime(String timeKind) {
        if("timeForOpen".equals(timeKind)){
            clinicsAddController.timeForOpen=time;
        }else if("timeForClose".equals(timeKind)){
            clinicsAddController.timeForClose=time;
        }
    }
    @FXML
    private void SethoursFlag() {
        ho.setStyle("-fx-fill:#2f2b43;");
        mi.setStyle("-fx-fill:white;");
        hou = 1;
        min = 0;
    }

    @FXML
    private void SetminutesFlag() {
        mi.setStyle("-fx-fill:#2f2b43;");
        ho.setStyle("-fx-fill:white;");
        min = 1;
        hou = 0;
    }

    @FXML
    private void click1() {
        if (hou == 1) {
            ho.setText("1");
            p1.setStyle("-fx-fill:#Fa2C56;-fx-background-radius:30px");
            HO = "1";
        } else if (min == 1) {
            mi.setText("5");
            p1.setStyle("-fx-background-color:#Fa2C56;-fx-background-radius:30px");
            MI = "5";
        }
    }

    @FXML
    private void click2() {
        if (hou == 1) {
            ho.setText("2");
            p2.setStyle("-fx-background-color:#Fa2C56;-fx-background-radius:30px");
            HO = "2";
        } else if (min == 1) {
            mi.setText("10");
            p2.setStyle("-fx-background-color:#Fa2C56;-fx-background-radius:30px");
            MI = "10";
        }
    }

    @FXML
    private void click3() {
        if (hou == 1) {
            ho.setText("3");
            p3.setStyle("-fx-background-color:#Fa2C56;-fx-background-radius:30px");
            HO = "3";
        } else if (min == 1) {
            mi.setText("15");
            p3.setStyle("-fx-background-color:#Fa2C56;-fx-background-radius:30px");
            MI = "15";
        }
    }

    @FXML
    private void click4() {
        if (hou == 1) {
            ho.setText("4");
            p4.setStyle("-fx-background-color:#Fa2C56;-fx-background-radius:30px");
            HO = "4";
        } else if (min == 1) {
            mi.setText("20");
            p4.setStyle("-fx-background-color:#Fa2C56;-fx-background-radius:30px");
            MI = "20";
        }
    }

    @FXML
    private void click5() {
        if (hou == 1) {
            ho.setText("5");
            p5.setStyle("-fx-background-color:#Fa2C56;-fx-background-radius:30px");
            HO = "5";
        } else if (min == 1) {
            mi.setText("25");
            p5.setStyle("-fx-background-color:#Fa2C56;-fx-background-radius:30px");
            MI = "25";
        }
    }

    @FXML
    private void click6() {
        if (hou == 1) {
            ho.setText("6");
            p6.setStyle("-fx-background-color:#Fa2C56;-fx-background-radius:30px");
            HO = "6";
        } else if (min == 1) {
            mi.setText("30");
            p6.setStyle("-fx-background-color:#Fa2C56;-fx-background-radius:30px");
            MI = "30";
        }
    }

    @FXML
    private void click7() {
        if (hou == 1) {
            ho.setText("7");
            p7.setStyle("-fx-background-color:#Fa2C56;-fx-background-radius:30px");
            HO = "7";
        } else if (min == 1) {
            mi.setText("35");
            p7.setStyle("-fx-background-color:#Fa2C56;-fx-background-radius:30px");
            MI = "35";
        }
    }

    @FXML
    private void click8() {
        if (hou == 1) {
            ho.setText("8");
            p8.setStyle("-fx-background-color:#Fa2C56;-fx-background-radius:30px");
            HO = "8";
        } else if (min == 1) {
            mi.setText("40");
            p8.setStyle("-fx-background-color:#Fa2C56;-fx-background-radius:30px");
            MI = "40";
        }
    }

    @FXML
    private void click9() {
        if (hou == 1) {
            ho.setText("9");
            p9.setStyle("-fx-background-color:#Fa2C56;-fx-background-radius:30px");
            HO = "9";
        } else if (min == 1) {
            mi.setText("45");
            p9.setStyle("-fx-background-color:#Fa2C56;-fx-background-radius:30px");
            MI = "45";
        }
    }

    @FXML
    private void click10() {
        if (hou == 1) {
            ho.setText("10");
            p10.setStyle("-fx-background-color:#Fa2C56;-fx-background-radius:30px");
            HO = "10";
        } else if (min == 1) {
            mi.setText("50");
            p10.setStyle("-fx-background-color:#Fa2C56;-fx-background-radius:30px");
            MI = "50";
        }
    }

    @FXML
    private void click11() {
        if (hou == 1) {
            ho.setText("11");
            p11.setStyle("-fx-background-color:#Fa2C56;-fx-background-radius:30px");
            HO = "11";
        } else if (min == 1) {
            mi.setText("55");
            p11.setStyle("-fx-background-color:#Fa2C56;-fx-background-radius:30px");
            MI = "55";
        }
    }

    @FXML
    private void click12() {
        if (hou == 1) {
            ho.setText("12");
            p12.setStyle("-fx-background-color:#Fa2C56;-fx-background-radius:30px");
            HO = "12";
        } else if (min == 1) {
            mi.setText("60");
            p12.setStyle("-fx-background-color:#Fa2C56;-fx-background-radius:30px");
            MI = "60";

        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        TranslateTransition moveAdminComponent = new TranslateTransition(Duration.seconds(0.3), mainPane);
        moveAdminComponent.setToX(840);
        moveAdminComponent.play();
    }

}
