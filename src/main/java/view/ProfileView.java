package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import interface_adapters.logout.LogoutController;
import interface_adapters.profile.ProfileController;
import interface_adapters.profile.ProfileState;
import interface_adapters.profile.ProfileViewModel;

/**
 * The View for when the user is using profile into the program.
 */
public class ProfileView extends JPanel implements ActionListener, PropertyChangeListener {

    private final String viewName = "Profile";
    private final ProfileViewModel profileViewModel;

    private final JTextField bioInputField = new JTextField(50);
    private final JLabel bioErrorField = new JLabel();

    private final JButton save;
    private final JButton cancel;
    private ProfileController profileController;
    private LogoutController logoutController;

    public ProfileView(ProfileViewModel profileViewModel) {

        this.profileViewModel = profileViewModel;
        this.profileViewModel.addPropertyChangeListener(this);

        final JLabel title = new JLabel("Profile Screen");
        title.setAlignmentX(JComponent.CENTER_ALIGNMENT);

        final LabelTextPanel bioInput = new LabelTextPanel(
                new JLabel("Bio"), bioInputField);

        final JPanel buttons = new JPanel();
        save = new JButton("Save");
        buttons.add(save);
        cancel = new JButton("Cancel");
        buttons.add(cancel);

        save.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        if (evt.getSource().equals(save)) {
                            final ProfileState currentState = profileViewModel.getState();

                            profileController.execute(
                                    currentState.getUsername(),
                                    currentState.getProfile()
                            );
                        }
                    }

                }
        );

        cancel.addActionListener(this);

        bioInputField.getDocument().addDocumentListener(new DocumentListener() {
            private void documentListenerHelper() {
                final ProfileState currentState = profileViewModel.getState();
                currentState.setBio(bioInputField.getText());
                profileViewModel.setState(currentState);
            }

            @Override
            public void insertUpdate(DocumentEvent e) {
                documentListenerHelper();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                documentListenerHelper();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                documentListenerHelper();
            }
        });

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.add(title);
        this.add(bioInput);
        this.add(bioErrorField);
        this.add(buttons);

    }

    /**
     * React to a button click that results in evt.
     * @param evt the ActionEvent to react to
     */
    public void actionPerformed(ActionEvent evt) {
        System.out.println("Click" + evt.getActionCommand());
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        final ProfileState state = (ProfileState) evt.getSource();
        setFields(state);
        bioErrorField.setText(state.getProfileError());
    }

    private void setFields(ProfileState state) {
        bioInputField.setText(state.getBio());
    }

    public String getViewName() {
        return viewName;
    }

    public void setProfileController(ProfileController profileController) {
        this.profileController = profileController;
    }

    public void setLogoutController(LogoutController logoutController) {
        this.logoutController = logoutController;
    }
}
