package data_access;

import com.google.gson.JsonObject;
import entity.StudentUser;
import entity.User;
import helper.ProjectConstants;
import helper.FirestoreHelper;
import use_case.login.LoginUserDataAccessInterface;
import use_case.signup.SignupUserDataAccessInterface;

public class DBUserAccessObject implements LoginUserDataAccessInterface, SignupUserDataAccessInterface {
    private FirestoreHelper helper;
    String collectionName;

    public DBUserAccessObject(String collectionName) {
        helper = new FirestoreHelper(ProjectConstants.API_KEY, ProjectConstants.PROJECT_ID);
        this.collectionName = collectionName;
    }

    @Override
    public boolean existsByName(String username) {
        return false;
    }

    @Override
    public void save(User user) {

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

    }

    @Override
    public String getCurrentUser() {
        return "";
    }
}
