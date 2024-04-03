package io.github.math0898.anotherpunishgui;

import lombok.Getter;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;

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
     * Whether LightAntiCheat should be used for issuing punishments or not.
     * -- GETTER --
     * Whether to use LightAntiCheat or not if it is present.
     */
    @Getter
    private boolean useLightAntiCheat = true;

    /**
     * Creates a new ConfigManager.
     */
    private ConfigManager () {
        AnotherPunishGUI plugin = AnotherPunishGUI.getInstance();
        plugin.saveDefaultConfig();
        FileConfiguration config = YamlConfiguration.loadConfiguration(new File("./plugins/AnotherPunishGUI/config.yaml"));
        useLightAntiCheat = config.getBoolean("light-anti-cheat", true);
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
