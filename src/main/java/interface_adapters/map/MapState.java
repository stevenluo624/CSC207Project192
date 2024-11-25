package interface_adapters.map;

/**
 * The State Information representing the Map Display.
 */
public class MapState {
    private String name = "";
    private String nameError;
    private double latitude;
    private String latitudeError;
    private double longitude;
    private String longitudeError;

    public String getName() {
        return name;
    }

    public String getNameError() {
        return nameError;
    }

    public double getLatitude() {
        return latitude;
    }

    public String getLatittudeError() {
        return latitudeError;
    }

    public double getLongitude() {
        return longitude;
    }

    public String getLongitudeError() {
        return longitudeError;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setNameError(String nameError) {
        this.nameError = nameError;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public void setLatittudeError(String latittudeError) {
        this.latitudeError = latittudeError;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public void setLongitudeError(String longitudeError) {
        this.longitudeError = longitudeError;
    }
}
