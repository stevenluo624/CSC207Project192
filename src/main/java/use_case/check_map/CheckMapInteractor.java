package use_case.check_map;


/**
 * The Check Map
 */
public class CheckMapInteractor implements CheckMapInputBoundary{
    private final CheckMapOutputBoundary locationPresenter;

    public CheckMapInteractor(CheckMapOutputBoundary checkMapOutputBoundary) {
        this.locationPresenter = checkMapOutputBoundary;
    }

    @Override
    public void execute(CheckMapInputData checkMapInputData) {
        final String name = checkMapInputData.getName();
        final String lat = checkMapInputData.getLatitude();
        final String longi = checkMapInputData.getLongitude();
        try {
            Double.parseDouble(lat);
            Double.parseDouble(longi);
            final CheckMapOutputData checkMapOutputData = new CheckMapOutputData(name, lat, longi
                    , false);
            locationPresenter.prepareSuccessView(checkMapOutputData);
        }
        catch (NumberFormatException error) {
            locationPresenter.prepareFailView("Latitude and Longitude must both be decimal values.");
        }
    }

    /**
     * Executes the switch to list of reviews use case.
     */
    @Override
    public void switchToListReviewView() {
        locationPresenter.switchToListReviewView();
    }
}
