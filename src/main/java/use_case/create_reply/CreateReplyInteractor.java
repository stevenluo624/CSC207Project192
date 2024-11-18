package use_case.create_reply;

import entity.User;
import entity.UserReview;

public class CreateReplyInteractor implements CreateReplyInputBoundary {
    private final CreateReplyDataAccessInterface dataAccess;
    private final CreateReplyOutputBoundary presenter;

    public CreateReplyInteractor(CreateReplyDataAccessInterface dataAccess, CreateReplyOutputBoundary presenter) {
        this.dataAccess = dataAccess;
        this.presenter = presenter;
    }

    @Override
    public void execute(CreateReplyInputData inputData) {
        User user = inputData.getUser();
        String comment = inputData.getComment();
        final UserReview reply = new UserReview(user, comment);
        dataAccess.saveReply(reply);

        final CreateReplyOutputData outputData = new CreateReplyOutputData(user, comment, false);
        presenter.prepareSuccessView(outputData);
    }
}
