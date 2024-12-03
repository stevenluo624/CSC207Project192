package use_case.create_review;

import entity.reviews_thread.Review;

/**
 *  DAO for the create review use case.
 */
public interface CreateReviewDataAccessInterface {

    /**
     * Adds a new review.
     * @param review contains details of the review
     */
    void saveReview(Review review);
}
