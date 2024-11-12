package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Stars extends JPanel {
    private JToggleButton[] stars;
    private int rating = 0;

    public Stars(int totalStars) {
        setLayout(new FlowLayout(FlowLayout.LEFT));
        stars = new JToggleButton[totalStars];

        for (int i = 0; i < totalStars; i++) {
            stars[i] = new JToggleButton(new ImageIcon("star_empty.png"));
            stars[i].setSelectedIcon(new ImageIcon("star_filled.png"));
            stars[i].setPreferredSize(new Dimension(30, 30));
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
        }
    }

    public void setRating(int newRating) {
        if (rating == newRating && stars[newRating - 1].isSelected()) {
            rating = newRating - 1;  // Reduce rating if the same star is clicked again
        } else {
            rating = newRating;
        }
        updateStars();
    }

    private void updateStars() {
        for (int i = 0; i < stars.length; i++) {
            stars[i].setSelected(i < rating);
        }
    }
}
