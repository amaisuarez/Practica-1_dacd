package suareznavarro.controller;

import suareznavarro.model.Location;
import suareznavarro.model.Weather;

import java.time.LocalDate;

public class WeatherController {
    private Location location;
    private int days;
    private WeatherProvider weatherProvider;
    private WeatherStore weatherStore;

    public WeatherController(Location location, int days, WeatherProvider weatherProvider, WeatherStore weatherStore) {
        this.location = location;
        this.days = days;
        this.weatherProvider = weatherProvider;
        this.weatherStore = weatherStore;
    }

    public void execute() {
        LocalDate currentDate = LocalDate.now();
        Weather weatherData = weatherProvider.getWeatherData(location.getLatitude(), location.getLongitude());
        String island = "NombreDeLaIsla";
        new WeatherStore.saveWeatherData(island, currentDate, weatherData);
    }
}
