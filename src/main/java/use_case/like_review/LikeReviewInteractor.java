package use_case.like_review;

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
    public void execute(LikeReviewInputData likeReviewInputData) {
        final String username = likeReviewInputData.getUsername();
        final String reviewId = likeReviewInputData.getReviewId();

        try {
            likeReviewDataAccessObject.saveLike(username, reviewId);
            int current_likes = likeReviewDataAccessObject.getLikeCount(reviewId);
            LikeReviewOutputData outputData = new LikeReviewOutputData(reviewId, username, current_likes, true);
            likeReviewPresenter.prepareSuccessView(outputData);

        } catch (Exception e) {
            likeReviewPresenter.prepareFailView(e.getMessage());
        }
    }
}
