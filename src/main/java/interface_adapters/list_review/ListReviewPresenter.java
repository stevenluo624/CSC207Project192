package interface_adapters.list_review;

import use_case.list_review.ListReviewOutputBoundary;
import use_case.list_review.ListReviewOutputData;

/**
 * Presenter for the list review use case.
 */
public class ListReviewPresenter implements ListReviewOutputBoundary {
    @Override
    public void prepareSuccessView(ListReviewOutputData outputData) {

    }

    @Override
    public void prepareFailView(String errorMessage) {

    }
}
