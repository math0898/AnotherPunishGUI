package io.github.math0898.anotherpunishgui.punisher;

import io.github.math0898.anotherpunishgui.structures.Punishment;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * The Punisher executes on punishments.
 *
 * @author Sugaku
 */
public interface Punisher {

    /**
     * Punishes the given player by the given staff.
     *
     * @param punishment The punishment to enact.
     * @param staff      The staff enacting this punishment.
     * @param player     The player receiving this punishment.
     */
    void punish (Punishment punishment, CommandSender staff, Player player);
}
