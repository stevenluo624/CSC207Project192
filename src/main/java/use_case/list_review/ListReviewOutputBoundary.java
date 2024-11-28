package use_case.list_review;

import use_case.check_map.CheckMapOutputData;
import use_case.profile.ProfileOutputData;

/**
 * Output boundary for the list reviews use case
 */
public interface ListReviewOutputBoundary {

    /**
     * Prepares the success view for list reviews.
     * @param outputData contains output data.
     */
    void prepareSuccessView(ListReviewOutputData outputData);


    /**
     * Prepares the failure view for list reviews.
     * @param errorMessage contains the failure message.
     */
    void prepareFailView(String errorMessage);

    /**
     * Switch to map view.
     * @param checkMapOutputData contains output for map.
     */
    void switchToMapView(CheckMapOutputData checkMapOutputData);

    /**
     * Switch to profile view.
     * @param profileOutputData contains output for profile
     */
    void switchToProfileView(ProfileOutputData profileOutputData);
}
