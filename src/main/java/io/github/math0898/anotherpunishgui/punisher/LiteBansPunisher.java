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
 * The LiteBansPunisher is used when LiteBans is found on the server and configured to be used instead of
 * the built-in punisher.
 *
 * @author Sugaku
 */
public class LiteBansPunisher implements Punisher {

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
        if (punishment.type().equalsIgnoreCase("ban")) Bukkit.getServer().dispatchCommand(staff, "litebans:ban " + player.getName() + " -N " + punishment.durations().get(durIndex) + "h You have been banned for " + punishment.displayName() + ". (" + punishment.displayDuration(durIndex) + ")");
        else if (punishment.type().equalsIgnoreCase("mute")) Bukkit.getServer().dispatchCommand(staff, "litebans:mute " + player.getName() + " -N " + punishment.durations().get(durIndex) + "h You have been muted for " + punishment.displayName() + ". (" + punishment.displayDuration(durIndex) + ")");
        Log log = new Log(staff.getName(), player.getUniqueId(), punishment.internalName(), punishment.type(), System.currentTimeMillis(), punishment.durations().get(durIndex) * 60 * 60 * 1000);
        Database database = DatabaseProvider.getInstance().getDatabase();
        database.save(log, DataTypes.LOG);
    }
}
