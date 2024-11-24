package interface_adapters.logout;

import interface_adapters.ViewManagerModel;
import interface_adapters.change_password.LoggedInState;
import interface_adapters.change_password.LoggedInViewModel;
import interface_adapters.login.LoginState;
import interface_adapters.login.LoginViewModel;
import use_case.logout.LogoutOutputBoundary;
import use_case.logout.LogoutOutputData;

/**
 * The Presenter for the Logout Use Case.
 */
public class LogoutPresenter implements LogoutOutputBoundary {

    private final LoggedInViewModel loggedInViewModel;
    private final ViewManagerModel viewManagerModel;
    private final LoginViewModel loginViewModel;

    public LogoutPresenter(ViewManagerModel viewManagerModel,
                           LoggedInViewModel loggedInViewModel,
                           LoginViewModel loginViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.loggedInViewModel = loggedInViewModel;
        this.loginViewModel = loginViewModel;
    }

    @Override
    public void prepareSuccessView(LogoutOutputData response) {
        final LoggedInState newLoggedInState = loggedInViewModel.getState();
        newLoggedInState.setUsername(null);
        newLoggedInState.setPassword(null);
        loggedInViewModel.setState(newLoggedInState);
        loggedInViewModel.firePropertyChanged();

        final LoginState newLoginState = loginViewModel.getState();
        newLoginState.setUsername("");
        newLoginState.setPassword("");
        loginViewModel.setState(newLoginState);
        loginViewModel.firePropertyChanged();


        this.viewManagerModel.setState(loginViewModel.getViewName());
        this.viewManagerModel.firePropertyChanged();
    }

    @Override
    public void prepareFailView(String error) {
    }
}
