package interface_adapters.create_review;

import entity.User;

/**
 * The State for a note.
 * <p>For this example, a note is simplay a string.</p>
 */
public class CreateReviewState {
    private int rating = 0;
    private String comment;
    private User user;
    private String error;

    public int getRating() {
        return rating;
    }

    public String getComment() {
        return comment;
    }
    
    public User getUser() {
        return user;
    }

    public String getError() {
        return error;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }
    
    public void setUser(User user) {
        this.user = user;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public void setError(String errorMessage) {
        this.error = error;
    }
}
