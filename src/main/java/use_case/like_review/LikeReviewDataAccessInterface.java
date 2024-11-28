package use_case.like_review;

/**
 *  DAO for the creae like review use case.
 */
public interface LikeReviewDataAccessInterface {
    /**
     * Checks if the user has already liked the review.
     * @param username contains details of the user.
     * @param reviewId contains ID of the review that the user liked.
     * @return True if user has already liked the review and false if not.
     */
    boolean hasUserLikedReview(String username, String reviewId);

    /**
     * Saves the users like.
     * @param username contains details of the user.
     * @param reviewId contains ID of the review that the user liked.
     */
    void saveLike(String username, String reviewId);

    /**
     * Gets the amount of likes that the review has.
     * @param reviewId contains ID of the review that the user liked.
     * @return the number of likes.
     */
    int getLikeCount(String reviewId);
}
