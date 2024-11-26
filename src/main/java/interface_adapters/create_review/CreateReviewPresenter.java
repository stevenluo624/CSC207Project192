package interface_adapters.create_review;

import use_case.create_review.CreateReviewOutputBoundary;
import use_case.create_review.CreateReviewOutputData;

/**
 * The presenter for our Note viewing and editing program.
 */
public class CreateReviewPresenter implements CreateReviewOutputBoundary {

    private final CreateReviewViewModel createReviewViewModel;

    public CreateReviewPresenter(CreateReviewViewModel createReviewViewModel) {
        this.createReviewViewModel = createReviewViewModel;
    }

    /**
     * Prepares the success view for the Note related Use Cases.
     *
     * @param outputData the output data
     */
    @Override
    public void prepareSuccessView(CreateReviewOutputData outputData) {
        createReviewViewModel.getState().setRating(outputData.getRating());
        createReviewViewModel.getState().setRatingError(null);
        createReviewViewModel.firePropertyChanged();
    }

    /**
     * Prepares the failure view for the Note related Use Cases.
     *
     * @param message the explanation of the failure
     */
    @Override
    public void prepareFailureView(String message) {
        createReviewViewModel.getState().setRatingError(message);
        createReviewViewModel.firePropertyChanged();
    }
}