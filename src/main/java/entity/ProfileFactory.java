package entity;

public interface ProfileFactory {
    /**
     * Creates a new ProfileFile.
     * @param username the username of the user
     * @param bio the bio of the user
     * @param profilePicture the profile picture of the user
     * @return the new profile for user
     */
    Profile create(String username,String bio, Object profilePicture);
}
