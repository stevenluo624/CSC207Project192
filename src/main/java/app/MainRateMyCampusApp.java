package app;

import data_access.DBReviewAccessObject;

public class MainRateMyCampusApp {

    public static void main(String[] args) {
        
        // Create data access objects to inject in the builder
        final ReviewDataAccessInterface reviewDataAccess = new DBReviewAccessObject();
        final UserDataAccessInterface userDataAccess = new DBUserAccessObject();

        final RateMyCampusAppBuilder builder = new RateMyCampusAppBuilder();
        
    }
}
