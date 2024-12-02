package use_case.signup;

import entity.User;
import app.UserFactory;
import entity.UserProfile;
import use_case.profile.ProfileDataAccessInterface;

/**
 * The Signup Interactor.
 */
public class  SignupInteractor implements SignupInputBoundary {
    private final SignupUserDataAccessInterface userDataAccessObject;
    private final ProfileDataAccessInterface profileDataAccessObject;
    private final SignupOutputBoundary userPresenter;
    private final UserFactory userFactory;

    public SignupInteractor(SignupUserDataAccessInterface signupDataAccessInterface,
                            ProfileDataAccessInterface profileDataAccessObject,
                            SignupOutputBoundary signupOutputBoundary,
                            UserFactory userFactory) {
        this.userDataAccessObject = signupDataAccessInterface;
        this.profileDataAccessObject = profileDataAccessObject;
        this.userPresenter = signupOutputBoundary;
        this.userFactory = userFactory;
    }

    @Override
    public void execute(SignupInputData signupInputData) {
        if (userDataAccessObject.existsByName(signupInputData.getUsername())) {
            userPresenter.prepareFailView("User already exists.");
        }
        else if (!signupInputData.getPassword().equals(signupInputData.getRepeatPassword())) {
            userPresenter.prepareFailView("Passwords don't match.");
        }
        else {
            final User user = userFactory.create(signupInputData.getUsername(), signupInputData.getPassword());
            profileDataAccessObject.save(new UserProfile(user.getUsername(), ""));
            userDataAccessObject.save(user);

            final SignupOutputData signupOutputData = new SignupOutputData(user.getUsername(), false);
            userPresenter.prepareSuccessView(signupOutputData);
        }
    }

    @Override
    public void switchToLoginView() {
        userPresenter.switchToLoginView();
    }
}
