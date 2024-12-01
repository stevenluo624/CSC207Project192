package use_case.profile;

import entity.Profile;
import entity.User;

/**
 * DAO for the ProfileFile Use Case.
 */
public interface ProfileDataAccessInterface {

    /**
     * Returns the username of the current user of the application.
     * @param profile the profile to save
     */
    void save(Profile profile);

    /**
     * Returns the bio of tthe current user of the application
     * @param username document id
     * @return the bio of the current user
     */
    String getBio(String username);

    /**
     * Returns the user of a profile
     * @param username document id
     * @return the username of the user
     */
    User getUser(String username);

}
