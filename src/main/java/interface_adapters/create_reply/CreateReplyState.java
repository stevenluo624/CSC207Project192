package interface_adapters.create_reply;

import entity.User;

/**
 * The initial State for a reply.
 */
public class CreateReplyState {
    private User user;
    private String comment;
    private String error;

    public User getUser() {
        return user;
    }

    public String getComment() {
        return comment;
    }

    public String getError() {
        return error;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public void setError(String message) {
        this.error = message;
    }
}
