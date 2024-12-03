package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import interface_adapters.profile.ProfileController;
import interface_adapters.profile.ProfileState;
import interface_adapters.profile.ProfileViewModel;

/**
 * The View for when the user is using profile into the program.
 */
public class ProfileView extends JPanel implements ActionListener, PropertyChangeListener {

    private final String viewName;
    private final ProfileViewModel profileViewModel;

    private final JTextField bioInputField = new JTextField(50);
    private final JLabel bioErrorField = new JLabel();

    private final JButton save;
    private final JButton back;
    private ProfileController profileController;

    public ProfileView(ProfileViewModel profileViewModel) {

        this.profileViewModel = profileViewModel;
        this.profileViewModel.addPropertyChangeListener(this);
        viewName = profileViewModel.getViewName();

        final JLabel title = new JLabel("Profile Screen");
        title.setAlignmentX(JComponent.CENTER_ALIGNMENT);

        final ProfileState beforeState = profileViewModel.getState();
        final JTextArea bioTextArea = new JTextArea(3, 20);
        bioTextArea.setText(beforeState.getBio());
        bioTextArea.setEditable(false);
        bioTextArea.setWrapStyleWord(true);
        bioTextArea.setAlignmentX(JComponent.CENTER_ALIGNMENT);


        final LabelTextPanel bioInput = new LabelTextPanel(
                new JLabel("Bio"), bioInputField);

        final JPanel buttons = new JPanel();
        save = new JButton("Save");
        buttons.add(save);
        back = new JButton("Back");
        buttons.add(back);

        save.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        if (evt.getSource().equals(save)) {
                            final ProfileState currentState = profileViewModel.getState();

                            profileController.execute(
                                    currentState.getUsername(),
                                    currentState.getBio()
                            );
                            System.out.println(currentState.getBio());
                            bioTextArea.setText(currentState.getBio());
                        }
                    }

                }
        );

        back.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {profileController.switchToListReviewView();}
                }
        );


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

        this.add(title);
        this.add(bioInput);
        this.add(bioTextArea);
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
        final ProfileState state = (ProfileState) evt.getNewValue();
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
}
