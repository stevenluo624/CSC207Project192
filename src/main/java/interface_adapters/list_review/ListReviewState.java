package interface_adapters.list_review;

import entity.reviews_thread.Review;
import entity.User;

import java.util.ArrayList;
import java.util.List;

/**
 * State for the list review view model.
 */
public class ListReviewState {

    private int pageNumber = 1;
    private int pageSize = 3;
    private String pageError;
    private List<Review> reviewList = new ArrayList<>();
    private String likeError;
    private String currentUser;
    private User currentUserObject;
    private boolean refreshed = false;

    public int getPageNumber() {
        return pageNumber;
    }

    public int getPageSize() {
        return pageSize;
    }

    public String getPageError() {
        return pageError;
    }

    public List<Review> getReviewList() {
        return reviewList;
    }

    public void setPageNumber(int pageNumber) {
        this.pageNumber = pageNumber;
    }

    public void prevPage() {
        if (pageNumber > 1) {
            pageNumber--;
        }
    }

    public void nextPage() {
        pageNumber++;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public void setPageError(String pageError) {
        this.pageError = pageError;
    }

    public void setLikeError(String likeError) {
        this.likeError = likeError;
    }

    public void setReviewList(List<Review> reviewList) {
        this.reviewList = reviewList;
    }

    @Override
    public String toString() {
        return "ListReviewState{" +
                "pageNumber=" + pageNumber +
                ", pageSize=" + pageSize +
                ", pageError='" + pageError + '\'' +
                ", reviewList=" + reviewList +
                '}';
    }

    public String getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(String currentUser) {
        this.currentUser = currentUser;
    }

    public String getLikeError() {
        return likeError;
    }

    public User getCurrentUserObject() {
        return currentUserObject;
    }

    public void setCurrentUserObject(User currentUserObject) {
        this.currentUserObject = currentUserObject;
    }

    public boolean isRefreshed() {
        return refreshed;
    }

    public void setRefreshed(boolean refreshed) {
        this.refreshed = refreshed;
    }
}
