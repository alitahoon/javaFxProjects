/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Database;

import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.control.Alert;

public class dbconnect {

    public dbconnect() {
    }

    private static final String username = "root";
    private static final String password = "";
    private static final String host = "127.0.0.1";
    private static final String port = "3306";
    private static final String dbName = "healthycaresystemdata";
    public static Connection con;

    //get Connection to Database
    public static void getConnect() {
        try {
            con = DriverManager.getConnection("jdbc:mysql://" + host + ":" + port + "/" + dbName, username, password);
        } catch (SQLException ex) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText(ex.getMessage());
            alert.showAndWait();
        }
    }

    //check user Login information
    public static boolean checkInfo(String username, String password) {
        getConnect();
        try {
            Statement stat = con.createStatement();
            ResultSet result = stat.executeQuery("select *from Users");
            while (result.next()) {
                if (result.getString("username").equals(username) && result.getString("pass").equals(password)) {
                    return true;
                } else {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setHeaderText(null);
                    alert.setContentText("Password or Username is wrong !");
                    alert.showAndWait();
                }
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
        return false;

    }

    //Add new user to databasr
    public void AddUser(String newUsername, String newPassword, String adminUsername, String adminPassword) {

    }

    //Check if Admin
    public static boolean isAdmin(String username, String password) {
        getConnect();
        try {
            Statement stat = con.createStatement();
            ResultSet result = stat.executeQuery("select *from admins");
            while (result.next()) {
                if (result.getString("username").equals(username) && result.getString("pass").equals(password)) {
                    return true;
                } else {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setHeaderText(null);
                    alert.setContentText("You are not admin !");
                    alert.showAndWait();
                }
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
        return false;
    }

    //insert data into Database
    public static void insertData(String tableName, String query) {
        try {
            getConnect();
            Statement stat = con.createStatement();
            stat.execute(query);
        } catch (SQLException ex) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText(ex.getMessage());
            alert.showAndWait();
        }
    }

    //insert data into Database
    public static void excuteQueryOndatabase(String query) {
        try {
            getConnect();
            Statement stat = con.createStatement();
            stat.execute(query);
        } catch (SQLException ex) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText(ex.getMessage());
            alert.showAndWait();
        }
    }

    //Get data from table
    public static ResultSet getAllData(String tableName) {
        ResultSet r = null;

        try {
            getConnect();
            Statement stat = con.createStatement();
            r = stat.executeQuery("select *from " + tableName);
            return r;

        } catch (SQLException ex) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText(ex.getMessage());
            alert.showAndWait();
            return r;
        }
    }

    //auto increase id values
    public static String autoNumber(String tableName, String columnName) {
        try {
            getConnect();
            Statement stat = con.createStatement();
            String autonum = "select max(" + columnName + ")+1 as autonum" + " from " + tableName;
            stat.executeQuery(autonum);
            String num = "";
            while (stat.getResultSet().next()) {
                num = stat.getResultSet().getString("autonum");
            }
            con.close();
            if (num == null || "".equals(num)) {
                return "1";
            } else {
                return num;
            }
        } catch (SQLException ex) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText(ex.getMessage());
            alert.showAndWait();
            return "0";
        }
    }

    //get record from database
    public static ResultSet getRecord(String tableName, String id,String idColumanName) {
        ResultSet r = null;
        try {
            getConnect();
            Statement stat = con.createStatement();
            String query = "select *from " + tableName + " where " +idColumanName+ " = " + id;
            r = stat.executeQuery(query);
            return r;
        } catch (SQLException ex) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText(ex.getMessage());
            alert.showAndWait();
        }
        return r;
    }

    //Delete record from database
    public static void deleteRecord(String tableName, String id,String idColumnName) {
        try {
            getConnect();
            Statement stat = con.createStatement();
            String query = "delete from " + tableName + " where "+ idColumnName+ " = " + id;
            stat.execute(query);
        } catch (SQLException ex) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText(ex.getMessage());
            alert.showAndWait();
        }
    }

    //get max Value in column
    public static String getMaxValue(String tableName, String columnName) {
        String maxNum = "";
        try {
            ResultSet r;
            getConnect();
            Statement stat = con.createStatement();
            String query = "select max(" + columnName + ")" + " as maxNum" + " from " + tableName;
            r = stat.executeQuery(query);
            while (r.next()) {
                maxNum = r.getString("maxNum");
                return maxNum;
            }
        } catch (SQLException ex) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText(ex.getMessage());
            alert.showAndWait();
        }
        return maxNum;
    }
      //get total Values in column
    public static Double getTotalValues(String tableName, String columnName, String idColumnName) {
        Double total = 0.0;
        ResultSet r;
        try {
            int max = Integer.parseInt(getMaxValue(tableName, idColumnName));
            for (int i = 0; i <= max; i++) {
                r = getRecord(tableName, String.valueOf(i),idColumnName);
                while (r.next()) {
                    total = total + Double.parseDouble(r.getString(columnName));
                }
            }
            return total;
        } catch (SQLException ex) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText(ex.getMessage());
            alert.showAndWait();
        }
        return total;
    }

    //get one columan
    public static ArrayList getOneColuman(String tableName, String columanName) {
        ArrayList arr = new ArrayList();
        try {
            getConnect();
            Statement stat = con.createStatement();
            ResultSet r = stat.executeQuery("select " + columanName + " from " + tableName);
            while (r.next()) {
                arr.add(r.getString(columanName));
            }
            return arr;
        } catch (SQLException ex) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText(ex.getMessage());
            alert.showAndWait();
        }
        return arr;
    }

    //get id by name
    public static String getId(String name,String tableName,String columnName,String nameIDcoluman) {
        String id = "0";
     
        try {
            getConnect();
            Statement stat = con.createStatement();
            ResultSet r=stat.executeQuery("select "+nameIDcoluman+" from "+tableName+" where "+columnName +" = "+" '"+name+"'");
            while (r.next()) {
                id=r.getString(nameIDcoluman);
            }
            return id;
        } catch (SQLException ex) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText(ex.getMessage());
            alert.showAndWait();
        }
        return id;
    }
    //get name by id
    public static String getName(String id,String tableName,String columnName,String nameIDcoluman) {
     
        try {
            getConnect();
            Statement stat = con.createStatement();
            ResultSet r=stat.executeQuery("select "+columnName+" from "+tableName+" where "+nameIDcoluman +" = "+" '"+id+"'");
            while (r.next()) {
                id=r.getString(columnName);
            }
            return id;
        } catch (SQLException ex) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText(ex.getMessage());
            alert.showAndWait();
        }
        return id;
    }
    //get Avagrge Values at column
    public static Double getAvagrge(String tableName,String columnName,String idColumanName) {
        Double avg=0.0;
        avg= (getTotalValues(tableName, columnName, idColumanName)/Integer.parseInt(getMaxValue(tableName, idColumanName)));
        return avg;
    }
}
