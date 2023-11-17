package control.model;

public class Weather {
    private double temperature;
    private double precipitation;
    private double humidity;
    private double cloudiness;
    private double windSpeed;
    private Location location;

    private String dxTxt;

    private String description;

    // getters and setters of weather
    public Weather(double temperature, double precipitation, double humidity, double cloudiness, double windSpeed, String dxTxt, String description) {
        this.temperature = temperature;
        this.precipitation = precipitation;
        this.humidity = humidity;
        this.cloudiness = cloudiness;
        this.windSpeed = windSpeed;
        this.dxTxt = dxTxt;
        this.description = description;

    }

    public double getTemperature() {
        return temperature;
    }

    public double getPrecipitation() {
        return precipitation;
    }

    public double getHumidity() {
        return humidity;
    }

    public double getCloudiness() {
        return cloudiness;
    }

    public double getWindSpeed() {
        return windSpeed;
    }

    public String getDxTxt() {
        return dxTxt;
    }

    public String getDescription() {
        return description;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;

    }
}
