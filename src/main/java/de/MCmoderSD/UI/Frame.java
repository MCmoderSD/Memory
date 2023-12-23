package de.MCmoderSD.UI;

import de.MCmoderSD.core.Controller;
import de.MCmoderSD.main.Config;
import de.MCmoderSD.utilities.Calculate;

import javax.swing.*;
import java.awt.*;

public class Frame extends JFrame {

    // Associations
    private final InputHandler inputHandler;
    private final Field field;
    private final InfoPanel infoPanel;
    private final Menu menu;
    private final Controller controller;

    public Frame(Config config) {
        super(config.getTitle());
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(config.isResizeable());
        setIconImage(config.getIcon());
        setLayout(new BorderLayout());
        setBackground(config.getBackgroundColor());
        setForeground(config.getTextColor());

        inputHandler = new InputHandler(this);
        field = new Field(this, config);
        pack();

        field.setVisible(false);
        infoPanel = new InfoPanel(this);
        menu = new Menu(this, config);
        controller = new Controller(this, field, config);

        pack();
        setLocation(Calculate.centerOfJFrame(this, false));
        setVisible(true);
    }

    // Getter
    public Controller getController() {
        return controller;
    }

    public InfoPanel getInfoPanel() {
        return infoPanel;
    }

    public Field getField() {
        return field;
    }

    public Menu getMenu() {
        return menu;
    }

    // Setter
    public void showMessage(String message, String title) {
        new Thread(() -> JOptionPane.showMessageDialog(this, message, title, JOptionPane.INFORMATION_MESSAGE)).start();
    }
}
