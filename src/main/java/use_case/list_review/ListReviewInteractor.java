package use_case.list_review;

import entity.UserReviewFactory;

public class ListReviewInteractor implements ListReviewInputBoundary {

    private final ListReviewDataAccessInterface reviewDataAccessObject;
    private final ListReviewOutputBoundary reviewPresenter;
    private final UserReviewFactory userReviewFactory;

    public ListReviewInteractor(ListReviewDataAccessInterface reviewDataAccessObject,
                                ListReviewOutputBoundary reviewPresenter,
                                UserReviewFactory userReviewFactory) {
        this.reviewDataAccessObject = reviewDataAccessObject;
        this.reviewPresenter = reviewPresenter;
        this.userReviewFactory = userReviewFactory;
    }

    @Override
    public void execute(ListReviewInputData listReviewInputData) {
        
    }
}
