package use_case.list_review;

import entity.UserReview;
import entity.UserReviewFactory;

import java.util.List;

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
        int pageNumber = listReviewInputData.getPageNumber();
        int pageSize = listReviewInputData.getPageSize();

        if (reviewDataAccessObject.checkPageExists(pageNumber, pageSize)) {
            if (pageNumber == 1) {
                reviewPresenter.prepareFailView("No reviews found");
            } else {
                reviewPresenter.prepareFailView("Page does not exist");
            }
        } else {
            List<UserReview> reviews = reviewDataAccessObject.getReviews(pageNumber, pageSize);
            reviewPresenter.prepareSuccessView(new ListReviewOutputData(pageNumber, pageSize, reviews, false));
        }

    }
}
