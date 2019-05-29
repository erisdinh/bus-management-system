package quynh;

import data.Bus;
import data.BusReservation;
import data.ConnectSQLServer;
import data.User;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class UserFormController implements Initializable {

    private Connection connection;
    private Stage stage;
    private BusManagementSystemModel model;
    private ArrayList<Bus> buses;
    private ArrayList<String> departure;
    private ArrayList<String> destination;
    private ArrayList<String> hours = new ArrayList<>();
    private BusReservation newBusReservation;
    private BusReservation subNewBusReservation;

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
    @FXML
    private Button buttonSearch;
    @FXML
    private TableView<BusReservation> tableReserveation;
    @FXML
    private TableColumn<BusReservation, Integer> columnResNum;
    @FXML
    private TableColumn<BusReservation, String> columnDeparture;
    @FXML
    private TableColumn<BusReservation, String> columnDestination;
    @FXML
    private TableColumn<BusReservation, Integer> columnBusNum;
    @FXML
    private TableColumn<BusReservation, String> columnSeat;
    @FXML
    private TableColumn<BusReservation, Date> columnDate;
    @FXML
    private TableColumn<BusReservation, Time> columnTime;
    @FXML
    private TableColumn<BusReservation, String> columnStatus;
    @FXML
    private Button buttonSelect;
    @FXML
    private Tab tabReservationHistory;
    @FXML
    private ComboBox<String> comboxBoxSearchDeparture;
    @FXML
    private ComboBox<String> comboBoxSearchDestination;
    @FXML
    private DatePicker datePickerSearchDate;
    @FXML
    private ComboBox<String> comboBoxSearchStatus;
    @FXML
    private TextField textfieldSearchResNum;
    @FXML
    private Button buttonChangePhoneNum;
    @FXML
    private Button buttonChangeMail;
    @FXML
    private TextField textfieldDOB;
    @FXML
    private TextField textfieldMajor;
    @FXML
    private TextField textfieldHomeCampus;
    @FXML
    private TextField textfieldPhoneNum;
    @FXML
    private TextField textfieldMail;
    @FXML
    private Button buttonChangePass;
    @FXML
    private TextField textfieldName;
    @FXML
    private TextField textfieldID;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        connection = ConnectSQLServer.getAutoConnection();

//        // Show Bus info after the user choose the bus number
//        comboBoxBusNum.setOnAction(new EventHandler<ActionEvent>() {
//            public void handle(ActionEvent e) {
//                buttonSeat.selectToggle(null);
//                if (comboBoxBusNum.getValue() == null) {
//                    int busNum = 0;
//                    showBusInfo(busNum);
//                } else {
//                    int busNum = comboBoxBusNum.getValue();
//                    showBusInfo(busNum);
//                }
//            }
//        });
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

            // reset toggle group buttonSeat
            buttonSeat.selectToggle(null);
            resetToggleGroupSeats();

            destination = new ArrayList<String>();
            String chosenDeparture = comboBoxDeparture.getValue();
            if (chosenDeparture == null) {
            } else {
                for (int i = 0; i < departure.size(); i++) {
                    if (chosenDeparture.equals(departure.get(i))) {

                    } else {
                        destination.add(departure.get(i));
                    }
                }
                comboBoxDestination.setItems(FXCollections.observableArrayList(destination));
            }

        });

        // Show bus hours list after changing comboBox Time
        comboBoxDestination.getSelectionModel().selectedItemProperty().addListener((ov, oldValue, newValue) -> {

            // reset toggle group buttonSeat
            buttonSeat.selectToggle(null);
            resetToggleGroupSeats();

            // Clear the selected items after the user changing the departure and destination
            comboBoxBusTime.setItems(null);

            String chosenDeparture = comboBoxDeparture.getValue();
            String chosenDestination = comboBoxDestination.getValue();
            if (chosenDestination == null || chosenDeparture == null) {
            } else {
                showBusHours(chosenDeparture, chosenDestination);
            }
        });

        datePickerDateRes.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                // reset toggle group buttonSeat
                buttonSeat.selectToggle(null);
                resetToggleGroupSeats();

                if (comboBoxDeparture.getValue() != null && comboBoxDestination.getValue() != null
                        && comboBoxBusTime.getValue() != null && comboBoxBusNum.getValue() != null) {
                    ArrayList<BusReservation> seatsInBus = new ArrayList<>();
                    seatsInBus = getUnvailableSeatsInBus();
                    setDisableUnavailableSeats(seatsInBus);
                }
            }
        });

        // Show the bus number and change the bus info (set bus num is 0) after the user choose the time
        comboBoxBusTime.getSelectionModel().selectedItemProperty().addListener((ov, oldValue, newValue) -> {

            // reset toggle group buttonSeat
            buttonSeat.selectToggle(null);
            resetToggleGroupSeats();

            // Clear the selected items after the user changing the departure and destination
            comboBoxBusNum.setItems(null);

            String chosenTime = comboBoxBusTime.getValue();
            String chosenDeparture = comboBoxDeparture.getValue();
            String chosenDestination = comboBoxDestination.getValue();
            if (chosenTime == null) {
            } else {
                showBusNum(chosenDeparture, chosenDestination, chosenTime);
                showBusInfo(0);
            }

        });

        // When the bus num change, change the bus info (show the bus info)
        comboBoxBusNum.getSelectionModel().selectedItemProperty().addListener((ov, oldValue, newValue) -> {

            // reset toggle group buttonSeat
            buttonSeat.selectToggle(null);
            resetToggleGroupSeats();

            if (comboBoxBusNum.getItems() == null) {
            } else {
                int busNum = comboBoxBusNum.getValue();
                showBusInfo(busNum);
                ArrayList<BusReservation> seatsInBus = new ArrayList<>();
                seatsInBus = getUnvailableSeatsInBus();
                setDisableUnavailableSeats(seatsInBus);
            }
        });

        // Set labelSeat to notice the user what seat is chosen
        buttonSeat.selectedToggleProperty().addListener((ov, oldValue, newValue) -> {

            ToggleButton chosenTogglebutton = (ToggleButton) buttonSeat.getSelectedToggle();

            if (chosenTogglebutton == null) {
            } else {
                String chosenSeat = chosenTogglebutton.getId();
                labelSeat.setText(chosenSeat);
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

    public void setModel(BusManagementSystemModel model) {
        this.model = model;
        model.initializeBusHours();
        model.getBusDatabase(connection);
        model.getBusReservationDatabase(connection);
    }

    @FXML
    private void handleButtonReset(ActionEvent event) {
        resetReserveForm();
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
        System.out.println(model.getLastBusReservation().getResNum());
        int userID = model.getCurrentUser().getId();
        String departure = comboBoxDeparture.getValue();
        String destination = comboBoxDestination.getValue();
        ToggleButton chosenTogglebutton = (ToggleButton) buttonSeat.getSelectedToggle();

        // If one of elements in the form is not filled, show a alert
        if (departure == null || destination == null
                || datePickerDateRes.getValue() == null
                || comboBoxBusTime.getValue() == null
                || comboBoxBusNum.getValue() == null
                || buttonSeat.getSelectedToggle() == null) {

            Alert alert = new Alert(Alert.AlertType.ERROR, "Please enter all information!");
            alert.showAndWait();
        } else {

            int resNum = model.getLastBusReservation().getResNum() + 1;
            int busNum = comboBoxBusNum.getValue();
            String seat = chosenTogglebutton.getId();
            String status = "On going";

            // Date/Time reserve the bus
            Date busResDate = Date.valueOf(datePickerDateRes.getValue());
            Time busResTime = Time.valueOf(comboBoxBusTime.getValue() + ":00");

            // Current Date/Time
            java.util.Date utilDate = new java.util.Date();
            Date currentDate = new Date(utilDate.getTime());
            Time currentTime = new Time(utilDate.getTime());

            subNewBusReservation = new BusReservation();
            newBusReservation = new BusReservation(resNum, userID, departure, destination, busNum, seat, busResDate, busResTime, currentDate, currentTime, status);

            // If the depature is Trafalgar and destination is Davis or vice versa
            // Set the subDestination is HMC
            // Set the time of the transit bus from HMC to Destination is the time of the reservated-bus + 30 mins
//            if((departure.equals("Trafalgar") && destination.equals("Davis")) || (departure.equals("Davis") || destination.equals("Trafalgar"))) {
//                String subDestination = "HMC";
//                
//                // Convert the time has chosen to localTime to plus 30 mins for the transit bus
//                String strTime = comboBoxBusTime.getValue();
//                if (strTime.charAt(1) == ':') {
//                    strTime = "0" + strTime;
//                }
//                LocalTime localTime = busResTime.toLocalTime();
//                localTime = localTime.plusMinutes(30);
//                Time subResTime = Time.valueOf(localTime);
//                
//                newBusReservation = new BusReservation(resNum, userID, departure, subDestination, busNum, seat, busResDate, busResTime, busResDate, subResTime, status);
//                subNewBusReservation = new BusReservation(resNum, userID, subDestination, destination, busNum, seat, busResDate, subResTime, busResDate, subResTime, status);
//            } else {
//                
//                // Store this new reservation in model
//                newBusReservation = new BusReservation(resNum, userID, departure, destination, busNum, seat, busResDate, busResTime, currentDate, currentTime, status);
//                subNewBusReservation = null;
//            }
            model.setNewBusReservation(newBusReservation);
            model.setSubNewReservation(subNewBusReservation);

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
            ctrlConfirmResForm.setModel(model);
            ctrlConfirmResForm.setStage(stage);

            // wait user response within this block
            stage.showAndWait();
            if (ctrlConfirmResForm.isCancel()) {

            } else {
                model.updateAllFromDatabase(connection);
                resetReserveForm();
            }
        }
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

    // Reset the Bus Reservation Form
    private void resetReserveForm() {
        labelSeat.setText(null);
        comboBoxDeparture.setItems(null);
        comboBoxDeparture.setItems(FXCollections.observableArrayList(departure));
        comboBoxDestination.setItems(null);
        datePickerDateRes.setValue(null);
        comboBoxBusTime.setItems(null);
        comboBoxBusNum.setItems(null);
        buttonSeat.selectToggle(null);
    }

    // Show Bus Number
    private void showBusNum(String departure, String destination, String time) {
        ArrayList<Integer> busesNum = new ArrayList<>();

        // User chosen time
        int chosenTime;

        // Get time
        String subTime = time.substring(0, 2);

        // If 2 first characters of the time containing ":"
        // Take the first character
        // otherwise, take both characters
        if (subTime.charAt(1) == ':') {
            chosenTime = Integer.parseInt(String.valueOf(subTime.charAt(0)));
        } else {
            chosenTime = Integer.parseInt(String.valueOf(subTime));
        }

        // Show busNum depend on time
        if (departure.equals("Trafalgar")) {
            System.out.println(chosenTime % 2);
            if (chosenTime % 2 == 0) {
                busesNum.add(2);
                busesNum.add(4);
            } else {
                busesNum.add(1);
                busesNum.add(3);
            }
        } else if (departure.equals("HMC")) {
            if (destination.equals("Davis")) {
                if (chosenTime % 2 == 0) {
                    busesNum.add(5);
                    busesNum.add(7);
                } else {
                    busesNum.add(2);
                    busesNum.add(4);
                }
            } else {
                System.out.println(chosenTime % 2);
                if (chosenTime % 2 != 0) {
                    busesNum.add(1);
                    busesNum.add(3);
                } else {
                    busesNum.add(6);
                    busesNum.add(8);
                }
            }
        } else {
            if (chosenTime % 2 == 0) {
                busesNum.add(6);
                busesNum.add(8);
            } else {
                busesNum.add(5);
                busesNum.add(7);
            }
        }

        comboBoxBusNum.setItems(FXCollections.observableArrayList(busesNum));
    }

    @FXML
    private void handleButtonSearch(ActionEvent event) {

        // Clear the observable list
        ObservableList<BusReservation> items = tableReserveation.getItems();
        items.clear();

//        Time time = Time.valueOf(comboBoxSearchTime.getValue() + ":00");
        ArrayList<BusReservation> result = new ArrayList<>();
        result = model.getUserReservations(model.getCurrentUser().getId());

        if (!(textfieldSearchResNum.getText().equals(""))) {
            int resNum = Integer.parseInt(textfieldSearchResNum.getText());
            System.out.println(resNum);
            result = model.searchByResNum(result, resNum);
        }
        if (!(comboxBoxSearchDeparture.getValue() == null)) {
            String departure = comboxBoxSearchDeparture.getValue();
            result = model.searchByDeparture(result, departure);
        }
        if (!(comboBoxSearchDestination.getValue() == null)) {
            String destination = comboBoxSearchDestination.getValue();
            result = model.searchByDestination(result, destination);
        }
        if (!(datePickerSearchDate.getValue() == null)) {
            Date date = Date.valueOf(datePickerSearchDate.getValue());
            result = model.searchByDate(result, date);
        }
        if (!(comboBoxSearchStatus.getValue() == null)) {
            String status = comboBoxSearchStatus.getValue();
            result = model.searchByStatus(result, status);
        }

        // Pass result list to an observable list
        for (int i = 0; i < result.size(); i++) {
            items.add(result.get(i));
            System.out.println(result.get(i).getResNum());
        }

        // Set items to the tableView
        tableReserveation.setItems(items);

        columnResNum.setCellValueFactory(new PropertyValueFactory<BusReservation, Integer>("resNum"));
        columnDeparture.setCellValueFactory(new PropertyValueFactory<BusReservation, String>("departure"));
        columnDestination.setCellValueFactory(new PropertyValueFactory<BusReservation, String>("destination"));
        columnBusNum.setCellValueFactory(new PropertyValueFactory<BusReservation, Integer>("busNum"));
        columnSeat.setCellValueFactory(new PropertyValueFactory<BusReservation, String>("seat"));
        columnDate.setCellValueFactory(new PropertyValueFactory<BusReservation, Date>("busResDate"));
        columnTime.setCellValueFactory(new PropertyValueFactory<BusReservation, Time>("busResTime"));
        columnStatus.setCellValueFactory(new PropertyValueFactory<BusReservation, String>("status"));

    }

    @FXML
    private void handleButtonSelect(ActionEvent event) {
    }

    private void InitialUserBusResevations() {
        resetHistoryForm();
        // Clear the observable list
        ObservableList<BusReservation> items = tableReserveation.getItems();
        items.clear();

        // Get all user reservation list
        ArrayList<BusReservation> userReservations = new ArrayList<>();
        int userID = model.getCurrentUser().getId();
        userReservations = model.getUserReservations(userID);

        // Pass user reservation list to an observable list
        for (int i = 0; i < userReservations.size(); i++) {
            items.add(userReservations.get(i));
        }

        // Set items to the tableView
        tableReserveation.setItems(items);

        columnResNum.setCellValueFactory(new PropertyValueFactory<BusReservation, Integer>("resNum"));
        columnDeparture.setCellValueFactory(new PropertyValueFactory<BusReservation, String>("departure"));
        columnDestination.setCellValueFactory(new PropertyValueFactory<BusReservation, String>("destination"));
        columnBusNum.setCellValueFactory(new PropertyValueFactory<BusReservation, Integer>("busNum"));
        columnSeat.setCellValueFactory(new PropertyValueFactory<BusReservation, String>("seat"));
        columnDate.setCellValueFactory(new PropertyValueFactory<BusReservation, Date>("busResDate"));
        columnTime.setCellValueFactory(new PropertyValueFactory<BusReservation, Time>("busResTime"));
        columnStatus.setCellValueFactory(new PropertyValueFactory<BusReservation, String>("status"));

    }

    // When the user click to see Reservation History -> Initialize the histpry form
    // Show the history in a table
    @FXML
    private void initialHistoryForm(Event event) {
        resetHistoryForm();

        // Initial status list for comboBoxSearchStatus
        ArrayList<String> statuses = new ArrayList<>();
        statuses.add("On going");
        statuses.add("Completed");
        statuses.add("Cancelled");

        model.getBusReservationDatabase(connection);
        InitialUserBusResevations();
        comboxBoxSearchDeparture.setItems(FXCollections.observableArrayList(this.departure));
        comboBoxSearchDestination.setItems(FXCollections.observableArrayList(this.departure));
        comboBoxSearchStatus.setItems(FXCollections.observableArrayList(statuses));
    }

    private void resetHistoryForm() {
        textfieldSearchResNum.setText("");
        comboxBoxSearchDeparture.setItems(null);
        comboBoxSearchDestination.setItems(null);
        datePickerSearchDate.setValue(null);
        comboBoxSearchStatus.setItems(null);
    }

    @FXML
    private void handleButtonChangePhoneNum(ActionEvent event) {
        if (buttonChangePhoneNum.getText().equals("Change")) {
            textfieldPhoneNum.setDisable(false);
            buttonChangePhoneNum.setText("Save");
        } else {
            String phoneNum = textfieldPhoneNum.getText();
            model.getCurrentUser().setPhoneNum(phoneNum);
            User user = model.getCurrentUser();
            boolean update = model.updateUserToDatabase(connection, user);
            if (update == false) {
                Alert alert = new Alert(Alert.AlertType.ERROR, "Cannot update!");
                alert.showAndWait();
            } else {
                buttonChangePhoneNum.setText("Change");
                initializeUserInfo();
            }
        }
    }

    @FXML
    private void handleButtonChangeMail(ActionEvent event) {
        if (buttonChangeMail.getText().equals("Change")) {
            textfieldMail.setDisable(false);
            buttonChangeMail.setText("Save");
        } else {
            String mail = textfieldMail.getText();
            model.getCurrentUser().setMail(mail);
            User user = model.getCurrentUser();
            boolean update = model.updateUserToDatabase(connection, user);
            if (update == false) {
                Alert alert = new Alert(Alert.AlertType.ERROR, "Cannot update!");
                alert.showAndWait();
            } else {
                buttonChangeMail.setText("Change");
                initializeUserInfo();
            }
        }
    }

    @FXML
    private void handleButtonChangePass(ActionEvent event) throws IOException {
        // Load scence graph from fxml
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("UserChangePasswordForm.fxml"));
        Parent root = (Parent) fxmlLoader.load();

        // Create and show about window as modal     
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle("Change Password");

        // Set icon
        stage.getIcons().add(new Image(BusManagementSystem.class.getResourceAsStream("/data/bus.png")));
        stage.setScene(scene);

        // Remember the stage, so can close it later     
        UserChangePasswordFormController ctrlChangePassword = fxmlLoader.getController();
        ctrlChangePassword.setModel(model);
        ctrlChangePassword.setStage(stage);

        // wait user response within this block
        stage.showAndWait();
    }

    // Initialize User Information Form
    @FXML
    private void initializeUserInfo(Event event) {
        initializeUserInfo();
    }

    private void initializeUserInfo() {
        textfieldID.setText(Integer.toString(model.getCurrentUser().getId()));
        textfieldName.setText(model.getCurrentUser().getName());
        textfieldDOB.setText(model.getCurrentUser().getDob().toString());
        textfieldMajor.setText(model.getCurrentUser().getMajor());
        textfieldHomeCampus.setText(model.getCurrentUser().getHomeCampus());
        textfieldPhoneNum.setText(model.getCurrentUser().getPhoneNum());
        textfieldMail.setText(model.getCurrentUser().getMail());

        textfieldID.setDisable(true);
        textfieldName.setDisable(true);
        textfieldDOB.setDisable(true);
        textfieldMajor.setDisable(true);
        textfieldHomeCampus.setDisable(true);
        textfieldPhoneNum.setDisable(true);
        textfieldMail.setDisable(true);
    }

    private void setDisableUnavailableSeats(ArrayList<BusReservation> result) {
        BusReservation tempBusReservation = new BusReservation();
        ObservableList<Toggle> toggles = buttonSeat.getToggles();

        resetToggleGroupSeats();

        System.out.println("Run Set Disable");

        for (int i = 0; i < result.size(); i++) {

            tempBusReservation = result.get(i);
            System.out.println("Temp Bus Reservation:" + tempBusReservation.getSeat());

            for (int j = 0; j < toggles.size(); j++) {
                Node tempNode = (Node) toggles.get(j);
                System.out.println("Temp Node: " + tempNode);
                if (tempNode.getId().equals(tempBusReservation.getSeat())) {
                    tempNode.setDisable(true);
                    System.out.println("Disable");
                }
            }
        }
    }

    private ArrayList<BusReservation> getUnvailableSeatsInBus() {
        ArrayList<BusReservation> seatsInBus = new ArrayList<>();
        seatsInBus = model.getBusReservationDatabase(connection);
        System.out.println("Before: " + seatsInBus.size());

        String chosenDepature = comboBoxDeparture.getValue();
        seatsInBus = model.searchByDeparture(seatsInBus, chosenDepature);
        System.out.println("After Departure: " + seatsInBus.size());

        String chosenDestination = comboBoxDestination.getValue();
        seatsInBus = model.searchByDestination(seatsInBus, chosenDestination);
        System.out.println("After Destination: " + seatsInBus.size());

        Date chosenDate = Date.valueOf(datePickerDateRes.getValue());
        seatsInBus = model.searchByDate(seatsInBus, chosenDate);
        System.out.println("After Date: " + seatsInBus.size());

        String chosenTime = comboBoxBusTime.getValue() + ":00";
        if (chosenTime.charAt(1) == ':') {
            chosenTime = "0" + chosenTime;
        }
        System.out.println(comboBoxBusTime.getValue());
        System.out.println(chosenTime);
        seatsInBus = model.searchByTime(seatsInBus, chosenTime);
        System.out.println("After Time: " + seatsInBus.size());

        int chosenBusNum = comboBoxBusNum.getValue();
        System.out.println("Chosen BusNum: " + chosenBusNum);
        seatsInBus = model.searchByBusNum(seatsInBus, chosenBusNum);
        System.out.println("After Bus Num: " + seatsInBus.size());

        return seatsInBus;
    }

    private void resetToggleGroupSeats() {
        buttonSeat.getToggles().forEach(toggle -> {
            Node node = (Node) toggle;
            node.setDisable(false);
        });
    }
}
