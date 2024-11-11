package interface_adapters.rate;

import entity.User;
import use_case.create_review.CreateReviewInputBoundary;
import use_case.create_review.CreateReviewInputData;
import use_case.create_review.CreateReviewInteractor;

/**
 * Controller for our Note related Use Cases.
 */
public class RateController {

    private final CreateReviewInteractor rateInteractor;

    public RateController(CreateReviewInteractor rateInteractor) {
        this.rateInteractor = rateInteractor;
    }

    /**
     * Executes the Note related Use Cases.
     * @param rating the rating to be recorded
     * @param user the user inputed the rating
     * @param comment the comment the user made
     */
    public void execute(User user, int rating, String comment) {
        final CreateReviewInputData createReviewInputData = new CreateReviewInputData(user, rating, comment);

        rateInteractor.execute(createReviewInputData);
    }
}