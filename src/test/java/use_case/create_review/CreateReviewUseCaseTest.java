package use_case.create_review;

import data_access.in_memory_dao.ReviewListInMemoryDAO;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CreateReviewUseCaseTest {

    @BeforeEach
    void setUp() {
        
    }

    @Test
    void successTest() {
        CreateReviewDataAccessInterface dataAccess = new ReviewListInMemoryDAO();

        
    }
}