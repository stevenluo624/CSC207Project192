package use_case.profile;

import entity.Profile;
import entity.UserProfile;

/**
 * The ProfileFile Interactor.
 */
public class ProfileInteractor implements ProfileInputBoundary {
    private final ProfileDataAccessInterface dataAccess;
    private final ProfileOutputBoundary presenter;

    public ProfileInteractor(ProfileDataAccessInterface dataAccess, ProfileOutputBoundary presenter) {
        this.dataAccess = dataAccess;
        this.presenter = presenter;
    }

    @Override
    public void excute(ProfileInputData inputData) {
        //final String bio = dataAccess.getBio(inputData.getBio());
        dataAccess.update(inputData.getUsername(), inputData.getBio());

        final ProfileOutputData outputData = new ProfileOutputData(inputData.getUsername(), inputData.getBio(), false);
        presenter.prepareSuccessView(outputData);
    }

    @Override
    public void switchToListReviewView() {
        presenter.switchToListReviewView();
    }


}
