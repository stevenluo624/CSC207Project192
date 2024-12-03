package data_access;

import com.google.gson.JsonObject;

import entity.Profile;
import entity.StudentUser;
import entity.User;
import data_access.helper.GlobalHelper;
import data_access.helper.ProjectConstants;
import data_access.helper.FirestoreHelper;
import use_case.profile.ProfileDataAccessInterface;

import java.util.HashMap;
import java.util.Map;

/**
 * Data access object for managing user profiles
 */
public class DBProfileAccessObject implements ProfileDataAccessInterface {
    private FirestoreHelper helper;
    String collectionName;

    public DBProfileAccessObject() {
        helper = GlobalHelper.getHelper();
        this.collectionName = ProjectConstants.PROFILE_COLLECTION;
    }

    @Override
    public void save(Profile profile) {
        Map<String, Object> data = new HashMap<>();
        data.put("username", profile.getUsername());
        data.put("bio", profile.getBio());
        helper.addDocument(collectionName, data, profile.getUsername());
    }

    @Override
    public void update(String username, String bio) {
        Map<String, Object> data = new HashMap<>();
        data.put("username", username);
        data.put("bio", bio);
        helper.updateDocument(collectionName, data, username);
    }


    @Override
    public String getBio(String username) {
        JsonObject data = helper.getDocument(collectionName, username);
        return data.getAsJsonObject("bio").get("stringValue").getAsString();
    }

    @Override
    public User getUser(String username) {
        JsonObject data = helper.getDocument(collectionName, username);
        String name = data.getAsJsonObject("username").get("stringValue").getAsString();
        String password = data.getAsJsonObject("password").get("stringValue").getAsString();
        return new StudentUser(name, password);
    }
}
