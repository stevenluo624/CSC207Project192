package data_access;

import com.google.api.core.ApiFuture;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.*;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.cloud.FirestoreClient;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;

import entity.StudentUser;
import entity.User;
import entity.UserReview;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import use_case.create_review.CreateReviewDataAccessInterface;

public class DBReviewAccessObject implements CreateReviewDataAccessInterface {
    private static final Logger log = LoggerFactory.getLogger(DBReviewAccessObject.class);
    private final CollectionReference studyLocations, foodLocations, userReviews, users;

    public DBReviewAccessObject() throws IOException {
        InputStream serviceAccount = new FileInputStream("api_data/api_key.json");
        GoogleCredentials credentials = GoogleCredentials.fromStream(serviceAccount);
        FirebaseOptions options = new FirebaseOptions.Builder()
                .setCredentials(credentials)
                .build();
        FirebaseApp customApp = FirebaseApp.initializeApp(options, "ReviewAccess");
        Firestore db = FirestoreClient.getFirestore(customApp);

        this.foodLocations = db.collection("foodLocations");
        this.studyLocations = db.collection("studyLocations");
        this.userReviews = db.collection("userReviews");
        this.users = db.collection("users");
    }

    @Override
    public String saveReview(UserReview review) {
        Map<String, Object> data = new HashMap<>();
        data.put("user", review.getUser().getUsername());
        data.put("rating", review.getRating());
        data.put("comment", review.getComment());
        ApiFuture<DocumentReference> docRef = userReviews.add(data);
        try {
            if (log.isInfoEnabled()) {
                log.info(docRef.get().toString() + " " + docRef.get().getId());
            }
            return docRef.get().getId();
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public UserReview getReview(String id) {
        DocumentReference docRef = userReviews.document(id);
        ApiFuture<DocumentSnapshot> future = docRef.get();
        try {
            DocumentSnapshot doc = future.get();
            String username = (String) doc.get("user");
            assert username != null;
            String password = (String) users.document(username).get().get().get("password");
            return new UserReview(new StudentUser(username, password), (Double) doc.get("rating"), (String) doc.get("comment"));
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }
    }
}
