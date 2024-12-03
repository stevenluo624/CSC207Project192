package use_case.create_review;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import entity.StudentUser;
import entity.User;
import data_access.in_memory_dao.ReviewListInMemoryDAO;

class CreateReviewUseCaseTest {
    User testUser;
    int testRating;
    String testComment;
    String testLocationName;

    ReviewListInMemoryDAO dataAccess;
    CreateReviewInputData inputData;
    CreateReviewInteractor interactor;

    @BeforeEach
    void setUp() {
        testUser = new StudentUser("Username", "Password");
        testRating = 0;
        testComment = "Comment";
        testLocationName = "Building 1";

        dataAccess = new ReviewListInMemoryDAO();
        inputData = new CreateReviewInputData(testUser, testRating, testComment, testLocationName);
    }

    @Test
    void successTest() {
        testUser = new StudentUser("Test Username", "Password");
        testRating = 5;
        testComment = "Test comment.";

        dataAccess = new ReviewListInMemoryDAO();
        inputData = new CreateReviewInputData(testUser, testRating, testComment, testLocationName);

        CreateReviewOutputBoundary mockPresenter = new CreateReviewOutputBoundary() {
            @Override
            public void prepareSuccessView(CreateReviewOutputData outputData) {
                assertEquals("Test Username", outputData.getUser().getUsername());
                assertEquals("Password", outputData.getUser().getPassword());
                assertEquals(5, outputData.getRating());
                assertEquals("Test comment.", outputData.getComment());
                assertFalse(outputData.isUseCaseFailed());

                // Check if the review was successfully saved to the DAO
                assertTrue(dataAccess.getReviews().stream().anyMatch(
                        review -> review.getUser().equals(testUser)
                                && review.getRating() == testRating
                                && review.getComment().equals(testComment)
                        )
                );

                // Check if id is correct
                assertEquals(1, dataAccess.getReviews().get(0).getId());
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
}
