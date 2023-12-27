package de.MCmoderSD.core;

import de.MCmoderSD.UI.Frame;
import de.MCmoderSD.data.Board;
import de.MCmoderSD.main.Config;
import de.MCmoderSD.objects.Player;

import java.awt.*;
import java.util.ArrayList;
import java.util.Objects;

public class Controller {

    // Associations
    private final Frame frame;
    private final Board board;
    private final Config config;

    // Attributes
    private final ArrayList<Player> players;

    // Variables
    private int currentPlayer;
    private Point firstCard;
    private Point secondCard;

    public Controller(Frame frame, Board board, Config config) {
        this.frame = frame;
        this.board = board;
        this.config = config;
        frame.getField().setVisible(false);
        players = new ArrayList<>();
    }

    public void flipCard(Point card) {
        if (firstCard == null) {
            firstCard = card;
            frame.getField().setCard(card, board.getCard(card));
        } else if (Objects.equals(card.toString(), firstCard.toString())) frame.showMessage("You can't flip the same card twice!", "Error");
        else if (secondCard == null) {
            secondCard = card;
            frame.getField().setCard(card, board.getCard(card));
            checkForMatch();
        }
    }

    private void checkForMatch() {
            if (board.getCard(firstCard).equals(board.getCard(secondCard))) {
                firstCard = null;
                secondCard = null;
                Player player = players.get(currentPlayer);
                player.addPoint();
                frame.getInfoPanel().append(player.getName() + config.getFoundPair() + config.getHasNowPoints() + player.getPoints());
                if (currentPlayer + 1 == players.size()) currentPlayer = 0;
                else currentPlayer++;
                frame.getInfoPanel().append(players.get(currentPlayer).getName() + config.getNowOnTurn());
            } else {
                new Thread(() -> {
                    try {
                        Thread.sleep(config.getWaitTime());
                    } catch (InterruptedException e) {
                        System.err.println(e.getMessage());
                    }

                    frame.getField().setCard(firstCard, config.getBackside());
                    frame.getField().setCard(secondCard, config.getBackside());
                    firstCard = null;
                    secondCard = null;

                    if (currentPlayer + 1 == players.size()) currentPlayer = 0;
                    else currentPlayer++;

                    frame.getInfoPanel().append(players.get(currentPlayer).getName() + config.getNowOnTurn());
                }).start();
            }
    }

    public void startGame() {
        if (players.isEmpty()) frame.showMessage("Please add at least one player!", "Error");
        else {
            frame.getMenu().setVisible(false);
            frame.getField().setVisible(true);
            currentPlayer = 0;
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
