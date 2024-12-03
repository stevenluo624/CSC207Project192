package use_case.create_review;

import entity.User;

/**
 * The Input Data for Create Review.
 */
public class CreateReviewInputData {
    private final User user;
    private final int rating;
    private final String comment;
    private final String locationName;

    public CreateReviewInputData(User user, int rating, String comment, String locationName) {
        this.user = user;
        this.rating = rating;
        this.comment = comment;
        this.locationName = locationName;
    }

    public User getUser() {
        return user;
    }

    public int getRating() {
        return rating;
    }

    public String getComment() {
        return comment;
    }

    public String getLocationName() { return locationName; }
}
