package quynh;

import data.Bus;
import data.ConnectSQLServer;
import java.net.URL;
import java.sql.Connection;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
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
        model.getBusDatabase(connection);

        ArrayList<Integer> busesNum = model.getBusesNum();

        comboBoxBusNum.setItems(FXCollections.observableList(busesNum));

        comboBoxBusNum.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e) {
                showBusInfo();
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
        System.out.println(busNum);

        bus = model.getBusByBusNum(busNum);
        if (bus == null) {
            return;
        } else {
            labelBusType.setText(bus.getType());
            labelNumOfSeat.setText(Integer.toString(bus.getNumOfSeat()));
        }

    }

}
