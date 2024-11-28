package app;

import entity.Like;
import entity.reviews_thread.Review;
import entity.User;

public interface LikeFactory {
    Like create(User user, Review review);
}