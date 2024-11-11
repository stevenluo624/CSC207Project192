package entity;

public class StudyLocation extends Location {
    private final String building;

    public StudyLocation(String name, String building) {
        super(name);
        this.building = building;
    }

    public String getBuilding() {
        return building;
    }
}
