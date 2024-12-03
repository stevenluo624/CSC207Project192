package interface_adapters.create_reply;

import interface_adapters.ViewManagerModel;
import interface_adapters.list_review.ListReviewViewModel;
import use_case.create_reply.CreateReplyOutputBoundary;
import use_case.create_reply.CreateReplyOutputData;

/**
 * The presenter for our Note viewing and editing program.
 */
public class CreateReplyPresenter implements CreateReplyOutputBoundary {

    private final CreateReplyViewModel createReplyViewModel;
    private final ListReviewViewModel listReviewViewModel;
    private final ViewManagerModel viewManagerModel;

    public CreateReplyPresenter(CreateReplyViewModel createReplyViewModel, ListReviewViewModel listReviewViewModel, ViewManagerModel viewManagerModel) {
        this.createReplyViewModel = createReplyViewModel;
        this.listReviewViewModel = listReviewViewModel;
        this.viewManagerModel = viewManagerModel;
    }

    /**
     * Prepares the success view for the reply related use cases
     * @param outputData the output data object
     */
    @Override
    public void prepareSuccessView(CreateReplyOutputData outputData) {
        createReplyViewModel.getState().setUser(outputData.getUser());
        createReplyViewModel.getState().setComment(outputData.getComment());
        createReplyViewModel.getState().setError(null); // No error detected
        createReplyViewModel.firePropertyChanged();

        viewManagerModel.setState(listReviewViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }

    /**
     * Prepares the failure view for the reply related use cases
     * @param message the explanation of the error
     */
    @Override
    public void prepareFailureView(String message) {
        if (!"".equals(message)) {
            createReplyViewModel.getState().setError(message);
        } else {
            createReplyViewModel.getState().setError("Error in the create reply use case presenter.");
        }
        createReplyViewModel.firePropertyChanged();
    }

    /**
     * Switch to the list review use case view
     */
    @Override
    public void switchToListReviewView() {
        viewManagerModel.setState(listReviewViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }
}
