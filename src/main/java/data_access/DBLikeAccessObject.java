package data_access;

import com.google.api.core.ApiFuture;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.*;
import com.google.gson.JsonObject;
import data_access.helper.FirestoreHelper;
import data_access.helper.GlobalHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import use_case.like_review.LikeReviewDataAccessInterface;

import java.util.HashMap;
import java.util.Map;


/**
 * Data access object for managing review likes
 */
public class DBLikeAccessObject implements LikeReviewDataAccessInterface {
    private final FirestoreHelper helper;
    private final String userReviews = "userReviews";
    private final String likes = "likes";

    public DBLikeAccessObject() {
        helper = GlobalHelper.getHelper();
    }
    @Override
    public boolean hasUserLikedReview(String username, String reviewId) {
        final String newReviewId = "review" + reviewId;
        System.out.println("Checking like status for review ID: " + newReviewId);
        String likeDocId = reviewId + "_" + username;
        return helper.checkExists(likes, likeDocId);
    }

    @Override
    public void saveLike(String username, String reviewId) {
        final String newReviewId = "review" + reviewId;
        System.out.println("Processing like operation for review: " + newReviewId);

        // Debug the exact ID we're checking
        System.out.println("Looking for review with exact ID: " + newReviewId);

        if (!helper.checkExists(userReviews, newReviewId)) {
            System.out.println("ERROR: Review not found in database: " + newReviewId);
            return;
        }

        String likeDocId = newReviewId + "_" + username;

        try {
            if (hasUserLikedReview(username, newReviewId)) {
                // Unlike
                helper.deleteDocument(likes, likeDocId);
                helper.incrementField(userReviews, newReviewId, "likes", -1);
                System.out.println("Unlike successful - removed like document and decremented count");
            } else {
                // Like
                Map<String, Object> likeData = new HashMap<>();
                likeData.put("username", username);
                likeData.put("reviewId", newReviewId);
                helper.addDocument(likes, likeData, likeDocId);
                helper.incrementField(userReviews, newReviewId, "likes", 1);
                System.out.println("Like successful - added like document and incremented count");
            }
        } catch (Exception e) {
            System.err.println("Error in like operation: " + e.getMessage());
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @Override
    public int getLikeCount(String reviewId) {
        final String newReviewId = "review" + reviewId;
        try {
            System.out.println("Getting like count for review: " + newReviewId);
            JsonObject doc = helper.getDocument(userReviews, newReviewId);

            if (doc != null && doc.has("likes")) {
                int count = doc.get("likes").getAsInt();
                System.out.println("Found like count: " + count);
                return count;
            }
            System.out.println("No likes field found, returning 0");
            return 0;
        } catch (Exception e) {
            System.err.println("Error getting like count: " + e.getMessage());
            return 0;
        }
    }
}