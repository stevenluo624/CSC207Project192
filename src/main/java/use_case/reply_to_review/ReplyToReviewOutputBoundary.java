package use_case.reply_to_review;

public interface ReplyToReviewOutputBoundary {

    /**
     * Prepare the view for the ReplyToReview use case
     * @param outputData the output data
     */
    void prepareSuccessView(ReplyToReviewOutputData outputData);

    /**
     * Prepare the view for the ReplyToReview use case when it fails
     * @param message the error message
     */
    void prepareFailureView(String message);
}
