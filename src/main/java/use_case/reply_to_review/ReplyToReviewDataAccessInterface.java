package use_case.reply_to_review;

import entity.UserReview;

public interface ReplyToReviewDataAccessInterface {

    /**
     * Adds a new reply.
     * @param reply contains details of a new reply.
     * @return the id of the new reply.
     */
    String saveReply(UserReview reply);

    /**
     * Retrieves the reply with the given id.
     * @param id the reply id.
     * @return details of the reply.
     */
    UserReview getReply(String id);
}
