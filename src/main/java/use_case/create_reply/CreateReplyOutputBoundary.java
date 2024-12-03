package use_case.create_reply;

import use_case.create_review.CreateReviewOutputData;

public interface CreateReplyOutputBoundary {

    /**
     * Prepare the view for the use case
     * @param outputData the output data
     */
    void prepareSuccessView(CreateReplyOutputData outputData);

    /**
     * Prepare the view for the use case when it fails
     * @param message the error message
     */
    void prepareFailureView(String message);
}
