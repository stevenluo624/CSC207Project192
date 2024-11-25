package entity;

import java.util.List;

/**
 * The representation of a Review in our program.
 */
public class Review implements ReviewThread {
    private final User user;
    private final int rating;
    private final String comment;
    private List<Review> listOfReplies;
    private int numberOfLikes;

    /**
     * Creates a new Review
     * @param user The user who created the review
     * @param rating The rating given in the review
     * @param comment The comment provided in the review
     */
    public Review(User user, int rating, String comment) {
        this.user = user;
        this.rating = rating;
        this.comment = comment;
    }

    /**
     * Creates a new Review (NOTE: Should only be used to create a reply!)
     * TODO: refactor this to have a Reply class that implements a Review interface
     */
    public Review(User user, String comment) {
        this(user, 0, comment);
        this.numberOfLikes = 0;
    }

    /**
     * Gets the username of the user who created this review
     * @return the username
     */
    public User getUser() {
        return user;
    }

    /**
     * Gets the rating
     * @return the rating
     */
    public int getRating() {
        return rating;
    }

    /**
     * Gets the comment
     * @return the comment
     */
    public String getComment() {
        return comment;
    }

    /**
     * Get listOfReplies
     * @return the list of replies
     */
    public List<Review> getListOfReplies() {
        return listOfReplies;
    }

    /**
     * Update list of replies with a new reply
     * @param reply a Review object representing a reply to this review
     */
    public void updateListOfReplies(Review reply) {
        this.listOfReplies.add(reply);
    }

    /**
     * Gets the number of likes
     * @return the number of likes
     */
    public int getNumberOfLikes() {
        return numberOfLikes;
    }

    /**
     * Incriments the number of likes
     */
    public void incrementLikes() {
        this.numberOfLikes++;
    }
}