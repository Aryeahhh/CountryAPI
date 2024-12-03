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

    @FXML
    public void initialize() {
        backButton.setOnAction(event -> handleBackButton());
    }

    public void setCountryDetails(Country country) {
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

    private void handleBackButton() {
        stage.close();
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }
}