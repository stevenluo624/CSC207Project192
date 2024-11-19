package interface_adapters.list_review;

import interface_adapters.ViewManagerModel;
import use_case.list_review.ListReviewOutputBoundary;
import use_case.list_review.ListReviewOutputData;

/**
 * Presenter for the list review use case.
 */
public class ListReviewPresenter implements ListReviewOutputBoundary {

    private final ListReviewViewModel listReviewViewModel;
    private final ViewManagerModel viewManagerModel;

    public ListReviewPresenter(ListReviewViewModel listReviewViewModel, ViewManagerModel viewManagerModel) {
        this.listReviewViewModel = listReviewViewModel;
        this.viewManagerModel = viewManagerModel;
    }

    @Override
    public void prepareSuccessView(ListReviewOutputData outputData) {
        final ListReviewState listReviewState = listReviewViewModel.getState();
        listReviewState.setPageNumber(outputData.getPageNumber());
        listReviewState.setPageSize(outputData.getPageSize());
        listReviewState.setReviewList(outputData.getReviewList());

        listReviewViewModel.setState(listReviewState);
        listReviewViewModel.firePropertyChanged();

        viewManagerModel.setState(listReviewViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }

    @Override
    public void prepareFailView(String errorMessage) {
        final ListReviewState listReviewState = listReviewViewModel.getState();
        listReviewState.setPageError(errorMessage);
        listReviewViewModel.firePropertyChanged();
    }
}
