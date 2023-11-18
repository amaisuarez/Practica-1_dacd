package control.controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import control.model.Location;
public class SQLController {

    private static final String DATABASE_URL = "jdbc:sqlite:src/main/resources/database.db";

    public static void createDatabase(List<Location> locations) {
        try {
            Class.forName("org.sqlite.JDBC");
            try (Connection connection = DriverManager.getConnection(DATABASE_URL);
                 Statement statement = connection.createStatement()) {

                for (Location location : locations) {
                    String tableName = location.getPlace().replaceAll("\\s+", "") + "Weather";
                    String createTableQuery = String.format(
                            "CREATE TABLE IF NOT EXISTS %s (" +
                                    "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                                    "date_time TEXT," +
                                    "temperature REAL," +
                                    "description TEXT," +
                                    "cloudiness REAL," +
                                    "wind_speed REAL," +
                                    "precipitation REAL," +
                                    "humidity REAL)",
                            tableName);

                    statement.executeUpdate(createTableQuery);
                }

                System.out.println("Database tables created successfully.");
            } catch (SQLException e) {
                System.err.println("Error with the database: " + e.getMessage());
            }
        } catch (ClassNotFoundException e) {
            System.err.println("Error with SQLite: " + e.getMessage());
        }
    }
}