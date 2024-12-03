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
    private Review rootReview;

    public CreateReplyInteractor(CreateReplyDataAccessInterface dataAccess, CreateReplyOutputBoundary presenter,
                                 Review rootReview) {
        this.dataAccess = dataAccess;
        this.presenter = presenter;
        this.rootReview = rootReview;
    }

    @Override
    public void execute(CreateReplyInputData inputData) {
        User user = inputData.getUser();
        String comment = inputData.getComment();
        rootReview = inputData.getReview();

        final Reply reply = new Reply(user, comment);
        final String replyId = "reply" + String.valueOf(reply.getId());

        dataAccess.updateReviewThread(rootReview, reply);
        rootReview.updateListOfReplies(replyId);

        final CreateReplyOutputData outputData = new CreateReplyOutputData(user, comment, false);
        presenter.prepareSuccessView(outputData);
    }
}
