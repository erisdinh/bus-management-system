package quynh;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class BusManagementSystem extends Application {

    @Override
    public void start(Stage stage) throws Exception {

        // Get the controller pass the FXML file to FXMLLoader
        FXMLLoader loader = new FXMLLoader(getClass().getResource("LoginForm.fxml"));
        Parent root = (Parent) loader.load();

        Scene scene = new Scene(root);

        stage.setScene(scene);

        // Set title
        stage.setTitle("Bus Management System");

        // Set icon
        stage.getIcons().add(new Image(BusManagementSystem.class.getResourceAsStream("/data/bus.png")));

        // Assign stage to the controller
        LoginFormController controller = (LoginFormController) loader.getController();
        controller.setStage(stage);
        
        stage.show();

    }

    public static void main(String[] args) {
        launch(args);
    }

}
