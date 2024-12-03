package use_case.create_reply;

import entity.reviews_thread.Reply;
import entity.User;
import entity.reviews_thread.Review;

/**
 * Create reply use case
 */
public class CreateReplyInteractor implements CreateReplyInputBoundary {
    private final CreateReplyDataAccessInterface dataAccess;
    private final CreateReplyOutputBoundary presenter;

    /**
     * @param dataAccess the data access for the use case
     * @param presenter the presenter for the use case
     */
    public CreateReplyInteractor(CreateReplyDataAccessInterface dataAccess, CreateReplyOutputBoundary presenter) {
        this.dataAccess = dataAccess;
        this.presenter = presenter;
    }

    @Override
    public void execute(CreateReplyInputData inputData) {
        User user = inputData.getUser();
        String comment = inputData.getComment();
        Review rootReview = inputData.getReview();

        final Reply reply = new Reply(user, comment);
        final String replyId = "reply" + reply.getId();

        dataAccess.updateReviewThread(rootReview, reply);
        rootReview.updateListOfReplies(replyId);

        final CreateReplyOutputData outputData = new CreateReplyOutputData(user, comment, false);
        presenter.prepareSuccessView(outputData);
    }

    /**
     * Executes the switch to list of reviews use case
     */
    @Override
    public void switchToListReviewView() {
        presenter.switchToListReviewView();
    }
}
