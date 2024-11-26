package interface_adapters.list_review;

import entity.Location;
import entity.UserReview;
import use_case.check_map.CheckMapInputData;
import use_case.list_review.ListReviewInputBoundary;
import use_case.list_review.ListReviewInputData;

/**
 * Controller for the list review use case.
 */
public class ListReviewController {

    private final ListReviewInputBoundary listReviewInteractor;

    public ListReviewController(ListReviewInputBoundary listReviewInteractor) {
        this.listReviewInteractor = listReviewInteractor;
    }

    /**
     * Executes the list review use case.
     * @param pageNumber which page to display.
     * @param pageSize how many items in one page.
     */
    public void execute(int pageNumber, int pageSize) {
        final ListReviewInputData inputData = new ListReviewInputData(pageNumber, pageSize);
        listReviewInteractor.execute(inputData);
    }

    /**
     * Switch to map view
     * @param review
     */
    public void switchToMapView(UserReview review) {
        Location location = review.getLocation();

        final CheckMapInputData inputData = new CheckMapInputData(
                location.getName(),
                location.getLatitude(),
                location.getLongitude()
        );
        listReviewInteractor.switchToMapView(inputData);
    }
}