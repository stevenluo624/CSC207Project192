package entity.reviews_thread;

import entity.User;

/**
 * This is a representation of a Reply that forms a thread.
 * You can think of it as a child of another reply or a review (the root of the thread).
 */
public class Reply extends AbstractReview {
    /**
     * Creates a new Reply object
     * @param user The user who created the reply
     * @param comment The comment provided in the reply
     */
    public Reply(User user, String comment) {
        super(user, comment);
    }
}
