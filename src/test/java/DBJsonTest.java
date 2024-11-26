//import app.RateMyCampusAppBuilder;
import app.TempBuilder;
import data_access.DBUserAccessObject;
import helper.FirestoreHelper;

import java.io.IOException;

public class DBJsonTest {
    public static void main(String[] args) throws IOException {
        final TempBuilder builder = new TempBuilder();
        builder.addListReviewView()
                .build().setVisible(true);
    }
}
