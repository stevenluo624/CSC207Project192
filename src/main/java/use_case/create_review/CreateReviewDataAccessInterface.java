package use_case.create_review;

import entity.UserReview;

public interface CreateReviewDataAccessInterface {

    /**
     * Save the review
     * @param review the review to save
     */
    void saveReview(UserReview review);
}
