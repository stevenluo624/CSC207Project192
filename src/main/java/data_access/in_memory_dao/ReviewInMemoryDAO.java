package data_access.in_memory_dao;

import entity.UserReview;
import use_case.list_review.ListReviewDataAccessInterface;

import java.util.List;

public class ReviewInMemoryDAO implements ListReviewDataAccessInterface {
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
    public List<UserReview> getReviews(int pageNumber, int pageSize) {
        return List.of();
    }
}
