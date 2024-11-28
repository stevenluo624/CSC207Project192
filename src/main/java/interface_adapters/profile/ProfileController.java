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
     * @param user the user that is creating the porfile.
     * @param profile the profile that the user is creating.
     */

    public void execute(User user, Profile profile) {
        final ProfileInputData profileInputData = new ProfileInputData(user, profile.getBio());

        profileInteractor.excute(profileInputData);
    }


}

