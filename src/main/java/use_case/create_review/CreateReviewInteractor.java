package use_case.create_review;

import entity.Location;
import entity.reviews_thread.Review;

/**
 * The Interactor for create review use case.
 */
public class CreateReviewInteractor implements CreateReviewInputBoundary {
    private final CreateReviewDataAccessInterface dataAccess;
    private final CreateReviewOutputBoundary presenter;

    public CreateReviewInteractor(CreateReviewDataAccessInterface dataAccess, CreateReviewOutputBoundary presenter) {
        this.dataAccess = dataAccess;
        this.presenter = presenter;
    }

    @Override
    public void execute(CreateReviewInputData inputData) {
        final Review review = new Review(inputData.getUser(), inputData.getRating(), inputData.getComment());
        review.setLocation(new Location(inputData.getLocationName(), "43.6532", "-79.3832"));
        dataAccess.saveReview(review);

        final CreateReviewOutputData outputData = new CreateReviewOutputData(inputData.getUser(),
                inputData.getRating(), inputData.getComment(), inputData.getLocationName(), false);
        presenter.prepareSuccessView(outputData);
    }

    /**
     * Executes the switch to list of reviews use case.
     */
    @Override
    public void switchToListReviewView() {
        presenter.switchToListReviewView();
    }
}
