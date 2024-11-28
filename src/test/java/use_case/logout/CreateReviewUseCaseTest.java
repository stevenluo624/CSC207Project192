package use_case.logout;

import data_access.in_memory_dao.LikeInMemoryDAO;
import entity.reviews_thread.Review;
import org.junit.jupiter.api.BeforeEach;
import use_case.create_review.*;
import org.junit.Test;

import static org.junit.Assert.*;

public class CreateReviewUseCaseTest {
    private CreateReviewInteractor interactor;
    private CreateReviewOutputBoundary presenter;
    private LikeInMemoryDAO likeInMemoryDAO;

    @BeforeEach
    public void setUp() {
        presenter = CreateReviewOutputBoundary;
        likeInMemoryDAO = LikeInMemoryDAO;
        interactor = new CreateReviewInteractor(likeInMemoryDAO, presenter);
    }

    @Test
    public void successfulCreateReview() {
        CreateReviewDataAccessInterface noteDAO = new CreateReviewDataAccessInterface() {


            /**
             * @param review contains details of the review
             * @return
             */
            @Override
            public String saveReview(Review review) {
                return "";
            }

            /**
             * @param id the review id
             * @return
             */
            @Override
            public Review getReview(String id) {
                return null;
            }

        CreateReviewOutputBoundary noteOB = new CreateReviewIOutputBoundary {
            @Override
            public void prepareSuccessView(String message) {
                assertEquals("test", message);
            }

            @Override
            public void prepareFailView(String errorMessage) {
                fail(errorMessage);
            }
        };

        NoteInteractor noteInteractor = new NoteInteractor(noteDAO, noteOB);

        noteInteractor.executeRefresh();
    }
}
