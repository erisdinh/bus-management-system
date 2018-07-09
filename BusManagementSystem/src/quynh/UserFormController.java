package quynh;

import data.Bus;
import data.ConnectSQLServer;
import java.net.URL;
import java.sql.Connection;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;

public class UserFormController implements Initializable {

    private Connection connection;
    private Stage stage;
    private BusManagementSystemModel model;
    private ArrayList<Bus> buses;
    private ArrayList<String> departure;
    private ArrayList<String> destination;
    private ArrayList<String> hours = new ArrayList<>();

    @FXML
    private ToggleButton A1;
    @FXML
    private ToggleGroup buttonSeat;
    @FXML
    private ToggleButton A2;
    @FXML
    private ToggleButton A3;
    @FXML
    private ToggleButton A4;
    @FXML
    private ToggleButton B1;
    @FXML
    private ToggleButton B2;
    @FXML
    private ToggleButton B3;
    @FXML
    private ToggleButton B4;
    @FXML
    private ToggleButton C1;
    @FXML
    private ToggleButton C2;
    @FXML
    private ToggleButton C3;
    @FXML
    private ToggleButton C4;
    @FXML
    private ToggleButton D1;
    @FXML
    private ToggleButton D2;
    @FXML
    private ToggleButton D3;
    @FXML
    private ToggleButton D4;
    @FXML
    private ToggleButton E1;
    @FXML
    private ToggleButton E2;
    @FXML
    private ToggleButton E3;
    @FXML
    private ToggleButton E4;
    @FXML
    private ToggleButton F1;
    @FXML
    private ToggleButton F2;
    @FXML
    private ToggleButton F3;
    @FXML
    private ToggleButton F4;
    @FXML
    private ToggleButton G1;
    @FXML
    private ToggleButton G2;
    @FXML
    private ToggleButton G3;
    @FXML
    private ToggleButton G4;
    @FXML
    private ToggleButton H1;
    @FXML
    private ToggleButton H2;
    @FXML
    private ToggleButton H3;
    @FXML
    private ToggleButton H4;
    @FXML
    private ToggleButton I1;
    @FXML
    private ToggleButton I2;
    @FXML
    private ToggleButton I3;
    @FXML
    private ToggleButton I4;
    @FXML
    private ToggleButton K1;
    @FXML
    private ToggleButton K2;
    @FXML
    private ToggleButton K3;
    @FXML
    private ToggleButton K4;
    @FXML
    private Label labelSeat;
    @FXML
    private ComboBox<Integer> comboBoxBusNum;
    @FXML
    private ComboBox<String> comboBoxDeparture;
    @FXML
    private ComboBox<String> comboBoxDestination;
    @FXML
    private DatePicker datePickerDateRes;
    @FXML
    private ComboBox<String> comboBoxBusTime;
    @FXML
    private Label labelBusType;
    @FXML
    private Label labelNumOfSeat;
    @FXML
    private Label labelNumOfAvaiSeat;
    @FXML
    private Button buttonSignOut;
    @FXML
    private Button buttonReserve;
    @FXML
    private Button buttonReset;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        connection = ConnectSQLServer.getAutoConnection();

        model = new BusManagementSystemModel();
        model.initializeBusHours();
        model.getBusDatabase(connection);

        ArrayList<Integer> busesNum = model.getBusesNum();

        // Display bus number list in comboBox
        comboBoxBusNum.setItems(FXCollections.observableList(busesNum));

        // Show Bus info after the user choose the bus number
        comboBoxBusNum.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e) {
                showBusInfo();
            }
        });

        // Add departure into DEPARTURE arraylist
        departure = new ArrayList<>();
        departure.add("Trafalgar");
        departure.add("HMC");
        departure.add("Davis");

        // Show departure list
        comboBoxDeparture.setItems(FXCollections.observableArrayList(departure));

        // Using lamda expression
        // Show Destination list fater changing comboxBox departure
        comboBoxDeparture.getSelectionModel().selectedItemProperty().addListener((ov, oldValue, newValue) -> {
            destination = new ArrayList<String>();
            if (comboBoxDeparture.equals("")) {
            } else {
                for (int i = 0; i < departure.size(); i++) {
                    String choseDeparture = comboBoxDeparture.getValue();
                    if (choseDeparture.equals(departure.get(i))) {

                    } else {
                        destination.add(departure.get(i));
                    }
                }
                comboBoxDestination.setItems(FXCollections.observableArrayList(destination));
            }

        });

        // Show bus hours list after changing comboBox Time
        comboBoxDestination.getSelectionModel().selectedItemProperty().addListener((ov, oldValue, newValue) -> {

            // Clear the selected items after the user changing the departure and destination
            comboBoxBusTime.setItems(null);
            String choseDeparture = comboBoxDeparture.getValue();
            String choseDestination = comboBoxDestination.getValue();
            if (choseDestination == null) {
            } else {
                showBusHours(choseDeparture, choseDestination);
            }
        });
    }

    public void setStage(Stage stage) {
        this.stage = stage;
        stage.setResizable(false);
        stage.setOnCloseRequest(e -> {

            // Prompt user to confirm
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Do you want to close?");
            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK) {

                // User click OK, close the application
                stage.close();
            } else {

                // Eat up the close event
                e.consume();
            }
        });
    }

    // A method to show the selected-bus-num Bus Info
    private void showBusInfo() {
        Bus bus = new Bus();

        int busNum = comboBoxBusNum.getValue();

        bus = model.getBusByBusNum(busNum);
        if (bus == null) {
            return;
        } else {
            labelBusType.setText(bus.getType());
            labelNumOfSeat.setText(Integer.toString(bus.getNumOfSeat()));
        }

    }

    // Show bus time list in comboBox Time
    private void showBusHours(String departure, String destination) {
        hours = new ArrayList<String>();

        // Get hours list depending on departure and destination
        if (departure.equals("Trafalgar")) {
            for (int i = 0; i < 8; i++) {
                hours.add(model.getNorthTraToHmcNo1().get(i));
                if (i < 7) {
                    hours.add(model.getNorthTraToHmcNo2().get(i));
                }
            }
        } else if (departure.equals("HMC")) {
            if (destination.equals("Davis")) {
                for (int i = 0; i < 8; i++) {
                    hours.add(model.getNorthHmcToDavisNo1().get(i));
                    if (i < 7) {
                        hours.add(model.getNorthHmcToDavisNo2().get(i));
                    }
                }
            } else {
                for (int i = 0; i < 8; i++) {
                    hours.add(model.getSouthHmcToTraNo2().get(i));
                    if (i < 7) {
                        hours.add(model.getSouthHmcToTraNo1().get(i));
                    }
                }
            }
        } else {
            for (int i = 0; i < 8; i++) {
                hours.add(model.getSouthDavisToHmcNo2().get(i));
                if (i < 7) {
                    hours.add(model.getSouthDavisToHmcNo1().get(i));
                }
            }
        }
        comboBoxBusTime.setItems(FXCollections.observableArrayList(hours));
    }

    @FXML
    private void handleButtonReset(ActionEvent event) {
    }
}
