package io.github.math0898.anotherpunishgui.punisher;

import io.github.math0898.anotherpunishgui.structures.Punishment;
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
        // todo: Implement.
    }
}
