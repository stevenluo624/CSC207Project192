package use_case.like_review;


import data_access.helper.Callback;



/**
 * Input Boundary for actions which are related to liking a review.
 */
public interface LikeReviewInputBoundary {
    /**
     * Executes the Like Review use case.
     * @param likeReviewInputData the input data
     */
    void execute(LikeReviewInputData likeReviewInputData, Callback callback);
}
