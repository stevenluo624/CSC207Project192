package use_case.logout;

import org.junit.Before;
import use_case.create_review.*;
import org.junit.Test;

import static org.junit.Assert.*;

public class CreateReviewUseCaseTest {
    private CreateReviewInteractor createReviewInteractor;
    private CreateReviewOutputBoundary presenter;
    private InMemoryDAO inMemoryDAO;

    @BeforeEach
    public void setUp() {
        presenter = mock(CreateReviewOutputBoundary.class);
    }

    private CreateReviewOutputBoundary mock(Class<CreateReviewOutputBoundary> createReviewOutputBoundaryClass) {
    }

    @Test
    public void successfulCreateReview() {

    }
}
