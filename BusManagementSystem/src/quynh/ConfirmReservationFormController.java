package quynh;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class ConfirmReservationFormController implements Initializable {
    
    private Stage stage = new Stage();
    private BusManagementSystemModel model;
    
    @FXML
    private Label labelDeparture;
    @FXML
    private Label labelDestination;
    @FXML
    private Label labelDate;
    @FXML
    private Label labelTime;
    @FXML
    private Label labelBusNum;
    @FXML
    private Label labelSeat;
    @FXML
    private Button buttonConfirm;
    @FXML
    private Button buttonCancel;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        model = new BusManagementSystemModel();
        
    }
    
    public void setStage(Stage stage) {
        this.stage = stage;
        stage.setResizable(false);
        stage.setOnCloseRequest(e -> {
                stage.close();
        });
    }

    @FXML
    private void handleButtonConfirm(ActionEvent event) {
        stage.close();
        model.reserveToDatabase();
    }

    @FXML
    private void handleButtonCancel(ActionEvent event) {
        stage.close();
    }
    
}
