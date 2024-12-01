package app;

import javax.swing.*;

public class MainRateMyCampusApp {

    public static void main(String[] args) {
        final RateMyCampusBuilder builder = new RateMyCampusBuilder();
        final JFrame application = builder.addListReviewView()
                .addMapView()
                .addListReviewUseCase()
                .addMapUseCase()
                .build();
        application.pack();
        application.setVisible(true);
    }
}
