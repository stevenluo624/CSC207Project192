package app;

import entity.Review;
import entity.User;

public interface ReviewFactory {
    /**
     * Creates a new Review.
     * @param user the user who created the review
     * @param rating the rating given in the review
     * @param comment the comment provided in the review
     * @return the new user review
     */
    Review create(User user, int rating, String comment);
}
