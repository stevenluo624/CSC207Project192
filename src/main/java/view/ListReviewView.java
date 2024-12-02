package view;

import data_access.DBLikeAccessObject;
import entity.UserReview;
import interface_adapters.ViewManagerModel;
import interface_adapters.like_review.LikeReviewController;
import interface_adapters.like_review.LikeReviewPresenter;
import interface_adapters.list_review.ListReviewController;
import interface_adapters.list_review.ListReviewState;
import interface_adapters.list_review.ListReviewViewModel;
import use_case.like_review.LikeReviewInteractor;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.List;

/**
 * The view showing a list of reviews
 */
public class ListReviewView extends JPanel implements ActionListener, PropertyChangeListener {
    private final String viewName = "review list";

    private final ListReviewViewModel listReviewViewModel;

    private ListReviewController listReviewController;
    private LikeReviewController likeReviewController;

    private JScrollPane scrollPanel;

    public ListReviewView(ListReviewViewModel listReviewViewModel) {
        this.listReviewViewModel = listReviewViewModel;
        this.listReviewViewModel.addPropertyChangeListener(this);

        final JLabel title = new JLabel(ListReviewViewModel.TITLE_LABEL);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        final ListReviewState state = listReviewViewModel.getState();

        LikeReviewInteractor likeReviewInteractor = new LikeReviewInteractor(
                new DBLikeAccessObject(),
                new LikeReviewPresenter(new ViewManagerModel(), listReviewViewModel));
        this.likeReviewController = new LikeReviewController(likeReviewInteractor);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weighty = 1.0;

        try {
            final JPanel reviewsPanel = new JPanel();
            reviewsPanel.setLayout(new BoxLayout(reviewsPanel, BoxLayout.Y_AXIS));
            List<UserReview> reviewList = state.getReviewList();

            for (UserReview review : reviewList) {
                final JPanel buttonsPanel = new JPanel();
                buttonsPanel.setLayout(new BoxLayout(buttonsPanel, BoxLayout.Y_AXIS));

                final JPanel bigPanel = new JPanel();
                bigPanel.setLayout(new GridBagLayout());

                final JButton likeButton = new LikeReviewButton(
                        this.likeReviewController,
                        state.getCurrentUser(),
                        review.getKey(),
                        review.getNumberOfLikes()
                );

                final JButton mapButton = new JButton(ListReviewViewModel.MAP_BUTTON_LABEL);
                mapButton.addActionListener(
                        new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent evt) {
                                listReviewController.switchToMapView(review);
                            }
                        }
                );

                gbc.gridx = 0;
                gbc.weightx = 0.7;
                bigPanel.add(new UserReviewPanel(review), gbc);

                buttonsPanel.add(likeButton);
                buttonsPanel.add(mapButton);

                gbc.gridx = 1;
                gbc.weightx = 0.3;
                bigPanel.add(buttonsPanel, gbc);

                reviewsPanel.add(bigPanel);
            }

            scrollPanel = new JScrollPane(reviewsPanel);
            scrollPanel.setPreferredSize(new Dimension(500, 600));
            JScrollBar scrollBar = scrollPanel.getVerticalScrollBar();
            scrollBar.setUnitIncrement(20);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        final JPanel menuPanel = new JPanel();
        menuPanel.setLayout(new GridBagLayout());
        gbc.gridx = 0;
        gbc.weightx = 0.6;
        menuPanel.add(title, gbc);

        final JButton addReviewButton = new JButton(ListReviewViewModel.ADD_REVIEW_BUTTON_LABEL);
        addReviewButton.addActionListener(
                evt -> {
                    if (evt.getSource().equals(addReviewButton)) {
                        listReviewController.switchToCreateReviewView(listReviewViewModel.getState().getCurrentUserObject());
                    }
                }
        );
        gbc.gridx = 1;
        gbc.weightx = 0.2;
        menuPanel.add(addReviewButton, gbc);

        final JButton profileButton = new JButton(ListReviewViewModel.PROFILE_BUTTON_LABEL);
        profileButton.addActionListener( evt -> {
                    if (evt.getSource().equals(profileButton)) {
                        listReviewController.switchToProfileView(listReviewViewModel.getState().getCurrentUser());
                    }
                }
        );

        gbc.gridx = 2;
        gbc.weightx = 0.2;
        menuPanel.add(profileButton, gbc);

        final JLabel pageLabel = new JLabel(
                ListReviewViewModel.PAGE_SIZE_LABEL + ": " + state.getPageSize(),
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

        this.add(menuPanel, BorderLayout.NORTH);
        this.add(scrollPanel, BorderLayout.CENTER);
        this.add(pageNavigation, BorderLayout.SOUTH);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
//        System.out.println(e);
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
//        System.out.println("Property change: " + evt);
        updateReviews();
        this.revalidate();
        this.repaint();
    }

    private void updateReviews() {
        final ListReviewState state = listReviewViewModel.getState();

        JScrollPane newScrollPanel;
        try {
            final JPanel reviewsPanel = new JPanel();
            reviewsPanel.setLayout(new BoxLayout(reviewsPanel, BoxLayout.Y_AXIS));
            List<UserReview> reviewList = state.getReviewList();

            GridBagConstraints gbc = new GridBagConstraints();
            gbc.fill = GridBagConstraints.BOTH;
            gbc.weighty = 1.0;

            for (UserReview review : reviewList) {
                final JPanel buttonsPanel = new JPanel();
                buttonsPanel.setLayout(new BoxLayout(buttonsPanel, BoxLayout.Y_AXIS));

                final JPanel bigPanel = new JPanel();
                bigPanel.setLayout(new GridBagLayout());

                final JButton likeButton = new LikeReviewButton(
                        this.likeReviewController,
                        state.getCurrentUser(),
                        review.getKey(),
                        review.getNumberOfLikes()
                );

                final JButton mapButton = new JButton(ListReviewViewModel.MAP_BUTTON_LABEL);
                mapButton.addActionListener(
                        new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent evt) {
                                listReviewController.switchToMapView(review);
                            }
                        }
                );
                // addMapAction(mapButton, review);

                gbc.gridx = 0;
                gbc.weightx = 0.7;
                bigPanel.add(new UserReviewPanel(review), gbc);

                buttonsPanel.add(likeButton);
                buttonsPanel.add(mapButton);

                gbc.gridx = 1;
                gbc.weightx = 0.3;
                bigPanel.add(buttonsPanel, gbc);

                reviewsPanel.add(bigPanel);
            }

            newScrollPanel = new JScrollPane(reviewsPanel);
            newScrollPanel.setPreferredSize(new Dimension(500, 600));
            JScrollBar scrollBar = newScrollPanel.getVerticalScrollBar();
            scrollBar.setUnitIncrement(20);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        this.remove(scrollPanel);
        this.add(newScrollPanel, BorderLayout.CENTER);

        scrollPanel = newScrollPanel;
    }

    private void addMapAction(JButton button, UserReview review) {
        button.addActionListener(evt -> {
            if (evt.getSource().equals(button)) {
                listReviewController.switchToMapView(review);

            }
        });
    }

    public String getViewName() {
        return viewName;
    }

    public void setListReviewController(ListReviewController listReviewController) {
        this.listReviewController = listReviewController;
    }
}
