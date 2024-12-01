package data_access;

import com.google.gson.JsonObject;
import entity.Profile;
import entity.StudentUser;
import entity.User;
import helper.GlobalHelper;
import helper.ProjectConstants;
import helper.FirestoreHelper;
import use_case.change_password.ChangePasswordUserDataAccessInterface;
import use_case.login.LoginUserDataAccessInterface;
import use_case.signup.SignupUserDataAccessInterface;

import java.util.HashMap;
import java.util.Map;

/**
 * Data access object for manading user data.
 */
public class DBUserAccessObject implements LoginUserDataAccessInterface, SignupUserDataAccessInterface, ChangePasswordUserDataAccessInterface {
    private FirestoreHelper helper;
    String collectionName;
    User user;

    public DBUserAccessObject() {
        helper = GlobalHelper.getHelper();
        this.collectionName = ProjectConstants.USER_COLLECTION;
    }

    @Override
    public boolean existsByName(String username) {
        return helper.checkExists(collectionName, username);
    }

    @Override
    public void save(User user) {
        Map<String, Object> data = new HashMap<>();
        data.put("username", user.getUsername());
        data.put("password", user.getPassword());
        helper.addDocument(collectionName, data, user.getUsername());
    }

    @Override
    public User get(String username) {
        JsonObject userJson = helper.getDocument(collectionName, username);
//        System.out.println(userJson);
        String name = userJson.getAsJsonObject("username").get("stringValue").getAsString();
        String password = userJson.getAsJsonObject("password").get("stringValue").getAsString();
        return new StudentUser(name, password);
    }

    @Override
    public void setCurrentUser(String name) {
        user = get(name);
    }

    @Override
    public User getCurrentUser() {
        return this.user;
    }

    @Override
    public void changePassword(User user) {
        Map<String, Object> data = new HashMap<>();
        data.put("username", user.getUsername());
        data.put("password", user.getPassword());
        helper.updateDocument(collectionName, data, user.getUsername());
    }
}
