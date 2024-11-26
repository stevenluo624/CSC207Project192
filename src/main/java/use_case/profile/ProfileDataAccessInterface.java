package use_case.profile;

import entity.Profile;

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
     * @return the bio of the current user
     */
    String getBio();
}
