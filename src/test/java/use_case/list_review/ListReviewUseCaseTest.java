package use_case.list_review;

import data_access.DBProfileAccessObject;
import data_access.DBReviewListAccessObject;
import interface_adapters.ViewManagerModel;
import interface_adapters.list_review.ListReviewPresenter;
import interface_adapters.list_review.ListReviewState;
import interface_adapters.list_review.ListReviewViewModel;
import interface_adapters.map.MapViewModel;
import interface_adapters.profile.ProfileViewModel;
import use_case.profile.ProfileDataAccessInterface;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ListReviewUseCaseTest {
    @Test
    void listReviewSuccessTest() {
        ListReviewViewModel listReviewViewModel = new ListReviewViewModel();
        MapViewModel mapViewModel = new MapViewModel();
        ProfileViewModel profileViewModel = new ProfileViewModel();
        ViewManagerModel viewManagerModel = new ViewManagerModel();

        ListReviewDataAccessInterface listReviewDAO = new DBReviewListAccessObject();
        ProfileDataAccessInterface profileDAO = new DBProfileAccessObject();
        ListReviewOutputBoundary listReviewOutputBoundary = new ListReviewPresenter(
                listReviewViewModel,
                mapViewModel,
                profileViewModel,
                viewManagerModel
        );

        ListReviewInteractor listReviewInteractor = new ListReviewInteractor(
                listReviewDAO,
                profileDAO,
                listReviewOutputBoundary
        );

        ListReviewInputData listReviewInputData = new ListReviewInputData(1, 4);
        listReviewInteractor.execute(listReviewInputData);

        ListReviewState listReviewState = listReviewViewModel.getState();
        assertNull(listReviewState.getPageError());
        assertEquals(listReviewState.getReviewList().size(), 4);
    }
}
