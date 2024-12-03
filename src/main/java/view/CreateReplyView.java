package view;

import entity.reviews_thread.Review;
import entity.User;
import interface_adapters.create_reply.CreateReplyController;
import interface_adapters.create_reply.CreateReplyState;
import interface_adapters.create_reply.CreateReplyViewModel;
import interface_adapters.create_review.CreateReviewState;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.text.BreakIterator;

/**
 *
 */
public class CreateReplyView extends JPanel implements ActionListener, PropertyChangeListener {
    private final CreateReplyViewModel viewModel;
    private CreateReplyController controller;

    private final String viewName;

    private final JTextField commentInputField = new JTextField(25);
    private final JLabel commentErrorField = new JLabel();

    private final JButton submit;
    private final JButton cancel;

    /**
     * @param createReplyViewModel a view model for the create reply use case
     */
    public CreateReplyView(CreateReplyViewModel createReplyViewModel) {
        viewModel = createReplyViewModel;
        viewModel.addPropertyChangeListener(this);
        viewName = viewModel.getViewName();

        User user = viewModel.getState().getUser();
        Review rootReview = viewModel.getState().getReview();

        JLabel title = new JLabel("Reply to");
        title.setAlignmentX(CENTER_ALIGNMENT);

        final LabelTextPanel replyInputLabel = new LabelTextPanel(
                new JLabel("Leave a comment"), commentInputField);

        final JPanel buttonsPanel = new JPanel();
        submit = new JButton("Submit");
        buttonsPanel.add(submit);
        cancel = new JButton("Cancel");
        buttonsPanel.add(cancel);
        buttonsPanel.setLayout(new BoxLayout(buttonsPanel, BoxLayout.X_AXIS));

        submit.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        if (evt.getSource().equals(submit)) {
                            final CreateReplyState currentState = viewModel.getState();

                            controller.execute(
                                    currentState.getUser(),
                                    currentState.getComment(),
                                    currentState.getReview()
                            );
                        }
                    }
                }
        );

        cancel.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        controller.switchToListReviewView();
                    }
                }
        );

        commentInputField.getDocument().addDocumentListener(new DocumentListener() {

            private void documentListenerHelper() {
                final CreateReplyState currentState = createReplyViewModel.getState();
                currentState.setComment(commentInputField.getText());
                createReplyViewModel.setState(currentState);
            }

            @Override
            public void insertUpdate(DocumentEvent e) {
                documentListenerHelper();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                documentListenerHelper();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                documentListenerHelper();
            }
        });

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        this.add(title);
        this.add(replyInputLabel);
        this.add(commentErrorField);
        this.add(buttonsPanel);
    }

    /**
     * React to a button click that results in e
     * @param evt the event to be processed
     */
    @Override
    public void actionPerformed(ActionEvent evt) {
        System.out.println("Click " + evt.getActionCommand());
    }

    /**
     * @param evt A PropertyChangeEvent object describing the event source
     *            and the property that has changed.
     */
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        final CreateReplyState state = (CreateReplyState) evt.getNewValue();
        setFields(state);
        commentErrorField.setText(state.getError());
    }

    private void setFields(CreateReplyState state) {
        commentInputField.setText(state.getComment());
    }

    public void setController(CreateReplyController controller) {
        this.controller = controller;
    }

    public String getViewName() {
        return viewName;
    }
}
