package data_access;

import entity.reviews_thread.AbstractReview;
import entity.reviews_thread.Reply;
import use_case.create_reply.CreateReplyDataAccessInterface;

/**
 * Data access object for managing replies to reviews
 */
public class  DBReplyAccessObject implements CreateReplyDataAccessInterface {
    @Override
    public String updateReviewThread(AbstractReview parentReview, Reply reply) {

        return "";
    }

    @Override
    public Reply getReply(String id) {
        return null;
    }
}
