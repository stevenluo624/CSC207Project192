package data_access.in_memory_dao;

import entity.reviews_thread.Review;
import entity.reviews_thread.Reply;
import use_case.create_reply.CreateReplyDataAccessInterface;

import java.util.ArrayList;
import java.util.List;

public class ReplyInMemoryDAO implements CreateReplyDataAccessInterface {
    private final List<Review> reviews = new ArrayList<>();
    private final List<Reply> replies = new ArrayList<>();

    /**
     * @param parentReview a review or reply that is being replied to with reply
     * @param reply        contains details of a new reply.
     */
    @Override
    public void updateReviewThread(Review parentReview, Reply reply) {
        if (!reviews.contains(parentReview)) {
            reviews.add(parentReview);
        }

        for (Review review : reviews) {
            if (review.equals(parentReview)) {
                review.updateListOfReplies(reply);
                break;
            }
        }

        replies.add(reply);
    }

    public List<Review> getReviews() {
        return reviews;
    }

    public List<Reply> getReplies() {
        return replies;
    }
}
