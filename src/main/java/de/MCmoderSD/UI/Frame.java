package de.MCmoderSD.UI;

import de.MCmoderSD.core.Controller;
import de.MCmoderSD.main.Config;
import de.MCmoderSD.utilities.Calculate;

import javax.swing.*;
import java.awt.*;

public class Frame extends JFrame {

    // Associations
    private final InputHandler inputHandler;
    private final UI ui;
    private final Controller controller;

    public Frame(Config config) {
        super(config.getTitle());
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(config.isResizeable());
        setIconImage(config.getIcon());
        setLayout(new BorderLayout());

        inputHandler = new InputHandler(this);
        ui = new UI(this, config);
        controller = new Controller(this, ui, config);


        pack();
        Calculate.centerOfJFrame(this, false);
        setVisible(true);
    }

    // Getter
    public Controller getController() {
        return controller;
    }

    // Setter
    public void showMessage(String message, String title) {
        new Thread(() -> JOptionPane.showMessageDialog(this, message, title, JOptionPane.INFORMATION_MESSAGE)).start();
    }
}
