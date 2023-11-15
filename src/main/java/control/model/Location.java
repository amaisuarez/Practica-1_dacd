package control.model;

public class Location {
    private double latitude;
    private double longitude;
    private String place;

    public Location(double latitude, double longitude, String place) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.place = place;
    }
    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public String getPlace() {
        return place;
    }


}
