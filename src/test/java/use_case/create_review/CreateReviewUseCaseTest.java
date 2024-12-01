//package use_case.create_review;
//
//import data_access.in_memory_dao.LikeInMemoryDAO;
//import data_access.in_memory_dao.ReviewInMemoryDAO;
//import entity.reviews_thread.Review;
//import org.junit.Before;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.Test;
//
//import static org.junit.Assert.*;
//
//public class CreateReviewUseCaseTest {
//    private CreateReviewInteractor createReviewInteractor;
//    private CreateReviewInputData createReviewInputData;
//    private ReviewInMemoryDAO reviewInMemoryDAO;
//
//    @BeforeEach
//    public void setUp() {
//        reviewInMemoryDAO = new ReviewInMemoryDAO();
//        createReviewInteractor = new CreateReviewInteractor(reviewInMemoryDAO, new LikeInMemoryDAO());
//        createReviewInputData = new CreateReviewInputData("user", "comment", "product");
//    }
//}
