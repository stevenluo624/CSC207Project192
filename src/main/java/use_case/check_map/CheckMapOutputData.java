package use_case.check_map;

/**
 * Output Data for the Check Map Use Case.
 */
public class CheckMapOutputData {

    private final String name;
    private final double latitude;
    private final double longitude;
    private final boolean useCaseFailed;

    public CheckMapOutputData(String name, double latitude, double longitude, boolean useCaseFailed) {
        this.name = name;
        this.latitude = latitude;
        this.longitude = longitude;
        this.useCaseFailed = useCaseFailed;
    }

    public String getName() {
        return name;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public boolean isUseCaseFailed() {
        return useCaseFailed;
    }
}
