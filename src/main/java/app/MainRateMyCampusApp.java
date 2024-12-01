package app;

import javax.swing.*;
import java.io.IOException;

public class MainRateMyCampusApp {

    public static void main(String[] args) throws IOException {
        final TempBuilder builder = new TempBuilder();
        final JFrame application = builder
                .addSignUpView()
                .addLoginView()
                .addListReviewView()
                .addProfileView()
                .addMapView()
                .addSignupUseCase()
                .addLoginUseCase()
                .addListReviewUseCase()
                .addMapUseCase()
                .addProfileUseCase()
                .build();
        application.pack();
        application.setVisible(true);
    }
}
