package interface_adapters.rate;

import interface_adapters.ViewModel;

/**
 * The ViewModel for the NoteView.
 */
public class RateViewModel extends ViewModel<RateState> {
    public RateViewModel() {
        super("rate");
        setState(new RateState());
    }
}
