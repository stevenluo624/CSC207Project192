package entity;

public class FoodLocation extends Location {
    private String type; // type of food served at this location

    public FoodLocation(String name, String latitude, String longitude, String type) {
        super(name, latitude, longitude);
        this.type = type;
    }

    public String getType() {
        return type;
    }
}
