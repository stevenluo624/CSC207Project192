package entity;

import entity.reviews_thread.Review;

public class Like {
    private final User user;
    private final Review review;


    public Like(User user, Review review) {
        this.user = user;
        this.review = review;
    }

    public User getUser() {
        return user;
    }

    public Review getReview() {
        return review;
    }
}
