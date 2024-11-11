package use_case.create_review;

import entity.User;

public class CreateReviewOutputData {
    private final User user;
    private final double rating;
    private final String comment;
    private final boolean useCaseFailed;

    public CreateReviewOutputData(User user, double rating, String comment, boolean useCaseFailed) {
        this.user = user;
        this.rating = rating;
        this.comment = comment;
        this.useCaseFailed = useCaseFailed;
    }

    public User getUser() {
        return user;
    }

    public double getRating() {
        return rating;
    }

    public String getComment() {
        return comment;
    }

    public boolean isUseCaseFailed() {
        return useCaseFailed;
    }
}
