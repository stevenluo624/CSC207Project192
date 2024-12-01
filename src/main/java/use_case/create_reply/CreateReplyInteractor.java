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
    private final Review rootReview;

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
        final Reply reply = new Reply(user, comment);
        rootReview.updateListOfReplies(reply);
        dataAccess.updateReviewThread(rootReview, reply);
        final CreateReplyOutputData outputData = new CreateReplyOutputData(user, comment, false);
        presenter.prepareSuccessView(outputData);

    }
}
