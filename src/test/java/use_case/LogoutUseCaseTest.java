package use_case;

import interface_adapters.ViewManagerModel;
import interface_adapters.change_password.LoggedInState;
import interface_adapters.change_password.LoggedInViewModel;
import interface_adapters.login.LoginViewModel;
import interface_adapters.logout.LogoutPresenter;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import use_case.logout.*;

import static org.junit.jupiter.api.Assertions.*;


public class LogoutUseCaseTest {
    private LogoutInputBoundary logoutInteractor;
    private ViewManagerModel viewManagerModel;
    private LoginViewModel loginViewModel;
    private LoggedInViewModel loggedInViewModel;
    private LogoutUserDataAccessInterface userDataAccessObject;

    private static class TestUserDataAccessObject implements LogoutUserDataAccessInterface {
        private String currentUsername;

        @Override
        public String getCurrentUsername() {
            return currentUsername;
        }

        @Override
        public void setCurrentUsername(String username) {
            this.currentUsername = username;
        }
    }

    @BeforeEach
    void setUp() {
        viewManagerModel = new ViewManagerModel();
        loginViewModel = new LoginViewModel();
        loggedInViewModel = new LoggedInViewModel();
        userDataAccessObject = new TestUserDataAccessObject();

        LogoutOutputBoundary presenter = new LogoutPresenter(
                viewManagerModel,
                loggedInViewModel,
                loginViewModel
        );

        logoutInteractor = new LogoutInteractor(userDataAccessObject,presenter);
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
        assertEquals("log in", viewManagerModel.getActiveView());

        assertEquals("", loginViewModel.getState().getUsername());
        assertEquals("", loginViewModel.getState().getPassword());
    }
}
