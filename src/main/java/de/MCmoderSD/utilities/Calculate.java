package de.MCmoderSD.utilities;

import de.MCmoderSD.main.Main;

import javax.swing.*;
import java.awt.*;
import java.io.InputStream;

public class Calculate {

    // Center JFrame
    public static Point centerOfJFrame(JFrame frame, boolean smallScreenMode) {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize(); // Screen Size

        int x;
        int y;

        if (smallScreenMode) { // ToDo better implementation
            x = (screenSize.width - frame.getWidth()) / 2;
            y = 0;
        } else {
            x = (screenSize.width - frame.getWidth()) / 2;
            y = (screenSize.height - frame.getHeight()) / 2;
        }

        return new Point(x, y);
    }

    // File Checker
    public static boolean doesFileExist(String resourcePath) {
        InputStream inputStream = Main.class.getResourceAsStream(resourcePath);
        return inputStream != null;
    }

    // Calculates a random Chance
    public static boolean randomChance(float percentage) {
        return Math.random() < percentage;
    }
}