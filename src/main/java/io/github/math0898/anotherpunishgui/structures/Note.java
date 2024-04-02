package io.github.math0898.anotherpunishgui.structures;

import org.bukkit.entity.Player;

/**
 * Notes are issued by staff about particular players.
 *
 * @author Sugaku
 */
public record Note (Player staff, Player target, String data) {
}
