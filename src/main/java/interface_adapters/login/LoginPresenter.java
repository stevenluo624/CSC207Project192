package interface_adapters.login;

import entity.StudentUser;
import interface_adapters.ViewManagerModel;
import interface_adapters.list_review.ListReviewState;
import interface_adapters.list_review.ListReviewViewModel;
import interface_adapters.profile.ProfileState;
import interface_adapters.profile.ProfileViewModel;
import use_case.login.LoginOutputBoundary;
import use_case.login.LoginOutputData;
import use_case.profile.ProfileOutputData;
import view.ProfileView;

/**
 * The Presenter for the Login Use Case.
 */
public class LoginPresenter implements LoginOutputBoundary {

    private final LoginViewModel loginViewModel;
    private final ListReviewViewModel listReviewViewModel;
    private final ViewManagerModel viewManagerModel;
    private final ProfileViewModel profileViewModel;

    public LoginPresenter(ViewManagerModel viewManagerModel,
                          ListReviewViewModel listReviewViewModel,
                          LoginViewModel loginViewModel,
                          ProfileViewModel profileViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.listReviewViewModel = listReviewViewModel;
        this.loginViewModel = loginViewModel;
        this.profileViewModel = profileViewModel;
    }

    @Override
    public void prepareSuccessView(LoginOutputData response) {
        // On success, switch to the logged in view.

        final ListReviewState listReviewState = listReviewViewModel.getState();
        listReviewState.setCurrentUser(response.getUsername());
        final LoginState loginState = loginViewModel.getState();
        listReviewState.setCurrentUserObject(new StudentUser(loginState.getUsername(), loginState.getPassword()));
        this.listReviewViewModel.setState(listReviewState);
        this.listReviewViewModel.firePropertyChanged();

        this.viewManagerModel.setState(listReviewViewModel.getViewName());
        this.viewManagerModel.firePropertyChanged();
    }

    @Override
    public void prepareFailView(String error) {
        final LoginState loginState = loginViewModel.getState();
        loginState.setLoginError(error);
        loginViewModel.firePropertyChanged();
    }

    @Override
    public void changeProfile(ProfileOutputData profileOutputData) {
        ProfileState profileState = profileViewModel.getState();
        profileState.setUsername(profileOutputData.getUsername());
        profileState.setBio(profileOutputData.getBio());
    }
}