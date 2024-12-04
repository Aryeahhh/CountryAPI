package org.example.assignment2;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.text.NumberFormat;
import java.util.Locale;
import java.util.stream.Collectors;

/**
 * Controller class for displaying the details of a country.
 * This class is responsible for populating the UI elements with data about the country.
 */
public class CountryDetailsController {

    @FXML
    private Label headerLabel;
    @FXML
    private Label capitalLabel;
    @FXML
    private Label populationLabel;
    @FXML
    private Label regionLabel;
    @FXML
    private Label subregionLabel;
    @FXML
    private Label areaLabel;
    @FXML
    private Label languagesLabel;
    @FXML
    private Label currencyLabel;
    @FXML
    private Label timezoneLabel;
    @FXML
    private Label callingCodeLabel;
    @FXML
    private ImageView flagImageView;
    @FXML
    private Button backButton;

    private Stage stage;

    /**
     * Initializes the controller by setting up the event handler for the back button.
     */
    @FXML
    public void initialize() {
        backButton.setOnAction(event -> handleBackButton());
    }

    /**
     * Sets the country details to be displayed in the UI.
     * This method populates various UI elements like labels and image views with data from the given country object.
     *
     * @param country The country whose details are to be displayed.
     */
    public void setCountryDetails(Country country) {
        // Format numbers using the US locale for consistency in formatting large numbers
        NumberFormat numberFormat = NumberFormat.getNumberInstance(Locale.US);

        headerLabel.setText(country.getName().getCommon());
        capitalLabel.setText("Capital: " + String.join(", ", country.getCapital()));
        populationLabel.setText("Population: " + numberFormat.format(country.getPopulation()));
        regionLabel.setText("Region: " + country.getRegion());
        subregionLabel.setText("Subregion: " + country.getSubregion());
        areaLabel.setText("Area: " + numberFormat.format(country.getArea()) + " km²");
        languagesLabel.setText("Languages: " + String.join(", ", country.getLanguages().values()));
        currencyLabel.setText("Currency: " + country.getCurrencies().values().stream().map(org.example.assignment2.Country.Currency::getName).collect(Collectors.joining(", ")));
        timezoneLabel.setText("Timezones: " + String.join(", ", country.getTimezones()));
        callingCodeLabel.setText("Calling Code: +" + country.getCallingCodes().get(0));
        flagImageView.setImage(new Image(country.getFlags().getPng()));
    }

    /**
     * Handles the action of clicking the back button.
     * Closes the current stage (window) when the back button is clicked.
     */
    private void handleBackButton() {
        stage.close();
    }

    /**
     * Sets the stage (window) for this controller.
     *
     * @param stage The stage to be set for this controller.
     */
    public void setStage(Stage stage) {
        this.stage = stage;
    }
}
