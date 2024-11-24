package use_case.like_review;

/**
 * The Input Data for the Like Review use case.
 */
public class LikeReviewInputData {
    private final String username;
    private final String reviewId;

    public LikeReviewInputData(String username, String reviewId) {
        this.username = username;
        this.reviewId = reviewId;
    }
    public String getUsername() {return this.username;}
    public String getReviewId() {return this.reviewId;}
}
