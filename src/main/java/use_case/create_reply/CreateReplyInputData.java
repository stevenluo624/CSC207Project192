package use_case.create_reply;

import entity.Review;
import entity.User;

public class CreateReplyInputData {
    private final User user;
    private final String comment;
    private final Review review;

    public CreateReplyInputData(User user, String comment, Review review) {
        this.user = user;
        this.comment = comment;
        this.review = review;
    }

    public User getUser() {
        return user;
    }

    public String getComment() {
        return comment;
    }

    public Review getReview() {
        return review;
    }
}
