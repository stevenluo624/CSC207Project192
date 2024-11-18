package data_access;

import com.google.gson.JsonObject;
import entity.UserReview;
import helper.FirestoreHelper;
import use_case.list_review.ListReviewDataAccessInterface;

import java.util.List;

public class DBReviewListAccessObject implements ListReviewDataAccessInterface {
    private FirestoreHelper helper;
    String collectionName;

    public DBReviewListAccessObject(String collectionName) {
        // TODO: add api key info.
        helper = new FirestoreHelper("", "");
        this.collectionName = collectionName;
    }

    @Override
    public boolean checkPageExists(int pageNumber, int pageSize) {
        return helper.checkPageExists(collectionName, pageNumber, pageSize);
    }

    @Override
    public List<UserReview> getReviews(int pageNumber, int pageSize) {
        JsonObject page = helper.getPage(collectionName, pageNumber, pageSize);
        
        return List.of();
    }
}