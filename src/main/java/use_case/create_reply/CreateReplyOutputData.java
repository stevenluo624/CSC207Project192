package use_case.create_reply;

import entity.User;

public class CreateReplyOutputData {
    private final User user;
    private final String comment;
    private final boolean useCaseFailed;

    public CreateReplyOutputData(User user, String comment, boolean useCaseFailed) {
        this.user = user;
        this.comment = comment;
        this.useCaseFailed = useCaseFailed;
    }

    public User getUser() {
        return user;
    }

    public String getComment() {
        return comment;
    }

    public boolean isUseCaseFailed() {
        return useCaseFailed;
    }
}
