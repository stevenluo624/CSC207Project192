package view;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import interface_adapters.create_review.CreateReviewController;
import interface_adapters.create_review.CreateReviewState;
import interface_adapters.create_review.CreateReviewViewModel;

/**
 * The View for when the user is ratting different place.
 */
public class RateView extends JPanel implements ActionListener, PropertyChangeListener {
    public static final int NUMSTARS = 5;
    public static final int DIMENSION = 30;
  
    private final String viewName = "create_review";
    private final CreateReviewViewModel  createReviewViewModel;

    private final JButton submit;
    private final JButton cancel;
    private CreateReviewController createReviewController;

    private final JToggleButton[] stars = new JToggleButton[NUMSTARS];

    private final JTextField commentInputField = new JTextField(25);
    private final JLabel commentErrorField = new JLabel();
    private final JTextField nameInputField = new JTextField(25);
    private final JLabel nameErrorField = new JLabel();

    public RateView(CreateReviewViewModel createReviewViewModel) {
        this.createReviewViewModel = createReviewViewModel;
        this.createReviewViewModel.addPropertyChangeListener(this);

        final LabelTextPanel locationName = new LabelTextPanel(
                new JLabel("Location name"), nameInputField);

        final LabelTextPanel locationReview = new LabelTextPanel(
                new JLabel("Leave a comment"), commentInputField);

        final JLabel title = new JLabel("Rate Screen");
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        final JPanel buttons = new JPanel();
        submit = new JButton("Submit");
        buttons.add(submit);
        cancel = new JButton("Cancel");
        buttons.add(cancel);

        submit.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        if (evt.getSource().equals(submit)) {
                            final CreateReviewState currentState = createReviewViewModel.getState();

                            createReviewController.execute(
                                    currentState.getUser(),
                                    currentState.getRating(),
                                    currentState.getComment(),
                                    currentState.getLocationName()
                            );
                        }
                    }
                }
        );

        cancel.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        createReviewController.switchToListReviewView();
                    }
                }
        );

        nameInputField.getDocument().addDocumentListener(new DocumentListener() {

            private void documentListenerHelper() {
                final CreateReviewState currentState = createReviewViewModel.getState();
                currentState.setLocationName(nameInputField.getText());
                createReviewViewModel.setState(currentState);
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

        commentInputField.getDocument().addDocumentListener(new DocumentListener() {

            private void documentListenerHelper() {
                final CreateReviewState currentState = createReviewViewModel.getState();
                currentState.setComment(commentInputField.getText());
                createReviewViewModel.setState(currentState);
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

        this.add(title);
        createStars();
        this.add(locationName);
        this.add(nameErrorField);
        this.add(locationReview);
        this.add(commentErrorField);
        this.add(buttons);
    }

    private void createStars() {
        for (int i = 0; i < stars.length; i++) {
            stars[i] = new JToggleButton(new ImageIcon("src/main/resources/images/star_empty.png"));
            stars[i].setSelectedIcon(new ImageIcon("src/main/resources/images/star_filled.png"));
            stars[i].setPreferredSize(new Dimension(DIMENSION, DIMENSION));
            stars[i].setBorderPainted(false);
            stars[i].setContentAreaFilled(false);
            stars[i].setFocusPainted(false);
            final int finalI = i;
            stars[i].addActionListener(evt -> setStarRating(finalI + 1));
            add(stars[i]);
        }
    }
    /**
     * React to a button click that results in evt.
     * @param evt the ActionEvent to react to
     */

    public void actionPerformed(ActionEvent evt) {
        System.out.println("Click " + evt.getActionCommand());
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        final CreateReviewState state = (CreateReviewState) evt.getNewValue();
        setFields(state);
        commentErrorField.setText(state.getError());
    }

    private void setFields(CreateReviewState state) {
        commentErrorField.setText(state.getComment());
    }

    public String getViewName() {
        return viewName;
    }

    public void setRateController(CreateReviewController createReviewController) {
        this.createReviewController = createReviewController;
    }

    private void setStarRating(int rating) {
        for (int i = 0; i < stars.length; i++) {
            stars[i].setSelected(true);
        }
        for (int i = rating; i < stars.length; i++) {
            stars[i].setSelected(false);
        }
        createReviewViewModel.getState().setRating(rating);
    }

}
