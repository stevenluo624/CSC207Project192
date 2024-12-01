package use_case.profile;

import entity.User;
/**
 * Output Data for the ProfileFile Use Case.
 */
public class ProfileOutputData {
    private final String username;
    private final String bio;
    private final boolean useCaseFailed;

    public ProfileOutputData(String username, String bio, boolean useCaseFailed) {
        this.username = username;
        this.bio = bio;
        this.useCaseFailed = useCaseFailed;
    }

    public String getUsername() {
        return username;
    }
    public String getBio() {
        return bio;
    }
    public boolean isUseCaseFailed() {
        return useCaseFailed;
    }
}
