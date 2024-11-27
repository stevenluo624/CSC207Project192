package use_case.create_reply;

import entity.Reply;

public interface CreateReplyDataAccessInterface {

    /**
     * Adds a new reply.
     * @param reply contains details of a new reply.
     * @return the id of the new reply.
     */
    String updateReviewThread(Reply reply);

    /**
     * Retrieves the reply with the given id.
     * @param id the reply id.
     * @return details of the reply.
     */
    Reply getReply(String id);
}
