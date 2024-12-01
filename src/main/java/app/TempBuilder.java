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
import interface_adapters.ViewManagerModel;
import interface_adapters.ViewModel;
import interface_adapters.create_review.CreateReviewViewModel;
import interface_adapters.list_review.ListReviewController;
import interface_adapters.list_review.ListReviewPresenter;
import interface_adapters.list_review.ListReviewState;
import interface_adapters.list_review.ListReviewViewModel;
//import interface_adapters.rate.RateController;
//import interface_adapters.rate.RatePresenter;
//import interface_adapters.rate.RateViewModel;
import interface_adapters.login.LoginViewModel;
import interface_adapters.map.MapController;
import interface_adapters.map.MapPresenter;
import interface_adapters.map.MapViewModel;
import interface_adapters.profile.ProfileViewModel;
import interface_adapters.signup.SignupViewModel;
import use_case.check_map.CheckMapInputBoundary;
import use_case.check_map.CheckMapInteractor;
import use_case.check_map.CheckMapOutputBoundary;
import use_case.list_review.ListReviewInputBoundary;
import use_case.list_review.ListReviewInteractor;
import use_case.list_review.ListReviewOutputBoundary;
import view.*;

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

    String defaultView = "";
    private JPanel view;
    private SignupView signupView;
    private SignupViewModel signupViewModel;
    private LoginView loginView;
    private LoginViewModel loginViewModel;
    private ListReviewView listReviewView;
    private ListReviewViewModel listReviewViewModel;
    private RateView createReviewView;
    private CreateReviewViewModel createReviewViewModel;
    private MapView mapView;
    private MapViewModel mapViewModel;
    private ProfileView profileView;
    private ProfileViewModel profileViewModel;



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
        listReviewView = new ListReviewView(listReviewViewModel);
        final ListReviewState state = listReviewViewModel.getState();
        state.setReviewList(dbReviewListAccessObject.getReviews(state.getPageNumber(), state.getPageSize()));

        cardPanel.add(listReviewView, listReviewView.getViewName());

        defaultView = listReviewView.getViewName();

        return this;
    }

    /**
     * Adds the Map View to the application.
     * @return this builder
     */
    public TempBuilder addMapView() {
        mapViewModel = new MapViewModel();
        mapView = new MapView(mapViewModel);
        cardPanel.add(mapView, mapView.getViewName());
        return this;
    }

    /**
     * Adds the CreateReview Use Case to the application.
     * @return this builder
     */
    public TempBuilder addCreateReviewUseCase() {
//        final CreateReviewOutputBoundary createReviewOutputBoundary = new RatePresenter((RateViewModel) model);
//        final CreateReviewInputBoundary createReviewInteractor = new CreateReviewInteractor(
//                dbReviewAccessObject, createReviewOutputBoundary);

//        final RateController rateController = new RateController((CreateReviewInteractor) createReviewInteractor);
//        RateView.setRateController(rateController);
        return this;
    }

    public TempBuilder addListReviewUseCase() {
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
        listReviewView.setListReviewController(listReviewController);
        return this;
    }

    public TempBuilder addMapUseCase() {
        final CheckMapOutputBoundary mapOutputBoundary = new MapPresenter(viewManagerModel, mapViewModel
                , listReviewViewModel);
        final CheckMapInputBoundary mapInteractor = new CheckMapInteractor(mapOutputBoundary);

        final MapController controller = new MapController((CheckMapInteractor) mapInteractor);
        mapView.setMapController(controller);
        return this;
    }

    /**
     * Force refreshes viewManagerModel for view changes.
     */
    public void refreshList() {
        listReviewViewModel.firePropertyChanged();
//        viewManagerModel.firePropertyChanged();
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