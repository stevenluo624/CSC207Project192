package use_case.list_review;

import entity.reviews_thread.Review;

import java.util.List;

/**
 * DAO for list reviews use case
 */
public interface ListReviewDataAccessInterface {

    /**
     * @param pageNumber which page to check.
     * @param pageSize how many data displayed per page.
     * @return whether there exists enough data to reach this page.
     */
    boolean checkPageExists(int pageNumber, int pageSize);

    /**
     * @param pageNumber which page to check.
     * @param pageSize how many data displayed per page.
     * @return list of reviews on the given page.
     */
    List<Review> getReviews(int pageNumber, int pageSize);
}
