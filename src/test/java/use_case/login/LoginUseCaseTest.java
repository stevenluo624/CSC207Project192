package use_case.login;

import data_access.in_memory_dao.ProfileInMemoryDAO;
import data_access.in_memory_dao.UserInMemoryDAO;
import entity.StudentUser;
import entity.User;
import app.UserFactory;
import org.junit.jupiter.api.Test;
import use_case.profile.ProfileDataAccessInterface;
import use_case.profile.ProfileOutputData;

import static org.junit.jupiter.api.Assertions.*;

class LoginUseCaseTest {

    @Test
    void successTest() {
        LoginInputData inputData = new LoginInputData("Paul", "password");
        LoginUserDataAccessInterface userRepository = new UserInMemoryDAO();
        ProfileDataAccessInterface profileRepository = new ProfileInMemoryDAO();

        // For the success test, we need to add Paul to the data access repository before we log in.
        UserFactory factory = new UserFactory() {
            @Override
            public User create(String name, String password) {
                return new StudentUser(name, password);
            }
        };
        User user = factory.create("Paul", "password");
        userRepository.save(user);

        // This creates a successPresenter that tests whether the test case is as we expect.
        LoginOutputBoundary successPresenter = new LoginOutputBoundary() {
            @Override
            public void prepareSuccessView(LoginOutputData user) {
                assertEquals("Paul", user.getUsername());
            }

            @Override
            public void prepareFailView(String error) {
                fail("Use case failure is unexpected.");
            }

            @Override
            public void changeProfile(ProfileOutputData profileOutputData) {

            }
        };

        LoginInputBoundary interactor = new LoginInteractor(userRepository, successPresenter,profileRepository);
        interactor.execute(inputData);
    }

    @Test
    void successUserLoggedInTest() {
        LoginInputData inputData = new LoginInputData("Paul", "password");
        LoginUserDataAccessInterface userRepository = new UserInMemoryDAO();
        ProfileDataAccessInterface profileRepository = new ProfileInMemoryDAO();
        // For the success test, we need to add Paul to the data access repository before we log in.
        UserFactory factory = new UserFactory() {
            @Override
            public User create(String name, String password) {
                return new StudentUser(name, password);
            }
        };
        User user = factory.create("Paul", "password");
        userRepository.save(user);

        // This creates a successPresenter that tests whether the test case is as we expect.
        LoginOutputBoundary successPresenter = new LoginOutputBoundary() {
            @Override
            public void prepareSuccessView(LoginOutputData user) {
                assertEquals("Paul", userRepository.getCurrentUser().getUsername());
            }

            @Override
            public void prepareFailView(String error) {
                fail("Use case failure is unexpected.");
            }

            @Override
            public void changeProfile(ProfileOutputData profileOutputData) {

            }
        };

        LoginInputBoundary interactor = new LoginInteractor(userRepository, successPresenter, profileRepository);
        assertNull(userRepository.getCurrentUser());

        interactor.execute(inputData);
    }

    @Test
    void failurePasswordMismatchTest() {
        LoginInputData inputData = new LoginInputData("Paul", "wrong");
        LoginUserDataAccessInterface userRepository = new UserInMemoryDAO();
        ProfileDataAccessInterface profileRepository = new ProfileInMemoryDAO();
        // For this failure test, we need to add Paul to the data access repository before we log in, and
        // the passwords should not match.
        UserFactory factory = new UserFactory() {
            @Override
            public User create(String name, String password) {
                return new StudentUser(name, password);
            }
        };
        User user = factory.create("Paul", "password");
        userRepository.save(user);

        // This creates a presenter that tests whether the test case is as we expect.
        LoginOutputBoundary failurePresenter = new LoginOutputBoundary() {
            @Override
            public void prepareSuccessView(LoginOutputData user) {
                // this should never be reached since the test case should fail
                fail("Use case success is unexpected.");
            }

            @Override
            public void prepareFailView(String error) {
                assertEquals("Incorrect password for \"Paul\".", error);
            }

            @Override
            public void changeProfile(ProfileOutputData profileOutputData) {

            }
        };

        LoginInputBoundary interactor = new LoginInteractor(userRepository, failurePresenter, profileRepository);
        interactor.execute(inputData);
    }

    @Test
    void failureUserDoesNotExistTest() {
        LoginInputData inputData = new LoginInputData("Paul", "password");
        LoginUserDataAccessInterface userRepository = new UserInMemoryDAO();
        ProfileDataAccessInterface profileRepository = new ProfileInMemoryDAO();
        // Add Paul to the repo so that when we check later they already exist

        // This creates a presenter that tests whether the test case is as we expect.
        LoginOutputBoundary failurePresenter = new LoginOutputBoundary() {
            @Override
            public void prepareSuccessView(LoginOutputData user) {
                // this should never be reached since the test case should fail
                fail("Use case success is unexpected.");
            }

            @Override
            public void prepareFailView(String error) {
                assertEquals("Paul: Account does not exist.", error);
            }

            @Override
            public void changeProfile(ProfileOutputData profileOutputData) {

            }
        };

        LoginInputBoundary interactor = new LoginInteractor(userRepository, failurePresenter, profileRepository);
        interactor.execute(inputData);
    }
}
