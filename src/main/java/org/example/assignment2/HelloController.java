package org.example.assignment2;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

/**
 * Controller class for the main view of the Country Info application.
 * This class handles the logic for searching countries, displaying
 * suggestions, and showing detailed country information.
 */
public class HelloController {

    @FXML
    private TextField searchField;  // Text field for entering country name
    @FXML
    private Button searchButton;   // Button for triggering the country search
    @FXML
    private ListView<String> suggestionsList;  // List view for displaying country suggestions

    private ObservableList<String> suggestions = FXCollections.observableArrayList();  // Observable list for country suggestions
    private ApiService apiService = new ApiService();  // API service for fetching country data

    /**
     * Initializes the controller by setting up event handlers and list view visibility.
     * This method is called automatically after the FXML file is loaded.
     */
    @FXML
    public void initialize() {
        // Set action for the search button to trigger the searchCountry method
        searchButton.setOnAction(event -> searchCountry());

        // Set event handler for key release in the search field to update suggestions
        searchField.setOnKeyReleased(this::updateSuggestions);

        // Set the suggestions list to the observable list and hide it initially
        suggestionsList.setItems(suggestions);
        suggestionsList.setVisible(false);

        // Set action for mouse click on suggestion to fill the search field and trigger search
        suggestionsList.setOnMouseClicked(event -> {
            String selectedCountry = suggestionsList.getSelectionModel().getSelectedItem();
            searchField.setText(selectedCountry);
            searchCountry();
        });
    }

    /**
     * Searches for a country by the name entered in the search field.
     * If a country is found, displays its details.
     */
    private void searchCountry() {
        String countryName = searchField.getText().replace(" ", "%20");
        try {
            // Fetch countries based on the entered name
            List<Country> countries = apiService.fetchCountryByName(countryName);
            if (countries != null && !countries.isEmpty()) {
                // Select the first country that matches the exact name
                Country country = countries.stream()
                        .filter(c -> c.getName().getCommon().equalsIgnoreCase(searchField.getText()))
                        .findFirst()
                        .orElse(countries.get(0));
                // Display country details
                showCountryDetails(country);
            } else {
                // Hide suggestions list if no country is found
                suggestionsList.setVisible(false);
            }
        } catch (Exception e) {
            // Handle errors without printing stack trace
            System.err.println("Error: Unable to search country");
        }
    }

    /**
     * Updates the list of country suggestions based on the current query in the search field.
     * This method is called whenever a key is released in the search field.
     *
     * @param event The key event triggered when a key is released in the search field.
     */
    private void updateSuggestions(KeyEvent event) {
        String query = searchField.getText().toLowerCase().replace(" ", "%20");
        if (!query.isEmpty()) {
            try {
                // Fetch countries that match the query
                List<Country> countries = apiService.fetchCountryByName(query);
                if (countries != null) {
                    // Update the suggestions list with country names
                    suggestions.setAll(countries.stream()
                            .map(country -> country.getName().getCommon())
                            .collect(Collectors.toList()));
                    suggestionsList.setVisible(true);  // Show the suggestions list
                } else {
                    suggestions.clear();  // Clear suggestions if no countries are found
                    suggestionsList.setVisible(false);  // Hide suggestions list
                }
            } catch (Exception e) {
                // Handle errors without printing stack trace
                System.err.println("Error: Unable to update suggestions");
            }
        } else {
            suggestions.clear();  // Clear suggestions if the search field is empty
            suggestionsList.setVisible(false);  // Hide suggestions list
        }
    }

    /**
     * Opens a new window displaying detailed information about a selected country.
     *
     * @param country The country whose details are to be displayed.
     * @throws IOException If loading the country details view fails.
     */
    private void showCountryDetails(Country country) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("country-details-view.fxml"));
        Stage stage = new Stage();
        Scene scene = new Scene(loader.load());
        CountryDetailsController controller = loader.getController();
        controller.setCountryDetails(country);  // Set country details in the controller
        controller.setStage(stage);  // Set the stage for the details view
        stage.setScene(scene);
        stage.setTitle("Country Details");
        stage.show();  // Display the details view
    }

    /**
     * Formats a number (e.g., population or area) with commas for thousands.
     *
     * @param number The number to format.
     * @return The formatted number as a string.
     */
    private String formatNumber(long number) {
        return NumberFormat.getNumberInstance(Locale.US).format(number);
    }
}
