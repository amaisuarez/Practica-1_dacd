package control.controller;

import java.sql.*;
import control.model.*;
import java.util.List;


public class DataInserter {

    private static final String DATABASE_URL = "jdbc:sqlite:src/main/resources/database.db";

    public static void insertWeatherData(List<Weather> weatherDataList) {
        try (Connection connection = DriverManager.getConnection(DATABASE_URL)) {
            for (Weather weatherData : weatherDataList) {
                String tableName = weatherData.getLocation().getPlace().replaceAll("\\s+", "") + "Weather";
                String insertQuery = String.format(
                        "INSERT INTO %s (date_time, temperature, description, cloudiness, wind_speed, precipitation, humidity) " +
                                "VALUES (?, ?, ?, ?, ?, ?, ?)", tableName);

                try (PreparedStatement preparedStatement = connection.prepareStatement(insertQuery)) {
                    preparedStatement.setString(1, weatherData.getDxTxt());
                    preparedStatement.setDouble(2, Math.round(weatherData.getTemperature() - 273.00));
                    preparedStatement.setString(3, weatherData.getDescription());
                    preparedStatement.setDouble(4, weatherData.getCloudiness());
                    preparedStatement.setDouble(5, weatherData.getWindSpeed());
                    preparedStatement.setDouble(6, weatherData.getPrecipitation());
                    preparedStatement.setDouble(7, weatherData.getHumidity());

                    preparedStatement.executeUpdate();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
