package entity;

public class FoodLocation extends Location {
    private String type; // type of food served at this location

    public FoodLocation(String name, String type) {
        super(name);
        this.type = type;
    }

    public String getType() {
        return type;
    }
}
