package suareznavarro.controller;

import suareznavarro.model.Weather;

public interface WeatherProvider {
    Weather getWeatherData(double lat, double lon);
    }


