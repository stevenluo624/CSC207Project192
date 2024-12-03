package data_access.in_memory_dao;

import entity.User;
import use_case.login.LoginUserDataAccessInterface;
import use_case.signup.SignupUserDataAccessInterface;

import java.util.HashMap;
import java.util.Map;

public class UserInMemoryDAO implements LoginUserDataAccessInterface, SignupUserDataAccessInterface {
    private final Map<String, User> users = new HashMap<>();

    private String currentUsername;

    @Override
    public boolean existsByName(String identifier) {
        return users.containsKey(identifier);
    }

    @Override
    public void save(User user) {
        users.put(user.getUsername(), user);
    }

    @Override
    public User get(String username) {
        return users.get(username);
    }

    /**
     * Setter for currentUser.
     *
     * @param name username
     */
    @Override
    public void setCurrentUser(String name) {
        currentUsername = name;
    }

    /**
     * Getter for currentUser.
     *
     * @return String: currentUser
     */
    @Override
    public User getCurrentUser() {
        return users.get(currentUsername);
    }
}
