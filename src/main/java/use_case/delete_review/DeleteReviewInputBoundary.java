package use_case.delete_review;

/**
 * The Delete Review Use Case.
 */
public interface DeleteReviewInputBoundary {

    /**
     * Execute the Delete Use Case.
     * @param deleteReviewInputData the input data for this use case
     */
    void execute (DeleteReviewInputData deleteReviewInputData);
}
