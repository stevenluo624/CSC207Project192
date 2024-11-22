package entity;

public class Like {
    private final User user;
    private final UserReview review;


    public Like(User user, UserReview review) {
        this.user = user;
        this.review = review;
    }

    public User getUser() {
        return user;
    }

    public UserReview getReview() {
        return review;
    }
}
