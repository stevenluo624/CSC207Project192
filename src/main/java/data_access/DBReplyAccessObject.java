package data_access;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import entity.reviews_thread.Review;

import data_access.helper.GlobalHelper;
import data_access.helper.ProjectConstants;
import data_access.helper.FirestoreHelper;

import entity.reviews_thread.Reply;
import use_case.create_reply.CreateReplyDataAccessInterface;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Data access object for managing replies to reviews
 */
public class DBReplyAccessObject implements CreateReplyDataAccessInterface {
    private final FirestoreHelper helper;
    private final String repliesCollectionName;
    private final String reviewsCollectionName;

    public DBReplyAccessObject() {
        helper = GlobalHelper.getHelper();
        this.repliesCollectionName = ProjectConstants.REPLIES_COLLECTION;
        this.reviewsCollectionName = ProjectConstants.REVIEWS_COLLECTION;
    }

    /**
     * @param parentReview a review or reply that is being replied to with reply
     * @param reply        contains details of a new reply.
     */
    @Override
    public void updateReviewThread(Review parentReview, Reply reply) {
        try {
            final String replyId = "reply" + String.valueOf(reply.getId());
            final String reviewId = "review" + String.valueOf(parentReview.getId());
            JsonObject parentReviewJson = helper.getDocument(reviewsCollectionName, reviewId);

            // Get the "replies" field from the parent review document and add the new reply to the "replies" array
            JsonArray repliesFieldJson = parentReviewJson.getAsJsonArray("replies");
            List<String> repliesList = new ArrayList<>();
            for (int i = 0; i < repliesFieldJson.size(); i++) {
                JsonObject repliesJson = repliesFieldJson.get(i).getAsJsonObject();
                repliesList.add(repliesJson.get("id").getAsString());
            }
            repliesList.add(replyId);

            // Update "reviews" collection in Firestore
            Map<String, Object> updatedReviewFields = new HashMap<>();
            updatedReviewFields.put("replies", repliesList);
            helper.updateDocument(reviewsCollectionName, updatedReviewFields, reviewId);

            // Update "replies" collection in Firestore
            Map<String, Object> replyDocumentData = new HashMap<>();
            replyDocumentData.put("user", reply.getUser().getUsername());
            replyDocumentData.put("comment", reply.getComment());
            replyDocumentData.put("likes", reply.getNumberOfLikes());
            replyDocumentData.put("id", replyId);
            helper.addDocument(repliesCollectionName, replyDocumentData, replyId);

        } catch (Exception e) {
            throw new RuntimeException("Failed to update the review thread", e);
        }

    }
}
