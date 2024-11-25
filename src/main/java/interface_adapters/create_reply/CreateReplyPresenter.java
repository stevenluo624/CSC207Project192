package interface_adapters.create_reply;

import use_case.create_reply.CreateReplyOutputBoundary;
import use_case.create_reply.CreateReplyOutputData;

/**
 * The presenter for our Note viewing and editing program.
 */
public class CreateReplyPresenter implements CreateReplyOutputBoundary {

    private final CreateReplyViewModel createReplyViewModel;

    public CreateReplyPresenter(CreateReplyViewModel createReplyViewModel) {
        this.createReplyViewModel = createReplyViewModel;
    }

    /**
     * Prepares the success view for the reply related use cases
     * @param outputData the output data object
     */
    @Override
    public void prepareSuccessView(CreateReplyOutputData outputData) {
        createReplyViewModel.getState().setUser(outputData.getUser());
        createReplyViewModel.getState().setComment(outputData.getComment());
        createReplyViewModel.getState().setError(null);
        createReplyViewModel.firePropertyChanged();
    }

    /**
     * Prepares the failure view for the reply related use cases
     * @param message the explanation of the error
     */
    @Override
    public void prepareFailureView(String message) {
        createReplyViewModel.getState().setError(message);
        createReplyViewModel.firePropertyChanged();
    }
}
