package control.controller;

import control.model.Location;
import control.model.Weather;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class Main {
    public static void main(String[] args) {
        List<Location> locations = LocationCSVParser.getLocationsFromCSV("src/main/resources/places.csv");
        SQLController.createDatabase(locations);


        while (true) {
            try {
                List<Weather> weatherData = getWeatherDataFromAPI();
                if (weatherData != null) {
                    boolean allWeatherHaveLocation = weatherData.stream()
                            .allMatch(weather -> weather.getLocation() != null);

                    if (allWeatherHaveLocation) {
                        DataInserter.insertWeatherData(weatherData);
                        System.out.println("New update in 6 hours...");
                    } else {
                        System.out.println("Some Weather objects do not have a Location set.");
                    }
                } else {
                    System.out.println("Weather data was null.");
                }
                TimeUnit.HOURS.sleep(6);
            } catch (InterruptedException e) {
                e.printStackTrace();
                break;
            }
        }
    }

    private static List<Weather> getWeatherDataFromAPI() {
        String csvFilePath = "src/main/resources/places.csv";
        String apiKey = "bef3ed697c1ead2aa693ab8b1b539fdb";

        WeatherMapProvider weatherMapProvider = new WeatherMapProvider();
        List<Location> locations = LocationCSVParser.getLocationsFromCSV(csvFilePath);
        List<Weather> weatherDataList = weatherMapProvider.processLocationsFromCSV(locations, apiKey);

        return weatherDataList;
    }
}
