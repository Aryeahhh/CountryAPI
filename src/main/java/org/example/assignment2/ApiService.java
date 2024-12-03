package org.example.assignment2;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.example.assignment2.Country;

import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

public class ApiService {
    private static final String API_URL = "https://restcountries.com/v3.1/name/";

    public static List<Country> fetchCountryByName(String countryName) throws Exception {
        URL url = new URL(API_URL + countryName);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");

        InputStreamReader reader = new InputStreamReader(connection.getInputStream());
        return new Gson().fromJson(reader, new TypeToken<List<Country>>() {}.getType());
    }
}