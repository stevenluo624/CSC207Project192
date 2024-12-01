package app;

import java.awt.*;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

//import data_access.DBReviewAccessObject;
import data_access.DBProfileAccessObject;
import data_access.DBReviewListAccessObject;
import data_access.DBUserAccessObject;
import entity.StudentUser;
import entity.User;
import interface_adapters.ViewManagerModel;
import interface_adapters.ViewModel;
import interface_adapters.create_review.CreateReviewController;
import interface_adapters.create_review.CreateReviewPresenter;
import interface_adapters.create_review.CreateReviewViewModel;
import interface_adapters.list_review.ListReviewController;
import interface_adapters.list_review.ListReviewPresenter;
import interface_adapters.list_review.ListReviewState;
import interface_adapters.list_review.ListReviewViewModel;
//import interface_adapters.rate.RateController;
//import interface_adapters.rate.RatePresenter;
//import interface_adapters.rate.RateViewModel;
import interface_adapters.login.LoginController;
import interface_adapters.login.LoginPresenter;
import interface_adapters.login.LoginViewModel;
import interface_adapters.map.MapController;
import interface_adapters.map.MapPresenter;
import interface_adapters.map.MapViewModel;
import interface_adapters.profile.ProfileController;
import interface_adapters.profile.ProfilePresenter;
import interface_adapters.profile.ProfileViewModel;
import interface_adapters.signup.SignupController;
import interface_adapters.signup.SignupPresenter;
import interface_adapters.signup.SignupViewModel;
import use_case.check_map.CheckMapInputBoundary;
import use_case.check_map.CheckMapInteractor;
import use_case.check_map.CheckMapOutputBoundary;
import use_case.create_review.CreateReviewInputBoundary;
import use_case.create_review.CreateReviewInteractor;
import use_case.create_review.CreateReviewOutputBoundary;
import use_case.list_review.ListReviewInputBoundary;
import use_case.list_review.ListReviewInteractor;
import use_case.list_review.ListReviewOutputBoundary;
import use_case.login.LoginInputBoundary;
import use_case.login.LoginInteractor;
import use_case.login.LoginOutputBoundary;
import use_case.profile.ProfileInteractor;
import use_case.profile.ProfileOutputBoundary;
import use_case.profile.ProfileInputBoundary;
import use_case.signup.SignupInputBoundary;
import use_case.signup.SignupInteractor;
import use_case.signup.SignupOutputBoundary;
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

    private UserFactory userFactory;



    public TempBuilder() {
        cardPanel.setLayout(cardLayout);

        signupViewModel = new SignupViewModel();
        loginViewModel = new LoginViewModel();
        listReviewViewModel = new ListReviewViewModel();
        mapViewModel = new MapViewModel();
        profileViewModel = new ProfileViewModel();
        createReviewViewModel = new CreateReviewViewModel();
        userFactory = new UserFactory() {
            @Override
            public User create(String name, String password) {
                return new StudentUser(name, password);
            }
        };
    }

    /**
     * Adds the SignUp View to the application.
     * @return this builder
     */
    public TempBuilder addSignUpView() {
        signupViewModel = new SignupViewModel();
        signupView = new SignupView(signupViewModel);
        cardPanel.add(signupView, signupView.getViewName());
        defaultView = signupView.getViewName();
        return this;
    }

    /**
     * Adds the Login View to the application.
     * @return this builder
     */
    public TempBuilder addLoginView() {
        loginViewModel = new LoginViewModel();
        loginView = new LoginView(loginViewModel);
        cardPanel.add(loginView, loginView.getViewName());
        return this;
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

        return this;
    }

    public TempBuilder addCreateReviewView() {
        createReviewViewModel = new CreateReviewViewModel();
        createReviewView = new RateView(createReviewViewModel);
        cardPanel.add(createReviewView, createReviewView.getViewName());
        return this;
    }

    /**
     * Add the Profile View to the application.
     * @return the Profile builder
     */
    public TempBuilder addProfileView() {
        profileViewModel = new ProfileViewModel();
        profileView = new ProfileView(profileViewModel);
        cardPanel.add(profileView, profileView.getViewName());
        return this;
    }

    /**
     * Adds the SignUp View to the application.
     * @return the Signup builder
     */
    public TempBuilder addSignupView() {
        signupViewModel = new SignupViewModel();
        signupView = new SignupView(signupViewModel);
        cardPanel.add(signupView, signupView.getViewName());
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

    public TempBuilder addSignupUseCase() {
        final SignupOutputBoundary signupOutputBoundary = new SignupPresenter(viewManagerModel, signupViewModel
                , loginViewModel);
        final SignupInputBoundary signupInteractor = new SignupInteractor(dbUserAccessObject, signupOutputBoundary,
                userFactory);

        final SignupController controller = new SignupController((SignupInteractor) signupInteractor);
        signupView.setSignupController(controller);
        return this;
    }

    /**
     * Adds the Login Use Case to the application.
     * @return this builder
     */
    public TempBuilder addLoginUseCase() {
        final LoginOutputBoundary loginOutputBoundary = new LoginPresenter(viewManagerModel,
                listReviewViewModel, loginViewModel);
        final LoginInputBoundary loginInteractor = new LoginInteractor(
                dbUserAccessObject, loginOutputBoundary);

        final LoginController loginController = new LoginController(loginInteractor);
        loginView.setLoginController(loginController);
        return this;
    }

    /**
     * Adds the CreateReview Use Case to the application.
     * @return this builder
     */
    public TempBuilder addCreateReviewUseCase() {
        final CreateReviewOutputBoundary createReviewOutputBoundary = new CreateReviewPresenter(
                (CreateReviewViewModel) createReviewViewModel);
        final CreateReviewInputBoundary createReviewInteractor = new CreateReviewInteractor(
                dbReviewListAccessObject, createReviewOutputBoundary);

        final CreateReviewController createReviewController = new CreateReviewController(
                (CreateReviewInteractor) createReviewInteractor);
        createReviewView.setRateController(createReviewController);
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

    /**
     * Adds the Profile Use Case to the application.
     * @return this builder
     */
    public TempBuilder addProfileUseCase() {
        final ProfileOutputBoundary profileOutputBoundary = new ProfilePresenter(viewManagerModel,
                profileViewModel);
        final ProfileInputBoundary profileInteractor = new ProfileInteractor(dbProfileAccessObject,
                profileOutputBoundary);
        final ProfileController controller = new ProfileController((ProfileInteractor) profileInteractor);
        profileView.setProfileController(controller);
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