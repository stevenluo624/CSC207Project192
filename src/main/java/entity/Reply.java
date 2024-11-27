package entity;

import java.util.ArrayList;
import java.util.List;

/**
 * This is a representation of a Reply that forms a thread.
 * You can think of it as a child of another reply or a review (the root of the thread).
 */
public class Reply implements ReviewThread {
    private final User user;
    private final String comment;
    private final List<Reply> listOfReplies;
    private int numberOfLikes;

    /**
     * Creates a new Reply
     * @param user The user who created the reply
     * @param comment The comment provided in the reply
     */
    public Reply(User user, String comment) {
        this.user = user;
        this.comment = comment;
        this.listOfReplies = new ArrayList<>();
        this.numberOfLikes = 0;
    }

    /**
     * Gets the username of the user who created this review
     * @return the username
     */
    @Override
    public User getUser() {
        return user;
    }

    /**
     * Gets the comment
     * @return comment
     */
    @Override
    public String getComment() {
        return comment;
    }

    /**
     * Get listOfReplies
     * @return the list of replies
     */
    @Override
    public List<Reply> getListOfReplies() {
        return listOfReplies;
    }

    /**
     * Gets the number of likes
     * @return the number of likes
     */
    @Override
    public int getNumberOfLikes() {
        return numberOfLikes;
    }

    /**
     * Update list of replies with a new reply
     * @param reply a Review object representing a reply to this review
     */
    @Override
    public void updateListOfReplies(Reply reply) {
        listOfReplies.add(reply);
    }

    /**
     * Incriments the number of likes
     */
    public void incrementLikes() {
        this.numberOfLikes++;
    }
}
