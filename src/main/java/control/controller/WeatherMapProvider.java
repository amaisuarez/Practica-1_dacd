package control.controller;

import control.model.Location;
import control.model.Weather;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class WeatherMapProvider {

    private final APIWeatherFetcher weatherFetcher;
    private final WeatherDataProcessor dataProcessor;

    public WeatherMapProvider() {
        this.weatherFetcher = new APIWeatherFetcher();
        this.dataProcessor = new WeatherDataProcessor();
    }

    public List<Weather> processLocationsFromCSV(List<Location> locations, String apiKey) {
        List<Weather> weatherDataList = new ArrayList<>();

        for (Location location : locations) {
            JSONObject weatherResponse = weatherFetcher.getWeatherData(location, apiKey);
            List<Weather> weatherData = dataProcessor.processWeatherData(weatherResponse, location);
            for (Weather weather : weatherData) {
                weather.setLocation(location);
            }
            weatherDataList.addAll(weatherData);
        }

        return weatherDataList;
    }
}