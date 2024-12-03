package view;

import data_access.DBLikeAccessObject;
import entity.reviews_thread.Review;
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
    private DBLikeAccessObject likeAccessObject; // Add this to interact with likes in DB

    private JScrollPane scrollPanel;


    public ListReviewView(ListReviewViewModel listReviewViewModel) {
        this.listReviewViewModel = listReviewViewModel;
        this.listReviewViewModel.addPropertyChangeListener(this);

        final JLabel title = new JLabel(ListReviewViewModel.TITLE_LABEL);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        final ListReviewState state = listReviewViewModel.getState();

        // Initialize the DBLikeAccessObject
        this.likeAccessObject = new DBLikeAccessObject();

        LikeReviewInteractor likeReviewInteractor = new LikeReviewInteractor(
                this.likeAccessObject,
                new LikeReviewPresenter(new ViewManagerModel(), listReviewViewModel));
        this.likeReviewController = new LikeReviewController(likeReviewInteractor);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weighty = 1.0;

        try {
            final JPanel reviewsPanel = new JPanel();
            reviewsPanel.setLayout(new BoxLayout(reviewsPanel, BoxLayout.Y_AXIS));
            List<Review> reviewList = state.getReviewList();

            for (Review review : reviewList) {
                final JPanel buttonsPanel = new JPanel();
                buttonsPanel.setLayout(new BoxLayout(buttonsPanel, BoxLayout.Y_AXIS));

                final JPanel bigPanel = new JPanel();
                bigPanel.setLayout(new GridBagLayout());

                // Check if the current user has liked this review using DBLikeAccessObject
                final boolean hasUserLiked = this.likeAccessObject.hasUserLikedReview(state.getCurrentUser(), review.getId());

                // Create the LikeReviewButton, passing the `hasUserLiked` value
                final JButton likeButton = new LikeReviewButton(
                        this.likeReviewController,
                        state.getCurrentUser(),
                        "review" + String.valueOf(review.getId()),
                        review.getNumberOfLikes(),
                        hasUserLiked // Pass the boolean value here
                );

                final JButton mapButton = new JButton(ListReviewViewModel.MAP_BUTTON_LABEL);
                mapButton.addActionListener(
                        new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent evt) {
                                state.setRefreshed(false);
                                listReviewController.switchToMapView(review);
                            }
                        }
                );

                final JButton replyButton = new JButton(ListReviewViewModel.REPLY_BUTTON_LABEL);
                replyButton.addActionListener(
                        evt -> {
                            if (evt.getSource().equals(replyButton)) {
                                listReviewController.switchToReplyView(listReviewViewModel.getState()
                                        .getCurrentUserObject());
                            }
                        }
                );

                gbc.gridx = 0;
                gbc.weightx = 0.7;
                bigPanel.add(new UserReviewPanel(review), gbc);

                buttonsPanel.add(likeButton);
                buttonsPanel.add(mapButton);
                buttonsPanel.add(replyButton);

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
                        state.setRefreshed(false);
                        listReviewController.switchToCreateReviewView(listReviewViewModel.getState()
                                .getCurrentUserObject());
                    }
                }
        );
        gbc.gridx = 1;
        gbc.weightx = 0.2;
        menuPanel.add(addReviewButton, gbc);

        final JButton profileButton = new JButton(ListReviewViewModel.PROFILE_BUTTON_LABEL);
        profileButton.addActionListener( evt -> {
                    if (evt.getSource().equals(profileButton)) {
                        state.setRefreshed(false);
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

                        currentState.setRefreshed(false);
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

                        currentState.setRefreshed(false);
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
        if (!state.isRefreshed()) {
            state.setRefreshed(true);
            System.out.println("refreshed");
            listReviewController.execute(state.getPageNumber(), state.getPageSize());
        } else {
            System.out.println("already refreshed");
            state.setRefreshed(false);
        }

        JScrollPane newScrollPanel;
        try {
            final JPanel reviewsPanel = new JPanel();
            reviewsPanel.setLayout(new BoxLayout(reviewsPanel, BoxLayout.Y_AXIS));
            List<Review> reviewList = state.getReviewList();

            GridBagConstraints gbc = new GridBagConstraints();
            gbc.fill = GridBagConstraints.BOTH;
            gbc.weighty = 1.0;

            for (Review review : reviewList) {
                final JPanel buttonsPanel = new JPanel();
                buttonsPanel.setLayout(new BoxLayout(buttonsPanel, BoxLayout.Y_AXIS));

                final JPanel bigPanel = new JPanel();
                bigPanel.setLayout(new GridBagLayout());

                // Check if the current user has liked this review using DBLikeAccessObject
                final boolean hasUserLiked = this.likeAccessObject.hasUserLikedReview(state.getCurrentUser(), review.getId());

                // Pass the `hasUserLiked` flag to the LikeReviewButton constructor
                final JButton likeButton = new LikeReviewButton(
                        this.likeReviewController,
                        state.getCurrentUser(),
                        "review" + String.valueOf(review.getId()),
                        review.getNumberOfLikes(),
                        hasUserLiked
                );

                final JButton mapButton = new JButton(ListReviewViewModel.MAP_BUTTON_LABEL);
                mapButton.addActionListener(
                        new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent evt) {
                                state.setRefreshed(false);
                                listReviewController.switchToMapView(review);
                            }
                        }
                );
                // addMapAction(mapButton, review);

                final JButton replyButton = new JButton(ListReviewViewModel.REPLY_BUTTON_LABEL);
                replyButton.addActionListener(
                        new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent evt) {
                                listReviewController.switchToReplyView(state.getCurrentUserObject());
                            }
                        }
                );

                gbc.gridx = 0;
                gbc.weightx = 0.7;
                bigPanel.add(new UserReviewPanel(review), gbc);

                buttonsPanel.add(likeButton);
                buttonsPanel.add(mapButton);
                buttonsPanel.add(replyButton);

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


    private void addMapAction(JButton button, Review review) {
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
