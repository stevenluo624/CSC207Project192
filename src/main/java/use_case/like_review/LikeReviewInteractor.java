package use_case.like_review;
import helper.Callback;
/**
 * The Like Review Interactor.
 */
public class LikeReviewInteractor implements LikeReviewInputBoundary{
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
            likeReviewDataAccessObject.saveLike(username, reviewId);
            int currentLikes = likeReviewDataAccessObject.getLikeCount(reviewId);
            LikeReviewOutputData outputData = new LikeReviewOutputData(reviewId, username, currentLikes, true);
            likeReviewPresenter.prepareSuccessView(outputData);
            callback.onComplete(true); // Notify success
        } catch (Exception e) {
            likeReviewPresenter.prepareFailView(e.getMessage());
            callback.onComplete(false); // Notify failure
        }
    }
}
