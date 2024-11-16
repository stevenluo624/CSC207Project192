package use_case.list_review;

/**
 * Input boundary for the list reviews use case.
 */
public interface ListReviewInputBoundary {

    /**
     * Executes list review use case.
     * @param listReviewInputData contains all needed input data.
     */
    void execute(ListReviewInputData listReviewInputData);
}
