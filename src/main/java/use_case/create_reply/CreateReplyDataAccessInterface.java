package use_case.create_reply;

import entity.reviews_thread.AbstractReview;
import entity.reviews_thread.Reply;

public interface CreateReplyDataAccessInterface {

    /**
     * Adds a new reply.
     * @param parentReview a review or reply that is being replied to with reply
     * @param reply contains details of a new reply.
     * @return the id of the new reply.
     */
    String updateReviewThread(AbstractReview parentReview, Reply reply);

    /**
     * Retrieves the reply with the given id.
     * @param id the reply id.
     * @return details of the reply.
     */
    Reply getReply(String id);
}
