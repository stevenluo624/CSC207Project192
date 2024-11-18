package use_case.reply_to_review;

public interface ReplyToReviewInputBoundary {

    /**
     * Execute the use case
     * @param inputData the input data for this use case
     */
    void execute(ReplyToReviewInputData inputData);
}
