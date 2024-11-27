package entity;

import java.util.List;

/**
 * A ReviewThread can be thought of as a tree that has a Review root and a series of replies as children.
 */
public interface ReviewThread {
    /**
     * Gets the username of the user who created this review
     * @return the username
     */
    User getUser();

    /**
     * Gets the comment
     * @return the comment
     */
    String getComment();

    /**
     * Get listOfReplies
     * @return the list of replies of this reply or review
     */
    List<Reply> getListOfReplies();

    /**
     * Gets the number of likes
     * @return the number of likes
     */
    public int getNumberOfLikes();

    /**
     * Update list of replies with a new reply
     * @param reply a Review object representing a reply to this review
     */
    public void updateListOfReplies(Reply reply);

    /**
     * Incriments the number of likes
     */
    void incrementLikes();
}
