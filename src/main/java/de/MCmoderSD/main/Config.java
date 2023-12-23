package de.MCmoderSD.main;

import de.MCmoderSD.utilities.audio.AudioPlayer;
import de.MCmoderSD.utilities.images.ImageReader;
import de.MCmoderSD.utilities.json.JsonNode;
import de.MCmoderSD.utilities.json.JsonUtility;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

@SuppressWarnings("unused")
public class Config {

    // Associations
    private final AudioPlayer audioPlayer;

    // Constants
    private final String[] args;
    private final int scale;
    private final int waitTime;
    private final boolean isResizeable;


    // Assets
    private final BufferedImage icon;
    private final ImageIcon backside;
    private final ImageIcon[] pairs;
    private final Color backgroundColor;
    private final Color textColor;

    // Language
    private final String language;
    private final String title;
    private final String username;
    private final String start;
    private final String addPlayer;
    private final String playerAdded;
    private final String nowOnTurn;
    private final String foundPair;
    private final String hasNowPoints;
    private final String wonWithPoints;
    private final String points;
    private final String sameCards;
    private final String sameCardsTitle;

    // Constructor
    public Config(String[] args) {
        this.args = args;

        if (args.length > 0) language = args[0];
        else language = "en";

        String configuration;
        if (args.length > 1) configuration = args[1];
        else configuration = "default";

        JsonUtility jsonUtility = new JsonUtility();

        JsonNode config = jsonUtility.load("/config/" + configuration + ".json");

        scale = config.get("scale").asInt();
        waitTime = config.get("waitTime").asInt();
        isResizeable = config.get("resizeable").asBoolean();

        // Assets
        audioPlayer = new AudioPlayer();
        ImageReader imageReader = new ImageReader();
        JsonNode pairSet = jsonUtility.load(config.get("pairSetPath").asText());

        icon = imageReader.read(config.get("iconPath").asText());
        pairs = new ImageIcon[pairSet.getSize()];
        backside = imageReader.createImageIcon(config.get("backsidePath").asText(), scale);

        for (int i = 0; i < pairSet.getSize(); i++)
            pairs[i] = imageReader.createImageIcon(pairSet.get("pair" + i).asText(), scale);

        backgroundColor = config.get("backgroundColor").asColor();
        textColor = config.get("textColor").asColor();

        // Language
        JsonNode languageSet = jsonUtility.load("/languages/" + language + ".json");
        title = languageSet.get("title").asText();
        username = languageSet.get("username").asText();
        start = languageSet.get("start").asText();
        addPlayer = languageSet.get("addPlayer").asText();
        playerAdded = languageSet.get("playerAdded").asText();
        nowOnTurn = languageSet.get("nowOnTurn").asText();
        foundPair = languageSet.get("foundPair").asText();
        hasNowPoints = languageSet.get("hasNowPoints").asText();
        wonWithPoints = languageSet.get("wonWithPoints").asText();
        points = languageSet.get("points").asText();
        sameCards = languageSet.get("sameCards").asText();
        sameCardsTitle = languageSet.get("sameCardsTitle").asText();
    }

    // Constructor with URL
    public Config(String[] args, String url) {
        this.args = args;

        if (args.length > 0) language = args[0];
        else language = "en";

        String configuration;
        if (args.length > 1) configuration = args[1];
        else configuration = "default";

        JsonUtility jsonUtility = new JsonUtility();

        JsonNode config = jsonUtility.load("/config/" + configuration + ".json");

        scale = config.get("scale").asInt();
        waitTime = config.get("waitTime").asInt();
        isResizeable = config.get("resizeable").asBoolean();

        // Assets
        audioPlayer = new AudioPlayer();
        ImageReader imageReader = new ImageReader();
        JsonNode pairSet = jsonUtility.load(config.get("pairSetPath").asText());

        icon = imageReader.read(config.get("iconPath").asText());
        pairs = new ImageIcon[pairSet.getSize()];
        backside = imageReader.createImageIcon(config.get("backsidePath").asText(), scale);

        for (int i = 0; i < pairSet.getSize(); i++)
            pairs[i] = imageReader.createImageIcon(pairSet.get("pair" + i).asText(), scale);

        backgroundColor = config.get("backgroundColor").asColor();
        textColor = config.get("textColor").asColor();

        // Language
        JsonNode languageSet = jsonUtility.load("/languages/" + language + ".json");
        title = languageSet.get("title").asText();
        username = languageSet.get("username").asText();
        start = languageSet.get("start").asText();
        addPlayer = languageSet.get("addPlayer").asText();
        playerAdded = languageSet.get("playerAdded").asText();
        nowOnTurn = languageSet.get("nowOnTurn").asText();
        foundPair = languageSet.get("foundPair").asText();
        hasNowPoints = languageSet.get("hasNowPoints").asText();
        wonWithPoints = languageSet.get("wonWithPoints").asText();
        points = languageSet.get("points").asText();
        sameCards = languageSet.get("sameCards").asText();
        sameCardsTitle = languageSet.get("sameCardsTitle").asText();
    }

    // Getter
    public AudioPlayer getAudioPlayer() {
        return audioPlayer;
    }

    public String[] getArgs() {
        return args;
    }

    public int getScale() {
        return scale;
    }

    public int getWaitTime() {
        return waitTime;
    }

    public boolean isResizeable() {
        return isResizeable;
    }

    public BufferedImage getIcon() {
        return icon;
    }

    public ImageIcon getBackside() {
        return backside;
    }

    public ImageIcon[] getPairs() {
        return pairs;
    }

    public Color getBackgroundColor() {
        return backgroundColor;
    }

    public Color getTextColor() {
        return textColor;
    }

    public String getLanguage() {
        return language;
    }

    public String getTitle() {
        return title;
    }

    public String getUsername() {
        return username;
    }

    public String getStart() {
        return start;
    }

    public String getAddPlayer() {
        return addPlayer;
    }

    public String getPlayerAdded() {
        return playerAdded;
    }

    public String getNowOnTurn() {
        return nowOnTurn;
    }

    public String getFoundPair() {
        return foundPair;
    }

    public String getHasNowPoints() {
        return hasNowPoints;
    }

    public String getWonWithPoints() {
        return wonWithPoints;
    }

    public String getSameCards() {
        return sameCards;
    }

    public String getSameCardsTitle() {
        return sameCardsTitle;
    }
}
