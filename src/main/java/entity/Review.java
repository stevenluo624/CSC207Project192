package entity;

import java.util.ArrayList;
import java.util.List;

/**
 * The representation of a Review in our program.
 * A review is the root of the thread. From it, a series of Reply objects can be created expanding the thread.
 * A review cannot form another review (i.e. a Review object cannot be part of the listOfReplies attribute.
 */
public class Review implements ReviewThread {
    private final User user;
    private final int rating;
    private final String comment;
    private final List<Reply> listOfReplies;
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
        this.listOfReplies.add(reply);
    }

    /**
     * Incriments the number of likes
     */
    @Override
    public void incrementLikes() {
        this.numberOfLikes++;
    }
}
