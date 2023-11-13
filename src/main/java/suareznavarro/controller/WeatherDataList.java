package suareznavarro.controller;
import suareznavarro.model.*;


import java.util.ArrayList;
import java.util.List;

public class WeatherDataList {
    private List<Weather> weatherDataList;

    public WeatherDataList() {
        this.weatherDataList = new ArrayList<>();
    }

    public void addWeatherData(Location location, Weather weatherData) {
        weatherData.setLocation(location);
        weatherDataList.add(weatherData);
    }

    public List<Weather> getWeatherDataList() {
        return weatherDataList;
    }
}
