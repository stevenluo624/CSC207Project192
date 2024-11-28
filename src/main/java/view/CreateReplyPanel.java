package view;

import entity.reviews_thread.Review;
import entity.User;
import interface_adapters.create_reply.CreateReplyController;
import interface_adapters.create_reply.CreateReplyState;
import interface_adapters.create_reply.CreateReplyViewModel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

/**
 *
 */
public class CreateReplyPanel extends JPanel implements ActionListener, PropertyChangeListener {
    private final User user;
    private Review rootReview = null;

    private final CreateReplyViewModel viewModel;
    private CreateReplyController controller;

    private final JTextArea replyTextArea = new JTextArea();
    private final JButton submitReplyButton = new JButton("Submit");
    private final JButton cancelReplyButton = new JButton("Cancel");

    /**
     * @param createReplyViewModel a view model for the create reply use case
     * @param reviewsPage the previous page to return to after submiting or cancelling the reply
     * @param user the user that create this reply
     * @param rootReview the review that the user is replying to
     */
    public CreateReplyPanel(CreateReplyViewModel createReplyViewModel, JPanel reviewsPage,
                            User user, Review rootReview) {
        this.user = user;
        this.rootReview = rootReview;
        final String title =  "Reply to " + rootReview.getUser().getUsername();

        // Create new frame for this use case
        JFrame frame = new JFrame(title);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setSize(400, 300);

        JLabel createReplyLabel = new JLabel(title);
        createReplyLabel.setAlignmentX(CENTER_ALIGNMENT);

        viewModel = createReplyViewModel;
        viewModel.addPropertyChangeListener(this);

        final JPanel buttonsPanel = new JPanel();
        buttonsPanel.add(cancelReplyButton);
        buttonsPanel.add(submitReplyButton);
        buttonsPanel.setLayout(new BoxLayout(buttonsPanel, BoxLayout.X_AXIS));

        cancelReplyButton.addActionListener(
                evt -> {
                    if (evt.getSource().equals(cancelReplyButton)) {
                        // Back button implementation (return to previous page)
                        frame.dispose();
                        frame.revalidate();
                        frame.repaint();
                    }
                }
        );

        submitReplyButton.addActionListener(
                evt -> {
                    if (evt.getSource().equals(submitReplyButton)) {
                        // Execute use case controller
                        final String replyString = replyTextArea.getText();
                        controller.execute(user, replyString, rootReview);
                        // Return to previous page
                        frame.dispose();
                        frame.revalidate();
                        frame.repaint();
                    }
                }
        );

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        this.add(createReplyLabel);
        this.add(replyTextArea);
        this.add(buttonsPanel);
        frame.add(this);
        frame.setVisible(true);
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
        if (state.getError() != null) {
            JOptionPane.showMessageDialog(this, state.getError(),
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void setFields(CreateReplyState state) {
        replyTextArea.setText(state.getComment());
    }

    public void setController(CreateReplyController controller) {
        this.controller = controller;
    }
}
