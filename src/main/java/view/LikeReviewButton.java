package view;

import interface_adapters.like_review.LikeReviewController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class LikeReviewButton extends JButton implements ActionListener {
    private PropertyChangeSupport support;
    private final LikeReviewController controller;
    private final String username;
    private final String reviewId;
    private int likeCount;
    private final ImageIcon likeIcon;
    private final ImageIcon likedIcon;
    private boolean isLiked;

    public LikeReviewButton(LikeReviewController controller, String username, String reviewId,
                            int initialLikeCount, boolean initialLikedState) {
        // Call super() first to ensure proper JButton initialization
        super();

        // Initialize PropertyChangeSupport after super()
        this.support = new PropertyChangeSupport(this);

        // Initialize other fields
        this.controller = controller;
        this.username = username;
        this.reviewId = reviewId;
        this.likeCount = initialLikeCount;
        this.isLiked = initialLikedState;

        // Load the original icons
        ImageIcon originalLikeIcon = new ImageIcon("src/main/resources/images/youtube-like-button-png-11.png");
        ImageIcon originalLikedIcon = new ImageIcon("src/main/resources/images/blue-like-button-icon.png");

        // Scale them to the correct size
        Image scaledLikeImage = originalLikeIcon.getImage().getScaledInstance(24, 24, Image.SCALE_SMOOTH);
        Image scaledLikedImage = originalLikedIcon.getImage().getScaledInstance(24, 24, Image.SCALE_SMOOTH);

        this.likeIcon = new ImageIcon(scaledLikeImage);
        this.likedIcon = new ImageIcon(scaledLikedImage);

        // Setup button appearance and behavior
        setupButton();
    }

    private void setupButton() {
        updateButtonAppearance();
        setContentAreaFilled(false);
        setBorderPainted(false);
        setFocusPainted(false);

        addActionListener(evt -> {
            controller.execute(username, reviewId, success -> {
                if (success) {
                    isLiked = !isLiked;
                    int oldLikeCount = likeCount;
                    likeCount += isLiked ? 1 : -1;
                    updateButtonAppearance();
                    // Fire property change event
                    if (support != null) {
                        support.firePropertyChange("likeUpdate", oldLikeCount, likeCount);
                    }
                    System.out.println("Database updated successfully. New state: liked=" + isLiked + ", count=" + likeCount);
                } else {
                    System.out.println("Database update failed.");
                }
            });
        });
    }

    @Override
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        if (support != null) {
            support.addPropertyChangeListener(listener);
        }
    }

    @Override
    public void removePropertyChangeListener(PropertyChangeListener listener) {
        if (support != null) {
            support.removePropertyChangeListener(listener);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // Implementation moved to setupButton()
    }

    private void updateButtonAppearance() {
        setIcon(isLiked ? likedIcon : likeIcon);
        setText(String.valueOf(likeCount));
        revalidate();
        repaint();
        System.out.println("Updating appearance - Icon: " + (isLiked ? "blue" : "black") + ", Count: " + likeCount);
    }
}