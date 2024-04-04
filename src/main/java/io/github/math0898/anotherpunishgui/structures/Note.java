package io.github.math0898.anotherpunishgui.structures;

import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;

/**
 * Notes are issued by staff about particular players.
 *
 * @author Sugaku
 */
public record Note (String staff, Player target, String data) implements YamlSavable {

    /**
     * Saves this object to the given configuration section.
     *
     * @param section The section to save this item at.
     */
    @Override
    public void save (ConfigurationSection section) {
        // todo: Implement.
    }
}
