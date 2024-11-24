package interface_adapters.like_review;

import interface_adapters.ViewManagerModel;
import interface_adapters.change_password.LoggedInState;
import interface_adapters.change_password.LoggedInViewModel;
import use_case.like_review.LikeReviewOutputBoundary;
import use_case.like_review.LikeReviewOutputData;

public class LikeReviewPresenter implements LikeReviewOutputBoundary {
    private final ViewManagerModel viewManagerModel;
    private final LoggedInViewModel loggedInViewModel;

    public LikeReviewPresenter(ViewManagerModel viewManagerModel, LoggedInViewModel loggedInViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.loggedInViewModel = loggedInViewModel;
    }

    @Override
    public void prepareSuccessView(LikeReviewOutputData response) {
        LoggedInState currentState = loggedInViewModel.getState();
        // Make sure to add appropriate methods to LoggedInState to handle this
        loggedInViewModel.setState(currentState);
        loggedInViewModel.firePropertyChanged();
    }

    @Override
    public void prepareFailView(String error) {
        LoggedInState currentState = loggedInViewModel.getState();
        currentState.setLikeError(error);  // Add this method to LoggedInState
        loggedInViewModel.setState(currentState);
        loggedInViewModel.firePropertyChanged();
    }
}