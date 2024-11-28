package app;

import java.awt.CardLayout;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

import data_access.DBReviewAccessObject;
import interface_adapters.ViewManagerModel;
import interface_adapters.create_review.CreateReviewController;
import interface_adapters.create_review.CreateReviewPresenter;
import interface_adapters.create_review.CreateReviewViewModel;
import use_case.create_review.CreateReviewInputBoundary;
import use_case.create_review.CreateReviewInteractor;
import use_case.create_review.CreateReviewOutputBoundary;
import view.RateView;
import view.ViewManager;

/**
 * The AppBuilder class is responsible for putting together the pieces of
 * our CA architecture; piece by piece.
 * <p/>
 * This is done by adding each View and then adding related Use Cases.
 */
// Checkstyle note: you can ignore the "Class Data Abstraction Coupling"
//                  and the "Class Fan-Out Complexity" issues for this lab; we encourage
//                  your team to think about ways to refactor the code to resolve these
//                  if your team decides to work with this as your starter code
//                  for your final project this term.
public class RateMyCampusAppBuilder {
    private final JPanel cardPanel = new JPanel();
    private final CardLayout cardLayout = new CardLayout();
    // thought question: is the hard dependency below a problem?
    private final ViewManagerModel viewManagerModel = new ViewManagerModel();
    private final ViewManager viewManager = new ViewManager(cardPanel, cardLayout, viewManagerModel);

    // thought question: is the hard dependency below a problem?
    private final DBReviewAccessObject dbReviewAccessObject = new DBReviewAccessObject();

    private RateView rateView;
    private CreateReviewViewModel createReviewViewModel;

    public RateMyCampusAppBuilder() throws IOException {
        cardPanel.setLayout(cardLayout);
    }

    /**
     * Adds the Rate View to the application.
     * @return this builder
     */
    public RateMyCampusAppBuilder addRateView() {
        createReviewViewModel = new CreateReviewViewModel();
        rateView = new RateView(createReviewViewModel);
        cardPanel.add(rateView, rateView.getViewName());
        return this;
    }

    /**
     * Adds the CreateReview Use Case to the application.
     * @return this builder
     */
    public RateMyCampusAppBuilder addCreateReviewUseCase() {
        final CreateReviewOutputBoundary createReviewOutputBoundary = new CreateReviewPresenter(createReviewViewModel);
        final CreateReviewInputBoundary createReviewInteractor = new CreateReviewInteractor(
                dbReviewAccessObject, createReviewOutputBoundary);

        final CreateReviewController createReviewController = new CreateReviewController(createReviewInteractor);
        RateView.setRateController(createReviewController);
        return this;
    }

    /**
     * Creates the JFrame for the application and initially sets the SignupView to be displayed.
     * @return the application
     */
    public JFrame build() {
        final JFrame application = new JFrame("Login Example");
        application.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        application.add(cardPanel);

        viewManagerModel.setState(rateView.getViewName());
        viewManagerModel.firePropertyChanged();

        return application;
    }
}