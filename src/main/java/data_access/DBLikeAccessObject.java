package data_access;

import com.google.gson.JsonObject;
import data_access.helper.FirestoreHelper;
import data_access.helper.ProjectConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import use_case.like_review.LikeReviewDataAccessInterface;

import java.util.HashMap;
import java.util.Map;


/**
 * Data access object for managing review likes
 */
public class DBLikeAccessObject implements LikeReviewDataAccessInterface {
    private static final Logger log = LoggerFactory.getLogger(DBLikeAccessObject.class);
    private final FirestoreHelper helper;
    private final String userReviews = "userReviews";
    private final String likes = "likes";

    public DBLikeAccessObject() {
        helper = new FirestoreHelper(ProjectConstants.API_KEY, ProjectConstants.PROJECT_ID);
    }

    @Override
    public boolean hasUserLikedReview(String username, String reviewId) {
        String doc = reviewId + "_" + username;
        log.info("Checking exist");
        boolean f = helper.checkExists(likes, doc);
        log.info(String.valueOf(f));
        return f;
    }

    @Override
    public void saveLike(String username, String reviewId) {
        String doc = reviewId + "_" + username;
        if (hasUserLikedReview(username, reviewId)) {
            try {
                helper.deleteDocument(likes, doc);
                helper.incrementField(userReviews, reviewId, likes, -1);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        } else {
            try {
                Map<String, Object> likeData = new HashMap<>();
                likeData.put("username", username);
                likeData.put("reviewId", reviewId);
                helper.addDocument(likes, likeData, doc);
                log.info("added data");
                helper.incrementField(userReviews, reviewId, likes, 1);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Override
    public int getLikeCount(String reviewId) {
        try {
            log.info("Get like");
            JsonObject doc = helper.getDocument(userReviews, reviewId);

            return doc.getAsJsonObject("likes").get("integerValue").getAsInt();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}