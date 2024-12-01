package use_case.profile;

/**
 * Output Boundary for actions which are related to profile.
 */
public interface ProfileOutputBoundary {

    /**
     * Prepares the success view for the Login Use Case.
     * @param outputData the output data
     */
    void prepareSuccessView(ProfileOutputData outputData);

    /**
     * Prepares the failure view for the Login Use Case.
     * @param errorMessage the explanation of the failure
     */
    void prepareFailView(String errorMessage);
}
