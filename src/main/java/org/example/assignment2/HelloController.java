package org.example.assignment2;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import org.example.assignment2.ApiService;

import java.util.List;

public class HelloController {
    @FXML
    private TextField searchField;
    @FXML
    private Button searchButton;
    @FXML
    private Label nameLabel;
    @FXML
    private Label capitalLabel;
    @FXML
    private Label populationLabel;
    @FXML
    private Label regionLabel;
    @FXML
    private Label subregionLabel;

    @FXML
    public void initialize() {
        searchButton.setOnAction(event -> searchCountry());
    }

    private void searchCountry() {
        String countryName = searchField.getText();
        try {
            List<Country> countries = ApiService.fetchCountryByName(countryName);
            if (!countries.isEmpty()) {
                Country country = countries.get(0);
                nameLabel.setText("Name: " + country.getName().getCommon());
                capitalLabel.setText("Capital: " + String.join(", ", country.getCapital()));
                populationLabel.setText("Population: " + country.getPopulation());
                regionLabel.setText("Region: " + country.getRegion());
                subregionLabel.setText("Subregion: " + country.getSubregion());
            } else {
                nameLabel.setText("Country not found");
                capitalLabel.setText("");
                populationLabel.setText("");
                regionLabel.setText("");
                subregionLabel.setText("");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}