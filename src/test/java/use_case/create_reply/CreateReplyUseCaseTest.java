package use_case.create_reply;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import data_access.DBReplyAccessObject;
import data_access.in_memory_dao.ReplyInMemoryDAO;
import data_access.helper.FirestoreHelper;
import data_access.helper.ProjectConstants;

import entity.StudentUser;
import entity.User;
import entity.reviews_thread.Reply;
import entity.reviews_thread.Review;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterEach;

class CreateReplyUseCaseTest {
    private User testReplyUser;
    private String testReplyComment;
    private Review rootReview;

    private ReplyInMemoryDAO mockDataAccess;
    private CreateReplyInputData inputData;
    private CreateReplyInteractor interactor;
    private CreateReplyOutputBoundary mockPresenter;

    @BeforeEach
    void setUp() {
        testReplyUser = new StudentUser("", "");
        testReplyComment = "";
        rootReview = new Review(new StudentUser("", ""), 0, "");

        mockDataAccess = new ReplyInMemoryDAO();
        inputData = new CreateReplyInputData(testReplyUser, testReplyComment, rootReview);
        mockPresenter = new CreateReplyOutputBoundary() {
            @Override
            public void prepareSuccessView(CreateReplyOutputData outputData) { /* Empty */ }
            @Override
            public void prepareFailureView(String message) { /* Empty */ }
        };
        interactor = new CreateReplyInteractor(mockDataAccess, mockPresenter);
    }

    @Test
    void successTest() {
        testReplyUser = new StudentUser("Test Username", "Password");
        testReplyComment = "Test comment.";
        rootReview = new Review(
                new StudentUser("ReviewUsername", "ReviewPassword"), 5, "Test");

        mockDataAccess = new ReplyInMemoryDAO();
        inputData = new CreateReplyInputData(testReplyUser, testReplyComment, rootReview);

        mockPresenter = new CreateReplyOutputBoundary() {
            @Override
            public void prepareSuccessView(CreateReplyOutputData outputData) {
                // Check username and comment
                assertEquals("Test Username", outputData.getUser().getUsername());
                assertEquals("Test comment.", outputData.getComment());

                // Check if reply was added to review's list of replies
                assertTrue(mockDataAccess.getReviews().stream().anyMatch(
                        review -> review.getListOfReplies().stream().anyMatch(
                                reply -> reply.getUser().equals(testReplyUser)
                                && reply.getComment().equals(testReplyComment)
                        )
                ));
            }

            @Override
            public void prepareFailureView(String message) {
                fail("Use case failure is unexpected. Error: " + message);
            }
        };

        interactor = new CreateReplyInteractor(mockDataAccess, mockPresenter);
        interactor.execute(inputData);
    }
}