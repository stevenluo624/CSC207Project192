package interface_adapters.create_review;

import interface_adapters.ViewManagerModel;
import interface_adapters.list_review.ListReviewViewModel;
import use_case.create_review.CreateReviewOutputBoundary;
import use_case.create_review.CreateReviewOutputData;

/**
 * The presenter for our Note viewing and editing program.
 */
public class CreateReviewPresenter implements CreateReviewOutputBoundary {

    private final CreateReviewViewModel createReviewViewModel;
    private final ListReviewViewModel listReviewViewModel;
    private final ViewManagerModel viewManagerModel;

    public CreateReviewPresenter(CreateReviewViewModel createReviewViewModel,
                                 ViewManagerModel viewManagerModel,
                                 ListReviewViewModel listReviewViewModel) {
        this.createReviewViewModel = createReviewViewModel;
        this.viewManagerModel = viewManagerModel;
        this.listReviewViewModel = listReviewViewModel;
    }

    /**
     * Prepares the success view for the Note related Use Cases.
     *
     * @param outputData the output data
     */
    @Override
    public void prepareSuccessView(CreateReviewOutputData outputData) {
        createReviewViewModel.getState().setRating(outputData.getRating());
        createReviewViewModel.getState().setError(null);
        createReviewViewModel.firePropertyChanged();

        viewManagerModel.setState(listReviewViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }

    /**
     * Prepares the failure view for the Note related Use Cases.
     *
     * @param message the explanation of the failure
     */
    @Override
    public void prepareFailureView(String message) {
        createReviewViewModel.getState().setError(message);
        createReviewViewModel.firePropertyChanged();
    }

    /**
     * Switches to List of Reviews View.
     */
    @Override
    public void switchToListReviewView() {
        viewManagerModel.setState(listReviewViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }
}
