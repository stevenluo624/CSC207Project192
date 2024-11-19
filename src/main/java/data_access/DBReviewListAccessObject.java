package data_access;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import entity.User;
import entity.UserReview;
import helper.FirestoreHelper;
import use_case.list_review.ListReviewDataAccessInterface;

import java.util.ArrayList;
import java.util.List;

public class DBReviewListAccessObject implements ListReviewDataAccessInterface {
    private FirestoreHelper helper;
    String collectionName;

    public DBReviewListAccessObject(String collectionName) {
        helper = new FirestoreHelper("AIzaSyAaeksmKcFCY8uLnzMJTBMCor0zCESv9nw", "ratemycampus-b5981");
        this.collectionName = collectionName;
    }

    @Override
    public boolean checkPageExists(int pageNumber, int pageSize) {
        return helper.checkPageExists(collectionName, pageNumber, pageSize);
    }

    @Override
    public List<UserReview> getReviews(int pageNumber, int pageSize) {
        JsonObject page = helper.getPage(collectionName, pageNumber, pageSize);
        // Parse the JSON string into a JsonObject
        JsonArray documents = page.getAsJsonArray("documents");

        // Initialize the result list
        List<UserReview> reviewList = new ArrayList<>();

        // Loop through each document in the "documents" array
        for (JsonElement element : documents) {
            JsonObject document = element.getAsJsonObject();
            JsonObject fields = document.getAsJsonObject("fields");

            // Extract fields, defaulting to null if the field doesn't exist
            String comment = fields.has("comment") ? fields.getAsJsonObject("comment").get("stringValue").getAsString() : null;
            String user = fields.has("user") ? fields.getAsJsonObject("user").get("stringValue").getAsString() : null;
            Double rating = fields.has("rating") ? fields.getAsJsonObject("rating").get("doubleValue").getAsDouble() : null;
            String location = fields.has("location") ? fields.getAsJsonObject("location").get("stringValue").getAsString() : null;

            // Create a map for the current review
            UserReview review = new UserReview()

            // Add the review map to the list
            reviewList.add(review);
        }

        return List.of();
    }
}