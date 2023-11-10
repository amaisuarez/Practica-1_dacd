package suareznavarro;

public class Location {
    private double latitude;
    private double longitude;

    // Constructor
    public Location(double latitude, double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }

    // Getter para obtener la latitud
    public double getLatitude() {
        return latitude;
    }

    // Setter para establecer la latitud
    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    // Getter para obtener la longitud
    public double getLongitude() {
        return longitude;
    }

    // Setter longitude
    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }
}
