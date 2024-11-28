package use_case;

import interface_adapters.ViewManagerModel;
import interface_adapters.change_password.LoggedInViewModel;
import interface_adapters.like_review.LikeReviewPresenter;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import use_case.like_review.*;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

public class LikeReviewUseCaseTest {
    private LikeReviewInputBoundary likeReviewInteractor;
    private TestLikeReviewDataAccess dataAccess;
    private LoggedInViewModel loggedInViewModel;

    @BeforeEach
    void setUp() {
        dataAccess = new TestLikeReviewDataAccess();

        loggedInViewModel = new LoggedInViewModel();

        LikeReviewOutputBoundary presenter = new LikeReviewPresenter(new ViewManagerModel(), loggedInViewModel);

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
            if (hasUserLikedReview(username, reviewId)){
                likes.get(reviewId).add(username);
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
}