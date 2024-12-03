package use_case.like_review;

import interface_adapters.ViewManagerModel;
import interface_adapters.like_review.LikeReviewPresenter;
import interface_adapters.list_review.ListReviewViewModel;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

public class LikeReviewUseCaseTest {
    private LikeReviewInputBoundary likeReviewInteractor;
    private TestLikeReviewDataAccess dataAccess;
    private ListReviewViewModel listReviewViewModel;

    @BeforeEach
    void setUp() {
        dataAccess = new TestLikeReviewDataAccess();

        listReviewViewModel = new ListReviewViewModel();

        LikeReviewOutputBoundary presenter = new LikeReviewPresenter(new ViewManagerModel(), listReviewViewModel);

        likeReviewInteractor = new LikeReviewInteractor(dataAccess, presenter);
    }

    /**
     * Test for the Like Review Button
     */
    private static class TestLikeReviewDataAccess implements LikeReviewDataAccessInterface {
        private final Map<String, Set<String>> likes = new HashMap<>();
        private final Map<String, Integer> likeCounts = new HashMap<>();

        @Override
        public boolean hasUserLikedReview(String username, String reviewId) {
            return likes.containsKey(reviewId) && likes.get(reviewId).contains(username);
        }

        @Override
        public void saveLike(String username, String reviewId) {
            if (hasUserLikedReview(username, reviewId)){
                likes.get(reviewId).remove(username);
                likeCounts.put(reviewId, likeCounts.get(reviewId) - 1);
            }
            else {
                likes.computeIfAbsent(reviewId, k -> new HashSet<>()).add(username);
                likeCounts.put(reviewId, likeCounts.getOrDefault(reviewId, 0) + 1);
            }
        }

        @Override
        public int getLikeCount(String reviewId) {
            return likeCounts.getOrDefault(reviewId, 0);
        }
    }

    @Test
    void userLikesReview() {
        String reviewId = "test123";
        String username = "testUser";

        LikeReviewInputData inputData = new LikeReviewInputData(username, reviewId);
        LikeReviewOutputBoundary successPresenter = new LikeReviewOutputBoundary() {
            @Override
            public void prepareSuccessView(LikeReviewOutputData outputData) {
                assertEquals("test123", outputData.getReviewId());
                assertEquals("testUser", outputData.getUsername());
                assertEquals(1, outputData.getCurrentLikeCount());
                assertTrue(outputData.isSuccess());
            }

            @Override
            public void prepareFailView(String errorMessage) {
                fail("Use case is not expected to fail");
            }
        };
        likeReviewInteractor = new LikeReviewInteractor(dataAccess, successPresenter);
        likeReviewInteractor.execute(inputData);

        assertTrue(dataAccess.hasUserLikedReview(username, reviewId));
        assertEquals(1, dataAccess.getLikeCount(reviewId));
    }

    @Test
    void userUnlikesReview() {
        String reviewId = "test123";
        String username = "testUser";
        LikeReviewInputData likeData = new LikeReviewInputData(username, reviewId);
        likeReviewInteractor.execute(likeData);

        assertTrue(dataAccess.hasUserLikedReview(username, reviewId));
        assertEquals(1, dataAccess.getLikeCount(reviewId));

        likeReviewInteractor.execute(likeData);

        assertFalse(dataAccess.hasUserLikedReview(username, reviewId));
        assertEquals(0, dataAccess.getLikeCount(reviewId));
    }

    @Test
    void exceptionTest() {
        String reviewId = "test123";
        String username = "testUser";
        LikeReviewInputData inputData = new LikeReviewInputData(username, reviewId);
        LikeReviewOutputBoundary failPresenter = new LikeReviewOutputBoundary() {
            @Override
            public void prepareSuccessView(LikeReviewOutputData outputData) {
               int time = 1/0;
            }

            @Override
            public void prepareFailView(String errorMessage) {
                assertEquals("/ by zero", errorMessage);
            }
        };
        likeReviewInteractor = new LikeReviewInteractor(dataAccess, failPresenter);
        likeReviewInteractor.execute(inputData);
    }
}