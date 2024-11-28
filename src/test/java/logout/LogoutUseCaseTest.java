package logout;

import interface_adapters.ViewManagerModel;
import interface_adapters.change_password.LoggedInState;
import interface_adapters.change_password.LoggedInViewModel;
import interface_adapters.login.LoginViewModel;
import interface_adapters.logout.LogoutPresenter;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import use_case.logout.LogoutInputBoundary;
import use_case.logout.LogoutInputData;
import use_case.logout.LogoutInteractor;
import use_case.logout.LogoutOutputBoundary;

import static org.junit.jupiter.api.Assertions.*;


public class LogoutUseCaseTest {
    private LogoutInputBoundary logoutInteractor;
    private ViewManagerModel viewManagerModel;
    private LoginViewModel loginViewModel;
    private LoggedInViewModel loggedInViewModel;

    @BeforeEach
    void setUp() {
        viewManagerModel = new ViewManagerModel();
        loginViewModel = new LoginViewModel();
        loggedInViewModel = new LoggedInViewModel();

        LogoutOutputBoundary presenter = new LogoutPresenter(viewManagerModel, loggedInViewModel, loginViewModel);

        logoutInteractor = new LogoutInteractor(presenter);
    }

    @Test
    void successfulLogout() {
        LoggedInState loggedInState = loggedInViewModel.getState();
        loggedInState.setUsername("testUser");
        loggedInState.setPassword("testPass");
        loggedInViewModel.setState(loggedInState);

        LogoutInputData inputData = new LogoutInputData("testUser");
        logoutInteractor.execute(inputData);

        assertNull(loggedInViewModel.getState().getUsername());
        assertNull(loggedInViewModel.getState().getPassword());
        assertEquals("log in", viewManagerModel.getViewName());
    }
}
