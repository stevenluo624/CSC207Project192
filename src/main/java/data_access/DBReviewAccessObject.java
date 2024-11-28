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

import entity.*;
import entity.reviews_thread.Review;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import use_case.create_review.CreateReviewDataAccessInterface;

public class DBReviewAccessObject implements CreateReviewDataAccessInterface {
    private static final Logger log = LoggerFactory.getLogger(DBReviewAccessObject.class);
    private final CollectionReference locations, reviews, users;

    public DBReviewAccessObject() throws IOException {
        InputStream serviceAccount = new FileInputStream("api_data/api_key.json");
        GoogleCredentials credentials = GoogleCredentials.fromStream(serviceAccount);
        // TODO: Builder() in com.google.firebase.FirebaseOptions.Builder has been deprecated
        FirebaseOptions options = new FirebaseOptions.Builder()
                .setCredentials(credentials)
                .build();
        FirebaseApp customApp = FirebaseApp.initializeApp(options, "ReviewAccess");
        Firestore db = FirestoreClient.getFirestore(customApp);

        this.locations = db.collection("locations");
        this.reviews = db.collection("reviews");
        this.users = db.collection("users");
    }

    @Override
    public String saveReview(Review review) {
        Map<String, Object> data = new HashMap<>();
        data.put("user", review.getUser().getUsername());
        data.put("rating", review.getRating());
        data.put("comment", review.getComment());
        data.put("location_name", review.getLocation().getName());
        data.put("location_description", review.getLocation().getDescription());
        data.put("location_address", review.getLocation().getAddress());
        data.put("location_rating", review.getLocation().getRating());
        data.put("location_latitude", review.getLocation().getLatitude());
        data.put("location_longitude", review.getLocation().getLongitude());

        if (review.getLocation() instanceof StudyLocation) {
            data.put("location_type", "study");
            data.put("building", ((StudyLocation) review.getLocation()).getBuilding());
        } else if (review.getLocation() instanceof FoodLocation) {
            data.put("location_type", "food");
            data.put("type", ((FoodLocation) review.getLocation()).getType());
        }

        ApiFuture<DocumentReference> docRef = reviews.add(data);
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
    public Review() getReview(String id) {
        DocumentReference docRef = reviews.document(id);
        ApiFuture<DocumentSnapshot> future = docRef.get();
        try {
            DocumentSnapshot doc = future.get();
            String username = (String) doc.get("user");
            assert username != null;
            String password = (String) users.document(username).get().get().get("password");
            User user = new StudentUser(username, password);
            Integer reviewRating = (Integer) doc.get("rating");
            String comment = (String) doc.get("comment");
            String locationName = (String) doc.get("location_name");
            String locationDescription = (String) doc.get("location_description");
            String locationAddress = (String) doc.get("location_address");
            String locationRating = (String) doc.get("location_rating");
            String locationType = (String) doc.get("location_type");
            Double locationLatitude = (Double) doc.get("location_latitude");
            Double locationLongitude = (Double) doc.get("location_longitude");
            if (locationType.equals("study")) {
                String building = (String) doc.get("building");
                return new Review(user, reviewRating, comment, new StudyLocation(locationName, locationLatitude
                        , locationLongitude, building));
            } else if (locationType.equals("food")) {
                String type = (String) doc.get("type");
                return new Review(user, reviewRating, comment, new FoodLocation(locationName, locationLatitude
                        , locationLongitude, type));
            }
            return null;
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }
    }
}
