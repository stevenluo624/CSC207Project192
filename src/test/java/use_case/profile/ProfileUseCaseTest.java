package use_case.profile;

import data_access.DBProfileAccessObject;
import data_access.DBUserAccessObject;
import data_access.in_memory_dao.ProfileInMemoryDAO;
import entity.Profile;
import entity.UserProfile;
import interface_adapters.ViewManagerModel;
import interface_adapters.profile.ProfilePresenter;
import interface_adapters.profile.ProfileState;
import interface_adapters.profile.ProfileViewModel;
import org.apache.http.impl.conn.InMemoryDnsResolver;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import app.ProfileFactory;

import static org.junit.jupiter.api.Assertions.*;

public class ProfileUseCaseTest {
    private ProfileInMemoryDAO profileInMemoryDAO;

    @BeforeEach
    void setUp() {
        this.profileInMemoryDAO = new ProfileInMemoryDAO();

    }
    @Test
    void successProfile() {
        ProfileInputData inputData = new ProfileInputData("Anthony", "I'm Anthony");
        ProfileInMemoryDAO profileInMemoryDAO = new ProfileInMemoryDAO();


        ProfileOutputBoundary successPresenter = new ProfileOutputBoundary() {
            @Override
            public void prepareSuccessView(ProfileOutputData outputData) {
                // check that the ouput data contains the bio of the user
                assertEquals("I'm Anthony", outputData.getBio());
                assertEquals("Anthony", outputData.getUsername());
            }

            @Override
            public void prepareFailView(String errorMessage) {
                fail("Use case fialure is unexpected.");
            }

            @Override
            public void switchToListReviewView() {
                // This is expected
            }
        };
        ProfileInputBoundary interactor = new ProfileInteractor(profileInMemoryDAO, successPresenter);
        interactor.excute(inputData);
    }

    @Test
    void switchToListReviewViewTest() {
        // switch the view to listreview.
        //This creates a presenter that tests whether the view model change as what we expect.
        ViewManagerModel viewManagerModel = new ViewManagerModel();
        ProfileOutputBoundary switchPresenter = new ProfileOutputBoundary() {
            @Override
            public void prepareSuccessView(ProfileOutputData outputData) {
                // this should not be successed since we are doing switch method
                fail("Profile view success is unexpected.");
            }
            @Override
            public void prepareFailView(String errorMessage) {
                // this should not be successed since we are doing switch method
                fail("Profile view success is unexpectd.");
            }
            @Override
            public void switchToListReviewView() {
                viewManagerModel.setState("review list");
                assertEquals("review list", viewManagerModel.getState());
            }
        };
        ProfileInputBoundary interactor = new ProfileInteractor(profileInMemoryDAO, switchPresenter);
        interactor.switchToListReviewView();
    }
}
