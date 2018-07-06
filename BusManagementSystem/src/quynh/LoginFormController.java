package quynh;

import data.User;
import data.ConnectSQLServer;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;

public class LoginFormController implements Initializable {

    private BusManagementSystemModel busManagementModel;
    private static Connection connection;
    private static Statement statement;
    private ArrayList<User> admins;

    @FXML
    private TextField textfieldID;
    @FXML
    private TextField textfieldPass;
    @FXML
    private Button buttonSignIn;
    @FXML
    private Label labelConnection;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        // Connection to database
        try {
            connection = ConnectSQLServer.getAutoConnection();

            // Prepare and create statement
            statement = connection.createStatement();

            // Success connection
            labelConnection.setText("Connection successfully!");

            busManagementModel = new BusManagementSystemModel();
        } catch (SQLException e) {
            labelConnection.setText("Connection failure!");
        }
    }

    @FXML
    private void handleButtonSignIn(ActionEvent event) throws SQLException {

        String ID = textfieldID.getText();
        String pass = textfieldPass.getText();

        busManagementModel.getUserDatabase(connection);

        // Check the ID and password
        if (busManagementModel.isUser(ID, pass) == true) {
            System.out.println("Sign in");
        } else {
            System.out.println("Try again");
        }

    }

}
