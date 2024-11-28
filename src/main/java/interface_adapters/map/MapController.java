package interface_adapters.map;

import use_case.check_map.CheckMapInputData;
import use_case.check_map.CheckMapInteractor;

public class MapController {

    private final CheckMapInteractor checkMapInteractor;

    public MapController(CheckMapInteractor checkMapInteractor) {
        this.checkMapInteractor = checkMapInteractor;
    }

    /**
     * Executes the Check Map Use Case
     * @param name name of the location
     * @param latitude latitude of the location
     * @param longitude longitude of the location
     */
    public void execute(String name, String latitude, String longitude) {
        final CheckMapInputData checkMapInputData = new CheckMapInputData(name, latitude, longitude);

        checkMapInteractor.execute(checkMapInputData);
    }

    /**
     * Executes the "switch to list of reviews" use case.
     */
    public void switchToListReviewView() {
        checkMapInteractor.switchToListReviewView();
    }
}
