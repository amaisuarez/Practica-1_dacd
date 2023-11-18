package control.controller;

import control.model.Location;
import control.model.Weather;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class WeatherDataProcessor {

    public List<Weather> processWeatherData(JSONObject jsonResponse, Location location) {
        List<Weather> weatherDataList = new ArrayList<>();

        JSONArray forecastList = jsonResponse.getJSONArray("list");
        for (int i = 0; i < forecastList.length(); i++) {
            JSONObject forecast = forecastList.getJSONObject(i);
            String dtTxt = forecast.getString("dt_txt");
            if (isForecastFor12PM(dtTxt)) {
                JSONObject main = forecast.getJSONObject("main");
                double temperature = main.getDouble("temp");
                double humidity = main.getDouble("humidity");

                double precipitation = getRainVolume(forecast);
                String description = getDescription(forecast);
                double cloudiness = forecast.getJSONObject("clouds").getInt("all");
                double windSpeed = forecast.getJSONObject("wind").getDouble("speed");

                Weather weatherData = new Weather(temperature, precipitation, humidity,
                        cloudiness, windSpeed, dtTxt, description, location);
                weatherDataList.add(weatherData);
            }
        }

        return weatherDataList;
    }

    private boolean isForecastFor12PM(String dtTxt) {
        return dtTxt.endsWith("12:00:00");
    }

    private double getRainVolume(JSONObject forecast) {
        if (forecast.has("rain") && forecast.getJSONObject("rain").has("3h")) {
            return forecast.getJSONObject("rain").getDouble("3h");
        }
        return 0.0;
    }

    private String getDescription(JSONObject forecast) {
        JSONArray weatherArray = forecast.getJSONArray("weather");
        if (weatherArray.length() > 0) {
            JSONObject weather = weatherArray.getJSONObject(0);
            return weather.getString("description");
        }
        return "No description";
    }
}