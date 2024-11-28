package use_case.delete_review;

/**
 * DAO for the delete review use case
 */
public interface DeleteReviewDataAccessInterface {
    /**
     * Checkes if the user owns the review.
     * @param currentusername this is the current user's username.
     * @param reviewID contains ID of the review that the current user is looking at.
     * @param username this is the username that write the review.
     * @return True if current user owns the review and false if not.
     */
    boolean userOwnReview(String currentusername, String username, String reviewID);

    /**
     * Delte the user's cuurent viewing review
     * @param reviewID contains ID of the review that the current user is looking at.
     */
    void deleteReview(String reviewID);
}   
