package suareznavarro;

import org.json.JSONArray;
import org.json.JSONObject;

import java.net.HttpURLConnection;
import java.net.URL;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class WeatherMapProvider {

    public static void main(String[] args) {
        String apiUrl = "https://api.openweathermap.org/data/2.5/forecast?lat=28.1003&lon=-15.6997&appid=bef3ed697c1ead2aa693ab8b1b539fdb";

        try {
            // Crear la conexión
            URL url = new URL(apiUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            // Configurar la solicitud
            connection.setRequestMethod("GET");

            // Obtener la respuesta
            int responseCode = connection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                // Leer la respuesta
                try (BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
                    StringBuilder response = new StringBuilder();
                    String line;

                    while ((line = reader.readLine()) != null) {
                        response.append(line);
                    }

                    // Procesar la respuesta como JSON
                    JSONObject jsonResponse = new JSONObject(response.toString());

                    // Extraer información específica
                    JSONArray forecastList = jsonResponse.getJSONArray("list");
                    for (int i = 0; i < forecastList.length(); i++) {
                        JSONObject forecast = forecastList.getJSONObject(i);
                        JSONObject main = forecast.getJSONObject("main");
                        double temperature = main.getDouble("temp");

                        JSONArray weatherArray = forecast.getJSONArray("weather");
                        JSONObject weather = weatherArray.getJSONObject(0);
                        String description = weather.getString("description");

                        JSONObject clouds = forecast.getJSONObject("clouds");
                        int cloudiness = clouds.getInt("all");

                        JSONObject wind = forecast.getJSONObject("wind");
                        double windSpeed = wind.getDouble("speed");

                        // Puedes imprimir o utilizar los datos como desees
                        System.out.println("Fecha: " + forecast.getString("dt_txt"));
                        System.out.println("Temperatura: " + temperature);
                        System.out.println("Descripción: " + description);
                        System.out.println("Nubosidad: " + cloudiness);
                        System.out.println("Velocidad del viento: " + windSpeed);
                        System.out.println("--------");
                    }
                }
            } else {
                System.out.println("Error al obtener la respuesta. Código de respuesta: " + responseCode);
            }

            // Cerrar la conexión
            connection.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
