package entity;

import java.util.List;

/**
 * The representation of a UserReview in our program.
 */
public class UserReview {
    private final User user;
    private final int rating;
    private final String comment;
    private List<UserReview> listOfReplies;

    /**
     * Creates a new UserReview
     * @param user The user who created the review
     * @param rating The rating given in the review
     * @param comment The comment provided in the review
     */
    public UserReview(User user, int rating, String comment) {
        this.user = user;
        this.rating = rating;
        this.comment = comment;
    }

    /**
     * Creates a new UserReview (NOTE: Should only be used to create a reply!)
     * TODO: refactor this to have a Reply class that implements a UserReview interface
     */
    public UserReview(User user, String comment) {
        this(user, 0, comment);
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
}