package use_case.create_review;

import entity.Location;
import entity.User;

/**
 * The Input Data for Create Review.
 */
public class CreateReviewInputData {
    private final User user;
    private final int rating;
    private final String comment;

    public CreateReviewInputData(User user, int rating, String comment) {
        this.user = user;
        this.rating = rating;
        this.comment = comment;
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
}
