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
    private String username = user.getUsername();
    private String bio = profile.getBio();

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

    public void setBio(String bio) {
        this.bio = bio;
    }

    public void setProfileError(String error) {
        this.profileError = error;
    }

}