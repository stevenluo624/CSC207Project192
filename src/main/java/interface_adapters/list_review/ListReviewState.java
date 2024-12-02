package interface_adapters.list_review;

import entity.User;
import entity.UserReview;

import java.util.ArrayList;
import java.util.List;

/**
 * State for the list review view model.
 */
public class ListReviewState {

    private int pageNumber = 1;
    private int pageSize = 3;
    private String pageError;
    private List<UserReview> reviewList = new ArrayList<>();
    private String likeError;
    private String currentUsername;
    private User currentUser;

    public int getPageNumber() {
        return pageNumber;
    }

    public int getPageSize() {
        return pageSize;
    }

    public String getPageError() {
        return pageError;
    }

    public List<UserReview> getReviewList() {
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

    public void setReviewList(List<UserReview> reviewList) {
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

    public String getCurrentUsername() {
        return currentUsername;
    }

    public void setCurrentUsername(String currentUsername) {
        this.currentUsername = currentUsername;
    }

    public String getLikeError() {
        return likeError;
    }

    public User getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(User currentUser) {
        this.currentUser = currentUser;
    }
}
