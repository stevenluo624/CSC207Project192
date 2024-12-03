package use_case.create_review;

import interface_adapters.ViewManagerModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import entity.StudentUser;
import entity.User;
import data_access.in_memory_dao.ReviewListInMemoryDAO;
import use_case.check_map.CheckMapInputBoundary;
import use_case.check_map.CheckMapInteractor;
import use_case.check_map.CheckMapOutputBoundary;
import use_case.check_map.CheckMapOutputData;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

class CreateReviewUseCaseTest {
    User testUser;
    int testRating;
    String testComment;
    String testLocationName;
    String testId;

    ReviewListInMemoryDAO dataAccess;
    CreateReviewInputData inputData;
    CreateReviewInteractor interactor;

    @BeforeEach
    void setUp() {
        testUser = new StudentUser("Username", "Password");
        testRating = 0;
        testComment = "Comment";
        testLocationName = "Building 1";
        testId = (testComment + "|" + testUser.getUsername()).replaceAll("\\s", "");

        dataAccess = new ReviewListInMemoryDAO();
        inputData = new CreateReviewInputData(testUser, testRating, testComment, testLocationName);
    }

    @Test
    void successTest() {
        testUser = new StudentUser("Test Username", "Password");
        testRating = 5;
        testComment = "Test comment.";
        testId = (testComment + "|" + testUser.getUsername()).replaceAll("\\s", "");

        dataAccess = new ReviewListInMemoryDAO();
        inputData = new CreateReviewInputData(testUser, testRating, testComment, testLocationName);

        CreateReviewOutputBoundary mockPresenter = new CreateReviewOutputBoundary() {
            @Override
            public void prepareSuccessView(CreateReviewOutputData outputData) {
                assertEquals("Test Username", outputData.getUser().getUsername());
                assertEquals("Password", outputData.getUser().getPassword());
                assertEquals(5, outputData.getRating());
                assertEquals("Test comment.", outputData.getComment());
                assertEquals("Building 1", outputData.getLocationName());
                assertFalse(outputData.isUseCaseFailed());

                // Check if the review was successfully saved to the DAO
                assertTrue(dataAccess.getReviews().stream().anyMatch(
                        review -> review.getUser().equals(testUser)
                                && review.getRating() == testRating
                                && review.getComment().equals(testComment)
                        )
                );

                // Check if id is correct
                assertEquals(testId, dataAccess.getReviews().get(0).getId());
            }

            @Override
            public void prepareFailureView(String message) {
                fail("Use case failure is unexpected. Error: " + message);
            }

            /**
             * Switches to List of Reviews View.
             */
            @Override
            public void switchToListReviewView() {
                fail("The use case should not switch");
            }
        };

        interactor = new CreateReviewInteractor(dataAccess, mockPresenter);
        interactor.execute(inputData);
    }

    @Test
    void switchToListReviewViewTest() {
        // switch the view to list of reviews
        // This creates a presenter that tests whether the test case is as we expect.
        ViewManagerModel viewManagerModel = new ViewManagerModel();
        CreateReviewOutputBoundary switchPresenter = new CreateReviewOutputBoundary() {
            @Override
            public void prepareSuccessView(CreateReviewOutputData review) {
                // this should never be reached since the test case should fail
                fail("Use case success is unexpected.");
            }

            @Override
            public void prepareFailureView(String error) {
                // this should never be reached since the test case should fail
                fail("Use case failure is unexpected.");
            }

            /**
             * Switches to List of Reviews View.
             */
            @Override
            public void switchToListReviewView() {
                viewManagerModel.setState("review list");
                assertEquals("review list", viewManagerModel.getState());
            }
        };
        CreateReviewInputBoundary interactor = new CreateReviewInteractor(dataAccess, switchPresenter);
        interactor.switchToListReviewView();
    }
}
