package interface_adapters.list_review;

import interface_adapters.ViewManagerModel;
import interface_adapters.map.MapController;
import interface_adapters.map.MapViewModel;
import use_case.check_map.CheckMapOutputData;
import use_case.list_review.ListReviewOutputBoundary;
import use_case.list_review.ListReviewOutputData;
import view.MapView;

/**
 * Presenter for the list review use case.
 */
public class ListReviewPresenter implements ListReviewOutputBoundary {

    private final ListReviewViewModel listReviewViewModel;
    private final MapViewModel mapViewModel;
    private final ViewManagerModel viewManagerModel;

    public ListReviewPresenter(ListReviewViewModel listReviewViewModel, MapViewModel mapViewModel, ViewManagerModel viewManagerModel) {
        this.listReviewViewModel = listReviewViewModel;
        this.mapViewModel = mapViewModel;
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

    @Override
    public void switchToMapView(CheckMapOutputData checkMapOutputData) {
        mapViewModel.getState().setName(checkMapOutputData.getName());
        mapViewModel.getState().setLatitude(checkMapOutputData.getLatitude());
        mapViewModel.getState().setLongitude(checkMapOutputData.getLongitude());
        viewManagerModel.setState(mapViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }


}
