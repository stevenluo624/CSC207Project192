package use_case.list_review;

/**
 * Input data for list review use case.
 */
public class ListReviewInputData {

    private final int pageNumber;
    private final int pageSize;

    public ListReviewInputData(int pageNumber, int pageSize) {
        this.pageNumber = pageNumber;
        this.pageSize = pageSize;
    }

    public int getPageNumber() {
        return pageNumber;
    }

    public int getPageSize() {
        return pageSize;
    }
}
