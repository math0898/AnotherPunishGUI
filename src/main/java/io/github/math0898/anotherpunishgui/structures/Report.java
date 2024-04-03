package io.github.math0898.anotherpunishgui.structures;

import java.util.UUID;

/**
 * A Report is issued by a player about another player.
 *
 * @author Sugaku
 */
public record Report (String issuer, UUID target, String reason, long creationTime, boolean resolved) {

    /**
     * Converts this Record into a human-readable string.
     *
     * @return This Record in a string format intended to be read by humans.
     */
    @Override
    public String toString() {
        return "Report: by " + issuer + " -> " + target + ".\n >" + reason + " [" + resolved +"] (" + creationTime + ")";
    }
}
