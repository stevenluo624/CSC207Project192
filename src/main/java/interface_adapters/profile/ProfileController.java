package interface_adapters.profile;

import entity.User;
import entity.Profile;
import use_case.profile.ProfileInputBoundary;
import use_case.profile.ProfileInputData;
import use_case.profile.ProfileInteractor;

/**
 * The Countroller for the ProfileFile Use Case.
 */
public class ProfileController {

    private final ProfileInteractor profileInteractor;

    public ProfileController(ProfileInteractor profileInteractor) {this.profileInteractor = profileInteractor;}

    /**
     * Executes the Note related Use Cases.
     * @param username the user that is creating the porfile.
     * @param bio the profile that the user is creating.
     */

    public void execute(String username, String bio) {
        final ProfileInputData profileInputData = new ProfileInputData(username, bio);

        profileInteractor.excute(profileInputData);
    }


    public void switchToListReviewView() {
        profileInteractor.switchToListReviewView();
    }
}

