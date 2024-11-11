package entity;

/**
 * The representation of a UserReview in our program.
 */
public class UserReview {
    private final User user;
    private final int rating;
    private final String comment;

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
        //this.location = location;
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
     * Gets the location of the review
     * @return the comment
     */
    /*
     public Location location() {
        return location;
    } */
}