package control.controller;

import java.sql.*;
import control.model.*;
import java.util.List;


public class DataInserter {

    private static final String DATABASE_URL = "jdbc:sqlite:/Users/amaisuarez/IdeaProjects/practica1_variacion2/src/main/resources/database.db";

    public static void insertWeatherData(List<Weather> weatherDataList) {
        try (Connection connection = DriverManager.getConnection(DATABASE_URL)) {

            String insertQuery = "INSERT INTO database (location_name, latitude, longitude, date_time, " +
                    "temperature, description, cloudiness, wind_speed, precipitation, humidity) " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

            try (PreparedStatement preparedStatement = connection.prepareStatement(insertQuery)) {
                for (Weather weatherData : weatherDataList) {
                    preparedStatement.setString(1, weatherData.getLocation().getPlace());
                    preparedStatement.setDouble(2, weatherData.getLocation().getLatitude());
                    preparedStatement.setDouble(3, weatherData.getLocation().getLongitude());
                    preparedStatement.setString(4, weatherData.getDxTxt());
                    preparedStatement.setDouble(5, Math.round(weatherData.getTemperature()-273.00));
                    preparedStatement.setString(6, weatherData.getDescription());
                    preparedStatement.setDouble(7, weatherData.getCloudiness());
                    preparedStatement.setDouble(8, weatherData.getWindSpeed());
                    preparedStatement.setDouble(9, weatherData.getPrecipitation());
                    preparedStatement.setDouble(10, weatherData.getHumidity());

                    preparedStatement.executeUpdate();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

