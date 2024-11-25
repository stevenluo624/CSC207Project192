package use_case.check_map;

/**
 * The input data for the Check Map Use Case
 */
public class CheckMapInputData {

    private final String name;
    private final double latitude;
    private final double longitude;

    public CheckMapInputData(String name, double latitude, double longitude) {
        this.name = name;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    String getName() {
        return name;
    }

    double getLatitude() {
        return latitude;
    }

    double getLongitude() {
        return longitude;

    }
}
