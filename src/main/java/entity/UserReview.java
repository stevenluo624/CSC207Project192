package entity;

import java.util.List;

/**
 * The representation of a UserReview in our program.
 */
public class UserReview {
    private final User user;
    private final int rating;
    private final String comment;
    private final Location location;
    private List<UserReview> listOfReplies;
    private int numberOfLikes;

    /**
     * Creates a new UserReview
     * @param user The user who created the review
     * @param rating The rating given in the review
     * @param comment The comment provided in the review
     * @param location The location being reviewed.
     */
    public UserReview(User user, int rating, String comment, Location location) {
        this.user = user;
        this.rating = rating;
        this.comment = comment;
        this.location = location;
    }

    /**
     * Creates a new UserReview (NOTE: Should only be used to create a reply!)
     * TODO: refactor this to have a Reply class that implements a UserReview interface
     */
    public UserReview(User user, String comment) {
        this(user, 0, comment, null);
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
    public List<UserReview> getListOfReplies() {
        return listOfReplies;
    }

    /**
     * Update list of replies with a new reply
     * @param reply a UserReview object representing a reply to this review
     */
    public void updateListOfReplies(UserReview reply) {
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
    public void incrementLikes() {this.numberOfLikes++;}

    public Location getLocation() {return location;}
}