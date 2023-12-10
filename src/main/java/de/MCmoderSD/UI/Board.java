package de.MCmoderSD.UI;

import de.MCmoderSD.main.Config;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;

public class Board extends JPanel {

    // Associations
    private final Config config;

    // Attributes
    private final HashMap<JButton, ImageIcon> buttonImageMap;

    public Board(UI ui, Frame frame, Config config) {
        super();

        this.config = config;
        ImageIcon[] pairImages = config.getPairs();

        int totalCards = pairImages.length * 2;


        int columns = (int) Math.sqrt(totalCards);
        int rows = totalCards / columns;


        buttonImageMap = new HashMap<>();
        setLayout(new GridLayout(rows, columns));
        setBorder(new LineBorder(Color.BLACK));

        ArrayList<ImageIcon> pairImageList = new ArrayList<>();
        for (ImageIcon pairImage : pairImages) {
            pairImageList.add(pairImage);
            pairImageList.add(pairImage);
        }

        for (int i = 0; i < rows; i++)
            for (int j = 0; j < columns; j++) {
                JButton button = new JButton();
                ImageIcon image = pairImageList.get((int) (Math.random() * pairImageList.size()));
                button.setSize(config.getScale(), config.getScale());
                button.setIcon(config.getBackside());
                button.setBorder(new LineBorder(Color.BLACK));
                button.addActionListener(e -> frame.getController().flipCard(button));
                buttonImageMap.put(button, image);
                pairImageList.remove(image);
                add(button);
            }

        ui.setPreferredSize(new Dimension(rows * config.getScale(), columns * config.getScale()));
        ui.add(this);
    }

    // Getter
    public HashMap<JButton, ImageIcon> getButtonImageMap() {
        return buttonImageMap;
    }

    // Setter
    public void showButton(JButton button) {
        button.setIcon(buttonImageMap.get(button));
    }

    public void hideButton(JButton button) {
        button.setIcon(config.getBackside());
    }
}
