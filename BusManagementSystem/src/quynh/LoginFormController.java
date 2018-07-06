package quynh;

import data.User;
import data.ConnectSQLServer;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;

public class LoginFormController implements Initializable {

    private Stage stage;
    private BusManagementSystemModel busManagementModel;
    private static Connection connection;
    private static Statement statement;

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

    public void setStage(Stage stage) {

        this.stage = stage;
        stage.setResizable(false);
        stage.setOnCloseRequest(e -> {

            // prompt user to confirm
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Do you want to close?");
            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK) {

                // User click OK, close the application
                Platform.exit();
            } else {

                // eat tup the close event
                e.consume();
            }
        });

    }

    @FXML
    private void handleButtonSignIn(ActionEvent event) throws SQLException {

        String ID = textfieldID.getText();
        String pass = textfieldPass.getText();

        busManagementModel.getUserDatabase(connection);

        if (ID.equals("") || pass.equals("")) {
            
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "Please enter your ID and Password!");
            Optional<ButtonType> result = alert.showAndWait();

        } else {

            // Check the ID and password
            if (busManagementModel.isUser(ID, pass) == true) {
                System.out.println("Sign in");
            } else {
                System.out.println("Try again");
                Alert alert = new Alert(Alert.AlertType.INFORMATION, "ID/Password is not correct!");
                Optional<ButtonType> result = alert.showAndWait();
            }

        }

    }

}
