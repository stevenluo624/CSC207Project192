package interface_adapters.like_review;

import interface_adapters.ViewManagerModel;
import interface_adapters.list_review.ListReviewState;
import interface_adapters.list_review.ListReviewViewModel;
import use_case.like_review.LikeReviewOutputBoundary;
import use_case.like_review.LikeReviewOutputData;

public class LikeReviewPresenter implements LikeReviewOutputBoundary {
    private final ViewManagerModel viewManagerModel;
    private final ListReviewViewModel loggedInViewModel;

    public LikeReviewPresenter(ViewManagerModel viewManagerModel, ListReviewViewModel loggedInViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.loggedInViewModel = loggedInViewModel;
    }

    @Override
    public void prepareSuccessView(LikeReviewOutputData response) {
        ListReviewState currentState = loggedInViewModel.getState();
        // Make sure to add appropriate methods to LoggedInState to handle this
        loggedInViewModel.setState(currentState);
        loggedInViewModel.firePropertyChanged();
    }

    @Override
    public void prepareFailView(String error) {
        ListReviewState currentState = loggedInViewModel.getState();
        currentState.setLikeError(error);  // Add this method to LoggedInState
        loggedInViewModel.setState(currentState);
        loggedInViewModel.firePropertyChanged();
    }
}