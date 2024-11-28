package use_case.delete_review;

/**
 * The Input Data for the Delete Review use case.
 */
public class DeleteReviewInputData {
    private final String username;
    private final String reviewID;
    
    public DeleteReviewInputData(String username, String reviewID) {
        this.username = username;
        this.reviewID = reviewID;
    }
    public String getUsername() {
        return username; 
    }
    public String getReviewID() {
        return reviewID;
    }
}
