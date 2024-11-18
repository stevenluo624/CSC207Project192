package use_case.create_reply;

import entity.User;

public class CreateReplyInputData {
    private final User user;
    private final String comment;

    public CreateReplyInputData(User user, String comment) {
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
