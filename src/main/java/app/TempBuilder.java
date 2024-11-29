package app;

import java.awt.*;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

//import data_access.DBReviewAccessObject;
import data_access.DBProfileAccessObject;
import data_access.DBReviewListAccessObject;
import data_access.DBUserAccessObject;
import app.UserFactory;
import interface_adapters.ViewManagerModel;
import interface_adapters.ViewModel;
import interface_adapters.list_review.ListReviewController;
import interface_adapters.list_review.ListReviewPresenter;
import interface_adapters.list_review.ListReviewState;
import interface_adapters.list_review.ListReviewViewModel;
//import interface_adapters.rate.RateController;
//import interface_adapters.rate.RatePresenter;
//import interface_adapters.rate.RateViewModel;
import interface_adapters.map.MapViewModel;
import interface_adapters.profile.ProfileViewModel;
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
public class TempBuilder {
    private final JPanel cardPanel = new JPanel();
    private final CardLayout cardLayout = new CardLayout();
    private final ViewManagerModel viewManagerModel = new ViewManagerModel();
    private final ViewManager viewManager = new ViewManager(cardPanel, cardLayout, viewManagerModel);

    private final DBReviewListAccessObject dbReviewListAccessObject = new DBReviewListAccessObject();
    private final DBUserAccessObject dbUserAccessObject = new DBUserAccessObject();
    private final DBProfileAccessObject dbProfileAccessObject = new DBProfileAccessObject();

    // TODO: add each view and initialized them in the constructor
    private ListReviewViewModel listReviewViewModel;
    private MapViewModel mapViewModel;
    private ProfileViewModel profileViewModel;

    String defaultView = "";

    public TempBuilder() {
        cardPanel.setLayout(cardLayout);

        listReviewViewModel = new ListReviewViewModel();
        mapViewModel = new MapViewModel();
        profileViewModel = new ProfileViewModel();
    }

    /**
     * Adds the List Review View to the application.
     * @return this builder
     */
    public TempBuilder addListReviewView() {
        listReviewViewModel = new ListReviewViewModel();
        final ListReviewState state = listReviewViewModel.getState();
        state.setReviewList(dbReviewListAccessObject.getReviews(state.getPageNumber(), state.getPageSize()));
        ListReviewView view = new ListReviewView(listReviewViewModel);

        final ListReviewOutputBoundary listReviewOutputBoundary = new ListReviewPresenter(
                listReviewViewModel,
                mapViewModel,
                profileViewModel,
                viewManagerModel
        );
        final ListReviewInputBoundary listReviewInteractor = new ListReviewInteractor(
                dbReviewListAccessObject,
                dbProfileAccessObject,
                listReviewOutputBoundary
        );
        final ListReviewController listReviewController = new ListReviewController(listReviewInteractor);
        view.setListReviewController(listReviewController);

        cardPanel.add(view, view.getViewName());

        defaultView = view.getViewName();

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

        viewManagerModel.setState(defaultView);
        viewManagerModel.firePropertyChanged();

        application.setSize(500, 400);

        return application;
    }
}