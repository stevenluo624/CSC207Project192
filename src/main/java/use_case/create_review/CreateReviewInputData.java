package use_case.create_review;

import entity.User;

public class CreateReviewInputData {
    private final User user;
    private final double rating;
    private final String comment;

    public CreateReviewInputData(User user, double rating, String comment) {
        this.user = user;
        this.rating = rating;
        this.comment = comment;
    }

    public User getUser() {
        return user;
    }

    public double getRating() {
        return rating;
    }

    public String getComment() {
        return comment;
    }
}
