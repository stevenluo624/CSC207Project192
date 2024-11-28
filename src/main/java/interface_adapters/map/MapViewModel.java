package interface_adapters.map;

import interface_adapters.ViewModel;

/**
 * The View Model for the Map.
 */
public class MapViewModel extends ViewModel<MapState> {

    public static final String TITLE_LABEL = "Map View";

    public MapViewModel() {
        super("Check Map");
        setState(new MapState());
    }
}
