package use_case.check_map;

/**
 * Output Data for the Check Map Use Case.
 */
public class CheckMapOutputData {

    private final String name;
    private final String latitude;
    private final String longitude;
    private final boolean useCaseFailed;

    public CheckMapOutputData(String name, String latitude, String longitude, boolean useCaseFailed) {
        this.name = name;
        this.latitude = latitude;
        this.longitude = longitude;
        this.useCaseFailed = useCaseFailed;
    }

    public String getName() {
        return name;
    }

    public String getLatitude() {
        return latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public boolean isUseCaseFailed() {
        return useCaseFailed;
    }
}
