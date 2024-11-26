package interface_adapters.map;

import interface_adapters.ViewManagerModel;
import interface_adapters.list_review.ListReviewViewModel;
import use_case.check_map.CheckMapOutputBoundary;
import use_case.check_map.CheckMapOutputData;

/**
 * The Presenter for the Check Map Use Case.
 */
public class MapPresenter implements CheckMapOutputBoundary{

    private final ViewManagerModel viewManagerModel;
    private final MapViewModel mapViewModel;
    private final ListReviewViewModel listReviewViewModel;

    public MapPresenter(ViewManagerModel viewManagerModel,
                        MapViewModel mapViewModel,
                        ListReviewViewModel listReviewViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.mapViewModel = mapViewModel;
        this.listReviewViewModel = listReviewViewModel;
    }

    // TODO: If the view Map button is hit, view the map.
    @Override
    public void prepareSuccessView(CheckMapOutputData response) {
        mapViewModel.getState().setName(response.getName());
        mapViewModel.getState().setLatitude(response.getLatitude());
        mapViewModel.getState().setLongitude(response.getLongitude());



        mapViewModel.firePropertyChanged();
    }

    @Override
    public void prepareFailView(String errorMessage) {
         final MapState mapState = mapViewModel.getState();
         mapState.setNameError(errorMessage);
         mapState.setLatitudeError(errorMessage);
         mapState.setLongitudeError(errorMessage);
         mapViewModel.firePropertyChanged();
    }

    /**
     * Switches to List of Reviews View.
     */
    @Override
    public void switchToListReviewView() {
        viewManagerModel.setState(listReviewViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }


}
