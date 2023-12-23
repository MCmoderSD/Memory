package de.MCmoderSD.data;

import de.MCmoderSD.main.Config;

import javax.swing.*;

public class Board {
    private final ImageIcon[][] board;
    private final ImageIcon backside;

    public Board(Config config) {



        int columns =
        int rows =

        board = new ImageIcon[][];
        backside = config.getBackside();
    }

    public ImageIcon getCard(int row, int column) {
        return board[row][column];
    }


}
