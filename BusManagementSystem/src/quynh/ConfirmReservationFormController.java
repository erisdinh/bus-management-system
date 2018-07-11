package quynh;

import data.BusReservation;
import data.ConnectSQLServer;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.Time;
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

    private Connection connection;
    private Stage stage = new Stage();
    private BusManagementSystemModel model = new BusManagementSystemModel();
    private BusReservation newBusReservation;

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
        showReservationInfo();
    }

    @FXML
    private void handleButtonConfirm(ActionEvent event) {

        // Current Date
        java.util.Date utilDate = new java.util.Date();
        Date currentDate = new Date(utilDate.getTime());
        Time currentTime = new Time(utilDate.getTime());

        model.getNewBusReservation().setUserResDate(currentDate);
        model.getNewBusReservation().setUserResTime(currentTime);
        boolean reserved = model.putReservationToDatabase(connection);
        if (reserved == false) {

        } else {
            stage.close();
        }
    }

    @FXML
    private void handleButtonCancel(ActionEvent event) {
        stage.close();
    }

    public void showReservationInfo() {
        labelDeparture.setText(model.getNewBusReservation().getDeparture());
        labelDestination.setText(model.getNewBusReservation().getDestination());
        labelDate.setText(model.getNewBusReservation().getBusResDate().toString());
        labelTime.setText(model.getNewBusReservation().getBusResTime().toString());
        labelBusNum.setText(Integer.toString(model.getNewBusReservation().getBusNum()));
        labelSeat.setText(model.getNewBusReservation().getSeat());

    }

}
