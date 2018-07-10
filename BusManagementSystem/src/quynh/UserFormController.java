package quynh;

import data.Bus;
import data.ConnectSQLServer;
import data.User;
import java.io.IOException;
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
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class UserFormController implements Initializable {

    private User currentUser = new User();
    private Connection connection;
    private Stage stage;
    private BusManagementSystemModel model;
    private ArrayList<Bus> buses;
    private ArrayList<String> departure;
    private ArrayList<String> destination;
    private ArrayList<String> hours = new ArrayList<>();

    public User getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser() {
        this.currentUser = model.getCurrentUser();
    }

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

        // Show Bus info after the user choose the bus number
        comboBoxBusNum.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e) {
                if (comboBoxBusNum.getValue() == null) {
                    int busNum = 0;
                    showBusInfo(busNum);
                } else {
                    int busNum = comboBoxBusNum.getValue();
                    showBusInfo(busNum);
                }
            }
        });

        // Initialize bus info (null)
        labelBusType.setText("");
        labelNumOfSeat.setText("");
        labelNumOfAvaiSeat.setText("");

        // Intialize choosing seat
        labelSeat.setText("");

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
            String choseDeparture = comboBoxDeparture.getValue();
            if (choseDeparture == null) {
            } else {
                for (int i = 0; i < departure.size(); i++) {
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
            if (choseDestination == null || choseDeparture == null) {
            } else {
                showBusHours(choseDeparture, choseDestination);
            }
        });

        comboBoxBusTime.getSelectionModel().selectedItemProperty().addListener((ov, oldValue, newValue) -> {

            // Clear the selected items after the user changing the departure and destination
            comboBoxBusNum.setItems(null);

            String choseTime = comboBoxBusTime.getValue();
            String choseDeparture = comboBoxDeparture.getValue();
            String choseDestination = comboBoxDestination.getValue();
            if (choseTime == null) {
            } else {
                showBusNum(choseDeparture, choseDestination, choseTime);
                showBusInfo(0);
            }

        });

        comboBoxBusNum.getSelectionModel().selectedItemProperty().addListener((ov, oldValue, newValue) -> {

            if (comboBoxBusNum.getItems() == null) {
            } else {
                int busNum = comboBoxBusNum.getValue();
                showBusInfo(busNum);
            }
        });

        buttonSeat.selectedToggleProperty().addListener((ov, oldValue, newValue) -> {
            ToggleButton choseTogglebutton = (ToggleButton) buttonSeat.getSelectedToggle();
            String choseSeat = choseTogglebutton.getId();
            labelSeat.setText(choseSeat);
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

    public void setModel(BusManagementSystemModel model) {
        this.model = model;
        model.initializeBusHours();
    }

    @FXML
    private void handleButtonReset(ActionEvent event) {
        reset();
    }

    @FXML
    private void handleButtonSignOut(ActionEvent event) throws IOException {
        this.stage.close();

        // Load scence graph from fxml
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("LoginForm.fxml"));
        Parent root = (Parent) fxmlLoader.load();

        // Create and show about window as modal     
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle("Welcome to Sheridan Bus Management System");
        // Set icon
        stage.getIcons().add(new Image(BusManagementSystem.class.getResourceAsStream("/data/bus.png")));
        stage.setScene(scene);
        stage.show();

        // Remember the stage, so can close it later     
        LoginFormController ctrlLoginForm = fxmlLoader.getController();
        ctrlLoginForm.setStage(stage);
    }

    @FXML
    private void handleButtonReserve(ActionEvent event) throws IOException {
        // Load scence graph from fxml
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("ConfirmReservationForm.fxml"));
        Parent root = (Parent) fxmlLoader.load();

        // Create and show about window as modal     
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle("Confirmation");

        // Set icon
        stage.getIcons().add(new Image(BusManagementSystem.class.getResourceAsStream("/data/bus.png")));
        stage.setScene(scene);

        // Remember the stage, so can close it later     
        ConfirmReservationFormController ctrlConfirmResForm = fxmlLoader.getController();
        ctrlConfirmResForm.setStage(stage);

        // wait user response within this block
        stage.showAndWait();
        reset();

    }

    // A method to show the selected-bus-num Bus Info
    private void showBusInfo(int tempBusNum) {
        int busNum = tempBusNum;
        if (tempBusNum == 0) {
            labelBusType.setText("");
            labelNumOfSeat.setText("");
            labelNumOfAvaiSeat.setText("");
        } else {
            Bus bus = new Bus();
            bus = model.getBusByBusNum(busNum);

            if (bus == null) {
                labelBusType.setText("");
                labelNumOfSeat.setText("");
                labelNumOfAvaiSeat.setText("");
                return;
            } else {
                labelBusType.setText(bus.getType());
                labelNumOfSeat.setText(Integer.toString(bus.getNumOfSeat()));
                System.out.println(bus.getBusNum());
            }
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

    private void reset() {
        comboBoxDeparture.setItems(null);
        comboBoxDestination.setItems(null);
        datePickerDateRes.setValue(null);
        comboBoxBusTime.setItems(null);
        comboBoxBusNum.setItems(null);
        buttonSeat.selectToggle(null);
    }

    private void showBusNum(String departure, String destination, String time) {
        ArrayList<Integer> busesNum = new ArrayList<>();
        int choseTime = Integer.parseInt(String.valueOf(time.charAt(0)));

        if (departure.equals("Trafalgar")) {
            if (choseTime % 2 == 0) {
                busesNum.add(2);
                busesNum.add(4);
            } else {
                busesNum.add(1);
                busesNum.add(3);
            }
        } else if (departure.equals("HMC")) {
            if (destination.equals("Davis")) {
                if (choseTime % 2 == 0) {
                    busesNum.add(5);
                    busesNum.add(7);
                } else {
                    busesNum.add(2);
                    busesNum.add(4);
                }
            } else {
                if (choseTime % 2 != 0) {
                    busesNum.add(1);
                    busesNum.add(3);
                } else {
                    busesNum.add(6);
                    busesNum.add(8);
                }
            }
        } else {
            if (choseTime % 2 == 0) {
                busesNum.add(6);
                busesNum.add(8);
            } else {
                busesNum.add(5);
                busesNum.add(7);
            }
        }

        comboBoxBusNum.setItems(FXCollections.observableArrayList(busesNum));
    }
}
