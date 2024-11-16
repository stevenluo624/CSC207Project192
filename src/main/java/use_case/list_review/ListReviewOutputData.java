package use_case.list_review;

import entity.UserReview;

import java.util.List;

public class ListReviewOutputData {

    private final List<UserReview> reviewList;
    private final boolean useCaseFailed;

    public ListReviewOutputData(List<UserReview> reviewList, boolean useCaseFailed) {
        this.reviewList = reviewList;
        this.useCaseFailed = useCaseFailed;
    }

    public List<UserReview> getReviewList() {
        return reviewList;
    }

    public boolean isUseCaseFailed() {
        return useCaseFailed;
    }
}
