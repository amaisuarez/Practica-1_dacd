package suareznavarro.controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class WeatherSQL {

    private static final String URL = "jdbc:sqlite:weather_database.db";

    public static void insertWeatherData(String locationName, double latitude, double longitude,
                                         String dateTime, double temperature, String description, int cloudiness, double windSpeed) {
        String sql = "INSERT INTO weather_data (location_name, latitude, longitude, date_time, temperature, description, cloudiness, wind_speed) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection connection = DriverManager.getConnection(URL);
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setString(1, locationName);
            preparedStatement.setDouble(2, latitude);
            preparedStatement.setDouble(3, longitude);
            preparedStatement.setString(4, dateTime);
            preparedStatement.setDouble(5, temperature);
            preparedStatement.setString(6, description);
            preparedStatement.setInt(7, cloudiness);
            preparedStatement.setDouble(8, windSpeed);

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
/*
    public static void main(String[] args) {
        // Ejemplo de uso para insertar datos
        insertWeatherData();
    }

 */


}

