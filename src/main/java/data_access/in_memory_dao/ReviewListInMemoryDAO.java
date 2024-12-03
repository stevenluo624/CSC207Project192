package data_access.in_memory_dao;

import entity.reviews_thread.Review;
import entity.reviews_thread.Review;
import use_case.create_review.CreateReviewDataAccessInterface;
import use_case.list_review.ListReviewDataAccessInterface;

import java.util.ArrayList;
import java.util.List;

public class ReviewListInMemoryDAO implements ListReviewDataAccessInterface, CreateReviewDataAccessInterface {
    List<Review> reviews = new ArrayList<>();

    /**
     * @param pageNumber which page to check.
     * @param pageSize   how many data displayed per page.
     * @return
     */
    @Override
    public boolean checkPageExists(int pageNumber, int pageSize) {
        return false;
    }

    /**
     * @param pageNumber which page to check.
     * @param pageSize   how many data displayed per page.
     * @return
     */
    @Override
    public List<Review> getReviews(int pageNumber, int pageSize) {
        return List.of();
    }

    /**
     * @param review contains details of the review
     */
    @Override
    public void saveReview(Review review) {
        reviews.add(review);
    }

    /**
     * @return reviews: list of review objects
     */
    public List<Review> getReviews() {
        return reviews;
    }
}
