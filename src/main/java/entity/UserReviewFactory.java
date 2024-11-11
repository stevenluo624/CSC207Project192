package entity;

public interface UserReviewFactory {
    /**
     * Creates a new UserReview.
     * @param user the user who created the review
     * @param rating the rating given in the review
     * @param comment the comment provided in the review
     * @return the new user review
     */
    UserReview create(User user, double rating, String comment);
}
