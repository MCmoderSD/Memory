package de.MCmoderSD.core;

import de.MCmoderSD.UI.Board;
import de.MCmoderSD.UI.Frame;
import de.MCmoderSD.UI.UI;
import de.MCmoderSD.main.Config;

import javax.swing.*;
import java.util.HashMap;

public class Controller {

    // Associations
    private final Frame frame;
    private final UI ui;
    private final Config config;
    private final Board board;

    // Attributes
    private final HashMap<JButton, ImageIcon> buttonImageMap;

    // Variables
    private JButton firstButton;
    private JButton secondButton;

    public Controller(Frame frame, UI ui, Config config) {
        this.frame = frame;
        this.ui = ui;
        this.config = config;


        board = ui.getBoard();
        buttonImageMap = board.getButtonImageMap();
    }

    public void flipCard(JButton button) {
        if (firstButton == null) {
            firstButton = button;
            board.showButton(button);
        } else if (button == firstButton) frame.showMessage("You can't flip the same card twice!", "Error");
        else if (secondButton == null) {
            secondButton = button;
            board.showButton(button);
            checkForMatch();
        }
    }

    private void checkForMatch() {
        if (firstButton != null && secondButton != null) {
            if (buttonImageMap.get(firstButton).equals(buttonImageMap.get(secondButton))) {
                firstButton = null;
                secondButton = null;
            } else {
                new Thread(() -> {
                    try {
                        Thread.sleep(config.getWaitTime());
                    } catch (InterruptedException e) {
                        System.err.println(e.getMessage());
                    }
                    board.hideButton(firstButton);
                    board.hideButton(secondButton);
                    firstButton = null;
                    secondButton = null;
                }).start();
            }
        }
    }
}
