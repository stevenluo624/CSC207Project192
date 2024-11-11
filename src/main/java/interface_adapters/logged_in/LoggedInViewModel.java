package interface_adapters.logged_in;

import interface_adapters.ViewModel;

/**
 * The View Model for the Logged In View.
 */
public class LoggedInViewModel extends ViewModel<LoggedInState> {
    public LoggedInViewModel() {
        super("logged in");
        setState(new LoggedInState());
    }
}