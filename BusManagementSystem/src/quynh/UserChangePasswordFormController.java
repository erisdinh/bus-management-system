package quynh;

import data.ConnectSQLServer;
import data.User;
import java.net.URL;
import java.sql.Connection;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.stage.Stage;

public class UserChangePasswordFormController implements Initializable {

    private Stage stage;
    private BusManagementSystemModel model;
    private Connection connection;

    @FXML
    private PasswordField textfieldNewPass;
    @FXML
    private PasswordField textfieldReNewPass;
    @FXML
    private Button buttonChange;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        connection = ConnectSQLServer.getAutoConnection();
    }

    public void setStage(Stage stage) {
        this.stage = stage;
        stage.setResizable(false);
        stage.setOnCloseRequest(e -> {
            stage.close();
        });
    }

    public void setModel(BusManagementSystemModel model) {
        this.model = model;
    }

    @FXML
    private void handleButtonChange(ActionEvent event) {

        // Compare new password and re new password
        String pass = textfieldNewPass.getText().trim();
        String rePass = textfieldReNewPass.getText().trim();

        if (pass.equals(rePass)) {

            model.getCurrentUser().setPass(pass);
            User user = model.getCurrentUser();
            boolean update = model.updateUserToDatabase(connection, user);

            if (update == false) {
                Alert alert = new Alert(Alert.AlertType.ERROR, "Cannot chagne your password!");
                alert.showAndWait();
            } else {
                Alert alert = new Alert(Alert.AlertType.INFORMATION, "Successgully changed password!");
                alert.showAndWait();
                this.stage.close();
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Password is not match!");
            alert.showAndWait();
        }
    }

}
