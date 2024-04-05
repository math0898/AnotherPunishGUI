package io.github.math0898.anotherpunishgui.structures;

import java.util.List;

/**
 * Punishments are specific outcomes picked by staff. They are configurable in config.yml.
 *
 * @author Sugaku
 */
public record Punishment (String internalName, String displayName, List<Long> durations) {

}
