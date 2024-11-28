package entity;

public class StudyLocation extends Location {
    private final String building;

    public StudyLocation(String name, String latitude, String longitude, String building) {
        super(name, latitude, longitude);
        this.building = building;
    }

    public String getBuilding() {
        return building;
    }
}
