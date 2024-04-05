package io.github.math0898.anotherpunishgui.structures;

import io.github.math0898.utils.items.ItemBuilder;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.inventory.ItemStack;

import java.text.SimpleDateFormat;
import java.util.UUID;

/**
 * A log is an entry in the database about a particular ban.
 *
 * @author Sugaku
 */
public record Log (String staff, UUID user, String punishment, long creation, long duration) implements YamlSavable {

    /**
     * Saves this object to the given configuration section.
     *
     * @param section The section to save this item at.
     */
    @Override
    public void save (ConfigurationSection section) {
        // todo: Implement!
    }

    /**
     * Gets an ItemStack representation of this report.
     *
     * @return An ItemStack representation of this report.
     */
    public ItemStack getItemStack () {
        SimpleDateFormat df = new SimpleDateFormat("d/M/y - H:m:s");
        ItemBuilder builder = new ItemBuilder(Material.PLAYER_HEAD)
                .setOwningPlayer(user)
                .setDisplayName(Bukkit.getOfflinePlayer(user).getName())
                .setLore(new String[] {
                        ChatColor.GRAY + "Banning Staff: " + staff,
                        ChatColor.GRAY + "Date: " + df.format(creation),
                        ChatColor.GRAY + "Reason: " + ChatColor.RED + punishment,
                        ChatColor.GRAY + "Completed: " + (System.currentTimeMillis() >= creation + duration ? ChatColor.GREEN + "Yes" : ChatColor.RED + "No")
                });
        return builder.build();
    }
}
