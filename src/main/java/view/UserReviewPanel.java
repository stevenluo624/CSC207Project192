package view;

import entity.UserReview;

import javax.swing.*;
import java.awt.*;

/**
 * A panel for displaying one review in the review list.
 */
public class UserReviewPanel extends JPanel {
    public UserReviewPanel(UserReview review) {
        // Set the layout for the panel
        setLayout(new GridLayout(4, 1, 10, 10)); // 4 rows, 1 column, spacing of 10px
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Padding

        // Initialize the labels with review data
        JLabel commentLabel = new JLabel("Comment: " + review.getComment());
        JLabel usernameLabel = new JLabel("Reviewer: " + review.getUser().getUsername());
        JLabel ratingLabel = new JLabel("Rating: " + review.getRating());
        JLabel locationLabel;
        if (review.getLocation() != null) {
            locationLabel = new JLabel("Location: " + review.getLocation().getName());
        } else {
            locationLabel = new JLabel("Location = null");
        }

        // Set font styles if desired
        commentLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        usernameLabel.setFont(new Font("Arial", Font.BOLD, 14));
        ratingLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        locationLabel.setFont(new Font("Arial", Font.PLAIN, 14));

        // Add the labels to the panel
        add(commentLabel);
        add(usernameLabel);
        add(ratingLabel);
        add(locationLabel);
    }
}