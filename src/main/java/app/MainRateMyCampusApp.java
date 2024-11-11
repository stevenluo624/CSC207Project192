package app;

import data_access.DBReviewAccessObject;
import data_access.DBUserAccessObject;

import java.io.IOException;

public class MainRateMyCampusApp {

    public static void main(String[] args) throws IOException {
        
        // Create data access objects to inject in the builder
        final DBReviewAccessObject reviewDataAccess = new DBReviewAccessObject();
        final DBUserAccessObject userDataAccess = new DBUserAccessObject();

        final RateMyCampusAppBuilder builder = new RateMyCampusAppBuilder();
        
    }
}
