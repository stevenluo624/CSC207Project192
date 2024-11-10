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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import use_case.login.LoginUserDataAccessInterface;
import use_case.signup.SignupUserDataAccessInterface;

public class DBUserAccessObject implements LoginUserDataAccessInterface, SignupUserDataAccessInterface {
    private static final Logger log = LoggerFactory.getLogger(DBUserAccessObject.class);
    private final CollectionReference users;

    public DBUserAccessObject() throws IOException {
        InputStream serviceAccount = new FileInputStream("api_data/api_key.json");
        GoogleCredentials credentials = GoogleCredentials.fromStream(serviceAccount);
        FirebaseOptions options = new FirebaseOptions.Builder()
                .setCredentials(credentials)
                .build();
        FirebaseApp customApp = FirebaseApp.initializeApp(options, "UserAccess");
        Firestore db = FirestoreClient.getFirestore(customApp);
        this.users = db.collection("users");
    }

    @Override
    public boolean existsByName(String username) {
        DocumentReference docRef = users.document(username);
        try {
            return docRef.get().get().exists();
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void save(User user) {
        DocumentReference docRef = users.document(user.getUsername());
        Map<String, Object> data = new HashMap<>();
        data.put("username", user.getUsername());
        data.put("password", user.getPassword());
        try {
            ApiFuture<WriteResult> result = docRef.set(data);
            if (log.isInfoEnabled()) {
                log.info(result.get().toString());
            }
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public User get(String username) {
        DocumentReference docRef = users.document(username);
        ApiFuture<DocumentSnapshot> future = docRef.get();
        try {
            DocumentSnapshot doc = future.get();
            return new StudentUser((String) doc.get("username"), (String) doc.get("password"));
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }
    }
}
