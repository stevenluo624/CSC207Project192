package interface_adapters.map;

/**
 * The State Information representing the Map Display
 */
public class MapState {
    private String name = "";
    private String nameError;
    private String latitude;
    private String latitudeError;
    private String longitude;
    private String longitudeError;

    public String getName() {
        return name;
    }

    public String getNameError() {
        return nameError;
    }

    public String getLatitude() {
        return latitude;
    }

    public String getLatitudeError() {
        return latitudeError;
    }

    public String getLongitude() {
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

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public void setLatitudeError(String latittudeError) {
        this.latitudeError = latittudeError;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public void setLongitudeError(String longitudeError) {
        this.longitudeError = longitudeError;
    }
}
