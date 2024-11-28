package use_case.profile;

import entity.User;
/**
 * Output Data for the ProfileFile Use Case.
 */
public class ProfileOutputData {
    private final User user;
    private String bio;
    private boolean useCaseFailed;

    public ProfileOutputData(User user, String bio, boolean useCaseFailed) {
        this.user = user;
        this.bio = bio;
        this.useCaseFailed = useCaseFailed;
    }

    public User getUser() {
        return user;
    }
    public String getBio() {
        return bio;
    }
    public boolean isUseCaseFailed() {
        return useCaseFailed;
    }
}
