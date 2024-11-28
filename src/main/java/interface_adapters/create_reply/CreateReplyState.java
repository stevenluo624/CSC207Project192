package interface_adapters.create_reply;

import entity.reviews_thread.Review;
import entity.User;

/**
 * The initial State for a reply.
 */
public class CreateReplyState {
    private User user;
    private String comment;
    private Review review;
    private String error;

    public User getUser() {
        return user;
    }

    public String getComment() {
        return comment;
    }

    public Review getReview() {
        return review;
    }

    public String getError() {
        return error;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public void setReview(Review review) {
        this.review = review;
    }

    public void setError(String message) {
        this.error = message;
    }
}
