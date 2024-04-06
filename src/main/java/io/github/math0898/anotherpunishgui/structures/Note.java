package io.github.math0898.anotherpunishgui.structures;

import io.github.math0898.utils.items.ItemBuilder;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

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
        section.set("staff", staff);
        section.set("uuid:", target.getUniqueId());
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
