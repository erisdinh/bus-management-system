package quynh;

import data.ConnectSQLServer;
import java.net.URL;
import java.sql.Connection;
import java.sql.Statement;
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

    @FXML
    private ToggleGroup accountType;
    @FXML
    private TextField textfieldID;
    @FXML
    private TextField textfieldPass;
    @FXML
    private RadioButton radioAdmin;
    @FXML
    private RadioButton radioUser;
    @FXML
    private Button buttonCancel;
    @FXML
    private Button buttonSignIn;
    @FXML
    private Label labelConnection;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // Connection to database

        try {
            Connection connection = ConnectSQLServer.getAutoConnection();
            labelConnection.setText("Connection successfully!");

            Statement statement = connection.createStatement();

        } catch (Exception e) {
            labelConnection.setText("Connection failure!");
        }

    }

}
