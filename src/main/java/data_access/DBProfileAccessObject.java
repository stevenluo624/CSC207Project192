package data_access;

import com.google.gson.JsonObject;

import entity.Profile;
import entity.StudentUser;
import entity.User;
import helper.ProjectConstants;
import helper.FirestoreHelper;
import use_case.profile.ProfileDataAccessInterface;

import java.util.HashMap;
import java.util.Map;

public class DBProfileAccessObject implements ProfileDataAccessInterface {
    private FirestoreHelper helper;
    String collectionName;

    public DBProfileAccessObject() {
        helper = new FirestoreHelper(ProjectConstants.API_KEY, ProjectConstants.PROJECT_ID);
        this.collectionName = ProjectConstants.PROFILE_COLLECTION;
    }

    @Override
    public void save(Profile profile) {
        Map<String, Object> data = new HashMap<>();
        data.put("username", profile.getUser().getUsername());
        data.put("password", profile.getUser().getPassword());
        data.put("bio", profile.getBio());
        helper.addDocument(collectionName, data, profile.getUser().getUsername());
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
