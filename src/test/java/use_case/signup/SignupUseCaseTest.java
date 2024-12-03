//package use_case.signup;
//
//import data_access.in_memory_dao.UserInMemoryDAO;
//import entity.StudentUser;
//import entity.User;
//import app.UserFactory;
//import org.junit.jupiter.api.Test;
//
//import java.time.LocalDateTime;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//class SignupUseCaseTest {
//
//    @Test
//    void successTest() {
//        SignupInputData inputData = new SignupInputData("Paul", "password", "password");
//        SignupUserDataAccessInterface userRepository = new UserInMemoryDAO();
//
//        // This creates a successPresenter that tests whether the test case is as we expect.
//        SignupOutputBoundary successPresenter = new SignupOutputBoundary() {
//            @Override
//            public void prepareSuccessView(SignupOutputData user) {
//                // 2 things to check: the output data is correct, and the user has been created in the DAO.
//                assertEquals("Paul", user.getUsername());
//                assertTrue(userRepository.existsByName("Paul"));
//            }
//
//            @Override
//            public void prepareFailView(String error) {
//                fail("Use case failure is unexpected.");
//            }
//
//            @Override
//            public void switchToLoginView() {
//                // This is expected
//            }
//        };
//
//        SignupInputBoundary interactor = new SignupInteractor(userRepository, successPresenter, new UserFactory() {
//            @Override
//            public User create(String name, String password) {
//                return new StudentUser(name, password);
//            }
//        });
//        interactor.execute(inputData);
//    }
//
//    @Test
//    void failurePasswordMismatchTest() {
//        SignupInputData inputData = new SignupInputData("Paul", "password", "wrong");
//        SignupUserDataAccessInterface userRepository = new UserInMemoryDAO();
//
//        // This creates a presenter that tests whether the test case is as we expect.
//        SignupOutputBoundary failurePresenter = new SignupOutputBoundary() {
//            @Override
//            public void prepareSuccessView(SignupOutputData user) {
//                // this should never be reached since the test case should fail
//                fail("Use case success is unexpected.");
//            }
//
//            @Override
//            public void prepareFailView(String error) {
//                assertEquals("Passwords don't match.", error);
//            }
//
//            @Override
//            public void switchToLoginView() {
//                // This is expected
//            }
//        };
//
//        SignupInputBoundary interactor = new SignupInteractor(userRepository, failurePresenter, new UserFactory() {
//            @Override
//            public User create(String name, String password) {
//                return new StudentUser(name, password);
//            }
//        });
//        interactor.execute(inputData);
//    }
//
//    @Test
//    void failureUserExistsTest() {
//        SignupInputData inputData = new SignupInputData("Paul", "password", "wrong");
//        SignupUserDataAccessInterface userRepository = new UserInMemoryDAO();
//
//        // Add Paul to the repo so that when we check later they already exist
//        UserFactory factory = new UserFactory() {
//            @Override
//            public User create(String name, String password) {
//                return new StudentUser(name, password);
//            }
//        };
//        User user = factory.create("Paul", "pwd");
//        userRepository.save(user);
//
//        // This creates a presenter that tests whether the test case is as we expect.
//        SignupOutputBoundary failurePresenter = new SignupOutputBoundary() {
//            @Override
//            public void prepareSuccessView(SignupOutputData user) {
//                // this should never be reached since the test case should fail
//                fail("Use case success is unexpected.");
//            }
//
//            @Override
//            public void prepareFailView(String error) {
//                assertEquals("User already exists.", error);
//            }
//
//            @Override
//            public void switchToLoginView() {
//                // This is expected
//            }
//        };
//
//        SignupInputBoundary interactor = new SignupInteractor(userRepository, failurePresenter, new UserFactory() {
//            @Override
//            public User create(String name, String password) {
//                return new StudentUser(name, password);
//            }
//        });
//        interactor.execute(inputData);
//    }
//}