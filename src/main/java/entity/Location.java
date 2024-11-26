package entity;

import java.util.List;

public abstract class Location {
    String name;
    String description = "";
    String address = "";
    int rating = 0;
    double latitude = 0.0;
    double longitude = 0.0;

    public Location(String name, double latitude, double longitude) {
        this.name = name;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * Calculates the location's score based on a list of scores and updates rating
     * @param scores list of floats representing scores for this location
     */
    public void setRating(List<Integer> scores) {
        int scoreAcc = this.rating;
        for (int score : scores) {
            scoreAcc += score;
        }
        this.rating = scoreAcc / scores.size();
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getAddress() {
        return address;
    }

    public int getRating() {
        return rating;
    }

    public double getLatitude() { return latitude; }

    public double getLongitude() { return longitude; }
}
