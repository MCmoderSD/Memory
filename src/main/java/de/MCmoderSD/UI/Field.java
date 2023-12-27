package de.MCmoderSD.UI;

import de.MCmoderSD.data.Board;
import de.MCmoderSD.main.Config;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;

public class Field extends JPanel {

    // Attributes
    private final JButton[][] buttons;

    public Field(Frame frame, Board board, Config config) {
        super();

        int padding = 10;
        buttons = new JButton[board.getRows()][board.getColumns()];
        setLayout(new GridLayout(board.getRows(), board.getColumns()));
        setBorder(new EmptyBorder(padding, padding, padding, padding));
        setBackground(config.getBackgroundColor());
        setForeground(config.getTextColor());

        for (int x = 0; x < board.getRows(); x++)
            for (int y = 0; y < board.getColumns(); y++) {
                int finalX = x;
                int finalY = y;
                buttons[x][y] = new JButton();
                buttons[x][y].setPreferredSize(new Dimension(config.getScale(), config.getScale()));
                buttons[x][y].setIcon(config.getBackside());
                buttons[x][y].setBorder(new LineBorder(Color.BLACK));
                buttons[x][y].addActionListener(e -> frame.getController().flipCard(new Point(finalX, finalY)));
                add(buttons[x][y]);
            }

        setPreferredSize(new Dimension(board.getColumns() * config.getScale() + 2 * padding, board.getRows() * config.getScale() + 2 * padding));
        frame.add(this, BorderLayout.NORTH);
    }

    // Setter
    public void setCard(Point card, ImageIcon image) {
        buttons[card.x][card.y].setIcon(image);
    }
}
