package entity.reviews_thread;

import entity.User;

import java.util.ArrayList;
import java.util.List;

/**
 * The representation of a Review in our program.
 * A review is the root of the thread. From it, a series of Reply objects can be created expanding the thread.
 * A review cannot form another review (i.e. a Review object cannot be part of the listOfReplies attribute.
 */
public class Review extends AbstractReview {
    private final int rating;
    private final List<Reply> listOfReplies;

    /**
     * Creates a new Review
     * @param user The user who created the review
     * @param rating The rating given in the review
     * @param comment The comment provided in the review
     */
    public Review(User user, int rating, String comment) {
        super(user, comment);
        this.listOfReplies = new ArrayList<>();
        this.rating = rating;
    }

    /**
     * Gets the rating
     * @return the rating
     */
    public int getRating() {
        return rating;
    }


    /**
     * Get listOfReplies
     * @return the list of replies
     */
    public List<Reply> getListOfReplies() {
        return listOfReplies;
    }

    /**
     * Update list of replies with a new reply
     * @param reply a Reply object representing a reply to this review or reply
     */
    public void updateListOfReplies(Reply reply) {
        listOfReplies.add(reply);
    }

}
