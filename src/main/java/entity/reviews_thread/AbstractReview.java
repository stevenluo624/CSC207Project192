package entity.reviews_thread;

import entity.Location;
import entity.User;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractReview implements ReviewThread {
    private final User user;
    private final String comment;
    private int numberOfLikes;

    /**
     * Creates a new AbstractReview object
     * @param user The user who created the reply
     * @param comment The comment provided in the reply
     */
    public AbstractReview(User user, String comment) {
        this.user = user;
        this.comment = comment;
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
     * Gets the number of likes
     * @return the number of likes
     */
    @Override
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
