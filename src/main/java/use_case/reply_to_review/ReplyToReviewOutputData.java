package use_case.reply_to_review;

import entity.User;

public class ReplyToReviewOutputData {
    private final User user;
    private final String comment;
    private final boolean useCaseFailed;

    public ReplyToReviewOutputData(User user, String comment, boolean useCaseFailed) {
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
