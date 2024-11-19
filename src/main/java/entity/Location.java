package entity;

import java.util.List;

public abstract class Location {
    String name;
    String description = "";
    String address = "";
    float rating = 0;

    public Location(String name) {
        this.name = name;
    }

    public void setName(String name) {
        this.name = name;
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
    public void setRating(List<Float> scores) {
        float scoreAcc = this.rating;
        for (float score : scores) {
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

    public float getRating() {
        return rating;
    }
}
