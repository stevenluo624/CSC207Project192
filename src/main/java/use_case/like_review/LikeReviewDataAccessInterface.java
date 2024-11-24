package use_case.like_review;

public interface LikeReviewDataAccessInterface {
    boolean hasUserLikedReview(String username, String reviewId);
    void saveLike(String username, String reviewId);
    int getLikeCount(String reviewId);
}
