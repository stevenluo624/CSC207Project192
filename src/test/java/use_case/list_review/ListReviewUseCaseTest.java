package use_case.list_review;

import app.ProfileFactory;
import data_access.DBProfileAccessObject;
import data_access.DBReviewListAccessObject;
import data_access.in_memory_dao.ProfileInMemoryDAO;
import data_access.in_memory_dao.ReviewListInMemoryDAO;
import entity.Profile;
import entity.UserProfile;
import interface_adapters.ViewManagerModel;
import interface_adapters.list_review.ListReviewPresenter;
import interface_adapters.list_review.ListReviewState;
import interface_adapters.list_review.ListReviewViewModel;
import interface_adapters.map.MapViewModel;
import interface_adapters.profile.ProfileViewModel;
import org.junit.jupiter.api.BeforeEach;
import use_case.check_map.*;
import use_case.create_review.CreateReviewInputBoundary;
import use_case.create_review.CreateReviewInteractor;
import use_case.profile.ProfileDataAccessInterface;

import org.junit.jupiter.api.Test;
import use_case.profile.ProfileInputData;
import use_case.profile.ProfileOutputData;

import static org.junit.jupiter.api.Assertions.*;

class ListReviewUseCaseTest {
    ProfileFactory profileFactory;
    ListReviewViewModel listReviewViewModel;
    MapViewModel mapViewModel;
    ProfileViewModel profileViewModel;
    ViewManagerModel viewManagerModel;
    ListReviewDataAccessInterface listReviewDAO;
    ReviewListInMemoryDAO listReviewInMemoryDAO;
    ProfileInMemoryDAO profileDAO;
    ListReviewOutputBoundary listReviewOutputBoundary;
    ListReviewInteractor listReviewInteractor;
    ListReviewInteractor listReviewEmptyInteractor;

    @BeforeEach
    void setUp() {
        // Initialize objects needed for the test
        this.profileFactory = new ProfileFactory() {
            @Override
            public Profile create(String username, String bio) {
                return new UserProfile(username, bio);
            }
        };
        listReviewViewModel = new ListReviewViewModel();
        mapViewModel = new MapViewModel();
        profileViewModel = new ProfileViewModel();
        viewManagerModel = new ViewManagerModel();

        listReviewDAO = new DBReviewListAccessObject();
        listReviewInMemoryDAO = new ReviewListInMemoryDAO();
        profileDAO = new ProfileInMemoryDAO();
        Profile profile = this.profileFactory.create("Anthony", "qqq");
        profileDAO.save(profile);


    }

    @Test
    void listReviewSuccessTest() {
        // Execute the interactor with test data
        ListReviewInputData listReviewInputData = new ListReviewInputData(1, 4);
        listReviewOutputBoundary = new ListReviewOutputBoundary() {
            @Override
            public void prepareSuccessView(ListReviewOutputData outputData) {
                assertEquals(1, outputData.getPageNumber());
                assertEquals(4, outputData.getPageSize());
                assertEquals(4, outputData.getReviewList().size());
                assertFalse(outputData.isUseCaseFailed());
            }

            @Override
            public void prepareFailView(String errorMessage) {
                fail("The use case is not expected to fail");
            }

            @Override
            public void switchToMapView(CheckMapOutputData checkMapOutputData) {
                fail("The use case is not expected to switch");
            }

            @Override
            public void switchToProfileView(ProfileOutputData profileOutputData) {
                fail("The use case is not expected to switch");
            }
        };
        listReviewInteractor = new ListReviewInteractor(
                listReviewDAO,
                profileDAO,
                listReviewOutputBoundary
        );
        listReviewInteractor.execute(listReviewInputData);
    }

    @Test
    void listReviewFailureEmptyListTest() {
        // Execute the interactor with test data
        ListReviewInputData listReviewInputData = new ListReviewInputData(1, 4);
        listReviewOutputBoundary = new ListReviewOutputBoundary() {
            @Override
            public void prepareSuccessView(ListReviewOutputData outputData) {
                fail("use case is not expected to succeed");
            }

            @Override
            public void prepareFailView(String errorMessage) {
                assertEquals("No reviews found", errorMessage);
            }

            @Override
            public void switchToMapView(CheckMapOutputData checkMapOutputData) {
                fail("The use case is not expected to switch");
            }

            @Override
            public void switchToProfileView(ProfileOutputData profileOutputData) {
                fail("The use case is not expected to switch");
            }
        };
        listReviewEmptyInteractor = new ListReviewInteractor(
                listReviewInMemoryDAO,
                profileDAO,
                listReviewOutputBoundary
        );
        listReviewEmptyInteractor.execute(listReviewInputData);
    }

    @Test
    void listReviewFailureEmptyPageTest() {
        // Execute the interactor with test data
        ListReviewInputData listReviewInputData = new ListReviewInputData(100, 4);
        listReviewOutputBoundary = new ListReviewOutputBoundary() {
            @Override
            public void prepareSuccessView(ListReviewOutputData outputData) {
                fail("use case is not expected to succeed");
            }

            @Override
            public void prepareFailView(String errorMessage) {
                assertEquals("Page does not exist", errorMessage);
            }

            @Override
            public void switchToMapView(CheckMapOutputData checkMapOutputData) {
                fail("The use case is not expected to switch");
            }

            @Override
            public void switchToProfileView(ProfileOutputData profileOutputData) {
                fail("The use case is not expected to switch");
            }
        };
        listReviewInteractor = new ListReviewInteractor(
                listReviewDAO,
                profileDAO,
                listReviewOutputBoundary
        );
        listReviewInteractor.execute(listReviewInputData);
    }

    @Test
    void switchToListReviewViewTest() {
        // This creates a presenter that tests whether the test case is as we expect.
        ViewManagerModel viewManagerModel = new ViewManagerModel();
        CheckMapInputData checkMapInputData = new CheckMapInputData("Truck", "1", "2");
        ListReviewOutputBoundary switchPresenter = new ListReviewOutputBoundary() {
            @Override
            public void prepareSuccessView(ListReviewOutputData location) {
                // this should never be reached since the test case should fail
                fail("Use case success is unexpected.");
            }

            @Override
            public void prepareFailView(String error) {
                // this should never be reached since the test case should fail
                fail("Use case failure is unexpected.");
            }

            /**
             * Switch to map view.
             *
             * @param checkMapOutputData contains output for map.
             */
            @Override
            public void switchToMapView(CheckMapOutputData checkMapOutputData) {
                viewManagerModel.setState("Check Map");
                assertEquals("Check Map", viewManagerModel.getState());
            }

            /**
             * Switch to profile view.
             *
             * @param profileOutputData contains output for profile
             */
            @Override
            public void switchToProfileView(ProfileOutputData profileOutputData) {
                viewManagerModel.setState("Profile");
                assertEquals("Profile", viewManagerModel.getState());
            }

        };
        ListReviewInputBoundary interactor = new ListReviewInteractor(
                listReviewInMemoryDAO,
                profileDAO,
                switchPresenter);
        interactor.switchToMapView(checkMapInputData);
        interactor.switchToProfileView("1234");
    }
}
