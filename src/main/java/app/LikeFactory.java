package app;

import entity.Like;
import entity.User;
import entity.UserReview;

public interface LikeFactory {
    Like create(User user, UserReview review);
}