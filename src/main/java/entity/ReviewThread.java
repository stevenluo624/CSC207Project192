package entity;

import java.util.List;

public interface ReviewThread {
    /**
     * Gets the username of the user who created this review
     * @return the username
     */
    User getUser();

    /**
     * Gets the rating
     * @return the rating
     */
    int getRating();

    /**
     * Gets the comment
     * @return the comment
     */
    String getComment();

    /**
     * Get listOfReplies
     * @return the list of replies
     */
    List<Review> getListOfReplies();

    /**
     * Update list of replies with a new reply
     * @param reply a Review object representing a reply to this review
     */
    public void updateListOfReplies(Review reply);

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
