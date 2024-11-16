package interface_adapters.list_review;

import use_case.list_review.ListReviewInputBoundary;
import use_case.list_review.ListReviewInputData;

/**
 * Controller for the list review use case.
 */
public class ListReviewController {

    private final ListReviewInputBoundary listReviewInteractor;

    public ListReviewController(ListReviewInputBoundary listReviewInteractor) {
        this.listReviewInteractor = listReviewInteractor;
    }

    /**
     * Executes the list review use case.
     * @param pageNumber which page to display.
     * @param pageSize how many items in one page.
     */
    public void execute(int pageNumber, int pageSize) {
        final ListReviewInputData inputData = new ListReviewInputData(pageNumber, pageSize);
        listReviewInteractor.execute(inputData);
    }
}
