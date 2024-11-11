package interface_adapters.rate;

import use_case.rate.RateteOutputBoundary;

/**
 * The presenter for our Note viewing and editing program.
 */
public class RatePresenter implements RateOutputBoundary {

    private final RateViewModel rateViewModel;

    public RatePresenter(RateViewModel rateViewModel) {
        this.rateViewModel = rateViewModel;
    }

    /**
     * Prepares the success view for the Note related Use Cases.
     *
     * @param rating the output data
     */
    @Override
    public void prepareSuccessView(int rating) {
        rateViewModel.getState().setRating(rating);
        rateViewModel.getState().setError(null);
        rateViewModel.firePropertyChanged();
    }

    /**
     * Prepares the failure view for the Note related Use Cases.
     *
     * @param errorMessage the explanation of the failure
     */
    @Override
    public void prepareFailView(String errorMessage) {
        rateViewModel.getState().setError(errorMessage);
        rateViewModel.firePropertyChanged();
    }
}
