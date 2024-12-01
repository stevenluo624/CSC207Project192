package entity;

/**
 * An implementation of the ProfileFile interface which handles user profile
 */
public class UserProfile implements Profile {
    private final String username;
    private final String bio;

    public UserProfile(String username, String bio) {
        this.username = username;
        this.bio = bio;
    }

    @Override
    public String getUsername() {return username;}

    @Override
    public String getBio() {return bio;}

}

