package use_case.like_review;

public class LikeReviewOutputData {
    private final String reviewId;
    private final String username;
    private final int currentLikeCount;
    private final boolean success;


    public LikeReviewOutputData(String reviewId, String username, int currentLikeCount, boolean success) {
        this.reviewId = reviewId;
        this.username = username;
        this.currentLikeCount = currentLikeCount;
        this.success = success;
    }

    public String getReviewId() {
        return reviewId;
    }

    public String getUsername() {
        return username;
    }

    public int getCurrentLikeCount() {
        return currentLikeCount;
    }

    public boolean isSuccess() {
        return success;
    }
}
