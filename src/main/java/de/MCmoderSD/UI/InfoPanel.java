package de.MCmoderSD.UI;

import javax.swing.*;
import java.awt.*;

public class InfoPanel extends JPanel {

    // Attributes
    private final JTextArea textArea;

    // Constructor
    public InfoPanel(Frame frame) {
        super();
        setLayout(null);
        setBackground(frame.getBackground());
        setForeground(frame.getForeground());


        Dimension size = frame.getField().getSize();
        int padding = Math.max(size.width, size.height) / 100;
        int fontSize = size.height / 30;

        textArea = new JTextArea();
        textArea.setEditable(false);
        textArea.setFont(new Font("Roboto", Font.PLAIN, fontSize));
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        textArea.setSize(size.width, size.height / 6);
        textArea.setLocation(10, 10);
        textArea.setBorder(null);
        add(textArea);

        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setSize(size.width - 2 * padding, size.height / 6 - 2 * padding);
        scrollPane.setLocation(padding, padding);
        scrollPane.setBorder(null);
        add(scrollPane);

        setPreferredSize(new Dimension(size.width, size.height / 6));
        frame.add(this, BorderLayout.SOUTH);
    }

    public void append(String text) {
        textArea.append(text + "\n");
    }
}
