package entity.reviews_thread;

import entity.Location;
import entity.User;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractReview {
    private final User user;
    private final String comment;
    private Location location;
    private final List<Reply> listOfReplies;
    private int numberOfLikes;
    private int counter;
    private final int id;

    /**
     * Creates a new AbstractReview object
     * @param user The user who created the reply
     * @param comment The comment provided in the reply
     */
    public AbstractReview(User user, String comment) {
        this.user = user;
        this.comment = comment;
        this.listOfReplies = new ArrayList<>();
        this.numberOfLikes = 0;
        counter += 1;
        this.id = counter;
    }

    /**
     * Gets the username of the user who created this review
     * @return the username
     */
    public User getUser() {
        return user;
    }

    /**
     * Gets the comment
     * @return comment
     */
    public String getComment() {
        return comment;
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

    /**
     * Getter for the id
     * @return id
     */
    public int getId() {
        return id;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }
}
