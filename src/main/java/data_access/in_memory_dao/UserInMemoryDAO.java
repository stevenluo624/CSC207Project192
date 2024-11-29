package data_access.in_memory_dao;

import entity.User;
import use_case.login.LoginUserDataAccessInterface;
import use_case.signup.SignupUserDataAccessInterface;

public class UserInMemoryDAO implements LoginUserDataAccessInterface, SignupUserDataAccessInterface {
    /**
     * @param username the username to look for
     * @return
     */
    @Override
    public boolean existsByName(String username) {
        return false;
    }

    /**
     * @param user the user to save
     */
    @Override
    public void save(User user) {

    }

    /**
     * @param username the username to look up
     * @return
     */
    @Override
    public User get(String username) {
        return null;
    }

    /**
     * @param name username
     */
    @Override
    public void setCurrentUser(String name) {

    }

    /**
     * @return
     */
    @Override
    public User getCurrentUser() {
        return null;
    }
}
