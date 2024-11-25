package use_case.profile;

/**
 * Output Boundary for actions which are related to profile.
 */
public interface ProfileBoundary {

    /**
     * Executes the Profile use case.
     * @param ProfileInputData the input data
     */
    void execute(ProfileInputData profileInputData);
}
