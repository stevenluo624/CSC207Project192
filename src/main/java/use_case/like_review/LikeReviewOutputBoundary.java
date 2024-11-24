package use_case.like_review;

import use_case.like_review.LikeReviewOutputData;

/**
 * The output boundary for the Like Review Use Case.
 */
public interface LikeReviewOutputBoundary {
    /**
     * Prepares the success view for the Like Review Use Case.
     * @param outputData the output data
     */
    void prepareSuccessView(LikeReviewOutputData outputData);

    /**
     * Prepares the failure view for the Like Review Use Case.
     * @param errorMessage the explanation of the failure
     */
    void prepareFailView(String errorMessage);
}
