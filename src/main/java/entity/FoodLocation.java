package entity;

public class FoodLocation extends Location {
    private String type; // type of food served at this location

    public FoodLocation(String name, double latitude, double longitude, String type) {
        super(name, latitude, longitude);
        this.type = type;
    }

    public String getType() {
        return type;
    }
}
