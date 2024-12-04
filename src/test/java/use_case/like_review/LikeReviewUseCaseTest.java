package use_case.like_review;

import data_access.helper.Callback;
import interface_adapters.ViewManagerModel;
import interface_adapters.like_review.LikeReviewPresenter;
import interface_adapters.list_review.ListReviewViewModel;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

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

    private static class TestLikeReviewDataAccess implements LikeReviewDataAccessInterface {
        private final Map<String, Set<String>> likes = new HashMap<>();
        private final Map<String, Integer> likeCounts = new HashMap<>();

        @Override
        public boolean hasUserLikedReview(String username, String reviewId) {
            return likes.containsKey(reviewId) && likes.get(reviewId).contains(username);
        }

        @Override
        public void saveLike(String username, String reviewId) {
            if (hasUserLikedReview(username, reviewId)) {
                likes.get(reviewId).remove(username);
                likeCounts.put(reviewId, likeCounts.get(reviewId) - 1);
            } else {
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
    void userLikesReview() throws InterruptedException {
        String reviewId = "test123";
        String username = "testUser";
        CountDownLatch latch = new CountDownLatch(1);

        LikeReviewInputData inputData = new LikeReviewInputData(username, reviewId);
        LikeReviewOutputBoundary successPresenter = new LikeReviewOutputBoundary() {
            @Override
            public void prepareSuccessView(LikeReviewOutputData outputData) {
                assertEquals("test123", outputData.getReviewId());
                assertEquals("testUser", outputData.getUsername());
                assertEquals(1, outputData.getCurrentLikeCount());
                assertTrue(outputData.isSuccess());
                latch.countDown();
            }

            @Override
            public void prepareFailView(String errorMessage) {
                fail("Use case failure was not expected");
                latch.countDown();
            }
        };

        likeReviewInteractor = new LikeReviewInteractor(dataAccess, successPresenter);
        likeReviewInteractor.execute(inputData, success -> {
            assertTrue(success);
            assertTrue(dataAccess.hasUserLikedReview(username, reviewId));
            assertEquals(1, dataAccess.getLikeCount(reviewId));
        });

        assertTrue(latch.await(1, TimeUnit.SECONDS), "Test timed out");
    }

    @Test
    void userUnlikesReview() throws InterruptedException {
        String reviewId = "test123";
        String username = "testUser";
        CountDownLatch latch = new CountDownLatch(2); // Wait for both operations

        LikeReviewInputData inputData = new LikeReviewInputData(username, reviewId);
        LikeReviewOutputBoundary successPresenter = new LikeReviewOutputBoundary() {
            @Override
            public void prepareSuccessView(LikeReviewOutputData outputData) {
                assertTrue(outputData.isSuccess());
                latch.countDown();
            }

            @Override
            public void prepareFailView(String errorMessage) {
                fail("Use case failure was not expected");
                latch.countDown();
            }
        };

        likeReviewInteractor = new LikeReviewInteractor(dataAccess, successPresenter);

        likeReviewInteractor.execute(inputData, success -> {
            assertTrue(success);
            assertTrue(dataAccess.hasUserLikedReview(username, reviewId));
            assertEquals(1, dataAccess.getLikeCount(reviewId));
        });

        likeReviewInteractor.execute(inputData, success -> {
            assertTrue(success);
            assertFalse(dataAccess.hasUserLikedReview(username, reviewId));
            assertEquals(0, dataAccess.getLikeCount(reviewId));
        });

        assertTrue(latch.await(2, TimeUnit.SECONDS), "Test timed out");
    }

    @Test
    void exceptionTest() throws InterruptedException {
        String reviewId = "test123";
        String username = "testUser";
        CountDownLatch latch = new CountDownLatch(1);

        LikeReviewInputData inputData = new LikeReviewInputData(username, reviewId);
        LikeReviewOutputBoundary failPresenter = new LikeReviewOutputBoundary() {
            @Override
            public void prepareSuccessView(LikeReviewOutputData outputData) {
                throw new ArithmeticException("Test exception");
            }

            @Override
            public void prepareFailView(String errorMessage) {
                assertEquals("Test exception", errorMessage);
                latch.countDown();
            }
        };

        likeReviewInteractor = new LikeReviewInteractor(dataAccess, failPresenter);
        likeReviewInteractor.execute(inputData, success -> {
            assertFalse(success);
        });

        assertTrue(latch.await(1, TimeUnit.SECONDS), "Test timed out");
    }
}