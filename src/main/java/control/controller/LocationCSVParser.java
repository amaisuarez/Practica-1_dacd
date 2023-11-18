package control.controller;

import control.model.Location;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class LocationCSVParser {

    public static List<Location> getLocationsFromCSV(String csvFilePath) {
        List<Location> locations = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader("src/main/resources/places.csv"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                String name = parts[0].trim();
                double latitude = Double.parseDouble(parts[1].trim());
                double longitude = Double.parseDouble(parts[2].trim());

                locations.add(new Location(latitude, longitude, name));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return locations;
    }
}