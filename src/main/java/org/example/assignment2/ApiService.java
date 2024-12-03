package org.example.assignment2;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

public class ApiService {
    private static final String API_URL = "https://restcountries.com/v3.1/name/";

    public List<Country> fetchCountryByName(String countryName) {
        try {
            URL url = new URL(API_URL + countryName);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            if (connection.getResponseCode() == 200) {
                InputStreamReader reader = new InputStreamReader(connection.getInputStream());
                return new Gson().fromJson(reader, new TypeToken<List<Country>>() {}.getType());
            } else {
                System.err.println("Error: " + connection.getResponseCode());
            }
        } catch (JsonSyntaxException | IOException e) {
            System.err.println("Updating Suggestions");
        }
        return null;
    }
}