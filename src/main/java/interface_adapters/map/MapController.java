package interface_adapters.map;

import use_case.check_map.CheckMapInputData;
import use_case.check_map.CheckMapInteractor;

/**
 * The Controller for Map use case.
 */
public class MapController {

    private final CheckMapInteractor checkMapInteractor;

    public MapController(CheckMapInteractor checkMapInteractor) {
        this.checkMapInteractor = checkMapInteractor;
    }

    /**
     * Executes the Check Map Use Case.
     * @param name name of the location
     * @param latitude latitude of the location
     * @param longitude longitude of the location
     */
    public void execute(String name, double latitude, double longitude) {
        final CheckMapInputData checkMapInputData = new CheckMapInputData(name, latitude, longitude);

        checkMapInteractor.execute(checkMapInputData);
    }
}
