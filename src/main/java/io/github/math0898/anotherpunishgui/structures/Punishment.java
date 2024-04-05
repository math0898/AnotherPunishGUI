package io.github.math0898.anotherpunishgui.structures;

import org.bukkit.Material;
import org.bukkit.configuration.ConfigurationSection;

import java.util.List;

/**
 * Punishments are specific outcomes picked by staff. They are configurable in config.yml.
 *
 * @author Sugaku
 */
public record Punishment (String internalName, String displayName, String type /* BAN/MUTE */, Material item, List<Integer> durations) {

    /**
     * Creates a new Punishment based off the given configuration section. Assumed to not be null.
     *
     * @param section The configuration section to define this punishment on.
     */
    public Punishment (ConfigurationSection section) {
        this(section.getString("internal-name"),
                section.getString("display-name"),
                section.getString("type"),
                Material.valueOf(section.getString("material")),
                section.getIntegerList("durations"));
        // todo: implement.
    }
}
