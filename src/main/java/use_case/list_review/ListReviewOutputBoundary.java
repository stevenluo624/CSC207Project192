package use_case.list_review;

import use_case.check_map.CheckMapOutputData;
import use_case.create_reply.CreateReplyOutputData;
import use_case.create_review.CreateReviewOutputData;
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

    /**
     * Switch to create review view.
     * @param createReviewOutputData contains output for create review
     */
    void switchToCreateReviewView(CreateReviewOutputData createReviewOutputData);

    /**
     * Switch to create reply view
     * @param createReplyOutputData contains output data for create reply
     */
    void switchToCreateReplyView(CreateReplyOutputData createReplyOutputData);
}
