package app;

import javax.swing.*;
import java.io.IOException;

public class MainRateMyCampusApp {

    public static void main(String[] args) {
        final TempBuilder builder = new TempBuilder();
        final JFrame application = builder
                .addSignUpView()
                .addLoginView()
                .addCreateReviewView()
                .addListReviewView()
                .addProfileView()
                .addMapView()
                .addCreateReviewView()
                .addSignupUseCase()
                .addLoginUseCase()
                .addCreateReviewUseCase()
                .addListReviewUseCase()
                .addMapUseCase()
                .addProfileUseCase()
                .addCreateReviewUseCase()
                .build();
        application.pack();
        application.setVisible(true);
    }
}
