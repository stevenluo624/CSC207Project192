package entity.reviews_thread;

import entity.User;

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
     * Gets the number of likes
     * @return the number of likes
     */
    public int getNumberOfLikes();

    /**
     * Incriments the number of likes
     */
    void incrementLikes();
}
