package use_case.profile;

import entity.User;
/**
 * The input data for the ProfileFile Use Case.
 */
public class ProfileInputData {

    private final String username;
    private final String bio;

    public ProfileInputData(String username, String bio) {
        this.username = username;
        this.bio = bio;
    }
    String getUsername() { return username; }
    String getBio() { return bio; }
}
