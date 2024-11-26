package use_case.check_map;

import entity.Location;
import entity.LocationFactory;

/**
 * The Check Map
 */
public class CheckMapInteractor implements CheckMapInputBoundary{
    private final CheckMapDataAccessInterface dataAccessObject;
    private final CheckMapOutputBoundary locationPresenter;
    private final LocationFactory locationFactory;

    public CheckMapInteractor(CheckMapDataAccessInterface checkMapDataAccessInterface,
                              CheckMapOutputBoundary checkMapOutputBoundary,
                              LocationFactory locationFactory) {
        this.dataAccessObject = checkMapDataAccessInterface;
        this.locationPresenter = checkMapOutputBoundary;
        this.locationFactory = locationFactory;
    }

    @Override
    public void execute(CheckMapInputData checkMapInputData) {
        final Location location = locationFactory.create(checkMapInputData. getName(),
                checkMapInputData.getLatitude(), checkMapInputData.getLongitude());

        final CheckMapOutputData checkMapOutputData = new CheckMapOutputData(location.getName(), location.getLatitude(),
                location.getLongitude(), false);

        try {
            dataAccessObject.checkMap(location);
            locationPresenter.prepareSuccessView(checkMapOutputData);
        } catch (DataAccessException e) {
            locationPresenter.prepareFailView(e.getMessage());
        }

    }
}
