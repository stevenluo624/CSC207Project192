package interface_adapters.rate;

/**
 * The State for a note.
 * <p>For this example, a note is simplay a string.</p>
 */
public class RateState {
    private int rating = 0;
    private String error;

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public void setError(String errorMessage) {
        this.error = errorMessage;
    }

    public String getError() {
        return error;
    }
}