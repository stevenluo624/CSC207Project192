package view;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JToggleButton;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import interface_adapters.rate.RateController;
import interface_adapters.rate.RateState;
import interface_adapters.rate.RateViewModel;

/**
 * The View for when the user is ratting different place.
 */
public class RateView extends JPanel implements ActionListener, PropertyChangeListener {

    public static final int NUMSTARS = 5;
    public static final int DIMENSION = 30;
    private final String viewName = "rate";
    private final RateViewModel rateViewModel;

    private final JButton submit;
    private final JButton cancel;
    private RateController rateController;

    private final JToggleButton[] stars = new JToggleButton[NUMSTARS];

    private final JTextField commentInputField = new JTextField(25);
    private final JLabel commentErrorField = new JLabel();

    public RateView(RateViewModel rateViewModel) {
        this.rateViewModel = rateViewModel;
        this.rateViewModel.addPropertyChangeListener(this);

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
                            final RateState currentState = rateViewModel.getState();

                            rateController.execute(
                                    currentState.getUser(),
                                    currentState.getRating(),
                                    currentState.getComment()
                            );
                        }
                    }
                }
        );
        cancel.addActionListener(this);

        commentInputField.getDocument().addDocumentListener(new DocumentListener() {

            private void documentListenerHelper() {
                final RateState currentState = rateViewModel.getState();
                currentState.setComment(commentInputField.getText());
                rateViewModel.setState(currentState);
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
        this.add(commentInputField);
        this.add(commentErrorField);
        this.add(buttons);
    }

    private void createStars() {
        for (int i = 0; i < stars.length; i++) {
            stars[i] = new JToggleButton(new ImageIcon("images/star_empty.png"));
            stars[i].setSelectedIcon(new ImageIcon("images/star_filled.png"));
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
        final RateState state = (RateState) evt.getNewValue();
        setFields(state);
        commentErrorField.setText(state.getCommentError());
    }

    private void setFields(RateState state) {
        commentErrorField.setText(state.getComment());
    }

    public String getViewName() {
        return viewName;
    }

    public void setRateController(RateController rateController) {
        this.rateController = rateController;
    }

    private void setStarRating(int rating) {
        for (int i = 0; i < stars.length; i++) {
            stars[i].setSelected(true);
        }
        for (int i = rating; i < stars.length; i++) {
            stars[i].setSelected(false);
        }
        rateViewModel.getState().setRating(rating);
    }

}
