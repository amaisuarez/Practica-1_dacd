package control.controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class SQLController {

    private static final String DATABASE_URL = "jdbc:sqlite:/Users/amaisuarez/IdeaProjects/practica1_variacion2/src/main/resources/database.db";

    public static void createDatabase() {
        try {

            Class.forName("org.sqlite.JDBC");

            try (Connection connection = DriverManager.getConnection(DATABASE_URL);
                 Statement statement = connection.createStatement()) {

                statement.executeUpdate("DROP TABLE IF EXISTS database");

                String createTableQuery = "CREATE TABLE database (" +
                        "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                        "location_name TEXT," +
                        "latitude REAL," +
                        "longitude REAL," +
                        "date_time TEXT," +
                        "temperature REAL," +
                        "description TEXT," +
                        "cloudiness REAL," +
                        "wind_speed REAL," +
                        "precipitation REAL," +
                        "humidity REAL)";

                statement.executeUpdate(createTableQuery);
                System.out.println("Database created successfully.");

            } catch (SQLException e) {
                System.err.println("Error with the database: " + e.getMessage());
            }
        } catch (ClassNotFoundException e) {
            System.err.println("Error with SQLite: " + e.getMessage());
        }
    }
}
