package use_case.create_review;

public interface CreateReviewInputBoundary {

    /**
     * Execute the use case
     * @param inputData the input data for this use case
     */
    void execute(CreateReviewInputData inputData);
}
