package view;

import entity.UserReview;
import interface_adapters.list_review.ListReviewController;
import interface_adapters.list_review.ListReviewState;
import interface_adapters.list_review.ListReviewViewModel;
import interface_adapters.signup.SignupViewModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.List;

public class ListReviewView extends JPanel implements ActionListener, PropertyChangeListener {
    private final String viewName = "review list";

    private final ListReviewViewModel listReviewViewModel;

    private ListReviewController listReviewController;

    public ListReviewView(ListReviewViewModel listReviewViewModel) {
        this.listReviewViewModel = listReviewViewModel;
        listReviewViewModel.addPropertyChangeListener(this);

        final JLabel title = new JLabel(ListReviewViewModel.TITLE_LABEL);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        final ListReviewState state = listReviewViewModel.getState();

        final JPanel reviewsPanel = new JPanel(new BorderLayout());
        List<UserReview> reviewList = state.getReviewList();

        for (UserReview review : reviewList) {
            reviewsPanel.add(new UserReviewPanel(review), BorderLayout.CENTER);
        }

        final JScrollPane scrollPanel = new JScrollPane(reviewsPanel);
        scrollPanel.setPreferredSize(new Dimension(500, 600));

        final JLabel pageLabel = new JLabel(ListReviewViewModel.PAGE_SIZE_LABEL + ": " + state.getPageSize());
        pageLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        this.setLayout(new BorderLayout());

        this.add(title, BorderLayout.NORTH);
        this.add(scrollPanel, BorderLayout.CENTER);
        this.add(pageLabel, BorderLayout.SOUTH);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {

    }

    public String getViewName() {
        return viewName;
    }
}
