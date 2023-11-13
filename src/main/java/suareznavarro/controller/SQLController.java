package suareznavarro.controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class SQLController {

    private static final String URL = "jdbc:sqlite:weatherinfo.db";

    public static void createDatabase() {
        try (Connection connection = DriverManager.getConnection(URL);
             PreparedStatement preparedStatement = connection.prepareStatement(getCreateTableSQL())) {
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static String getCreateTableSQL() {
        return "CREATE TABLE IF NOT EXISTS weather_data (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "location_name TEXT," +
                "latitude REAL," +
                "longitude REAL," +
                "date_time TEXT," +
                "temperature REAL," +
                "description TEXT," +
                "cloudiness INTEGER," +
                "wind_speed REAL)";
    }

    public static void main(String[] args) {
        createDatabase();
    }
}
