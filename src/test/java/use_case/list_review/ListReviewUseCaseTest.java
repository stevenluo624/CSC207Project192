package use_case.list_review;

import data_access.DBProfileAccessObject;
import data_access.DBReviewListAccessObject;
import interface_adapters.ViewManagerModel;
import interface_adapters.create_review.CreateReviewViewModel;
import interface_adapters.list_review.ListReviewPresenter;
import interface_adapters.list_review.ListReviewState;
import interface_adapters.list_review.ListReviewViewModel;
import interface_adapters.map.MapViewModel;
import interface_adapters.profile.ProfileViewModel;
import org.junit.jupiter.api.BeforeEach;
import use_case.profile.ProfileDataAccessInterface;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ListReviewUseCaseTest {
    ListReviewViewModel listReviewViewModel;
    MapViewModel mapViewModel;
    ProfileViewModel profileViewModel;
    CreateReviewViewModel createReviewViewModel;
    ViewManagerModel viewManagerModel;
    ListReviewDataAccessInterface listReviewDAO;
    ProfileDataAccessInterface profileDAO;
    ListReviewOutputBoundary listReviewOutputBoundary;
    ListReviewInteractor listReviewInteractor;

    @BeforeEach
    void setUp() {
        // Initialize objects needed for the test
        listReviewViewModel = new ListReviewViewModel();
        mapViewModel = new MapViewModel();
        profileViewModel = new ProfileViewModel();
        createReviewViewModel = new CreateReviewViewModel();
        viewManagerModel = new ViewManagerModel();

        listReviewDAO = new DBReviewListAccessObject();
        profileDAO = new DBProfileAccessObject();
        listReviewOutputBoundary = new ListReviewPresenter(
                listReviewViewModel,
                mapViewModel,
                profileViewModel,
                createReviewViewModel,
                viewManagerModel
        );

        listReviewInteractor = new ListReviewInteractor(
                listReviewDAO,
                profileDAO,
                listReviewOutputBoundary
        );
    }

    @Test
    void listReviewSuccessTest() {
        // Execute the interactor with test data
        ListReviewInputData listReviewInputData = new ListReviewInputData(1, 4);
        listReviewInteractor.execute(listReviewInputData);

        // Verify the state of the view model has been changed correctly.
        ListReviewState listReviewState = listReviewViewModel.getState();
        assertNull(listReviewState.getPageError());
        assertEquals(4, listReviewState.getReviewList().size());
    }

    @Test
    void listReviewFailureTest() {
        // Execute the interactor with test data
        ListReviewInputData listReviewInputData = new ListReviewInputData(100, 4);
        listReviewInteractor.execute(listReviewInputData);

        // Verify the state of the view model has been changed correctly.
        ListReviewState listReviewState = listReviewViewModel.getState();
        assertEquals("Page does not exist", listReviewState.getPageError());
    }
}
