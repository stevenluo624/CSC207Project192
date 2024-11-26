package interface_adapters.create_review;

import interface_adapters.ViewModel;

/**
 * The ViewModel for the NoteView.
 */
public class CreateReviewViewModel extends ViewModel<CreateReviewState> {
    public CreateReviewViewModel() {
        super("create_review");
        setState(new CreateReviewState());
    }
}
