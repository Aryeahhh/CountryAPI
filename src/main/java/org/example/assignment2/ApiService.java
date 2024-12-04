package org.example.assignment2;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

/**
 * Service class for interacting with the external API to fetch country information.
 * Uses REST API to fetch details of a country by name.
 */
public class ApiService {
    // Base URL for the REST API
    private static final String API_URL = "https://restcountries.com/v3.1/name/";

    /**
     * Fetches country data by its name from the external API.
     * The country name is passed as a parameter, and the method returns a list of Country objects.
     *
     * @param countryName the name of the country to search for.
     * @return a list of Country objects corresponding to the country name, or null if an error occurs.
     */
    public List<Country> fetchCountryByName(String countryName) {
        try {
            // Constructing the URL using the base API URL and the provided country name
            URL url = new URL(API_URL + countryName);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            // If the response code is 200 (OK), read the response and parse it into a list of Country objects
            if (connection.getResponseCode() == 200) {
                InputStreamReader reader = new InputStreamReader(connection.getInputStream());
                return new Gson().fromJson(reader, new TypeToken<List<Country>>() {}.getType());
            } else {
                // If the response code is not 200, print an error message
                System.err.println("Error: " + connection.getResponseCode());
            }
        } catch (JsonSyntaxException | IOException e) {
            // Handle any JSON syntax or IO errors that may occur during the API request
            System.err.println("Updating Suggestions.");
        }
        return null; // Return null if an error occurs
    }
}
