package data_access;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;

import com.google.api.core.ApiFuture;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.CollectionReference;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.FieldValue;
import com.google.cloud.firestore.Firestore;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.cloud.FirestoreClient;
import use_case.like_review.LikeReviewDataAccessInterface;

/**
 * Data Access Object for handling like operations in Firestore.
 */
public class DBLikeAccessObject implements LikeReviewDataAccessInterface {
    private static final String LIKES_FIELD = "likes";
    private final CollectionReference userReviews;
    private final CollectionReference likes;

    public DBLikeAccessObject() throws IOException {
        final InputStream serviceAccount = new FileInputStream("api_data/api_key.json");
        final GoogleCredentials credentials = GoogleCredentials.fromStream(serviceAccount);
        final FirebaseOptions options = new FirebaseOptions.Builder()
                .setCredentials(credentials)
                .build();
        final FirebaseApp customApp = FirebaseApp.initializeApp(options, "LikeAccess");
        final Firestore db = FirestoreClient.getFirestore(customApp);

        this.likes = db.collection(LIKES_FIELD);
        this.userReviews = db.collection("userReviews");
    }

    @Override
    public boolean hasUserLikedReview(String username, String reviewId) {
        final DocumentReference docRef = likes.document(reviewId + "_" + username);
        try {
            return docRef.get().get().exists();
        }
        catch (InterruptedException | ExecutionException exception) {
            throw new RuntimeException(exception);
        }
    }

    @Override
    public void saveLike(String username, String reviewId) {
        final DocumentReference likeRef = likes.document(reviewId + "_" + username);
        final DocumentReference reviewRef = userReviews.document(reviewId);

        if (hasUserLikedReview(username, reviewId)) {
            try {
                likeRef.delete();
                reviewRef.update(LIKES_FIELD, FieldValue.increment(-1));
            }
            catch (Exception exception) {
                throw new RuntimeException(exception);
            }
        }
        else {
            try {
                final Map<String, Object> likeData = new HashMap<>();
                likeData.put("username", username);
                likeData.put("reviewId", reviewId);
                likeRef.set(likeData);
                reviewRef.update(LIKES_FIELD, FieldValue.increment(1));
            }
            catch (Exception exception) {
                throw new RuntimeException(exception);
            }
        }
    }

    @Override
    public int getLikeCount(String reviewId) {
        final DocumentReference docRef = userReviews.document(reviewId);
        final ApiFuture<DocumentSnapshot> future = docRef.get();

        try {
            final DocumentSnapshot doc = future.get();
            final Long numLikes = (Long) doc.get(LIKES_FIELD);
            int likeCount = 0;
            if (numLikes != null) {
                likeCount = numLikes.intValue();
            }
            return likeCount;
        }
        catch (InterruptedException | ExecutionException exception) {
            throw new RuntimeException(exception);
        }
    }
}
