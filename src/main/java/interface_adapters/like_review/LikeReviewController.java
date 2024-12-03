package interface_adapters.like_review;

import use_case.like_review.LikeReviewInputBoundary;
import use_case.like_review.LikeReviewInputData;
import helper.Callback;

/**
 * The controller for the Like Review Use Case.
 */
public class LikeReviewController {
    private final LikeReviewInputBoundary likeReviewUseCaseInteractor;

    public LikeReviewController(LikeReviewInputBoundary likeReviewUseCaseInteractor) {
        this.likeReviewUseCaseInteractor = likeReviewUseCaseInteractor;
    }

    /**
     * Executes the Like Review Use Case with a callback.
     *
     * @param username the username of the user liking/unliking
     * @param reviewId the ID of the review being liked/unliked
     * @param callback a function to handle the result of the operation
     */
    public void execute(String username, String reviewId, Callback callback) {
        final LikeReviewInputData likeReviewInputData = new LikeReviewInputData(username, reviewId);
        likeReviewUseCaseInteractor.execute(likeReviewInputData, callback);
    }
}
