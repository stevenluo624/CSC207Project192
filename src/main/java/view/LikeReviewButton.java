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

    public LikeReviewButton(LikeReviewController controller, String username, String reviewId, int initialLikeCount) {
        this.controller = controller;
        this.username = username;
        this.reviewId = reviewId;
        this.likeCount = initialLikeCount;


        ImageIcon originalLikeIcon = new ImageIcon("images/youtube-like-button-png-11.png");
        ImageIcon originalLikedIcon = new ImageIcon("images/blue-like-button-icon.png");

        Image scaledLikeImage = originalLikeIcon.getImage().getScaledInstance(24, 24, Image.SCALE_SMOOTH);
        Image scaledLikedImage = originalLikedIcon.getImage().getScaledInstance(24, 24, Image.SCALE_SMOOTH);

        this.likeIcon = new ImageIcon(scaledLikeImage);
        this.likedIcon = new ImageIcon(scaledLikedImage);
        setIcon(likeIcon);
        setText(String.valueOf(initialLikeCount));
        setContentAreaFilled(false);
        setBorderPainted(false);
        setFocusPainted(false);

        addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        controller.execute(username, reviewId);
    }

    public void updateLikeCount(int newLikeCount, boolean isLiked) {
        this.likeCount = newLikeCount;
        setText("Likes: " + likeCount);
        setIcon(isLiked ? likedIcon : likeIcon);
    }
}
