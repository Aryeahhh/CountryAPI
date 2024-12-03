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

public class HelloController {
    @FXML
    private TextField searchField;
    @FXML
    private Button searchButton;
    @FXML
    private ListView<String> suggestionsList;

    private ObservableList<String> suggestions = FXCollections.observableArrayList();
    private ApiService apiService = new ApiService();

    @FXML
    public void initialize() {
        searchButton.setOnAction(event -> searchCountry());
        searchField.setOnKeyReleased(this::updateSuggestions);
        suggestionsList.setItems(suggestions);
        suggestionsList.setVisible(false);
        suggestionsList.setOnMouseClicked(event -> {
            String selectedCountry = suggestionsList.getSelectionModel().getSelectedItem();
            searchField.setText(selectedCountry);
            searchCountry();
        });
    }

    private void searchCountry() {
        String countryName = searchField.getText().replace(" ", "%20");
        try {
            List<Country> countries = apiService.fetchCountryByName(countryName);
            if (!countries.isEmpty()) {
                // Find the country that matches the selected suggestion exactly
                Country country = countries.stream()
                        .filter(c -> c.getName().getCommon().equalsIgnoreCase(searchField.getText()))
                        .findFirst()
                        .orElse(countries.get(0));
                showCountryDetails(country);
            } else {
                suggestionsList.setVisible(false);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void updateSuggestions(KeyEvent event) {
        String query = searchField.getText().toLowerCase().replace(" ", "%20");
        if (!query.isEmpty()) {
            try {
                List<Country> countries = apiService.fetchCountryByName(query);
                suggestions.setAll(countries.stream()
                        .map(country -> country.getName().getCommon())
                        .collect(Collectors.toList()));
                suggestionsList.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            suggestions.clear();
            suggestionsList.setVisible(false);
        }
    }

    private void showCountryDetails(Country country) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("country-details-view.fxml"));
        Stage stage = new Stage();
        Scene scene = new Scene(loader.load());
        CountryDetailsController controller = loader.getController();
        controller.setCountryDetails(country);
        controller.setStage(stage);
        stage.setScene(scene);
        stage.setTitle("Country Details");
        stage.show();
    }

    private String formatNumber(long number) {
        return NumberFormat.getNumberInstance(Locale.US).format(number);
    }
}