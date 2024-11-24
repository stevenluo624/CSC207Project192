package data_access;

import com.google.api.core.ApiFuture;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.*;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.cloud.FirestoreClient;
import entity.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import use_case.like_review.LikeReviewDataAccessInterface;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;


public class DBLikeAccessObject implements LikeReviewDataAccessInterface {
    private static final Logger log = LoggerFactory.getLogger(DBLikeAccessObject.class);
    private final CollectionReference userReviews;
    private final CollectionReference likes;

    public DBLikeAccessObject() throws IOException{
        InputStream serviceAccount = new FileInputStream("api_data/api_key.json");
        GoogleCredentials credentials = GoogleCredentials.fromStream(serviceAccount);
        FirebaseOptions options = new FirebaseOptions.Builder()
                .setCredentials(credentials)
                .build();
        FirebaseApp customApp = FirebaseApp.initializeApp(options, "LikeAccess");
        Firestore db = FirestoreClient.getFirestore(customApp);

        this.likes = db.collection("likes");
        this.userReviews = db.collection("userReviews");
    }

    @Override
    public boolean hasUserLikedReview(String username, String reviewId) {
        DocumentReference docRef = likes.document(reviewId + "_" + username);
        try {
            return docRef.get().get().exists();
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void saveLike(String username, String reviewId) {
        DocumentReference likeRef = likes.document(reviewId + "_" + username);
        DocumentReference reviewRef = userReviews.document(reviewId);
        if (hasUserLikedReview(username, reviewId)) {
            try {
                likeRef.delete();
                reviewRef.update("likes", FieldValue.increment(-1));
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        } else {
            try {
                Map<String, Object> likeData = new HashMap<>();
                likeData.put("username", username);
                likeData.put("reviewId", reviewId);
                likeRef.set(likeData);
                reviewRef.update("likes", FieldValue.increment(1));
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Override
    public int getLikeCount(String reviewId) {
        DocumentReference docRef = userReviews.document(reviewId);
        ApiFuture<DocumentSnapshot> future = docRef.get();
        try {
            DocumentSnapshot doc = future.get();
            Long likes = (Long) doc.get("likes");
            return likes != null ? likes.intValue() : 0;
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }
    }
}