package data_access.in_memory_dao;

import use_case.like_review.LikeReviewDataAccessInterface;

public class LikeInMemoryDAO implements LikeReviewDataAccessInterface {
    /**
     * @param username contains details of the user.
     * @param reviewId contains ID of the review that the user liked.
     * @return
     */
    @Override
    public boolean hasUserLikedReview(String username, String reviewId) {
        return false;
    }

    /**
     * @param username contains details of the user.
     * @param reviewId contains ID of the review that the user liked.
     */
    @Override
    public void saveLike(String username, String reviewId) {

    }

    /**
     * @param reviewId contains ID of the review that the user liked.
     * @return
     */
    @Override
    public int getLikeCount(String reviewId) {
        return 0;
    }
}
