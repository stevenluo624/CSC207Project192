package use_case.create_review;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.Assert.*;

import entity.StudentUser;
import entity.User;
import entity.reviews_thread.Review;

import data_access.in_memory_dao.ReviewListInMemoryDAO;
import use_case.create_review.CreateReviewInputData;
import use_case.create_review.CreateReviewDataAccessInterface;
import use_case.create_review.CreateReviewInteractor;
import interface_adapters.create_review.CreateReviewPresenter;

class CreateReviewUseCaseTest {
    User testUser;
    int testRating;
    String testComment;

    ReviewListInMemoryDAO dataAccess;
    CreateReviewInputData inputData;
    CreateReviewInteractor interactor;


    @BeforeEach
    void setUp() {
        testUser = new StudentUser("Test Username", "Password");
        testRating = 5;
        testComment = "Test comment.";

        dataAccess = new ReviewListInMemoryDAO();
        inputData = new CreateReviewInputData(testUser, testRating, testComment);
    }

    @Test
    void successTest() {
        testUser = new StudentUser("Test Username", "Password");
        testRating = 5;
        testComment = "Test comment.";

        dataAccess = new ReviewListInMemoryDAO();
        inputData = new CreateReviewInputData(testUser, testRating, testComment);
        dataAccess.saveReview(new Review(testUser, testRating, testComment));

        CreateReviewOutputBoundary mockPresenter = new CreateReviewOutputBoundary() {
            @Override
            public void prepareSuccessView(CreateReviewOutputData outputData) {
                assertEquals("Test Username", outputData.getUser().getUsername());
                assertEquals("Password", outputData.getUser().getPassword());
                assertEquals(5, outputData.getRating());
                assertEquals("Test comment.", outputData.getComment());

                assertTrue(dataAccess.getReviews().contains(
                        new Review(outputData.getUser(), outputData.getRating(), outputData.getComment())
                ));
            }

            @Override
            public void prepareFailureView(String message) {
                fail("Use case failure is unexpected. Error: " + message);
            }
        };

        interactor = new CreateReviewInteractor(dataAccess, mockPresenter);
        interactor.execute(inputData);
    }
}