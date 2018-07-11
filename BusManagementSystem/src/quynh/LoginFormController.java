package quynh;

import data.User;
import data.ConnectSQLServer;
import java.io.IOException;
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
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class LoginFormController implements Initializable {

    private User currentUser;
    private Stage stage;
    private BusManagementSystemModel model;
    private static Connection connection;
    private static Statement statement;

    public User getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(User currentUser) {
        this.currentUser = currentUser;
    }
    
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

            model = new BusManagementSystemModel();

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

                // eat up the close event
                e.consume();
            }
        });

    }

    @FXML
    private void handleButtonSignIn(ActionEvent event) throws SQLException, IOException {

//        String ID = textfieldID.getText();
//        String pass = textfieldPass.getText();
        int ID = Integer.parseInt("991486726");
        String pass = "password";

        model.getUserDatabase(connection);
//        ID.equals("") || pass.equals("")
        if (ID == 0) {

            Alert alert = new Alert(Alert.AlertType.INFORMATION, "Please enter your ID and Password!");
            Optional<ButtonType> result = alert.showAndWait();

        } else {

            // Check the ID and password
            if (model.isUser(ID, pass) == true) {
                model.getBusDatabase(connection);
                System.out.println("Sign in");

                // Close LogIn Form
                this.stage.close();

                // Load scence graph from fxml
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("UserForm.fxml"));
                Parent root = (Parent) fxmlLoader.load();

                // Create and show about window as modal     
                Scene scene = new Scene(root);
                Stage stage = new Stage();
                stage.initModality(Modality.APPLICATION_MODAL);
                stage.setTitle("Welcome to Sheridan Bus Management System");
                // Set icon
                stage.getIcons().add(new Image(BusManagementSystem.class.getResourceAsStream("/data/bus.png")));
                stage.setScene(scene);

                // Remember the stage, so can close it later     
                UserFormController ctrlUserForm = fxmlLoader.getController();
                ctrlUserForm.setStage(stage);
                ctrlUserForm.setModel(model);
                
                // wait user response within this block
                stage.showAndWait();

            } else {
                System.out.println("Try again");
                Alert alert = new Alert(Alert.AlertType.INFORMATION, "ID/Password is not correct!");
                Optional<ButtonType> result = alert.showAndWait();
            }

        }

    }

}
