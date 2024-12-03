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
    private final List<String> listOfReplyIds;
    private final String id;

    /**
     * Creates a new Review
     * @param user The user who created the review
     * @param rating The rating given in the review
     * @param comment The comment provided in the review
     */
    public Review(User user, int rating, String comment) {
        super(user, comment);
        this.listOfReplyIds = new ArrayList<>();
        this.rating = rating;
        this.id = (comment + "|" + user.getUsername()).replaceAll("\\s", "");
    }

    /**
     * Gets the rating
     * @return the rating
     */
    public int getRating() {
        return rating;
    }


    /**
     * Get listOfReplyIds
     * @return the list of replies
     */
    public List<String> getListOfReplies() {
        return listOfReplyIds;
    }

    /**
     * Update list of replies with a new reply
     * @param replyId a Reply object representing a reply to this review or reply
     */
    public void updateListOfReplies(String replyId) {
        listOfReplyIds.add(replyId);
    }

    /**
     * Getter for the id
     * @return id
     */
    public String getId() {
        return id;
    }
}
