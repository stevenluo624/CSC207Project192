package interface_adapters.list_review;

import interface_adapters.ViewManagerModel;
import interface_adapters.create_reply.CreateReplyState;
import interface_adapters.create_reply.CreateReplyViewModel;
import interface_adapters.create_review.CreateReviewState;
import interface_adapters.create_review.CreateReviewViewModel;
import interface_adapters.map.MapController;
import interface_adapters.map.MapViewModel;
import interface_adapters.profile.ProfileState;
import interface_adapters.profile.ProfileViewModel;
import use_case.check_map.CheckMapOutputData;
import use_case.create_reply.CreateReplyOutputData;
import use_case.create_review.CreateReviewOutputData;
import use_case.list_review.ListReviewOutputBoundary;
import use_case.list_review.ListReviewOutputData;
import use_case.profile.ProfileOutputData;
import view.MapView;

/**dqw
 * Presenter for the list review use case.
 */
public class ListReviewPresenter implements ListReviewOutputBoundary {

    private final ListReviewViewModel listReviewViewModel;
    private final MapViewModel mapViewModel;
    private final ProfileViewModel profileViewModel;
    private final CreateReviewViewModel createReviewViewModel;
    private final CreateReplyViewModel createReplyViewModel;
    private final ViewManagerModel viewManagerModel;

    public ListReviewPresenter(ListReviewViewModel listReviewViewModel,
                               MapViewModel mapViewModel,
                               ProfileViewModel profileViewModel, CreateReviewViewModel createReviewViewModel, CreateReplyViewModel createReplyViewModel,
                               ViewManagerModel viewManagerModel) {
        this.listReviewViewModel = listReviewViewModel;
        this.mapViewModel = mapViewModel;
        this.profileViewModel = profileViewModel;
        this.createReviewViewModel = createReviewViewModel;
        this.createReplyViewModel = createReplyViewModel;
        this.viewManagerModel = viewManagerModel;
    }

    @Override
    public void prepareSuccessView(ListReviewOutputData outputData) {
        final ListReviewState listReviewState = listReviewViewModel.getState();
        listReviewState.setPageNumber(outputData.getPageNumber());
        listReviewState.setPageSize(outputData.getPageSize());
        listReviewState.setReviewList(outputData.getReviewList());

        listReviewViewModel.setState(listReviewState);
        listReviewViewModel.firePropertyChanged();

        viewManagerModel.setState(listReviewViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }

    @Override
    public void prepareFailView(String errorMessage) {
        final ListReviewState listReviewState = listReviewViewModel.getState();
        listReviewState.setPageError(errorMessage);
        listReviewViewModel.firePropertyChanged();
    }

    @Override
    public void switchToMapView(CheckMapOutputData checkMapOutputData) {
        mapViewModel.getState().setName(checkMapOutputData.getName());
        mapViewModel.getState().setLatitude(checkMapOutputData.getLatitude());
        mapViewModel.getState().setLongitude(checkMapOutputData.getLongitude());
        viewManagerModel.setState(mapViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }

    @Override
    public void switchToProfileView(ProfileOutputData profileOutputData) {
        ProfileState profileState = profileViewModel.getState();
        profileState.setUsername(profileOutputData.getUsername());
        profileState.setBio(profileOutputData.getBio());
        profileViewModel.firePropertyChanged();
        viewManagerModel.setState(profileViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }

    @Override
    public void switchToCreateReviewView(CreateReviewOutputData createReviewOutputData) {
        CreateReviewState createReviewState = createReviewViewModel.getState();
        createReviewState.setUser(createReviewOutputData.getUser());
        viewManagerModel.setState(createReviewViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }

    /**
     * @param createReplyOutputData contains output data for create reply
     */
    @Override
    public void switchToCreateReplyView(CreateReplyOutputData createReplyOutputData) {
        CreateReplyState createReplyState = createReplyViewModel.getState();
        createReplyState.setUser(createReplyOutputData.getUser());
        viewManagerModel.setState(createReplyViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }
}
