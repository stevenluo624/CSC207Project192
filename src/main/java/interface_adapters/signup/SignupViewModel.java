package interface_adapters.signup;

import interface_adapters.ViewModel;

/**
 * The ViewModel for the Signup View.
 */
public class SignupViewModel extends ViewModel<interface_adapters.signup.SignupState> {

    public static final String TITLE_LABEL = "Sign Up View";
    public static final String USERNAME_LABEL = "Choose username";
    public static final String PASSWORD_LABEL = "Choose password";
    public static final String REPEAT_PASSWORD_LABEL = "Enter password again";

    public static final String SIGNUP_BUTTON_LABEL = "Sign up";
    public static final String CANCEL_BUTTON_LABEL = "Cancel";

    public static final String TO_LOGIN_BUTTON_LABEL = "Go to Login";

    public SignupViewModel() {
        super("sign up");
        setState(new interface_adapters.signup.SignupState());
    }

}
