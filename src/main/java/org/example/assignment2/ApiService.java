package org.example.assignment2;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;

import java.io.EOFException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

public class ApiService {

    public List<Country> fetchCountryByName(String countryName) throws IOException {
        String urlString = "https://restcountries.com/v3.1/name/" + countryName;
        URL url = new URL(urlString);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");

        try (InputStreamReader reader = new InputStreamReader(connection.getInputStream())) {
            Type countryListType = new TypeToken<List<Country>>() {}.getType();
            return new Gson().fromJson(reader, countryListType);
        } catch (JsonSyntaxException | EOFException e) {
            e.printStackTrace();
            throw new IOException("Error parsing JSON response", e);
        } finally {
            connection.disconnect();
        }
    }
}