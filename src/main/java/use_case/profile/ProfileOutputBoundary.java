package use_case.profile;

import use_case.login.LoginOutputData;

/**
 * Output Boundary for actions which are related to profile.
 */
public interface ProfileOutputBoundary {

    /**
     * Executes the ProfileFile use case.
     * @param profileInputData the input data
     */
    void execute(ProfileOutputData profileInputData);

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
