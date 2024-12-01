package interface_adapters.profile;

import interface_adapters.ViewManagerModel;
import interface_adapters.profile.ProfileState;
import interface_adapters.profile.ProfileViewModel;
import use_case.profile.ProfileOutputBoundary;
import use_case.profile.ProfileOutputData;

/**
 * The Presenter for the ProfileFile Use Case.
 */
public class ProfilePresenter implements ProfileOutputBoundary {

    private final ProfileViewModel profileViewModel;
    private final ViewManagerModel viewManagerModel;

    public ProfilePresenter(ViewManagerModel viewManagerModel, ProfileViewModel profileViewModel) {
        this.profileViewModel = profileViewModel;
        this.viewManagerModel = viewManagerModel;
    }

    @Override
    public void prepareSuccessView(ProfileOutputData outputData) {
        final ProfileState profileState = profileViewModel.getState();
        profileState.setUsername(outputData.getUsername());
        profileState.setBio(outputData.getBio());

        this.profileViewModel.setState(profileState);
        this.profileViewModel.firePropertyChanged();

        this.viewManagerModel.setState(profileViewModel.getViewName());
        this.viewManagerModel.firePropertyChanged();
    }

    @Override
    public void prepareFailView(String errorMessage) {
        final ProfileState profileState = profileViewModel.getState();
        profileState.setProfileError(errorMessage);
        profileViewModel.firePropertyChanged();
    }
}

