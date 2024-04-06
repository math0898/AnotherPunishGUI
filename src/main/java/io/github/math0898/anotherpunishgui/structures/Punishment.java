package io.github.math0898.anotherpunishgui.structures;

import io.github.math0898.anotherpunishgui.database.DatabaseProvider;
import io.github.math0898.utils.items.ItemBuilder;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
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
    }

    /**
     * Gets the duration in either #h or #d format.
     *
     * @param index The index of the duration to get in this format.
     */
    public String displayDuration (int index) {
        return durations.get(index) < 24 ? durations.get(index) + "h" : durations.get(index) / 24 + "d";
    }

    /**
     * Gets the index of the punishment duration that should be used.
     *
     * @param player The player we're considering.
     * @return The index of the punishment duration to use.
     */
    public int getDurationIndex (Player player) {
        List<Log> logs = DatabaseProvider.getInstance().getDatabase().getLogs(player);
        int punishments = 0;
        for (Log l : logs)
            if (l.punishment().equalsIgnoreCase(internalName))
                punishments++;
        if (punishments >= durations.size()) punishments = durations.size() - 1;
        return punishments;
    }

    /**
     * Returns an ItemStack representation of this Punishment using the logs of the given player.
     *
     * @param player The player to check the logs of to highlight punishment duration.
     * @return An ItemStack representation of this punishment based on the given player.
     */
    public ItemStack getItemStack (Player player) {
        ItemBuilder builder = new ItemBuilder(item);
        builder.setDisplayName(ChatColor.translateAlternateColorCodes('&', displayName));
        List<String> opts = new ArrayList<>();
        int punishments = getDurationIndex(player);
        for (int i = 0; i < durations.size(); i++) {
            if (punishments == i)
                opts.add(ChatColor.GRAY + "- " + ChatColor.GREEN + displayDuration(i));
            else opts.add(ChatColor.GRAY + "- " + displayDuration(i));
        }
        builder.setLore(opts.toArray(new String[0]));
        return builder.build();
    }
}
