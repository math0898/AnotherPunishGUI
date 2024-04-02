package io.github.math0898.anotherpunishgui.structures;

import org.bukkit.entity.Player;

/**
 * A Report is issued by a player about another player.
 *
 * @author Sugaku
 */
public record Report (Player issuer, Player target, String reason, long creationTime, boolean resolved) {

}
