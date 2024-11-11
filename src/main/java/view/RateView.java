package view;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import interface_adapters.login.LoginState;
import interface_adapters.rate.RateController;
import interface_adapters.rate.RateState;
import interface_adapters.rate.RateModel;

/**
 * The View for when the user is ratting different place.
 */
public class RateView extends JPanel implements ActionListener, PropertyChangeListener {

    private final String viewName = "Submit";
    private final RateViewModel rateViewModel;

    private final JButton submit;
    private final JButton cancel;
    private RateController rateController;

    private JToggleButton[] stars;
    private int rating = 0;

    public RateView(RateviewModel rateViewModel) {
        this.rateViewModel = rateViewModel;
        this.rateViewModel.addPropertyChangeListener(this);

        final JLabel title = new JLabel("Rate Screen");
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        final JPanel buttons = new JPanel();
        submit = new JButton("Submit");
        buttons.add(submit);
        cancel = new JButton("Cancel");
        buttons.add(cancel);
        cancel.addActionListener(this);

        submit.addActionListener(e -> System.out.println("Rating submitted: " + ratingPanel.getRating()));

        this.add(title);
        this.add(buttons);

    }
    /**
     * React to a button click that results in evt.
     * @param evt the ActionEvent to react to
     */
    public void actionPerformed(ActionEvent evt){System.out.println("Click " + evt.getActionCommand());}

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        final RateState state = (RateState) evt.getNewValue();
        setFields(state);
    }

    private void setFields(RateState state) {

    }

    public String getViewName() {return viewName;}

    public void setRateController(RateController rateController){
        this.rateController = rateController;
    }


    public Stars(int totalStars) {
        setLayout(new FlowLayout(FlowLayout.LEFT));
        ButtonGroup starGroup = new ButtonGroup();  // Group to allow toggling behavior
        stars = new JToggleButton[totalStars];

        // Create and add stars
        for (int i = 0; i < totalStars; i++) {
            stars[i] = new JToggleButton(new ImageIcon("images/star_empty.png"));
            stars[i].setSelectedIcon(new ImageIcon("images/star_filled.png"));
            stars[i].setPreferredSize(new Dimension(20,20));
            stars[i].setBorderPainted(false);
            stars[i].setContentAreaFilled(false);
            stars[i].setFocusPainted(false);

            final int index = i;
            stars[i].addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    setRating(index + 1);
                }
            });

            add(stars[i]);
            starGroup.add(stars[i]);
        }
    }

    public void setRating(int newRating) {
        if (rating == newRating) {
            if (stars[newRating - 1].isSelected()) {
                stars[newRating- -1].setSelected(false);
                rating = newRating - 1;
            }
            else {
                rating = newRating; // Keep the same rating if clicked again
            }
            }
        else {
            rating = newRating;
        }
        updateStars();
    }

    private void updateStars() {
        for (int i = 0; i < stars.length; i++) {
            stars[i].setSelected(i < rating);
        }
    }

    public int getRating() {
        return rating;
    }



}
