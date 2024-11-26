package interface_adapters.profile;

import interface_adapters.ViewModel;

/**
 * The View Model for the ProfileFile in View.
 */
public class ProfileViewModel extends ViewModel<ProfileState> {

    public ProfileViewModel() {
        super("profile");
        setState(new ProfileState());
    }
}
