package de.MCmoderSD.UI;

import de.MCmoderSD.main.Config;

import javax.swing.*;
import java.awt.*;

public class Menu extends JPanel {
    public Menu(Frame frame, Config config) {
        super();
        setLayout(null);
        setBackground(config.getBackgroundColor());
        setForeground(config.getTextColor());

        Dimension size = frame.getField().getSize();

        int fontSize = size.height / 25;
        Font font = new Font("Roboto", Font.PLAIN, fontSize);
        Font titleFont = new Font("Roboto", Font.BOLD, (int) (fontSize * 1.25));

        // Init Title
        JLabel title = new JLabel(config.getTitle());
        title.setFont(new Font("Arial", Font.BOLD, 50));
        title.setFont(titleFont);
        title.setSize(size.width, size.height / 8);
        title.setHorizontalAlignment(JLabel.CENTER);
        add(title);

        // Init Player Label
        JLabel playerLabel = new JLabel(config.getUsername());
        playerLabel.setFont(font);
        playerLabel.setSize(size.width / 2, size.height / 15);
        playerLabel.setLocation((size.width - playerLabel.getWidth()) / 2, size.height / 3);
        add(playerLabel);

        // Init TextFields
        JTextField playerField = new JTextField();
        playerField.setFont(font);
        playerField.setSize(size.width / 2, size.height / 15);
        playerField.setLocation((size.width - playerField.getWidth()) / 2, playerLabel.getY() + playerLabel.getHeight() + 10);
        add(playerField);

        // Init Add Player Button
        JButton addPlayerButton = new JButton(config.getAddPlayer());
        addPlayerButton.setFont(font);
        addPlayerButton.setSize(size.width / 2, size.height / 15);
        addPlayerButton.setLocation((size.width - addPlayerButton.getWidth()) / 2, playerField.getY() + playerField.getHeight() + 10);
        addPlayerButton.addActionListener(e -> frame.getController().addPlayer(playerField.getText()));
        add(addPlayerButton);

        // Init Buttons
        JButton startButton = new JButton(config.getStart());
        startButton.setFont(font);
        startButton.setSize(size.width / 2, size.height / 15);
        startButton.setLocation((size.width - startButton.getWidth()) / 2, addPlayerButton.getY() + addPlayerButton.getHeight() + 10);
        startButton.addActionListener(e -> frame.getController().startGame());
        add(startButton);

        setPreferredSize(size);
        frame.add(this, BorderLayout.CENTER);
    }
}