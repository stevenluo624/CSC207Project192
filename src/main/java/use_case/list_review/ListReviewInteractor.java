package use_case.list_review;

import entity.User;
import entity.UserReview;
import use_case.check_map.CheckMapInputData;
import use_case.check_map.CheckMapOutputData;
import use_case.profile.ProfileDataAccessInterface;
import use_case.profile.ProfileOutputBoundary;
import use_case.profile.ProfileOutputData;

import java.util.List;

/**
 * Interactor for list review use case.
 */
public class ListReviewInteractor implements ListReviewInputBoundary {

    private final ListReviewDataAccessInterface reviewDataAccessObject;
    private final ProfileDataAccessInterface profileDataAccessObject;
    private final ListReviewOutputBoundary reviewPresenter;

    public ListReviewInteractor(ListReviewDataAccessInterface reviewDataAccessObject,
                                ProfileDataAccessInterface profileDataAccessObject,
                                ListReviewOutputBoundary reviewPresenter) {
        this.reviewDataAccessObject = reviewDataAccessObject;
        this.profileDataAccessObject = profileDataAccessObject;
        this.reviewPresenter = reviewPresenter;
    }

    @Override
    public void execute(ListReviewInputData listReviewInputData) {
        int pageNumber = listReviewInputData.getPageNumber();
        int pageSize = listReviewInputData.getPageSize();

        List<UserReview> reviews = reviewDataAccessObject.getReviews(pageNumber, pageSize);

        if (reviews.isEmpty()) {
            if (pageNumber == 1) {
                System.out.println("No reviews found");
                reviewPresenter.prepareFailView("No reviews found");
            } else {
                System.out.println("Page does not exist");
                reviewPresenter.prepareFailView("Page does not exist");
            }
        } else {
            reviewPresenter.prepareSuccessView(new ListReviewOutputData(pageNumber, pageSize, reviews, false));
        }
    }

    @Override
    public void switchToMapView(CheckMapInputData checkMapInputData) {
        String name = checkMapInputData.getName();
        String latitude = checkMapInputData.getLatitude();
        String longitude = checkMapInputData.getLongitude();
        reviewPresenter.switchToMapView(new CheckMapOutputData(name, latitude, longitude, false));
    }

    @Override
    public void switchToProfileView(String username) {
        String bio = profileDataAccessObject.getBio(username);
        reviewPresenter.switchToProfileView(new ProfileOutputData(username, bio, false));
    }
}
