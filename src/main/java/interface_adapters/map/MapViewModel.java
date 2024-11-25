package interface_adapters.map;

import interface_adapters.ViewModel;

/**
 * The View Model for the Map.
 */
public class MapViewModel extends ViewModel<MapState> {

    public static final String TITLE_LABEL = "Map View";

    public static final String TO_LOCATION_LIST_LABEL = "Go Back to Locations";

    public MapViewModel() {
        super("Check Map");
        setState(new MapState());
    }
}
