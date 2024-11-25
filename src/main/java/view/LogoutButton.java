package view;

import interface_adapters.logout.LogoutController;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LogoutButton extends JButton implements ActionListener {
    private final LogoutController controller;
    private final String username;

    public LogoutButton(LogoutController controller, String username) {
        super("Logout");
        this.controller = controller;
        this.username = username;
        addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        controller.execute(username);
    }
}
