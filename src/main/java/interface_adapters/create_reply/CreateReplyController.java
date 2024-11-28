package interface_adapters.create_reply;

import entity.reviews_thread.Review;
import entity.User;
import use_case.create_reply.CreateReplyInputData;
import use_case.create_reply.CreateReplyInteractor;

/**
 * Controller for the create reply use case.
 */
public class CreateReplyController {

    private final CreateReplyInteractor createReplyInteractor;

    public CreateReplyController(CreateReplyInteractor createReplyInteractor) {
        this.createReplyInteractor = createReplyInteractor;
    }

    /**
     * Executes the use cases related to reply
     * @param user the user inputed the rating
     * @param comment the comment the user made
     * @param review the review the user is replying to
     */
    public void execute(User user, String comment, Review review) {
        final CreateReplyInputData createReplyInputData = new CreateReplyInputData(user, comment, review);
        createReplyInteractor.execute(createReplyInputData);
    }
}
