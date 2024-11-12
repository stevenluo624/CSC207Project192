package app;

import data_access.DBReviewAccessObject;
import data_access.DBUserAccessObject;
import use_case.create_review.CreateReviewDataAccessInterface;
import use_case.login.LoginUserDataAccessInterface;

import java.io.IOException;

public class MainRateMyCampusApp {

    public static void main(String[] args) throws IOException {
        
        // Create data access objects to inject in the builder
        final CreateReviewDataAccessInterface reviewDataAccess = new DBReviewAccessObject();
        final LoginUserDataAccessInterface userDataAccess = new DBUserAccessObject();

        final RateMyCampusAppBuilder builder = new RateMyCampusAppBuilder();
        builder.addRateView()
                .addCreateReviewUseCase()
                .build().setVisible(true);
    }
}
