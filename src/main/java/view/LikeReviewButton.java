package view;

import interface_adapters.like_review.LikeReviewController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LikeReviewButton extends JButton implements ActionListener {
    private final LikeReviewController controller;
    private final String username;
    private final String reviewId;
    private int likeCount;
    private final ImageIcon likeIcon;
    private final ImageIcon likedIcon;
    private boolean isLiked;

    public LikeReviewButton(LikeReviewController controller, String username, String reviewId,
                            int initialLikeCount, boolean initialLikedState) {
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

        updateButtonAppearance();

        setContentAreaFilled(false);
        setBorderPainted(false);
        setFocusPainted(false);

        addActionListener(this);
        System.out.println("Button created with count: " + likeCount + ", liked: " + isLiked);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        controller.execute(username, reviewId, success -> {
            if (success) {
                isLiked = !isLiked;
                likeCount += isLiked ? 1 : -1;
                updateButtonAppearance();
                System.out.println("Database updated successfully. New state: liked=" + isLiked + ", count=" + likeCount);
            } else {
                System.out.println("Database update failed.");
            }
        });
    }

    private void updateButtonAppearance() {
        setIcon(isLiked ? likedIcon : likeIcon);
        setText(String.valueOf(likeCount));
        revalidate();
        repaint();
        System.out.println("Updating appearance - Icon: " + (isLiked ? "blue" : "black") + ", Count: " + likeCount);
    }
}
