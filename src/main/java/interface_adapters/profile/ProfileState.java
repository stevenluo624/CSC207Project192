package interface_adapters.profile;

import entity.Profile;
import entity.User;

/**
 * The State information representing the user using profile.
 */
public class ProfileState {
    private User user;
    private Profile profile;
    private String profileError;
    private String username = "";
    private String bio = "";

    public ProfileState(ProfileState copy) {
        user = copy.user;
        profile = copy.profile;
    }

    // Because of the previous copy constructor, the default constructor must be explicit.
    public ProfileState() {
        this.profileError = "";
    }

    public Profile getProfile() {
        return profile;
    }

    public User getUser() {
        return user;
    }

    public String getUsername() {
        return username;
    }

    public String getBio() {
        return bio;
    }

    public String getProfileError() {
        return profileError;
    }

    public void setUser(User user) {
        this.user = user;
        this.username = user.getUsername();
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public void setProfile(Profile profile) {this.profile = profile;}

    public void setProfileError(String error) {
        this.profileError = error;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
