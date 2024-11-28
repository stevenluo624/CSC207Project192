package use_case.list_review;

import entity.UserReview;

import java.util.List;

/**
 * Output data for list review use case.
 */
public class ListReviewOutputData {

    private final int pageNumber;
    private final int pageSize;
    private final List<UserReview> reviewList;
    private final boolean useCaseFailed;

    public ListReviewOutputData(int pageNumber, int pageSize, List<UserReview> reviewList, boolean useCaseFailed) {
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

    public List<UserReview> getReviewList() {
        return reviewList;
    }

    public boolean isUseCaseFailed() {
        return useCaseFailed;
    }
}
