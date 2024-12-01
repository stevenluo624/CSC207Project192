package app;

import javax.swing.*;
import java.io.IOException;

public class MainRateMyCampusApp {

    public static void main(String[] args) {
        final TempBuilder builder = new TempBuilder();
        final JFrame application = builder.addListReviewView()
                .addMapView()
                .addListReviewUseCase()
                .addMapUseCase()
                .build();
        application.pack();
        application.setVisible(true);
    }
}
