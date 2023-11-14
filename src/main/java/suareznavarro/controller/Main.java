package suareznavarro.controller;
import java.util.List;
import suareznavarro.model.*;

public class Main {
    public static void main(String[] args) {
        SQLController.createDatabase();
        List<Weather> weatherData = getWeatherDataFromAPI();
        DataInserter.insertWeatherData(weatherData);
    }

    private static List<Weather> getWeatherDataFromAPI() {
        String csvFilePath = "/Users/amaisuarez/IdeaProjects/practica1_variacion2/src/main/resources/places.csv";
        String apiKey = "bef3ed697c1ead2aa693ab8b1b539fdb";

        WeatherMapProvider weatherMapProvider = new WeatherMapProvider();
        return weatherMapProvider.processLocationsFromCSV(csvFilePath, apiKey);
    }
}
