/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import static Controller.nursesAddController.timeKind;
import Models.nurses;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.animation.TranslateTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.util.Duration;
import Models.nurses;

/**
 * FXML Controller class
 *
 * @author Ali Tahoon
 */
public class nursesUpdateController implements Initializable {

    @FXML
    private Button btnClear;

    @FXML
    private Button btnUpdate;

    @FXML
    private AnchorPane childPane;

    @FXML
    private AnchorPane childPane1;


    @FXML
    private ComboBox<String> cmpDepartment;

    @FXML
    private DatePicker datBirhdate;

    @FXML
    private AnchorPane departmentPane;

    @FXML
    private RadioButton rdFemale;

    @FXML
    private RadioButton rdMale;

    @FXML
    private TableColumn<nurses, String> tblAddress;

    @FXML
    private TableColumn<nurses, String> tblDepartment;

    @FXML
    private TableView<nurses> tblDoctorInfo;

    @FXML
    private TableColumn<nurses, String> tblGender;

    @FXML
    private TableColumn<nurses, Integer> tblID;

    @FXML
    private TableColumn<nurses, String> tblName;

    @FXML
    private TableColumn<nurses, Double> tblSalary;

    @FXML
    private TableColumn<nurses, String> tblShiftEnd;

    @FXML
    private TableColumn<nurses, String> tblShiftStart;

    @FXML
    private TableColumn<nurses, String> tblbirhDate;

    @FXML
    private TableColumn<nurses, String> tblgrade;

    @FXML
    private TextField txtAddress;

    @FXML
    private TextField txtGrade;

    @FXML
    private TextField txtID;

    @FXML
    private TextField txtName;

    @FXML
    private TextField txtSalary;

    @FXML
    private TextField txtShiftEnd;

    @FXML
    private TextField txtShiftStart;

    nurses nurModel = null;
    ObservableList<nurses> nurses = FXCollections.observableArrayList();
    ObservableList<String> cliListForCMPDep = FXCollections.observableArrayList(Database.dbconnect.getOneColuman("department", "depName"));

    private RadioButton getSelectedRadioButton(RadioButton male, RadioButton female) {
        if (male.isSelected()) {
            return male;
        } else if (female.isSelected()) {
            return female;
        } else {
            return male;
        }
    }

