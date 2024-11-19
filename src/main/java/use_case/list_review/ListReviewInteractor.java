package use_case.list_review;

import entity.UserReview;
import entity.UserReviewFactory;

import java.util.List;

public class ListReviewInteractor implements ListReviewInputBoundary {

    private final ListReviewDataAccessInterface reviewDataAccessObject;
    private final ListReviewOutputBoundary reviewPresenter;

    public ListReviewInteractor(ListReviewDataAccessInterface reviewDataAccessObject,
                                ListReviewOutputBoundary reviewPresenter) {
        this.reviewDataAccessObject = reviewDataAccessObject;
        this.reviewPresenter = reviewPresenter;
    }

    @Override
    public void execute(ListReviewInputData listReviewInputData) {
        int pageNumber = listReviewInputData.getPageNumber();
        int pageSize = listReviewInputData.getPageSize();

        List<UserReview> reviews = reviewDataAccessObject.getReviews(pageNumber, pageSize);

        if (reviews.isEmpty()) {
            if (pageNumber == 1) {
                System.out.println("No reviews found");
                reviewPresenter.prepareFailView("No reviews found");
            } else {
                System.out.println("Page does not exist");
                reviewPresenter.prepareFailView("Page does not exist");
            }
        } else {
            reviewPresenter.prepareSuccessView(new ListReviewOutputData(pageNumber, pageSize, reviews, false));
        }

    }
}
