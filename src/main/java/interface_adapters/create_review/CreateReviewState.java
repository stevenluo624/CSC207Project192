package interface_adapters.create_review;

import entity.Location;
import entity.User;

/**
 * The State for a note.
 * <p>For this example, a note is simplay a string.</p>
 */
public class CreateReviewState {
    private int rating = 0;
    private String comment;
    private User user;
    private String commentError;
    private String ratingError;
    private Location location;

    public int getRating() {
        return rating;
    }

    public String getComment() {
        return comment;
    }
    
    public User getUser() {
        return user;
    }

    public String getCommentError() {
        return commentError;
    }
    
    public String getRatingError() {
        return ratingError;
    }

    public Location getLocation() { return location; }

    public void setRating(int rating) {
        this.rating = rating;
    }
    
    public void setUser(User user) {
        this.user = user;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public void setCommentError(String errorMessage) {
        this.commentError = errorMessage;
    }

    public void setRatingError(String errorMessage) {
        this.ratingError = errorMessage;
    }

    public void setLocation(Location location) {this.location = location; }
}
