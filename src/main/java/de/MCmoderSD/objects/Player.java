package de.MCmoderSD.objects;

@SuppressWarnings("unused")
public class Player {

    // Attributes
    private final String name;
    private int points;
    private int mistakes;

    public Player(String name) {
        this.name = name;
        points = 0;
        mistakes = 0;
    }

    // Setter
    public void addPoint() {
        points++;
    }

    public void addMistake() {
        mistakes++;
    }

    // Getter
    public String getName() {
        return name;
    }

    public int getPoints() {
        return points;
    }

    public int getMistakes() {
        return mistakes;
    }

    public int getTries() {
        return points + mistakes;
    }

    public double getAccuracy() {
        return (double) points / (points + mistakes);
    }
}
