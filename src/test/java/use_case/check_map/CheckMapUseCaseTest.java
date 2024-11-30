package use_case.check_map;

import interface_adapters.ViewManagerModel;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CheckMapUseCaseTest {

    @Test
    void successTest() {
        CheckMapInputData inputData = new CheckMapInputData("Food Truck 1", "43.6532"
                , "-79.3832");

        // This creates a successPresenter that tests whether the test case is as we expect.
        CheckMapOutputBoundary successPresenter = new CheckMapOutputBoundary() {
            @Override
            public void prepareSuccessView(CheckMapOutputData location) {
                assertEquals("Food Truck 1", location.getName());
                assertEquals("43.6532", location.getLatitude());
                assertEquals("-79.3832", location.getLongitude());
                assertFalse(location.isUseCaseFailed());
            }

            @Override
            public void prepareFailView(String error) {
                fail("Use case failure is unexpected.");
            }

            /**
             * Switches to List of Reviews View.
             */
            @Override
            public void switchToListReviewView() {
                // This is expected
            }
        };

        CheckMapInputBoundary interactor = new CheckMapInteractor(successPresenter);
        interactor.execute(inputData);
    }

    @Test
    void failureLatitudeTypeTest() {
        CheckMapInputData inputData = new CheckMapInputData("Food Truck 1", "forty-six"
                , "66.33");
        // For this failure test, we set the latitude to a string that cannot be cast to type double
        // This creates a presenter that tests whether the test case is as we expect.
        CheckMapOutputBoundary failurePresenter = new CheckMapOutputBoundary() {
            @Override
            public void prepareSuccessView(CheckMapOutputData location) {
                // this should never be reached since the test case should fail
                fail("Use case success is unexpected.");
            }

            @Override
            public void prepareFailView(String error) {
                assertEquals("Latitude and Longitude must both be decimal values.", error);
            }

            /**
             * Switches to List of Reviews View.
             */
            @Override
            public void switchToListReviewView() {
                // This is expected
            }
        };

        CheckMapInputBoundary interactor = new CheckMapInteractor(failurePresenter);
        interactor.execute(inputData);
    }

    @Test
    void switchToListReviewViewTest() {
        // switch the view to list of reviews
        // This creates a presenter that tests whether the test case is as we expect.
        ViewManagerModel viewManagerModel = new ViewManagerModel();
        CheckMapOutputBoundary switchPresenter = new CheckMapOutputBoundary() {
            @Override
            public void prepareSuccessView(CheckMapOutputData location) {
                // this should never be reached since the test case should fail
                fail("Use case success is unexpected.");
            }

            @Override
            public void prepareFailView(String error) {
                // this should never be reached since the test case should fail
                fail("Use case failure is unexpected.");
            }

            /**
             * Switches to List of Reviews View.
             */
            @Override
            public void switchToListReviewView() {
                viewManagerModel.setState("review list");
                assertEquals("review list", viewManagerModel.getState());
            }
        };
        CheckMapInputBoundary interactor = new CheckMapInteractor(switchPresenter);
        interactor.switchToListReviewView();
    }
}