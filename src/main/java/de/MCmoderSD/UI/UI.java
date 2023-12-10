package de.MCmoderSD.UI;

import de.MCmoderSD.main.Config;

import javax.swing.*;
import java.awt.*;

public class UI extends JPanel {

    // Associations
    private final Frame frame;
    private final Config config;

    // Attributes
    private final Board board;

    public UI(Frame frame, Config config) {
        super();

        this.frame = frame;
        this.config = config;

        board = new Board(this, frame, config);


        setPreferredSize(new Dimension(1000, 1000));
        frame.add(this);
    }

    public Board getBoard() {
        return board;
    }
}
