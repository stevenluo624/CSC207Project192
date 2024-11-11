package interface_adapters.rate;

/**
 * The State for a note.
 * <p>For this example, a note is simplay a string.</p>
 */
public class RateState {
    private int rating = 0;
    private String comment;
    private String username;
    private String commentError;
    private String ratingError;

    public int getRating() {
        return rating;
    }

    public String getComment() {
        return comment;
    }
    
    public String getUsername() {
        return username;
    }

    public String getCommentError() {
        return commentError;
    }
    
    public String getRatingError() {
        return ratingerror;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }
    
    public void setUsername(String username) {
        this.username = username;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public void setCommentError(String errorMessage) {
        this.commentError = errorMessage;
    }

    public void setRatingError(String errorMessage) {
        this.ratingerror = errorMessage;
    }
}
