package suareznavarro.controller;
import suareznavarro.model.Weather;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;


public interface WeatherStore {
    public class saveWeatherData {
        static final String DB_URL = "jdbc:mysql://localhost/";
        static final String USER = "Amai";
        static final String PASS = "123";

        public saveWeatherData(String island, LocalDate currentDate, Weather weatherData) {
        }

        public static void main(String[] args) {
            // Open a connection
            try(Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
                Statement stmt = conn.createStatement();
            ) {
                String sql = "CREATE DATABASE WEATHER";
                stmt.executeUpdate(sql);
                System.out.println("Database created successfully...");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

}