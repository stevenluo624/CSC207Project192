package interface_adapters.rate;

import use_case.rate.RateInputBoundary;

/**
 * Controller for our Note related Use Cases.
 */
public class RateController {

    private final RateInputBoundary rateInteractor;

    public RateController(RateInputBoundary rateInteractor) {
        this.rateInteractor = rateInteractor;
    }

    /**
     * Executes the Note related Use Cases.
     * @param review the rating to be recorded
     */
    public void execute(int review) {
        if (review > 0 && review <= 5) {
            rateInteractor.executeSave(review);
        }
        else {
            rateInteractor.executeRefresh();
        }
    }
}