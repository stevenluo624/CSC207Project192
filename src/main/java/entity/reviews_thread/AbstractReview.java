package entity.reviews_thread;

import entity.Location;
import entity.User;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public abstract class AbstractReview {
    private final User user;
    private final String comment;
    private Location location;
    private int numberOfLikes;
    private final String id;

    /**
     * Creates a new AbstractReview object
     * @param user The user who created the reply
     * @param comment The comment provided in the reply
     */
    public AbstractReview(User user, String comment) {
        this.user = user;
        this.comment = comment;
        this.numberOfLikes = 0;
        this.id = (comment + "|" + user.getUsername()).replaceAll("\\s", "");
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
    public String getId() {
        return id;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }
}
