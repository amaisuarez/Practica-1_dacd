package suareznavarro.controller;

import org.json.JSONArray;
import org.json.JSONObject;
import suareznavarro.model.*;

import java.io.FileReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;


public class WeatherMapProvider {

    public static void main(String[] args) {
        String csvFilePath = "/Users/amaisuarez/IdeaProjects/practica1_variacion2/src/main/resources/places.csv";

        // Obtener la lista de ubicaciones desde el archivo CSV
        List<Location> locations = getLocationsFromCSV(csvFilePath);

        // Tu clave de API de OpenWeatherMap
        String apiKey = "bef3ed697c1ead2aa693ab8b1b539fdb";

        // Realizar solicitudes para cada ubicación
        for (Location location : locations) {
            getWeatherData(location.getPlace(), location.getLatitude(), location.getLongitude(), apiKey);
        }
    }

    private static List<Location> getLocationsFromCSV(String csvFilePath) {
        List<Location> locations = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(csvFilePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                String name = parts[0].trim();
                double latitude = Double.parseDouble(parts[1].trim());
                double longitude = Double.parseDouble(parts[2].trim());

                Location location = new Location(latitude, longitude, name);
                locations.add(location);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return locations;
    }
    private static void getWeatherData(String locationName, double latitude, double longitude, String apiKey) {
        String apiUrl = "https://api.openweathermap.org/data/2.5/weather?lat=" + latitude + "&lon=" + longitude + "&appid=" + apiKey;

        try {
            // Crear la conexión
            URL url = new URL(apiUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            // Configurar la solicitud
            connection.setRequestMethod("GET");

            // Obtener la respuesta
            int responseCode = connection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                // Leer la respuesta
                try (BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
                    StringBuilder response = new StringBuilder();
                    String line;

                    while ((line = reader.readLine()) != null) {
                        response.append(line);
                    }

                    // Procesar la respuesta como JSON
                    JSONObject jsonResponse = new JSONObject(response.toString());

                    // Obtener información sobre la ubicación
                    JSONObject city = jsonResponse.getJSONObject("city");
                    String cityName = city.getString("name");
                    JSONObject coord = city.getJSONObject("coord");
                    //double latitude = coord.getDouble("lat");
                    //double longitude = coord.getDouble("lon");

                    // Extraer información específica y filtrar por hora
                    JSONArray forecastList = jsonResponse.getJSONArray("list");
                    for (int i = 0; i < forecastList.length(); i++) {
                        JSONObject forecast = forecastList.getJSONObject(i);

                        // Obtener la fecha y hora de la predicción
                        String dtTxt = forecast.getString("dt_txt");

                        // Verificar si es a las 12:00:00
                        if (dtTxt.endsWith("12:00:00")) {
                            JSONObject main = forecast.getJSONObject("main");
                            double temperature = main.getDouble("temp");

                            JSONArray weatherArray = forecast.getJSONArray("weather");
                            JSONObject weather = weatherArray.getJSONObject(0);
                            String description = weather.getString("description");

                            JSONObject clouds = forecast.getJSONObject("clouds");
                            int cloudiness = clouds.getInt("all");

                            JSONObject wind = forecast.getJSONObject("wind");
                            double windSpeed = wind.getDouble("speed");

                            // Imprimir los datos incluyendo información de ubicación
                            System.out.println("Place: " + cityName + " (Latitude: " + latitude + ", Longitude: " + longitude + ")");
                            System.out.println("Fecha y hora: " + dtTxt);
                            System.out.println("Temperature: " + temperature);
                            System.out.println("Description: " + description);
                            System.out.println("Cloudiness: " + cloudiness);
                            System.out.println("WindSpeed: " + windSpeed);
                            System.out.println("--------");

                        }
                    }
                }
            } else {
                System.out.println("Error. Código de respuesta: " + responseCode);
            }


            connection.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
