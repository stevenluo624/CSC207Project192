package interface_adapters.create_reply;

import interface_adapters.ViewModel;

/**
 * The ViewModel for the Create Reply use case.
 */
public class CreateReplyViewModel extends ViewModel<CreateReplyState> {
    public CreateReplyViewModel() {
        super("Create Reply");
        setState(new CreateReplyState());
    }
}
