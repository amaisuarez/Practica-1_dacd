package control.controller;

import org.json.JSONArray;
import org.json.JSONObject;
import control.model.Location;
import control.model.Weather;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class WeatherMapProvider {

    public List<Weather> processLocationsFromCSV(String csvFilePath, String apiKey) {
        List<Location> locations = getLocationsFromCSV(csvFilePath);
        List<Weather> weatherDataList = new ArrayList<>();

        for (Location location : locations) {
            List<Weather> weatherData = getWeatherData(location, apiKey);
            weatherDataList.addAll(weatherData);
        }

        return weatherDataList;
    }

    public static List<Location> getLocationsFromCSV(String csvFilePath) {
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

    public static List<Weather> getWeatherData(Location location, String apiKey) {
        List<Weather> weatherDataList = new ArrayList<>();
        String apiUrl = "https://api.openweathermap.org/data/2.5/forecast?lat=" + location.getLatitude() + "&lon=" + location.getLongitude() + "&appid=" + apiKey;

        try {

            URL url = new URL(apiUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");


            int responseCode = connection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {

                try (BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
                    StringBuilder response = new StringBuilder();
                    String line;

                    while ((line = reader.readLine()) != null) {
                        response.append(line);
                    }


                    JSONObject jsonResponse = new JSONObject(response.toString());


                    JSONArray forecastList = jsonResponse.getJSONArray("list");
                    for (int i = 0; i < forecastList.length(); i++) {
                        JSONObject forecast = forecastList.getJSONObject(i);

                        String dtTxt = forecast.getString("dt_txt");

                        if (is12PM(dtTxt)) {
                            JSONObject main = forecast.getJSONObject("main");
                            double temperature = main.getDouble("temp");
                            int humidity = main.getInt("humidity");


                            double precipitation = 0.0;


                            if (forecast.has("rain") && forecast.getJSONObject("rain").has("3h")) {
                                precipitation = forecast.getJSONObject("rain").getDouble("3h");
                            }

                            JSONArray weatherArray = forecast.getJSONArray("weather");
                            JSONObject weather = weatherArray.getJSONObject(0);
                            String description = weather.getString("description");

                            JSONObject clouds = forecast.getJSONObject("clouds");
                            int cloudiness = clouds.getInt("all");


                            JSONObject wind = forecast.getJSONObject("wind");
                            double windSpeed = wind.getDouble("speed");

                            Weather weatherData = new Weather(temperature, precipitation, humidity,
                                    cloudiness, windSpeed, dtTxt, description);
                            weatherData.setLocation(location);
                            weatherDataList.add(weatherData);

                            System.out.println("Place: " + location.getPlace() +
                                    " (Latitude: " + location.getLatitude() +
                                    ", Longitude: " + location.getLongitude() + ")");
                            System.out.println("Date and hour: " + dtTxt);
                            System.out.println("Temperature: " + Math.round(temperature - 273.00));
                            System.out.println("Description: " + description);
                            System.out.println("Cloudiness: " + cloudiness);
                            System.out.println("WindSpeed: " + windSpeed);
                            System.out.println("Precipitation: " + precipitation);
                            System.out.println("Humidity: " + humidity);
                            System.out.println("--------");
                        }


                    }
                }

            } else {
                System.out.println("Error: " + responseCode);
            }

            connection.disconnect();
        } catch (
                Exception e) {
            e.printStackTrace();
        }
        return weatherDataList;
    }

        private static boolean is12PM (String dtTxt){
            return dtTxt.endsWith("12:00:00");
        }

    }

