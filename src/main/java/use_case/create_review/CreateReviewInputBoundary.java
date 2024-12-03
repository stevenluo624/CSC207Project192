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

    /**
     * Executes the switch to list of reviews use case.
     */
    void switchToListReviewView();
}
