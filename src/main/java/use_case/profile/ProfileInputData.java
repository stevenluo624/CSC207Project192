package use_case.profile;

import entity.User;
/**
 * The input data for the ProfileFile Use Case.
 */
public class ProfileInputData {

    private final User user;
    private final String bio;

    public ProfileInputData(User user, String bio) {
        this.user = user;
        this.bio = bio;
    }
    User getUser() { return user; }
    String getBio() { return bio; }
}
