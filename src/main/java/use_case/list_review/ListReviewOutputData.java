package use_case.list_review;

import entity.reviews_thread.Review;

import java.util.List;

/**
 * Output data for list review use case.
 */
public class ListReviewOutputData {

    private final int pageNumber;
    private final int pageSize;
    private final List<Review> reviewList;
    private final boolean useCaseFailed;

    public ListReviewOutputData(int pageNumber, int pageSize, List<Review> reviewList, boolean useCaseFailed) {
        this.pageNumber = pageNumber;
        this.pageSize = pageSize;
        this.reviewList = reviewList;
        this.useCaseFailed = useCaseFailed;
    }

    public int getPageNumber() {
        return pageNumber;
    }

    public int getPageSize() {
        return pageSize;
    }

    public List<Review> getReviewList() {
        return reviewList;
    }

    public boolean isUseCaseFailed() {
        return useCaseFailed;
    }
}
