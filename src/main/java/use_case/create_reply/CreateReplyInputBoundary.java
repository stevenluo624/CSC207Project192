package use_case.create_reply;

public interface CreateReplyInputBoundary {

    /**
     * Execute the use case
     * @param inputData the input data for this use case
     */
    void execute(CreateReplyInputData inputData);

    void switchToListReviewView();
}
