package io.github.math0898.anotherpunishgui.punisher;

import io.github.math0898.anotherpunishgui.database.DataTypes;
import io.github.math0898.anotherpunishgui.database.Database;
import io.github.math0898.anotherpunishgui.database.DatabaseProvider;
import io.github.math0898.anotherpunishgui.structures.Log;
import io.github.math0898.anotherpunishgui.structures.Punishment;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * The StandalonePunisher is used when LightAntiCheat is not found, or configured not to be used to issue punishments.
 *
 * @author Sugaku
 */
public class StandalonePunisher implements Punisher {

    /**
     * Punishes the given player by the given staff.
     *
     * @param punishment The punishment to enact.
     * @param staff      The staff enacting this punishment.
     * @param player     The player receiving this punishment.
     */
    @Override
    public void punish (Punishment punishment, CommandSender staff, Player player) {
        int durIndex = punishment.getDurationIndex(player);
        // /ban is a vanilla Minecraft command but does not support durations. In this case we're fishing for another provider such as Essentials without checking.
        if (punishment.type().equalsIgnoreCase("ban")) Bukkit.getServer().dispatchCommand(staff, "ban " + player.getName() + punishment.durations().get(durIndex) + "h You have been banned for " + punishment.displayName() + ". (" + punishment.displayDuration(durIndex) + ")");
        // /mute is not a vanilla Minecraft command. In this case we're fishing for another provider such as Essentials without checking.
        else if (punishment.type().equalsIgnoreCase("mute")) Bukkit.getServer().dispatchCommand(staff, "mute " + player.getName() + punishment.durations().get(durIndex) + "h You have been muted for " + punishment.displayName() + ". (" + punishment.displayDuration(durIndex) + ")");
        // In either of these cases AnotherPunishGUI could enact the punishment fairly easily but this project has already expanded too much.
        Log log = new Log(staff.getName(), player.getUniqueId(), punishment.internalName(), punishment.type(), System.currentTimeMillis(), punishment.durations().get(durIndex) * 60 * 60 * 1000);
        Database database = DatabaseProvider.getInstance().getDatabase();
        database.save(log, DataTypes.LOG);
    }
}
