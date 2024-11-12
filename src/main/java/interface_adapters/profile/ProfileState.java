package interface_adapters.profile;

/**
 * The State information representing the user using profile
 */
public class ProfileState {
    private String username = "";

    private String password = "";
    private String passwordError = "";

    private String bio = "";
    private String bioError;

    //TODO: How to save the pfp.

    public ProfileState(ProfileState copy) {
        username = copy.username;
        password = copy.password;
        passwordError = copy.passwordError;
    }

    // Because of the previous copy constructor, the default constructor must be explicit.
    public ProfileState() {
    }

    public String getUsername() {return username;}

    public void setUsername(String username) {this.username = username;}

    public void setPasswordError(String passwordError) {this.passwordError = passwordError;}

    public String getBio() {return bio;}

    public void SetBio(String bio) {this.bio = bio;}
}
