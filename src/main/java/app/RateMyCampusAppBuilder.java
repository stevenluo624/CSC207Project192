package app;

import java.awt.*;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

//import data_access.DBReviewAccessObject;
import data_access.DBReviewListAccessObject;
import entity.UserFactory;
import interface_adapters.ViewManagerModel;
import interface_adapters.ViewModel;
import interface_adapters.list_review.ListReviewController;
import interface_adapters.list_review.ListReviewPresenter;
import interface_adapters.list_review.ListReviewState;
import interface_adapters.list_review.ListReviewViewModel;
import interface_adapters.rate.RateController;
import interface_adapters.rate.RatePresenter;
import interface_adapters.rate.RateViewModel;
import use_case.create_review.CreateReviewInputBoundary;
import use_case.create_review.CreateReviewInteractor;
import use_case.create_review.CreateReviewOutputBoundary;
import use_case.list_review.ListReviewInputBoundary;
import use_case.list_review.ListReviewInteractor;
import use_case.list_review.ListReviewOutputBoundary;
import view.ListReviewView;
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
//    private final DBReviewAccessObject dbReviewAccessObject = new DBReviewAccessObject();
    private final DBReviewListAccessObject dbReviewListAccessObject = new DBReviewListAccessObject();

    private JPanel view;
    private ViewModel model;

    public RateMyCampusAppBuilder() {
        cardPanel.setLayout(cardLayout);
    }

    /**
     * Adds the Rate View to the application.
     * @return this builder
     */
    public RateMyCampusAppBuilder addRateView() {
        model = new RateViewModel();
        view = new RateView((RateViewModel) model);
        cardPanel.add(view, ((RateView) view).getViewName());
        return this;
    }

    /**
     * Adds the List Review View to the application.
     * @return this builder
     */
    public RateMyCampusAppBuilder addListReviewView() {
        model = new ListReviewViewModel();
        final ListReviewState state = (ListReviewState) model.getState();
        state.setReviewList(dbReviewListAccessObject.getReviews(state.getPageNumber(), state.getPageSize()));
        view = new ListReviewView((ListReviewViewModel) model);

        final ListReviewOutputBoundary listReviewOutputBoundary = new ListReviewPresenter(
                (ListReviewViewModel) model,
                new ViewManagerModel()
                );
        final ListReviewInputBoundary listReviewInteractor = new ListReviewInteractor(
                dbReviewListAccessObject,
                listReviewOutputBoundary
                );
        final ListReviewController listReviewController = new ListReviewController(listReviewInteractor);
        ((ListReviewView) view).setListReviewController(listReviewController);

        cardPanel.add(view, ((ListReviewView) view).getViewName());

        return this;
    }

    /**
     * Adds the CreateReview Use Case to the application.
     * @return this builder
     */
    public RateMyCampusAppBuilder addCreateReviewUseCase() {
        final CreateReviewOutputBoundary createReviewOutputBoundary = new RatePresenter((RateViewModel) model);
//        final CreateReviewInputBoundary createReviewInteractor = new CreateReviewInteractor(
//                dbReviewAccessObject, createReviewOutputBoundary);

//        final RateController rateController = new RateController((CreateReviewInteractor) createReviewInteractor);
//        RateView.setRateController(rateController);
        return this;
    }

    /**
     * Creates the JFrame for the application and initially sets the SignupView to be displayed.
     * @return the application
     */
    public JFrame build() {
        final JFrame application = new JFrame("List Review Example");
        application.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        application.add(cardPanel);

        viewManagerModel.setState(((ListReviewView) view).getViewName());
        viewManagerModel.firePropertyChanged();

        application.setSize(500, 400);

        return application;
    }
}