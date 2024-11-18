package use_case.reply_to_review;

import entity.User;

public class ReplyToReviewInputData {
    private final User user;
    private final String comment;

    public ReplyToReviewInputData(User user, String comment) {
        this.user = user;
        this.comment = comment;
    }

    public User getUser() {
        return user;
    }

    public String getComment() {
        return comment;
    }
}
