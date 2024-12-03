package use_case.like_review;

import use_case.logout.LogoutInputData;
import helper.Callback;

/**
 * Input Boundary for actions which are related to liking a review.
 */
public interface LikeReviewInputBoundary {
    void execute(LikeReviewInputData likeReviewInputData, Callback callback);
}
