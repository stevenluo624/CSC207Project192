package use_case.profile;

/**
 * The ProfileFile Use Case
 */
public interface ProfileInputBoundary {

    /**
     * Excute the ProfileFile Use Case
     * @param inputData the output data for this use case
     */
    void excute (ProfileInputData inputData);

    /**
     * Executes the switch to list of reviews use case.
     */
    void switchToListReviewView();
}
