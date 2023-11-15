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

    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }

    public double getPrecipitation() {
        return precipitation;
    }

    public void setPrecipitation(double precipitation) {
        this.precipitation = precipitation;
    }

    public double getHumidity() {
        return humidity;
    }

    public void setHumidity(double humidity) {
        this.humidity = humidity;
    }

    public double getCloudiness() {
        return cloudiness;
    }

    public void setCloudiness(double cloudiness) {
        this.cloudiness = cloudiness;
    }

    public double getWindSpeed() {
        return windSpeed;
    }

    public void setWindSpeed(double windSpeed) {
        this.windSpeed = windSpeed;
    }

    public String getDxTxt() {
        return dxTxt;
    }

    public void setDxTxt(String dxTxt) {
        this.dxTxt = dxTxt;
    }

    public void setDescription(String description) {
        this.description = description;
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
