package io.github.math0898.anotherpunishgui;

/**
 * The ConfigManager handles loading and saving the configuration files for AnotherPunishGUI.
 *
 * @author Sugaku
 */
public class ConfigManager {

    /**
     * The active ConfigManager instance.
     */
    private static ConfigManager instance;

    /**
     * Creates a new ConfigManager.
     */
    private ConfigManager () {

    }

    /**
     * A static accessor method to the ConfigManager instance.
     *
     * @return The active ConfigManager instance.
     */
    public static ConfigManager getInstance () {
        if (instance == null) instance = new ConfigManager();
        return instance;
    }
}
