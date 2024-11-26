package entity;

/**
 * An implementation of the ProfileFile interface which handles user profile
 */
public class UserProfile implements Profile {
    private final User user;
    private final String bio;

    public UserProfile(User user, String bio) {
        this.user = user;
        this.bio = bio;
    }

    @Override
    public User getUser() {return user;}

    @Override
    public String getBio() {return bio;}

    @Override
    public Object getProfilePicture() {
        return null;
    }

}

