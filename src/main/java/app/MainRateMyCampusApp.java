package app;

import java.io.IOException;

public class MainRateMyCampusApp {

    public static void main(String[] args) throws IOException {
        final TempBuilder builder = new TempBuilder();
        builder.addListReviewView()
                .build().setVisible(true);
    }
}
