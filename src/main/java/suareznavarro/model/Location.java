package suareznavarro.model;

public class Location {
    private double latitude;
    private double longitude;

    private String place;

    // Constructor
    public Location(double latitude, double longitude, String place) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.place = place;
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

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

}
