package io.github.math0898.anotherpunishgui.structures;

/**
 * Punishments are specific outcomes picked by staff. They are configurable in config.yml.
 *
 * @author Sugaku
 */
public record Punishment (String internalName, String displayName) {
    // Todo: Will probably need a command list, need to also check LiteAntiCheat API to see how they do punishments.
}
