package view;

import entity.User;
import interface_adapters.create_reply.CreateReplyController;
import interface_adapters.create_reply.CreateReplyState;
import interface_adapters.create_reply.CreateReplyViewModel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CreateReplyPanel extends JPanel implements ActionListener {
    private final User user;

    private final JTextArea replyTextArea;
    private final JButton submitReplyButton;

    private final CreateReplyViewModel viewModel;
    private final CreateReplyController controller;

    public CreateReplyPanel(CreateReplyViewModel newViewModel, CreateReplyController newController, User user) {
        this.user = user;

        replyTextArea = new JTextArea();
        submitReplyButton = new JButton("Submit");

        viewModel = newViewModel;
        controller = newController;

        submitReplyButton.addActionListener(this);

        add(replyTextArea);
        add(submitReplyButton);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        CreateReplyState currState = viewModel.getState();
        currState.setComment(replyTextArea.getText());
        currState.setUser(user);
        controller.execute(user, replyTextArea.getText());
    }
}
