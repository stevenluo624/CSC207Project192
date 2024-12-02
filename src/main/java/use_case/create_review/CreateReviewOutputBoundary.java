package use_case.create_review;

/**
 * The Output Boundary for Create Review use case.
 */
public interface CreateReviewOutputBoundary {

    /**
     * Prepare the view for the Create Review use case.
     * @param outputData the output data
     */
    void prepareSuccessView(CreateReviewOutputData outputData);

    /**
     * Prepare the view for the Create Review use case when it fails.
     * @param message the error message
     */
    void prepareFailureView(String message);

    /**
     * Switches to List of Reviews View.
     */
    void switchToListReviewView();
}
