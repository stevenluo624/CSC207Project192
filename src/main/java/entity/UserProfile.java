package entity;

/**
 * An implementation of the Profile interface which handles user profile
 */
public class UserProfile implements Profile {
    private final String bio;
    private final Object profile_picture;

    public UserProfile(String bio, Object profile_picture) {
        this.bio = bio;
        this.profile_picture = profile_picture;
    }

    @Override
    public String getBio() {return bio;}

    @Override
    public Object getProfilePicture() {return profile_picture;}

}

