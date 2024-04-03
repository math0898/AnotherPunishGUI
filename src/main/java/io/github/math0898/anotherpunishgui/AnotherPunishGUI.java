package io.github.math0898.anotherpunishgui;

import io.github.math0898.anotherpunishgui.commands.SRootCommand;
import lombok.Getter;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * The main plugin class for the AnotherPunishGUI plugin.
 *
 * @author Sugaku
 */
public final class AnotherPunishGUI extends JavaPlugin {

    /**
     * The active AnotherPunishGUI instance at runtime.
     * -- GETTER --
     *  An accessor method for the active AnotherPunishGUI instance.
     */
    @Getter
    private static AnotherPunishGUI instance;

    /**
     * Called when the plugin is enabled. Used to register listeners, commands, load configuration, etc.
     */
    @Override
    public void onEnable () {
        instance = this;
        new SRootCommand();
        ConfigManager.getInstance();
    }
}
