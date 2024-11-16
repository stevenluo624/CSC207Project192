package use_case.list_review;

/**
 * Output boundary for the list reviews use case
 */
public interface ListReviewOutputBoundary {

    /**
     * Prepares the success view for list reviews.
     * @param outputData contains output data.
     */
    void prepareSuccessView(ListReviewOutputData outputData);


    /**
     * Prepares the failure view for list reviews.
     * @param errorMessage contains the failure message.
     */
    void prepareFailView(String errorMessage);
}
