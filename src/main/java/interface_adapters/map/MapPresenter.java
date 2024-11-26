package interface_adapters.map;

import interface_adapters.ViewManagerModel;
import use_case.check_map.CheckMapOutputBoundary;
import use_case.check_map.CheckMapOutputData;
import view.MapView;

/**
 * The Presenter for the Check Map Use Case.
 */
public class MapPresenter implements CheckMapOutputBoundary{

    private final ViewManagerModel viewManagerModel;
    private final MapViewModel mapViewModel;

    public MapPresenter(ViewManagerModel viewManagerModel,
                        MapViewModel mapViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.mapViewModel = mapViewModel;
    }

    // TODO: If the back button is hit, go back to list of reviews use case.
    @Override
    public void prepareSuccessView(CheckMapOutputData response) {
        // Once the user is done viewing the map, switch back to list of locations
        mapViewModel.getState().setName(response.getName());
        mapViewModel.getState().setLatitude(response.getLatitude());
        mapViewModel.getState().setLongitude(response.getLongitude());

        mapViewModel.firePropertyChanged();
    }

    @Override
    public void prepareFailView(String errorMessage) {
        // This state currently shouldn't fail
    }
}
