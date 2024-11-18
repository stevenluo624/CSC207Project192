package use_case.reply_to_review;

import entity.User;
import entity.UserReview;

public class ReplyToReviewInteractor implements ReplyToReviewInputBoundary {
    private final ReplyToReviewDataAccessInterface dataAccess;
    private final ReplyToReviewOutputBoundary presenter;

    public ReplyToReviewInteractor(ReplyToReviewDataAccessInterface dataAccess, ReplyToReviewOutputBoundary presenter) {
        this.dataAccess = dataAccess;
        this.presenter = presenter;
    }

    @Override
    public void execute(ReplyToReviewInputData inputData) {
        User user = inputData.getUser();
        String comment = inputData.getComment();
        final UserReview reply = new UserReview(user, comment);
        dataAccess.saveReply(reply);

        final ReplyToReviewOutputData outputData = new ReplyToReviewOutputData(user, comment, false);
        presenter.prepareSuccessView(outputData);
    }
}
