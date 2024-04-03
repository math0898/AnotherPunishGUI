package io.github.math0898.anotherpunishgui.punisher;

import io.github.math0898.anotherpunishgui.ConfigManager;
import org.bukkit.Bukkit;

/**
 * The PunisherProvider provides a valid punisher to the rest of the plugin depending on libraries.
 *
 * @author Sugaku
 */
public class PunisherProvider {

    /**
     * The active PunisherProvider instance.
     */
    private static PunisherProvider instance = null;

    /**
     * The punisher to use during runtime.
     */
    private final Punisher punisher;

    /**
     * Creates the PunisherProvider and determines which punisher to utilize.
     */
    private PunisherProvider () {
        ConfigManager manager = ConfigManager.getInstance();
        if (manager.isUseLiteBans() && Bukkit.getPluginManager().isPluginEnabled("LightAntiCheat"))
            punisher = new LightAntiCheatPunisher();
        else punisher = new StandalonePunisher();
    }

    /**
     * Static accessor to the PunisherProvider.
     *
     * @return The active PunisherProvider.
     */
    public static PunisherProvider getInstance () {
        if (instance == null) instance = new PunisherProvider();
        return instance;
    }

    /**
     * An accessor to the active Punisher.
     *
     * @return The active Punisher.
     */
    public Punisher getPunisher () {
        return punisher;
    }
}
