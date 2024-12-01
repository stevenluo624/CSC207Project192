package data_access;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import entity.Location;
import entity.User;
import entity.UserReview;
import entity.reviews_thread.Review;

import data_access.helper.GlobalHelper;
import data_access.helper.ProjectConstants;
import data_access.helper.FirestoreHelper;

import use_case.create_review.CreateReviewDataAccessInterface;
import use_case.list_review.ListReviewDataAccessInterface;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Data access object for managing reviews.
 */
public class DBReviewListAccessObject implements ListReviewDataAccessInterface, CreateReviewDataAccessInterface {
    private final FirestoreHelper helper;
    private final String collectionName;

    public DBReviewListAccessObject() {
        helper = GlobalHelper.getHelper();
        this.collectionName = ProjectConstants.REVIEWS_COLLECTION;
    }

    @Override
    public boolean checkPageExists(int pageNumber, int pageSize) {
        return helper.checkPageExists(collectionName, pageNumber, pageSize);
    }

    @Override
    public List<UserReview> getReviews(int pageNumber, int pageSize) {
        if (!checkPageExists(pageNumber, pageSize)) {
            return new ArrayList<>();
        }

        JsonObject page = helper.getPage(collectionName, pageNumber, pageSize);
        JsonArray documents = page.getAsJsonArray("documents");

        List<UserReview> reviewList = new ArrayList<>();

        DBUserAccessObject dbUserAccessObject = new DBUserAccessObject();

        for (JsonElement element : documents) {
            JsonObject document = element.getAsJsonObject();
            JsonObject fields = document.getAsJsonObject("fields");

            String comment = fields.has("comment") ? fields.getAsJsonObject("comment")
                    .get("stringValue").getAsString() : null;
            String user = fields.has("user") ? fields.getAsJsonObject("user")
                    .get("stringValue").getAsString() : null;
            Double rating = fields.has("rating") ? fields.getAsJsonObject("rating")
                    .get("doubleValue").getAsDouble() : -1;
            String location = fields.has("location") ? fields.getAsJsonObject("location")
                    .get("stringValue").getAsString() : null;
            String latitude = fields.has("latitude") ? fields.getAsJsonObject("latitude")
                    .get("stringValue").getAsString() : null;
            String longitude = fields.has("longitude") ? fields.getAsJsonObject("longitude")
                    .get("stringValue").getAsString() : null;
            String key = document.get("name").getAsString().substring(70);

            User userObject = dbUserAccessObject.get(user);
            Location locationObject = new Location(location, latitude, longitude) {};

            UserReview review = new UserReview(userObject, (int) Math.round(rating), comment, locationObject);
            review.setKey(key);

            reviewList.add(review);
        }

        return reviewList;
    }

    /**
     * Saves the review into the database and returns a String with the firestore id
     * @param review contains details of the review
     */
    @Override
    public void saveReview(Review review) {
        String documentValue = "review" + review.getId();
        Map<String, Object> data = new HashMap<>();

        data.put("user", review.getUser().getUsername());
        data.put("rating", review.getRating());
        data.put("comment", review.getComment());
        data.put("replies", review.getListOfReplies());
        data.put("likes", review.getNumberOfLikes());

        helper.addDocument(collectionName, data);
}