package interface_adapters.create_reply;

import entity.User;
import use_case.create_reply.CreateReplyInputData;
import use_case.create_reply.CreateReplyInteractor;

/**
 * Controller for our Note related Use Cases.
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
     */
    public void execute(User user, String comment) {
        final CreateReplyInputData createReplyInputData = new CreateReplyInputData(user, comment);

        createReplyInteractor.execute(createReplyInputData);
    }
}
