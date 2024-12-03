package use_case.create_reply;

import entity.reviews_thread.Review;
import entity.reviews_thread.Reply;

public interface CreateReplyDataAccessInterface {
    /**
     * Adds a new reply.
     * @param parentReview a review or reply that is being replied to with reply
     * @param reply contains details of a new reply.
     */
    void updateReviewThread(Review parentReview, Reply reply);
}
