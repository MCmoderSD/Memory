package de.MCmoderSD.core;

import de.MCmoderSD.UI.Field;
import de.MCmoderSD.UI.Frame;
import de.MCmoderSD.main.Config;
import de.MCmoderSD.objects.Player;

import javax.swing.*;
import java.util.ArrayList;
import java.util.HashMap;

public class Controller {

    // Associations
    private final Frame frame;
    private final Config config;
    private final Field field;

    // Attributes
    private final HashMap<JButton, ImageIcon> buttonImageMap;
    private final ArrayList<Player> players;

    // Variables
    private JButton firstButton;
    private JButton secondButton;

    public Controller(Frame frame, Field field, Config config) {
        this.frame = frame;
        this.field = field;
        this.config = config;

        players = new ArrayList<>();

        buttonImageMap = field.getButtonImageMap();
    }

    public void flipCard(JButton button) {
        if (firstButton == null) {
            firstButton = button;
            field.showButton(button);
        } else if (button == firstButton) frame.showMessage("You can't flip the same card twice!", "Error");
        else if (secondButton == null) {
            secondButton = button;
            field.showButton(button);
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
                    field.hideButton(firstButton);
                    field.hideButton(secondButton);
                    firstButton = null;
                    secondButton = null;
                }).start();
            }
        }
    }

    public void startGame() {
        if (players.isEmpty()) frame.showMessage("Please add at least one player!", "Error");
        else {
            frame.getMenu().setVisible(false);
            frame.getField().setVisible(true);
        }
    }

    public void addPlayer(String playerName) {

        // Remove spaces
        while (playerName.startsWith(" ")) playerName = playerName.substring(1);
        while (playerName.endsWith(" ")) playerName = playerName.substring(0, playerName.length() - 1);


        // Check if player name is valid
        if (playerName.isEmpty()) frame.showMessage("Please enter a name!", "Error");
        else if (playerName.length() < 3) frame.showMessage("Please enter a longer name!", "Error");
        else if (playerName.length() > 32) frame.showMessage("Please enter a shorter name!", "Error");
        else {
            for (Player player : players)
                if (player.getName().equals(playerName)) {
                    frame.showMessage("This player already exists!", "Error");
                    return;
                }

            players.add(new Player(playerName));
            frame.getInfoPanel().append(config.getPlayerAdded() + playerName);
        }
    }
}
