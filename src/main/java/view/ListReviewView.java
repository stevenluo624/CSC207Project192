package view;

import entity.UserReview;
import interface_adapters.list_review.ListReviewController;
import interface_adapters.list_review.ListReviewState;
import interface_adapters.list_review.ListReviewViewModel;
import interface_adapters.signup.SignupState;
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

    private JScrollPane scrollPanel;

    public ListReviewView(ListReviewViewModel listReviewViewModel) {
        this.listReviewViewModel = listReviewViewModel;
        listReviewViewModel.addPropertyChangeListener(this);

        final JLabel title = new JLabel(ListReviewViewModel.TITLE_LABEL);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        final ListReviewState state = listReviewViewModel.getState();

        final JPanel reviewsPanel = new JPanel();
        reviewsPanel.setLayout(new BoxLayout(reviewsPanel, BoxLayout.Y_AXIS));
        List<UserReview> reviewList = state.getReviewList();

        for (UserReview review : reviewList) {
            reviewsPanel.add(new UserReviewPanel(review));
        }

        scrollPanel = new JScrollPane(reviewsPanel);
        scrollPanel.setPreferredSize(new Dimension(500, 600));
        JScrollBar scrollBar = scrollPanel.getVerticalScrollBar();
        scrollBar.setUnitIncrement(20);

        final JLabel pageLabel = new JLabel(ListReviewViewModel.PAGE_SIZE_LABEL + ": " + state.getPageSize(),
                SwingConstants.CENTER);

        final JButton prevPageButton = new JButton(ListReviewViewModel.PREVIOUS_PAGE_BUTTON_LABEL);
        final JButton nextPageButton = new JButton(ListReviewViewModel.NEXT_PAGE_BUTTON_LABEL);

        prevPageButton.addActionListener(
                evt -> {
                    if (evt.getSource().equals(prevPageButton)) {
                        final ListReviewState currentState = listReviewViewModel.getState();
                        currentState.prevPage();

                        listReviewController.execute(
                                currentState.getPageNumber(),
                                currentState.getPageSize()
                        );
                    }
                }
        );

        nextPageButton.addActionListener(
                evt -> {
                    if (evt.getSource().equals(nextPageButton)) {
                        final ListReviewState currentState = listReviewViewModel.getState();
                        currentState.nextPage();

                        listReviewController.execute(
                                currentState.getPageNumber(),
                                currentState.getPageSize()
                        );
                    }
                }
        );

        final JPanel pageNavigation = new JPanel(new BorderLayout());
        pageNavigation.add(pageLabel, BorderLayout.CENTER);
        pageNavigation.add(prevPageButton, BorderLayout.WEST);
        pageNavigation.add(nextPageButton, BorderLayout.EAST);

        this.setLayout(new BorderLayout());

        this.add(title, BorderLayout.NORTH);
        this.add(scrollPanel, BorderLayout.CENTER);
        this.add(pageNavigation, BorderLayout.SOUTH);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        updateReviews();
        this.revalidate();
        this.repaint();
    }

    private void updateReviews() {
        final JPanel reviewsPanel = new JPanel();
        reviewsPanel.setLayout(new BoxLayout(reviewsPanel, BoxLayout.Y_AXIS));
        List<UserReview> reviewList = this.listReviewViewModel.getState().getReviewList();

        for (UserReview review : reviewList) {
            reviewsPanel.add(new UserReviewPanel(review));
        }

        JScrollPane newScrollPanel = new JScrollPane(reviewsPanel);
        newScrollPanel.setPreferredSize(new Dimension(500, 600));
        JScrollBar scrollBar = newScrollPanel.getVerticalScrollBar();
        scrollBar.setUnitIncrement(20);

        this.remove(scrollPanel);
        this.add(newScrollPanel, BorderLayout.CENTER);

        scrollPanel = newScrollPanel;
    }

    public String getViewName() {
        return viewName;
    }

    public void setListReviewController(ListReviewController listReviewController) {
        this.listReviewController = listReviewController;
    }
}
