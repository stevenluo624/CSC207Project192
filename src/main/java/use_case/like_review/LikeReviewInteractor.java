package use_case.like_review;
import helper.Callback;
/**
 * The Like Review Interactor.
 */
public class LikeReviewInteractor implements LikeReviewInputBoundary {
    final LikeReviewDataAccessInterface likeReviewDataAccessObject;
    final LikeReviewOutputBoundary likeReviewPresenter;

    public LikeReviewInteractor(LikeReviewDataAccessInterface likeReviewDataAccessObject, LikeReviewOutputBoundary likeReviewPresenter) {
        this.likeReviewDataAccessObject = likeReviewDataAccessObject;
        this.likeReviewPresenter = likeReviewPresenter;
    }

    @Override
    public void execute(LikeReviewInputData likeReviewInputData, Callback callback) {
        final String username = likeReviewInputData.getUsername();
        final String reviewId = likeReviewInputData.getReviewId();

        try {
            // Save the like/unlike in the database
            likeReviewDataAccessObject.saveLike(username, reviewId);

            // Retrieve the updated like count
            int currentLikes = likeReviewDataAccessObject.getLikeCount(reviewId);

            // Prepare the success view
            LikeReviewOutputData outputData = new LikeReviewOutputData(reviewId, username, currentLikes, true);
            likeReviewPresenter.prepareSuccessView(outputData);

            // Notify the callback of success
            callback.onComplete(true);
          
        } catch (Exception e) {
            // Prepare the fail view
            likeReviewPresenter.prepareFailView(e.getMessage());

            // Notify the callback of failure
            callback.onComplete(false);
        }
    }
}

