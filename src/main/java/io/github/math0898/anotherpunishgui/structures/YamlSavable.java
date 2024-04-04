package io.github.math0898.anotherpunishgui.structures;

import org.bukkit.configuration.ConfigurationSection;

/**
 * The YamlSavable interface is used to uniformly save notes, reports, and punishments to local saved files.
 *
 * @author Sugaku
 */
public interface YamlSavable {

    /**
     * Saves this object to the given configuration section.
     *
     * @param section The section to save this item at.
     */
    void save (ConfigurationSection section);
}
