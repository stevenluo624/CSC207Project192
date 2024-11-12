package interface_adapters.rate;

import use_case.create_review.CreateReviewOutputBoundary;
import use_case.create_review.CreateReviewOutputData;

/**
 * The presenter for our Note viewing and editing program.
 */
public class RatePresenter implements CreateReviewOutputBoundary {

    private final RateViewModel rateViewModel;

    public RatePresenter(RateViewModel rateViewModel) {
        this.rateViewModel = rateViewModel;
    }

    /**
     * Prepares the success view for the Note related Use Cases.
     *
     * @param outputData the output data
     */
    @Override
    public void prepareSuccessView(CreateReviewOutputData outputData) {
        rateViewModel.getState().setRating(outputData.getRating());
        rateViewModel.getState().setRatingError(null);
        rateViewModel.firePropertyChanged();
    }

    /**
     * Prepares the failure view for the Note related Use Cases.
     *
     * @param message the explanation of the failure
     */
    @Override
    public void prepareFailureView(String message) {
        rateViewModel.getState().setRatingError(message);
        rateViewModel.firePropertyChanged();
    }
}
