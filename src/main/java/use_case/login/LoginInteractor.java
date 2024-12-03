package use_case.login;

import entity.User;
import interface_adapters.profile.ProfileState;
import use_case.profile.ProfileDataAccessInterface;
import use_case.profile.ProfileOutputData;

/**
 * The Login Interactor.
 */
public class LoginInteractor implements LoginInputBoundary {
    private final LoginUserDataAccessInterface userDataAccessObject;
    private final ProfileDataAccessInterface profileDataAccessObject;
    private final LoginOutputBoundary loginPresenter;

    public LoginInteractor(LoginUserDataAccessInterface userDataAccessInterface,
                           LoginOutputBoundary loginOutputBoundary,
                           ProfileDataAccessInterface profileDataAccessInterface) {
        this.userDataAccessObject = userDataAccessInterface;
        this.profileDataAccessObject = profileDataAccessInterface;
        this.loginPresenter = loginOutputBoundary;
    }

    @Override
    public void execute(LoginInputData loginInputData) {
        final String username = loginInputData.getUsername();
        final String password = loginInputData.getPassword();
        if (!userDataAccessObject.existsByName(username)) {
            loginPresenter.prepareFailView(username + ": Account does not exist.");
        }
        else {
            final String pwd = userDataAccessObject.get(username).getPassword();
            if (!password.equals(pwd)) {
                loginPresenter.prepareFailView("Incorrect password for \"" + username + "\".");
            }
            else {

                final User user = userDataAccessObject.get(loginInputData.getUsername());
                userDataAccessObject.setCurrentUser(user.getUsername());
                final LoginOutputData loginOutputData = new LoginOutputData(user.getUsername(), false);
                loginPresenter.prepareSuccessView(loginOutputData);
                String bio = profileDataAccessObject.getBio(user.getUsername());
                loginPresenter.changeProfile(new ProfileOutputData(user.getUsername(), bio, false));

            }
        }
    }
}