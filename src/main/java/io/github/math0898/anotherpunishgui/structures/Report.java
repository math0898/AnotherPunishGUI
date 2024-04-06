package io.github.math0898.anotherpunishgui.structures;

import io.github.math0898.utils.items.ItemBuilder;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.inventory.ItemStack;

import java.util.UUID;

/**
 * A Report is issued by a player about another player.
 *
 * @author Sugaku
 */
public record Report (String issuer, UUID target, String reason, long creationTime, boolean resolved) implements YamlSavable {

    /**
     * Creates a new Report based on the given configuration section.
     *
     * @param section The configuration section to make this Report out of.
     */
    public Report (ConfigurationSection section) {
        this(section.getString("issuer"),
                UUID.fromString(section.getString("uuid", "")),
                section.getString("reason"),
                section.getLong("creation"),
                section.getBoolean("resolved"));
    }

    /**
     * Converts this Record into a human-readable string.
     *
     * @return This Record in a string format intended to be read by humans.
     */
    @Override
    public String toString() {
        return "Report: by " + issuer + " -> " + target + ".\n >" + reason + " [" + resolved +"] (" + creationTime + ")";
    }

    /**
     * Saves this object to the given configuration section.
     *
     * @param section The section to save this item at.
     */
    @Override
    public void save (ConfigurationSection section) {
        section.set("issuer", issuer);
        section.set("uuid", target.toString());
        section.set("reason", reason);
        section.set("creation", creationTime);
        section.set("resolved", resolved);
    }

    /**
     * Gets an ItemStack representation of this report.
     *
     * @return An ItemStack representation of this report.
     */
    public ItemStack getItemStack () {
        ItemBuilder builder = new ItemBuilder(Material.PLAYER_HEAD)
                .setOwningPlayer(target)
                .setDisplayName(Bukkit.getOfflinePlayer(target).getName())
                .setLore(new String[] {
                        ChatColor.GRAY + "Reporting player: " + issuer,
                        ChatColor.GRAY + "Reason: " + ChatColor.YELLOW + reason,
                        ChatColor.GRAY + "Resolved: " + (resolved ? ChatColor.GREEN + "Yes" : ChatColor.RED + "No")
                });
        return builder.build();
    }
}
