package use_case.signup;

import data_access.in_memory_dao.ProfileInMemoryDAO;
import data_access.in_memory_dao.UserInMemoryDAO;
import entity.StudentUser;
import entity.User;
import app.UserFactory;
import interface_adapters.ViewManagerModel;
import org.junit.jupiter.api.Test;
import use_case.check_map.CheckMapInputBoundary;
import use_case.check_map.CheckMapInteractor;
import use_case.check_map.CheckMapOutputBoundary;
import use_case.check_map.CheckMapOutputData;
import use_case.profile.ProfileDataAccessInterface;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class SignupUseCaseTest {

    @Test
    void successTest() {
        SignupInputData inputData = new SignupInputData("Paul", "password", "password");
        SignupUserDataAccessInterface userRepository = new UserInMemoryDAO();
        ProfileDataAccessInterface profileRepository = new ProfileInMemoryDAO();

        // This creates a successPresenter that tests whether the test case is as we expect.
        SignupOutputBoundary successPresenter = new SignupOutputBoundary() {
            @Override
            public void prepareSuccessView(SignupOutputData user) {
                // 2 things to check: the output data is correct, and the user has been created in the DAO.
                assertEquals("Paul", user.getUsername());
                assertTrue(userRepository.existsByName("Paul"));
                assertFalse(user.isUseCaseFailed());
            }

            @Override
            public void prepareFailView(String error) {
                fail("Use case failure is unexpected.");
            }

            @Override
            public void switchToLoginView() {
                // This is expected
            }
        };

        SignupInputBoundary interactor = new SignupInteractor(userRepository, profileRepository, successPresenter,
                new UserFactory() {
            @Override
            public User create(String name, String password) {
                return new StudentUser(name, password);
            }
        });
        interactor.execute(inputData);
    }

    @Test
    void failurePasswordMismatchTest() {
        SignupInputData inputData = new SignupInputData("Paul", "password", "wrong");
        ProfileDataAccessInterface profileRepository = new ProfileInMemoryDAO();
        SignupUserDataAccessInterface userRepository = new UserInMemoryDAO();

        // This creates a presenter that tests whether the test case is as we expect.
        SignupOutputBoundary failurePresenter = new SignupOutputBoundary() {
            @Override
            public void prepareSuccessView(SignupOutputData user) {
                // this should never be reached since the test case should fail
                fail("Use case success is unexpected.");
            }

            @Override
            public void prepareFailView(String error) {
                assertEquals("Passwords don't match.", error);
            }

            @Override
            public void switchToLoginView() {
                // This is expected
            }
        };

        SignupInputBoundary interactor = new SignupInteractor(userRepository, profileRepository, failurePresenter, new UserFactory() {
            @Override
            public User create(String name, String password) {
                return new StudentUser(name, password);
            }
        });
        interactor.execute(inputData);
    }

    @Test
    void failureUserExistsTest() {
        SignupInputData inputData = new SignupInputData("Paul", "password", "wrong");
        SignupUserDataAccessInterface userRepository = new UserInMemoryDAO();
        ProfileDataAccessInterface profileRepository = new ProfileInMemoryDAO();


        // Add Paul to the repo so that when we check later they already exist
        UserFactory factory = new UserFactory() {
            @Override
            public User create(String name, String password) {
                return new StudentUser(name, password);
            }
        };
        User user = factory.create("Paul", "pwd");
        userRepository.save(user);

        // This creates a presenter that tests whether the test case is as we expect.
        SignupOutputBoundary failurePresenter = new SignupOutputBoundary() {
            @Override
            public void prepareSuccessView(SignupOutputData user) {
                // this should never be reached since the test case should fail
                fail("Use case success is unexpected.");
            }

            @Override
            public void prepareFailView(String error) {
                assertEquals("User already exists.", error);
            }

            @Override
            public void switchToLoginView() {
                // This is expected
            }
        };

        SignupInputBoundary interactor = new SignupInteractor(userRepository, profileRepository, failurePresenter,
                new UserFactory() {
            @Override
            public User create(String name, String password) {
                return new StudentUser(name, password);
            }
        });
        interactor.execute(inputData);
    }

    @Test
    void switchToListReviewViewTest() {
        // switch the view to list of reviews
        // This creates a presenter that tests whether the test case is as we expect.
        ViewManagerModel viewManagerModel = new ViewManagerModel();
        SignupUserDataAccessInterface userRepository = new UserInMemoryDAO();
        ProfileDataAccessInterface profileRepository = new ProfileInMemoryDAO();
        SignupOutputBoundary switchPresenter = new SignupOutputBoundary() {
            @Override
            public void prepareSuccessView(SignupOutputData user) {
                // this should never be reached since the test case should fail
                fail("Use case success is unexpected.");
            }

            @Override
            public void prepareFailView(String error) {
                // this should never be reached since the test case should fail
                fail("Use case failure is unexpected.");
            }

            /**
             * Switches to List of Reviews View.
             */
            @Override
            public void switchToLoginView() {
                viewManagerModel.setState("login");
                assertEquals("login", viewManagerModel.getState());
            }
        };
        SignupInputBoundary interactor = new SignupInteractor(userRepository, profileRepository, switchPresenter, new UserFactory() {
            @Override
            public User create(String name, String password) {
                return new StudentUser(name, password);
            }
        });
        interactor.switchToLoginView();
    }
}