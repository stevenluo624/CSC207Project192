package interface_adapters.rate;

/**
 * The State for a note.
 * <p>For this example, a note is simplay a string.</p>
 */
public class RateState {
    private int rating = 0;
    private String comment;
    private String username;
    private String ratingError;
    private String commentError;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getRatingError() {
        return ratingerror;
    }

    public void setRatingError(String errorMessage) {
        this.ratingerror = errorMessage;
    }

    public String getCommentError() {
        return commentError;
    }

    public void setCommentError(String errorMessage) {
        this.commentError = errorMessage;
    }
}
