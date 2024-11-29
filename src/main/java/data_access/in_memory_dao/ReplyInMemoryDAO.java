package data_access.in_memory_dao;

import entity.reviews_thread.AbstractReview;
import entity.reviews_thread.Reply;
import use_case.create_reply.CreateReplyDataAccessInterface;

public class ReplyInMemoryDAO implements CreateReplyDataAccessInterface {
    /**
     * @param parentReview a review or reply that is being replied to with reply
     * @param reply        contains details of a new reply.
     * @return
     */
    @Override
    public String updateReviewThread(AbstractReview parentReview, Reply reply) {
        return "";
    }

    /**
     * @param id the reply id.
     * @return
     */
    @Override
    public Reply getReply(String id) {
        return null;
    }
}
