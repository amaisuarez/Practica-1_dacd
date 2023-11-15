package control.controller;

import java.util.List;
import control.model.*;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;



public class Main {
    public static void main(String[] args) {
        SQLController.createDatabase();
        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

        while (true) {
            try {
                List<Weather> weatherData = getWeatherDataFromAPI();
                DataInserter.insertWeatherData(weatherData);
                System.out.println("New update in 6 hours...");
                TimeUnit.MINUTES.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private static List<Weather> getWeatherDataFromAPI() {
        String csvFilePath = "/Users/amaisuarez/IdeaProjects/practica1_variacion2/src/main/resources/places.csv";
        String apiKey = "bef3ed697c1ead2aa693ab8b1b539fdb";

        WeatherMapProvider weatherMapProvider = new WeatherMapProvider();
        return weatherMapProvider.processLocationsFromCSV(csvFilePath, apiKey);
    }
}



