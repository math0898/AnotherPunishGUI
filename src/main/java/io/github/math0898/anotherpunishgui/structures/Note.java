package io.github.math0898.anotherpunishgui.structures;

import io.github.math0898.utils.items.ItemBuilder;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.inventory.ItemStack;

import java.util.UUID;

/**
 * Notes are issued by staff about particular players.
 *
 * @author Sugaku
 */
public record Note (String staff, UUID target, String data) implements YamlSavable {

    /**
     * Creates a note from the data contained in the given configuration section.
     *
     * @param section The configuration section to pull data from.
     */
    public Note (ConfigurationSection section) {
        this(section.getString("staff"),
                UUID.fromString(section.getString("target", "")),
                section.getString("data"));
    }

    /**
     * Saves this object to the given configuration section.
     *
     * @param section The section to save this item at.
     */
    @Override
    public void save (ConfigurationSection section) {
        section.set("staff", staff);
        section.set("uuid", target);
        section.set("data", data);
    }

    /**
     * Gets an ItemStack representation of this note.
     *
     * @return An ItemStack representation of this note.
     */
    public ItemStack getItemStack () {
        ItemBuilder builder = new ItemBuilder(Material.PAPER)
                .setDisplayName(staff)
                .setLore(new String[] {ChatColor.GRAY + "- " + data});
        return builder.build();
    }
}
