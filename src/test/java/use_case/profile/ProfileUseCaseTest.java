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
    private ProfileFactory profileFactory;

    @BeforeEach
    void setUp() {
        this.profileFactory = new ProfileFactory() {
            @Override
            public Profile create(String username, String bio) {
                return new UserProfile(username, bio);
            }
        };
        this.profileInMemoryDAO = new ProfileInMemoryDAO();

    }
    @Test
    void successProfile() {
        ProfileInputData inputData = new ProfileInputData("Anthony", "I'm Anthony");
        ProfileInMemoryDAO profileInMemoryDAO = new ProfileInMemoryDAO();

        // For the success test, we need to add the bio into the Profile.
        Profile profile = this.profileFactory.create("Anthony", "qqq");
        profileInMemoryDAO.save(profile);

        ProfileOutputBoundary successPresenter = new ProfileOutputBoundary() {
            @Override
            public void prepareSuccessView(ProfileOutputData outputData) {
                // check that tthe ouput data contains the bio of the user
                assertEquals("I'm Anthony", outputData.getBio());
            }

            @Override
            public void prepareFailView(String errorMessage) {
                fail("Use case fialure is unexpected.");
            }

            @Override
            public void switchToListReviewView() {

            }
        };
        ProfileInputBoundary interactor = new ProfileInteractor(profileInMemoryDAO, successPresenter);
        interactor.excute(inputData);
    }
}
