package view;

import entity.reviews_thread.Review;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

/**
 * A panel for displaying one review in the review list.
 */
public class UserReviewPanel extends JPanel {
    public UserReviewPanel(Review review) {
        setLayout(new FlowLayout());

        Border padding = BorderFactory.createEmptyBorder(10, 10, 10, 10);
        Border lineBorder = BorderFactory.createLineBorder(Color.BLACK, 2);
        setBorder(BorderFactory.createCompoundBorder(lineBorder, padding));
        setBackground(Color.LIGHT_GRAY);
        setPreferredSize(new Dimension(0, 100));
        setMinimumSize(new Dimension(Integer.MAX_VALUE, 200));

        JLabel commentLabel = new JLabel("Comment: " + review.getComment());
        JLabel usernameLabel = new JLabel("Reviewer: " + review.getUser().getUsername());
        JLabel ratingLabel = new JLabel("Rating: " + review.getRating());
        JLabel locationLabel;
        if (review.getLocation() != null) {
            locationLabel = new JLabel("Location: " + review.getLocation().getName());
        } else {
            locationLabel = new JLabel("Location = null");
        }

        commentLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        usernameLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        ratingLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        locationLabel.setFont(new Font("Arial", Font.BOLD, 14));

        add(locationLabel);
        add(ratingLabel);
        add(commentLabel);
        add(usernameLabel);

    }
}