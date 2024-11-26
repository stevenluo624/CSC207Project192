package entity;

/**
 * The representation of a user profile in our program.
 */
public interface Profile {

    /**
     * Returns the user of the current user.
     * @return the user of the current user.
     */
    User getUser();
    /**
     * Returns the bio of the user.
     * @return the bio of the user.
     */
    String getBio();

    /**
     * Returns the profile picture of the user.
     * @return the profile picture of the user
     */
    Object getProfilePicture();
}
