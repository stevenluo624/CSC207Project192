package use_case.create_review;

/**
 * The Input Boundary for Create Review use case.
 */
public interface CreateReviewInputBoundary {

    /**
     * Execute the use case.
     * @param inputData the input data for this use case
     */
    void execute(CreateReviewInputData inputData);
}
