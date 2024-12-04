package org.example.assignment2;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Main application class for the Country Info app.
 * This class is responsible for launching the JavaFX application,
 * loading the initial FXML view, and displaying the window.
 */
public class HelloApplication extends Application {

    /**
     * The entry point for starting the JavaFX application.
     * This method loads the FXML file, creates a scene, and sets up the primary stage.
     *
     * @param stage The primary stage for this application.
     * @throws IOException If the FXML file cannot be loaded.
     */
    @Override
    public void start(Stage stage) throws IOException {
        // Load the FXML file for the initial view
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));

        // Create a scene with the loaded FXML and set its dimensions
        Scene scene = new Scene(fxmlLoader.load(), 800, 600); // Increased size

        // Set the title of the stage and assign the scene to the stage
        stage.setTitle("Country Info");
        // Set the logo as the window's icon
        stage.getIcons().add(new Image(getClass().getResourceAsStream("logo.png")));
        stage.setScene(scene);

        // Show the stage (window) on screen
        stage.show();
    }

    /**
     * The main method to launch the JavaFX application.
     * This method invokes the launch() method to start the JavaFX runtime.
     *
     * @param args Command line arguments passed to the application (not used here).
     */
    public static void main(String[] args) {
        launch();
    }
}
