package de.MCmoderSD.data;

import de.MCmoderSD.main.Config;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Board {
    private final int columns;
    private final int rows;
    private final ImageIcon[][] board;

    public Board(Config config) {

        int possibleRows = 1;

        ImageIcon[] images = config.getPairs();
        ArrayList<ImageIcon> pairs = new ArrayList<>();

        for (int i = 0; 0.5 * i < images.length; i++) pairs.add(images[(int) (i * 0.5)]);
        while (pairs.size() % possibleRows == 0) possibleRows++;

        rows = possibleRows;
        columns = pairs.size() / possibleRows;
        board = new ImageIcon[rows][columns];

        for (int x = 0; x < rows; x++)
            for (int y = 0; y < columns; y++) {
                ImageIcon image = pairs.get((int) (Math.random() * pairs.size()));
                board[x][y] = image;
                pairs.remove(image);
            }
    }

    public ImageIcon getCard(Point card) {
        return board[card.x][card.y];
    }

    public int getColumns() {
        return columns;
    }

    public int getRows() {
        return rows;
    }
}
