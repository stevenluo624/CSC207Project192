package data_access.in_memory_dao;

import entity.Profile;
import entity.User;
import use_case.profile.ProfileDataAccessInterface;

public class ProfileInMemoryDAO implements ProfileDataAccessInterface {
    /**
     * @param profile the profile to save
     */
    @Override
    public void save(Profile profile) {

    }

    @Override
    public void update(String username, String bio) {

    }

    /**
     * @param username document id
     * @return
     */
    @Override
    public String getBio(String username) {
        return "";
    }

    /**
     * @param username document id
     * @return
     */
    @Override
    public User getUser(String username) {
        return null;
    }
}