    @FXML
    private void handelViewData() {
        nurses.clear();
        loadData();
        try {
            ResultSet r = Database.dbconnect.getAllData("nursesData");
            while (r.next()) {
                nurModel = new nurses(Integer.parseInt(r.getString("nurID")), r.getString("nurName"), Double.parseDouble(r.getString("nurSalary")),
                        r.getString("nurShiftStart"), r.getString("nurShiftEnd"), r.getString("depName"), 
                        r.getString("nurGender"), r.getString("nurBirthdate"), r.getString("nurGrade"), r.getString("nurAddress"));
                nurses.add(nurModel);
                tblDoctorInfo.setItems(nurses);

            }
        } catch (SQLException ex) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText(ex.getMessage());
            alert.showAndWait();
        }

    }

    @FXML
    private void clearText() {
        txtShiftStart.setText("");
        txtShiftEnd.setText("");
        txtSalary.setText("");
        txtGrade.setText("");
        txtName.setText("");
        txtAddress.setText("");
        rdFemale.setSelected(false);
        rdMale.setSelected(false);
        cmpDepartment.setValue(null);
        datBirhdate.setValue(null);
        txtName.requestFocus();
    }

    @FXML
    private void getTimePickerForStartShift() {
        mainPane.setVisible(true);
        TranslateTransition moveAdminComponent = new TranslateTransition(Duration.seconds(0.3), mainPane);
        moveAdminComponent.setToX(690);
        moveAdminComponent.play();
        timeKind = "open";
    }

    @FXML
    private void getTimePickerForEndShift() {
        mainPane.setVisible(true);
        TranslateTransition moveAdminComponent = new TranslateTransition(Duration.seconds(0.3), mainPane);
        moveAdminComponent.setToX(1065);
        moveAdminComponent.play();
        timeKind = "close";
    }

    @FXML
    private void handleUpdateButton(javafx.scene.input.MouseEvent event) {
        String query = "UPDATE `nurses` SET "
                + "`nurName`='" + txtName.getText() + "',`nurGender`= '" + getSelectedRadioButton(rdMale, rdFemale).getText()
                + "',`nurGrade`=' " + txtGrade.getText() + "',`nurAddress`=' " + txtAddress.getText() + "',`nurShiftStart`=' " + txtShiftStart.getText() + "',`nurShiftEnd`=' " + txtShiftEnd.getText()
                + "',`nurBirthdate`=' " + datBirhdate.getValue() + "',`nurSalary`=' " + txtSalary.getText() + "',`nurDepID`= "
                + Database.dbconnect.getId(cmpDepartment.getValue(), "department", "depName", "depID") ;
        Database.dbconnect.excuteQueryOndatabase(query);
        handelViewData();
        clearText();
    }

    @FXML
    private void handleLoadData(javafx.scene.input.MouseEvent event) {
        nurModel = tblDoctorInfo.getSelectionModel().getSelectedItem();
        txtName.setText(nurModel.getName());
        txtID.setText(String.valueOf(nurModel.getId()));
        txtAddress.setText(nurModel.getAddress());
        txtSalary.setText(String.valueOf(nurModel.getSalary()));
        txtShiftEnd.setText(nurModel.getShiftEnd());
        txtShiftStart.setText(nurModel.getShiftStart());
        txtGrade.setText(nurModel.getGrade());
        if (nurModel.getGender().equals("Male")) {
            rdMale.setSelected(true);
        } else if (nurModel.getGender().equals("Female")) {
            rdFemale.setSelected(true);
        }
        cmpDepartment.setValue(nurModel.getDepName());
        LocalDate l = LocalDate.parse(nurModel.getBirthdate());
        System.out.println(l);
        datBirhdate.setValue(l);
    }

    private void loadData() {
        tblName.setCellValueFactory(new PropertyValueFactory<>("name"));
        tblShiftEnd.setCellValueFactory(new PropertyValueFactory<>("shiftEnd"));
        tblShiftStart.setCellValueFactory(new PropertyValueFactory<>("shiftStart"));
        tblAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        tblGender.setCellValueFactory(new PropertyValueFactory<>("gender"));
        tblSalary.setCellValueFactory(new PropertyValueFactory<>("salary"));
        tblbirhDate.setCellValueFactory(new PropertyValueFactory<>("Birthdate"));
        tblID.setCellValueFactory(new PropertyValueFactory<>("id"));
        tblDepartment.setCellValueFactory(new PropertyValueFactory<>("depName"));
        tblgrade.setCellValueFactory(new PropertyValueFactory<>("grade"));

    }

    @FXML
    private void handleChooseGenderMale() {
        if (rdMale.isSelected()) {
            rdFemale.setSelected(false);
        }
    }

    @FXML
    private void handleChooseGenderFemale() {
        if (rdFemale.isSelected()) {
            rdMale.setSelected(false);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        handelViewData();
        mainPane.setVisible(false);
        cmpDepartment.getItems().addAll(cliListForCMPDep);
        TranslateTransition movePane = new TranslateTransition(Duration.seconds(0.5), childPane);
        movePane.setToX(1245);
        movePane.play();
    }

    // start Time Picker Handle
    private int hou = 0, min = 0;
    public static String local = "", HO = " ", MI = " ", timeKind = "";

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
    public void setTime(javafx.scene.input.MouseEvent event) {
        OpenORClose(timeKind);
        mainPane.setVisible(false);

    }

    private void OpenORClose(String timeKind) {
        if ("open".equals(timeKind)) {
            txtShiftStart.setText(HO + ":" + MI + " " + local);
            TranslateTransition moveAdminComponent = new TranslateTransition(Duration.seconds(0.3), mainPane);
            moveAdminComponent.setToX(-690);
            moveAdminComponent.play();
            clearPicker();
        } else if ("close".equals(timeKind)) {
            txtShiftEnd.setText(HO + ":" + MI + " " + local);
            TranslateTransition moveAdminComponent = new TranslateTransition(Duration.seconds(0.3), mainPane);
            moveAdminComponent.setToX(-1065);
            moveAdminComponent.play();
            clearPicker();
        }
        System.out.println("Controller.clinicsAddController.OpenORClose()");
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
            mi.setText("00");
            p12.setStyle("-fx-background-color:#Fa2C56;-fx-background-radius:30px");
            MI = "00";

        }
    }

    private void clearPicker() {
        local = "";
        HO = " ";
        MI = " ";
        timeKind = "";
        ho.setText("00");
        mi.setText("00");
        ho.setStyle("-fx-fill:white;");
        mi.setStyle("-fx-fill:white;");
        AM.setStyle("-fx-fill:white;");
        PM.setStyle("-fx-fill;white");
        p1.setStyle("-fx-background-color:#none");
        p2.setStyle("-fx-background-color:#none");
        p3.setStyle("-fx-background-color:#none");
        p4.setStyle("-fx-background-color:#none");
        p5.setStyle("-fx-background-color:#none");
        p6.setStyle("-fx-background-color:#none");
        p7.setStyle("-fx-background-color:#none");
        p8.setStyle("-fx-background-color:#none");
        p9.setStyle("-fx-background-color:#none");
        p10.setStyle("-fx-background-color:#none");
        p11.setStyle("-fx-background-color:#none");
        p12.setStyle("-fx-background-color:#none");
    }
    //End Time Picker Handle

}
